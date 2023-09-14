package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

public class BoardDao {

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

	// 게시판 목록
	public List<BoardVo> boardList(String keyword) {
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "SELECT b.no ";
			query += "		 ,b.title ";
			query += "		 ,b.content ";
			query += "		 ,b.hit ";
			query += "		 ,TO_CHAR(b.reg_date, 'YY-MM-DD HH24:MI') regDate ";
			query += "		 ,u.no ";
			query += "		 ,u.name ";
			query += "  FROM board b, users u ";
			query += " WHERE b.user_no = u.no ";
			
			if(!keyword.equals("")) {	// keyword가 ""가 아니면 ==> keyword가 있으면 검색
				query += "   AND b.title LIKE ? ";
				query += " ORDER BY b.no DESC";
				
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, '%' + keyword + '%');
				
			} else {
				query += " ORDER BY b.no DESC";
				
				pstmt = conn.prepareStatement(query);
			}
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 4. 결과처리
			while(rs.next()) {

				int no = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				int userNo = rs.getInt(6);
				String userName = rs.getString(7);
				
				UserVo userVo = new UserVo();
				userVo.setNo(userNo);
				userVo.setName(userName);
				
				BoardVo boardVo = new BoardVo(no, title, content, hit, regDate, userVo);
				
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return boardList;
	}
	
	// 게시판 작성
	public int boardInsert(BoardVo boardVo, int userNo) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "INSERT INTO board "
						 + "VALUES(seq_board_no.NEXTVAL, ?, ?, 0, sysdate, ?) ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, userNo);

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}
	
	// 게시판 상세보기
	public BoardVo boardDetail(int no) {
		
		BoardVo boardVo = null;
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "SELECT b.no ";
			query += "		 ,b.title ";
			query += "		 ,b.content ";
			query += "		 ,b.hit ";
			query += "		 ,TO_CHAR(b.reg_date, 'YY-MM-DD HH24:MI') regDate ";
			query += "		 ,u.no ";
			query += "		 ,u.name ";
			query += "  FROM board b, users u ";
			query += " WHERE b.user_no = u.no ";
			query += "   AND b.no = ? ";
			query += " ORDER BY b.no DESC";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setInt(1, no);
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 4. 결과처리
			rs.next();
			String title = rs.getString(2);
			String content = rs.getString(3);
			int hit = rs.getInt(4);
			String regDate = rs.getString(5);
			int userNo = rs.getInt(6);
			String userName = rs.getString(7);
			
			UserVo userVo = new UserVo();
			userVo.setNo(userNo);
			userVo.setName(userName);
			
			boardVo = new BoardVo(no, title, content, hit, regDate, userVo);
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return boardVo;
	}
	
	// 게시판 조회수
	public int boardHitCount(int no) {
		
		int count = -1;
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "UPDATE board ";
			query += "   SET hit = (SELECT hit ";
			query += "       		  FROM board ";
			query += "   	 		 WHERE no = ?)+1 ";
			query += " WHERE no = ? ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			
			// 실행
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}
	
	// 게시판 수정
	public int boardUpdate(BoardVo boardVo) {
		
		int count = -1;
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "UPDATE board ";
			query += "   SET title = ? ";
			query += "       ,content = ? ";
			query += " WHERE no = ? ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getNo());
			
			// 실행
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}
	
	// 게시판 삭제
	public int boardDelete(int no) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "DELETE FROM board ";
			query += " WHERE no = ? ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setInt(1, no);

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}
	
}
