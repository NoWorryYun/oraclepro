package com.javaex.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PhoneDao phoneDao = new PhoneDao();
		
		System.out.println("**************************************");
		System.out.println("**        전화번호 관리 프로그램          **");
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
				System.out.println("**************************************");
				System.out.println("**             감사합니다              **");
				System.out.println("**************************************");
				break;
			} else if(number == 1) {
				System.out.println("<1.리스트>");
				List<PersonVo> personList = phoneDao.personSelect();
				for(int i = 0 ; i < personList.size() ; i++) {
					int pId = personList.get(i).getPersonId();
					String pName = personList.get(i).getName();
					String pHp= personList.get(i).getHp();
					String pCom = personList.get(i).getCompany();
				
					System.out.println(pId+".  " + pName + "  " + pHp + "  " + pCom);
				}
				System.out.println("");
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
				System.out.println("");
			} else if(number == 3) {
				System.out.println("<3.수정>");
				System.out.print("번호 > ");
				int personId = sc.nextInt();
				sc.nextLine();
				System.out.print("이름 > ");
				String name = sc.nextLine();
				System.out.print("휴대전화 > ");
				String hp = sc.nextLine();
				System.out.print("회사번호 > ");
				String company = sc.nextLine();
				
				PersonVo personVo = new PersonVo(name, hp, company, personId);
				
				phoneDao.personUpdate(personVo);
				System.out.println("");
			} else if(number == 4) {
				System.out.println("<4.삭제>");
				System.out.print(">번호 : ");
				int authorId = sc.nextInt();
				phoneDao.personDelete(authorId);
				
			} else if(number == 5) {
				System.out.println("<5.검색>");
				System.out.print("검색어 > ");
				String search = sc.nextLine();
				List<PersonVo> personList = phoneDao.personSearch(search);
				for(int i = 0 ; i < personList.size() ; i++) {
					int pId = personList.get(i).getPersonId();
					String pName = personList.get(i).getName();
					String pHp= personList.get(i).getHp();
					String pCom = personList.get(i).getCompany();
				
					System.out.println(pId+".  " + pName + "  " + pHp + "  " + pCom);
				}
			} else {
				System.out.println("[다시 입력해주세요.]");
				System.out.println("");
			}
				
		
		
		
		
		
		
		
		
		}
		
		sc.close();
		
	}

}
