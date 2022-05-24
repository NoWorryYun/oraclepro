package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhoneDao {

	// 필드
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 주소
	private String id = "phonedb";
	private String pw = "phonedb";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	// 생성자

	// GS

	// 일반
	private void getConnection() {
	
	try {
		// 1. JDBC 드라이버(Oracle) 로딩
		Class.forName(driver); // 오라클 접속
		// 2. Connection 얻어오기
		conn = DriverManager.getConnection(url, id, pw);
	} catch (SQLException e) {
		System.out.println("error:" + e);
	} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버로딩실패-" + e);
	}	
		
	}
	
	private void close() {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
	}
	//insert
	public int personInsert(PersonVo personVo) {
		int count = -1;
		
		getConnection();
		
		try {
			
			// 3. SQL문준비/ 바인딩/ 실행

			// SQL 문 준비
			String query = "";
			query += " insert into person";
			query += " values(seq_person_id.nextval, ?, ?, ?)";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 등록 되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		return count;

	}

}
