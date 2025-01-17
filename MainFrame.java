package Calendar2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainFrame extends JFrame {
    private DatabaseManager dbManager;
    private JLabel headerLabel;
    private JPanel calendarPanel;
    private int currentMonth = 1;
    private int currentYear = 2025;
    private Map<String, String> schedules; // Map<String, String>으로 선언하고

    public MainFrame() {
        dbManager = new DatabaseManager();
        schedules = new HashMap<>();  // schedules 초기화

        setTitle("캘린더");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 헤더 부분
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        // 이전 달 버튼
        JButton prevMonthButton = new JButton("<");
        prevMonthButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentMonth == 1) {
                    currentMonth = 12;
                    currentYear--;
                } else {
                    currentMonth--;
                }
                updateCalendar();
            }
        });

        // 다음 달 버튼
        JButton nextMonthButton = new JButton(">");
        nextMonthButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentMonth == 12) {
                    currentMonth = 1;
                    currentYear++;
                } else {
                    currentMonth++;
                }
                updateCalendar();
            }
        });

        headerLabel = new JLabel(getMonthYearLabel());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(prevMonthButton);
        headerPanel.add(headerLabel);
        headerPanel.add(nextMonthButton);
        add(headerPanel, BorderLayout.NORTH);

        // 캘린더 패널
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(7, 7));  // 7x7 배열
        add(calendarPanel, BorderLayout.CENTER);

        updateCalendar();  // 캘린더 갱신
    }

    // =======<<캘린더 구조 업데이트>>========
    private void updateCalendar() {
        calendarPanel.removeAll();

        calendarPanel.setLayout(new GridLayout(0, 7)); // 행은 맞춰서 추가, 열은 7로 고정

        // 요일 표시
        String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 16));
            dayLabel.setOpaque(true);
            dayLabel.setForeground(Color.DARK_GRAY); // 글자 색상만 좀 보이게
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            calendarPanel.add(dayLabel);
        }

        // 해당 월의 첫 날이 무슨 요일인지 계산하기
        int startDayOfWeek = getStartDayOfWeek(currentYear, currentMonth);
        int totalDaysInMonth = getTotalDaysInMonth(currentYear, currentMonth);

        // 빈 칸 추가하기
        for (int i = 0; i < startDayOfWeek; i++) {
            calendarPanel.add(new JLabel()); // 빈 칸 추가
        }

        // 날짜를 버튼으로 해서 이벤트 추가
        for (int i = 1; i <= totalDaysInMonth; i++) {
            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BorderLayout());
            dayPanel.setBackground(Color.white);
            dayPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

            // 일정 표시용 JTextArea 일정 있으면 보이게
            String dateKey = String.format("%04d-%02d-%02d", currentYear, currentMonth, i);
            JTextArea scheduleTextArea = new JTextArea();
            scheduleTextArea.setEditable(false);
            scheduleTextArea.setFont(new Font("Arial", Font.PLAIN, 10));
            scheduleTextArea.setLineWrap(true);
            scheduleTextArea.setWrapStyleWord(true);
            scheduleTextArea.setBackground(Color.white);

            if (schedules.containsKey(dateKey)) {
                scheduleTextArea.setText(schedules.get(dateKey)); // 기존 일정 표시
            } else {
                scheduleTextArea.setText(""); // 일정이 없으면 빈 텍스트
            }

            dayPanel.add(scheduleTextArea, BorderLayout.CENTER); // JTextArea를 중앙에 배치

            // 날짜 버튼
            JButton dateButton = new JButton();
            dateButton.setText(String.valueOf(i)); // 버튼에 날짜 텍스트 표시
            dateButton.setBackground(Color.WHITE);
            dateButton.setFocusPainted(false); // 버튼 클릭 시 테두리 없애기
            dateButton.setFont(new Font("Arial", Font.BOLD, 12)); // 버튼 폰트 설정
            dateButton.setPreferredSize(new Dimension(50, 15)); // 세로 길이를 짧게 설정 (너비는 유지)

            // 일정이 있으면 버튼 색상 변경
            if (schedules.containsKey(dateKey)) {
                dateButton.setBackground(new Color(200, 230, 255)); // 연한 파란색
            }

            dayPanel.add(dateButton, BorderLayout.SOUTH); // 버튼을 하단에 배치

            // 날짜 클릭할 때 일정 추가/수정/삭제 이벤트 처리
            final String date = String.format("%04d-%02d-%02d", currentYear, currentMonth, i);
            dateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String existingSchedule = schedules.get(date);
                    if (existingSchedule != null) {
                        // 일정 확인 및 수정/삭제
                        JPanel panel = new JPanel(new GridLayout(0, 1));
                        JLabel label = new JLabel("일정: " + existingSchedule);
                        panel.add(label);

                        // 버튼 추가
                        Object[] options = {"수정", "삭제", "닫기"};
                        int choice = JOptionPane.showOptionDialog(
                            MainFrame.this,
                            panel,
                            "일정 관리",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            options[0]
                        );

                        // 사용자 선택에 따른 처리
                        if (choice == JOptionPane.YES_OPTION) { // 수정
                            String newSchedule = JOptionPane.showInputDialog(MainFrame.this, "새로운 일정을 입력하세요:", existingSchedule);
                            if (newSchedule != null && !newSchedule.isEmpty()) {
                                dbManager.updateSchedule(date, newSchedule);
                                schedules.put(date, newSchedule); //디비랑 메모리 둘 다 업데이트
                                updateCalendar(); // 캘린더 갱신
                                JOptionPane.showMessageDialog(MainFrame.this, "일정이 수정되었습니다!");
                            }
                        } else if (choice == JOptionPane.NO_OPTION) { // 삭제
                            int confirm = JOptionPane.showConfirmDialog(MainFrame.this, "정말 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                dbManager.deleteSchedule(date);
                                schedules.remove(date);//디비, 메모리에서 다 삭제
                                updateCalendar(); // 캘린더 갱신
                                JOptionPane.showMessageDialog(MainFrame.this, "일정이 삭제되었습니다!");
                            }
                        }
                    } else {
                        // 일정 추가
                        String schedule = JOptionPane.showInputDialog("일정을 입력해주세요!");
                        if (schedule != null && !schedule.isEmpty()) {
                            dbManager.addSchedule(date, schedule); // 일정 저장
                            schedules.put(date, schedule); // 일정을 메모리에도 저장
                            updateCalendar(); // 캘린더 갱신
                            JOptionPane.showMessageDialog(MainFrame.this, "일정이 추가되었습니다!");
                        }
                    }
                }
            });

            calendarPanel.add(dayPanel);
        }

        // 레이아웃 새로 고침
        calendarPanel.revalidate();
        calendarPanel.repaint();

        // 월/년 레이블 업데이트
        headerLabel.setText(getMonthYearLabel());
    }

//========<<날짜 계산>>========
    private String getMonthYearLabel() {
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        return months[currentMonth - 1] + " " + currentYear;
    }

    private int getStartDayOfWeek(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        return (startDay - 1) % 7;
    }

    private int getTotalDaysInMonth(int year, int month) { //이걸 직접 구하려고 했다니
        if (month == 2) {
            return (year % 4 == 0) ? 29 : 28;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return 31;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
