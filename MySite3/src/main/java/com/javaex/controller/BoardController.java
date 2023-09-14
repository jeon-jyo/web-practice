package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("board");

		// 업무구분용 파라미터 체크
		String action = request.getParameter("action");
		
		switch (action) {
			case "list": {
				
				System.out.println("action : " + action);
				
				BoardDao boardDao = new BoardDao();
				List<BoardVo> boardList = boardDao.boardList("");
				
				request.setAttribute("boardList", boardList);
				
				// 게시판 목록 - 포워드
				WebUtil.forward(request, response, "WEB-INF/views/board/list.jsp");
			    break;
			}
			case "search": {
				
				System.out.println("action : " + action);
				
				String keyword = request.getParameter("keyword");
				
				BoardDao boardDao = new BoardDao();
				List<BoardVo> boardList = boardDao.boardList(keyword);
				
				request.setAttribute("boardList", boardList);
				
				// 게시판 목록 - 포워드
				WebUtil.forward(request, response, "WEB-INF/views/board/list.jsp");
			    break;
			}
			case "writeForm": {
				
				System.out.println("action : " + action);
				
				// 게시판 작성 - 포워드
				WebUtil.forward(request, response, "WEB-INF/views/board/writeForm.jsp");
			    break;
			}
			case "write": {
				
				System.out.println("action : " + action);
				
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				BoardVo boardVo = new BoardVo();
				boardVo.setTitle(title);
				boardVo.setContent(content);
				
				UserVo authUser = (UserVo)request.getSession().getAttribute("authUser");
				int userNo = authUser.getNo();
				
				BoardDao boardDao = new BoardDao();
				int count = boardDao.boardInsert(boardVo, userNo);
				if(count == 1) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}
				
				// 게시판 목록 - 리다이렉트
				WebUtil.redirect(request, response, "board?action=list");
			    break;
			}
			case "detail": {
				
				System.out.println("action : " + action);
				
				int boardNo = Integer.parseInt(request.getParameter("no"));
				
				BoardDao boardDao = new BoardDao();
				int count = boardDao.boardHitCount(boardNo);
				if(count == 1) {
					System.out.println("조회수 업데이트");
				}
				BoardVo boardVo = boardDao.boardDetail(boardNo);
				
				request.setAttribute("boardVo", boardVo);
				
				// 게시판 상세 - 포워드
				WebUtil.forward(request, response, "WEB-INF/views/board/read.jsp");
			    break;
			}
			case "modifyForm": {
				
				System.out.println("action : " + action);
				
				int boardNo = Integer.parseInt(request.getParameter("no"));
				
				BoardDao boardDao = new BoardDao();
				BoardVo boardVo = boardDao.boardDetail(boardNo);
				
				request.setAttribute("boardVo", boardVo);
				
				// 게시판 수정폼 - 포워드
				WebUtil.forward(request, response, "WEB-INF/views/board/modifyForm.jsp");
				break;
			}
			case "modify": {
				
				System.out.println("action : " + action);
				
				int boardNo = Integer.parseInt(request.getParameter("no"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				BoardVo boardVo = new BoardVo();
				boardVo.setNo(boardNo);
				boardVo.setTitle(title);
				boardVo.setContent(content);
				
				BoardDao boardDao = new BoardDao();
				int count = boardDao.boardUpdate(boardVo);
				if(count == 1) {
					System.out.println("수정 성공");
					
				} else {
					System.out.println("수정 실패");
				}
				
				// 게시판 상세 - 리다이렉트
				WebUtil.redirect(request, response, "board?action=detail&no="+boardNo);
			    break;
			}
			case "delete": {
				
				System.out.println("action : " + action);
				
				int boardNo = Integer.parseInt(request.getParameter("no"));
				
				BoardDao boardDao = new BoardDao();
				int count = boardDao.boardDelete(boardNo);
				if(count == 1) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}
				
				// 게시판 목록 - 리다이렉트
				WebUtil.redirect(request, response, "board?action=list");
			    break;
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
