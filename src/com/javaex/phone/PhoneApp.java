package com.javaex.phone;

import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PhoneDao phoneDao = new PhoneDao();
		
		System.out.println("**************************************");
		System.out.println("********  전화번호 관리 프로그램  *********");
		System.out.println("**************************************");
		System.out.println("");
		System.out.println("");
		
		while(true) {
			
			System.out.println("1. 리스트 2. 등록 3. 수정 4. 삭제 5. 검색 6. 종료");
			System.out.println("-------------------------------------------");
			System.out.print(">메뉴번호: ");
			int number = sc.nextInt();
			sc.nextLine();
			
			if(number == 6) {
				break;
			} else if(number == 1) {
				System.out.println("<1.리스트>");
				System.out.println();
			} else if(number == 2) {
				System.out.println("<2.등록>");
				System.out.print("이름 > ");
				String name = sc.nextLine();
				System.out.print("휴대전화 > ");
				String hp = sc.nextLine();
				System.out.print("회사번호 > ");
				String company = sc.nextLine();
				
				PersonVo personVo = new PersonVo(name, hp, company);
				phoneDao.personInsert(personVo);
				
			} else if(number == 3) {
				
			} else if(number == 4) {
				
			} else if(number == 5) {
				
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
				
		
		
		
		
		
		
		
		
		}
		
		sc.close();
		
	}

}
