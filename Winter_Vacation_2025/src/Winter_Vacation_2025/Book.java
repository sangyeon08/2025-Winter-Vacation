package Winter_Vacation_2025;

import java.util.*;

class BookInfo {
    String name;
    String author;
    String genre;
    boolean isBorrowed;

    public BookInfo(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.isBorrowed = false; // 대출 안한 초기세팅
    }
}

class BookManagement {
    ArrayList<BookInfo> Info = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    //=========도서 등록=========
    public void addInfo() {
        System.out.print("책 제목: ");
        String name = sc.nextLine();
        System.out.print("지은이: ");
        String author = sc.nextLine();
        System.out.print("장르: ");
        String genre = sc.nextLine();

        BookInfo info = new BookInfo(name, author, genre);
        Info.add(info);
        System.out.println(name + "이(가) 추가되었습니다.");
    }

    // ==========도서 검색==========
    public void searchBook(String name) {
        for (BookInfo info : Info) {
            if (info.name.equals(name)) {
                System.out.println("책 제목: " + info.name);
                System.out.println("지은이: " + info.author);
                System.out.println("장르: " + info.genre);
                System.out.println("대출 상태: " + (info.isBorrowed ? "대출 중" : "대출 가능"));
                return;
            }
        }
        System.out.println("해당 도서를 찾을 수 없습니다.");
    }

    // =============도서 정보 수정================
    public void updateMember(String name, String key, String value) {
        for (BookInfo info : Info) {
            if (info.name.equals(name)) {
                switch (key) {
                    case "지은이":
                        info.author = value;
                        break;
                    case "장르":
                        info.genre = value;
                        break;
                    default:
                        System.out.println("잘못된 선택입니다.");
                        return;
                }
                System.out.println(name + "의 정보가 수정되었습니다.");
                return;
            }
        }
        System.out.println("해당 도서를 찾을 수 없습니다. 수정이 취소되었습니다.");
    }


    // ===============도서 삭제=================
    public void deleteInfo(String name) {
        for (int i = 0; i < Info.size(); i++) {
            if (Info.get(i).name.equals(name)) {
                Info.remove(i);
                System.out.println(name + "이(가) 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 도서를 찾을 수 없습니다.");
    }

    // ================전체 도서 목록 출력=============
    public void printallInfo() {
        System.out.println("------------------------");
        for (BookInfo info : Info) {
            System.out.println("책 제목: " + info.name);
            System.out.println("지은이: " + info.author);
            System.out.println("장르: " + info.genre);
            System.out.println("대출 상태: " + (info.isBorrowed ? "대출 중" : "대출 가능")); // ? 이게 더 효율적이라더라...
            System.out.println("------------------------");
        }
    }

    // =============도서 대출==============
    public void bookBorrow(String name) {
        for (BookInfo info : Info) {
            if (info.name.equals(name)) {
                if (info.isBorrowed) {
                    System.out.println(name + "은(는) 이미 대출 중입니다.");
                } else {
                    info.isBorrowed = true;
                    System.out.println(name + "이(가) 대출되었습니다.");
                }
                return;
            }
        }
        System.out.println("해당 도서를 찾을 수 없습니다.");
    }

    // =============도서 반납=============
    public void bookReturn(String name) {
        for (BookInfo info : Info) {
            if (info.name.equals(name)) {
                if (!info.isBorrowed) {
                    System.out.println(name + "은(는) 대출되지 않았습니다.");
                } else {
                    info.isBorrowed = false;
                    System.out.println(name + "이(가) 반납되었습니다.");
                }
                return;
            }
        }
        System.out.println("해당 도서를 찾을 수 없습니다.");
    }
}

public class Book {
    public static void main(String[] args) {
        BookManagement manager = new BookManagement();
        Scanner sc = new Scanner(System.in);
        System.out.println("======<로그인>======");
        System.out.print("성함을 입력하세요 : ");
        String Id = sc.next();
        System.out.print("비밀번호를 입력하세요 : ");
        String Pw = sc.next();
        
        while (true) {
            System.out.println("\n<메뉴>");
            System.out.println("1. 도서 등록");
            System.out.println("2. 도서 검색");
            System.out.println("3. 도서 정보 수정");
            System.out.println("4. 도서 삭제");
            System.out.println("5. 도서 목록");
            System.out.println("6. 도서 대출");
            System.out.println("7. 도서 반납");
            System.out.println("0. 프로그램 종료");
            System.out.print("메뉴 선택: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("------------------");
                    System.out.println("도서 등록을 시작합니다.");
                    manager.addInfo();
                    System.out.println("도서 등록이 완료되었습니다.");
                    System.out.println("------------------");
                    break;
                case 2:
                    System.out.println("------------------");
                    System.out.println("도서 검색을 시작합니다.");
                    System.out.print("검색할 이름: ");
                    String searchName = sc.nextLine();
                    manager.searchBook(searchName);
                    System.out.println("도서 검색이 완료되었습니다.");
                    System.out.println("------------------");
                    break;
                case 3:
                    System.out.println("------------------");
                    System.out.println("도서 정보 수정을 시작합니다.");
                    System.out.print("수정할 도서 이름: ");
                    String updateName = sc.nextLine();
                    System.out.print("수정할 항목 (지은이, 장르): ");
                    String updateKey = sc.nextLine();
                    System.out.print("새로운 값: ");
                    String updateValue = sc.nextLine();
                    manager.updateMember(updateName, updateKey, updateValue);
                    System.out.println("도서 정보 수정이 완료되었습니다.");
                    System.out.println("------------------");
                    break;
                case 4:
                    System.out.println("------------------");
                    System.out.println("도서 삭제를 시작합니다.");
                    System.out.print("삭제할 도서 이름: ");
                    String deleteName = sc.nextLine();
                    manager.deleteInfo(deleteName);
                    System.out.println("도서 삭제가 완료되었습니다.");
                    System.out.println("------------------");
                    break;
                case 5:
                    System.out.println("------------------");
                    System.out.println("도서 목록을 출력합니다.");
                    manager.printallInfo();
                    System.out.println("도서 목록 출력이 완료되었습니다.");
                    System.out.println("------------------");
                    break;
                case 6:
                    System.out.println("------------------");
                    System.out.println("도서 대출을 시작합니다.");
                    System.out.print("대출할 도서 이름 : ");
                    String borrowname = sc.nextLine();
                    manager.bookBorrow(borrowname);
                    System.out.println("도서 대출을 종료합니다..");
                    System.out.println("------------------");
                    break;
                case 7:
                    System.out.println("------------------");
                    System.out.println("도서 반납을 시작합니다.");
                    System.out.print("반납할 도서 이름 : ");
                    String returnname = sc.nextLine();
                    manager.bookReturn(returnname);
                    System.out.println("도서 반납을 종료합니다.");
                    System.out.println("------------------");
                    break;
                case 0:
                    System.out.println("------------------");
                    System.out.println("프로그램을 종료합니다.");
                    System.out.println("------------------");
                    return;
                default:
                    System.out.println("------------------");
                    System.out.println("잘못된 메뉴입니다.");
                    System.out.println("------------------");
            }
        }
    }
}

