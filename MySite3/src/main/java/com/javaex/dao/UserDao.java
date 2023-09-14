package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {
	
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
	
	// 회원가입
	public int userInsert(UserVo userVo) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "INSERT INTO users "
						 + "VALUES(seq_users_id.NEXTVAL, ?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getGender());

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}
	
	// 로그인
	public UserVo userLogin(UserVo userVo) {
		
		/*
		 * rs.next() 가 있으면 new UserVo() 해야함
		 * 객체가 만들어지면 이미 null이 아니기 때문
		 */
		UserVo authUser = null;
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "SELECT no ";
			query += "		 ,id ";
			query += "		 ,password ";
			query += "		 ,name ";
			query += "		 ,gender ";
			query += "  FROM users ";
			query += " WHERE id = ? ";
			query += "   AND password = ? ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPassword());

			// 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(4);
				
				// 세션에 가급적 정보를 조금 넣음
				authUser = new UserVo();
				authUser.setNo(no);
				authUser.setName(name);
			}

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return authUser;
	}
	
	// 유저 정보 조회
	public UserVo userSelect(int no) {
		
		UserVo authUser = null;
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "SELECT no ";
			query += "		 ,id ";
			query += "		 ,password ";
			query += "		 ,name ";
			query += "		 ,gender ";
			query += "  FROM users ";
			query += " WHERE no = ? ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setInt(1, no);

			// 실행
			rs = pstmt.executeQuery();
			
			rs.next();
			String id = rs.getString(2);
			String password = rs.getString(3);
			String name = rs.getString(4);
			String gender = rs.getString(5);
			
			authUser = new UserVo(no, id, password, name, gender);

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return authUser;
	}
	
	// 정보 수정
	public int userUpdate(UserVo userVo) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "UPDATE users ";
			query += "   SET password = ? ";
			query += "       ,name = ? ";
			query += "   	 ,gender = ? ";
			query += " WHERE no = ? ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, userVo.getPassword());
			pstmt.setString(2, userVo.getName());
			pstmt.setString(3, userVo.getGender());
			pstmt.setInt(4, userVo.getNo());

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}
	
}
