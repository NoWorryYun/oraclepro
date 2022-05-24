package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	// 필드
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 주소
	private String id = "phonedb";
	private String pw = "phonedb";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private ResultSet rs = null;
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
			if (rs != null) {
				rs.close();
			}
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

	// insert
	public int personInsert(PersonVo personVo) {
		int count = -1;

		getConnection();

		try {

			String query = "";
			query += " insert into person";
			query += " values(seq_person_id.nextval, ?, ?, ?)";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 등록되었습니다.]");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;

	}

	// delete
	public int personDelete(int personId) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " delete from person";
			query += " where person_id = ?";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);
			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 삭제되었습니다.]");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	// Update
	public int personUpdate(PersonVo personVo) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " update person";
			query += " set name = ?,";
			query += "     hp = ?";
			query += "     company = ?";
			query += " where person_id = ?";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 수정되었습니다.]");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	// Select
	public List<PersonVo> personSelect() {
		// 리스트
		List<PersonVo> personList = new ArrayList<PersonVo>();

		getConnection();

		try {
			// SQL 문 준비
			String query = "";
			query += " select  person_id,";
			query += "         name,";
			query += "         hp,";
			query += "         company";
			query += " from person";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행***
			// ResultSet 가져오기
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 리스트로 만들기

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, name, hp, company);

				personList.add(personVo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return personList;
	}

	// Search
	public List<PersonVo> personSearch(String words) {
		// 리스트
		List<PersonVo> personList = new ArrayList<PersonVo>();

		getConnection();

		try {
			// SQL 문 준비
			String query = "";
			query += " select  person_id,";
			query += "         name,";
			query += "         hp,";
			query += "         company";
			query += " from person";
			query += " where name like '%"+words+"%'";
			query += " or hp like '%"+words+"%'";
			query += " or company like '%"+words+"%'";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			// 실행***
			// ResultSet 가져오기
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 리스트로 만들기

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo pVo = new PersonVo(personId, name, hp, company);
				
				personList.add(pVo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return personList;
	}
}
