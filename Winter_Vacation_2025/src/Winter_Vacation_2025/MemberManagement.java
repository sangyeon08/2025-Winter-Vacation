package Winter_Vacation_2025;

import java.util.ArrayList;
import java.util.Scanner;

class Member {
	String name;
	String gender;
	int age;
	String address;

	public Member(String name, String gender, int age, String address) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
	}
}

public class MemberManagement {
	ArrayList<Member> members = new ArrayList<>(); //멤버 객체 몽땅 넣기
	Scanner sc = new Scanner(System.in);

	public void addMember() {
		System.out.print("이름: ");
		String name = sc.nextLine();
		System.out.print("성별: ");
		String gender = sc.nextLine();
		System.out.print("나이: ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("주소: ");
		String address = sc.nextLine();

		Member member = new Member(name, gender, age, address);
		members.add(member);
		System.out.println(name + "님이 추가되었습니다.");
	}

	public void searchMember(String name) {
		for (Member member : members) {
			if (member.name.equals(name)) {
				System.out.println("이름: " + member.name);
				System.out.println("성별: " + member.gender);
				System.out.println("나이: " + member.age);
				System.out.println("주소: " + member.address);
				return;
			}
		}
		System.out.println("해당 회원을 찾을 수 없습니다.");
	}

	//수정할 때
	public void updateMember(String name, String key, String value) {
		for (Member member : members) {
			if (member.name.equals(name)) {
				switch (key) {
				case "성별":
					member.gender = value;
					break;
				case "나이":
					member.age = Integer.parseInt(value);
					break;
				case "주소":
					member.address = value;
					break;
				default:
					System.out.println("잘못된 선택입니다.");
				}
				System.out.println(name + "님의 정보가 수정되었습니다.");
				return;
			}
		}
		System.out.println("해당 회원을 찾을 수 없습니다.");
	}

	//삭제할 때 모든 정보 돌아가며 지우기
	public void deleteMember(String name) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).name.equals(name)) {
				members.remove(i);
				System.out.println(name + "님이 삭제되었습니다.");
				return;
			}
		}
		System.out.println("해당 회원을 찾을 수 없습니다.");
	}

	//회원 정보 다 출력
	public void printAllMembers() {
		System.out.println("------------------------");
		for (Member member : members) {
			System.out.println("이름: " + member.name);
			System.out.println("성별: " + member.gender);
			System.out.println("나이: " + member.age);
			System.out.println("주소: " + member.address);
			System.out.println("------------------------");
		}
	}

	public static void main(String[] args) {
		MemberManagement manager = new MemberManagement();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("1.  회원 가입 ");
			System.out.print("2.  회원 검색 ");
			System.out.print("3.  회원 정보 수정 ");
			System.out.print("4.  회원 삭제 ");
			System.out.print("5.  회원 목록 ");
			System.out.println("0.  프로그램 종료 ");
			System.out.print("메뉴 선택: ");
			int choice = sc.nextInt();
			sc.nextLine();

			
			//수정할 때 선택사항 갈무리
			switch (choice) {
			case 1:
				manager.addMember();
				break;
			case 2:
				System.out.print("검색할 이름: ");
				String searchName = sc.nextLine();
				manager.searchMember(searchName);
				break;
			case 3:
				System.out.print("수정할 회원 이름: ");
				String updateName = sc.nextLine();
				System.out.print("수정할 항목 (성별, 나이, 주소): ");
				String updateKey = sc.nextLine();
				System.out.print("새로운 값: ");
				String updateValue = sc.nextLine();
				manager.updateMember(updateName, updateKey, updateValue);
				break;
			case 4:
				System.out.print("삭제할 회원 이름: ");
				String deleteName = sc.nextLine();
				manager.deleteMember(deleteName);
				break;
			case 5:
				manager.printAllMembers();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 메뉴입니다.");
			}
		}
	}
}