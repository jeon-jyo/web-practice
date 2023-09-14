package com.javaex.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVo;

public class GuestDao {

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error : 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
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
			System.out.println("error : " + e);
		}
	}
	
	// 리스트
	public List<GuestVo> guestSelect() {

		List<GuestVo> guestList = new ArrayList<GuestVo>();

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "SELECT no ";
			query += "		 ,name ";
			query += "		 ,password ";
			query += "		 ,content ";
			query += "		 ,reg_date ";
			query += "  FROM guest ";
			query += " ORDER BY no ";

			pstmt = conn.prepareStatement(query);
			// 실행
			rs = pstmt.executeQuery();

			// 4. 결과처리
			while(rs.next()) {

				int no = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String content = rs.getString(4);
				Date regDate = rs.getDate(5);

				GuestVo guestVo = new GuestVo(no, name, password, content, regDate);

				guestList.add(guestVo);
			}

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return guestList;
	}
	
	// 등록
	public int guestInsert(GuestVo guestVo) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "INSERT INTO guest "
						 + "VALUES(seq_guest_id.NEXTVAL, ?, ?, ?, sysdate) ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, guestVo.getName());
			pstmt.setString(2, guestVo.getPassword());
			pstmt.setString(3, guestVo.getContent());

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}
	
	// 삭제
	public int guestDelete(int no, String password) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "DELETE FROM guest ";
			query += " WHERE no = ? ";
			query += "   AND password = ? ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setInt(1, no);
			pstmt.setString(2, password);

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}

}
