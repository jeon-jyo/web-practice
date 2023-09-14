package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("user");
		
		// 업무구분용 파라미터 체크
		String action = request.getParameter("action");
		
		switch (action) {
			case "joinForm": {
				
				System.out.println("action : " + action);
				
				// 회원가입폼 - 포워드
				WebUtil.forward(request, response, "WEB-INF/views/user/joinForm.jsp");
			    break;
			}
			case "join": {
				
				System.out.println("action : " + action);
				
				// 파라미터 꺼내고 1개로 묶기
				String id = request.getParameter("id");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String gender = request.getParameter("gender");
				
				UserVo userVo = new UserVo(id, password, name, gender);
				System.out.println(userVo);
				
				// 회원가입
				UserDao userDao = new UserDao();
				int count = userDao.userInsert(userVo);
				if(count == 1) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}
				
				// 가입성공 안내페이지 - 포워드
				WebUtil.forward(request, response, "WEB-INF/views/user/joinOk.jsp");
				break;
			}
			case "loginForm": {
				
				System.out.println("action : " + action);
				
				// 로그인폼 - 포워드
				WebUtil.forward(request, response, "WEB-INF/views/user/loginForm.jsp");
				break;
			}
			case "login": {
				
				System.out.println("action : " + action);
				
				// 파라미터 꺼내고 1개로 묶기
				String id = request.getParameter("id");
				String password = request.getParameter("password");
				
				UserVo userVo = new UserVo();
				userVo.setId(id);
				userVo.setPassword(password);
				System.out.println("userVo : " + userVo);
				
				// 로그인한 사용자가 있는지 확인
				UserDao userDao = new UserDao();
				UserVo authUser = userDao.userLogin(userVo);
				
				if(authUser != null) {
					System.out.println("로그인 성공");
					System.out.println("authUser : " + authUser);
					
					// 로그인 - 세션에 값 넣기
					HttpSession session = request.getSession();
					session.setAttribute("authUser", authUser);
					
					// 메인 - 리다이렉트
					WebUtil.redirect(request, response, "/MySite3/main");

				} else {
					System.out.println("로그인 실패");
					
					// 로그인폼 - 리다이렉트
					WebUtil.redirect(request, response, "/MySite3/user?action=loginForm&result=fail");
				}
				break;
			}
			case "logout": {
				
				System.out.println("action : " + action);

				// 로그아웃 - 세션의 모든 값을 지움
				HttpSession session = request.getSession();
				session.invalidate();
				
				// 메인 - 리다이렉트 - 빈 화면 갔다가 메인 주소치고 메인으로 가는 것
				WebUtil.redirect(request, response, "/MySite3/main");
				break;
			}
			case "modifyForm": {
				
				System.out.println("action : " + action);
				
				UserVo authUser = (UserVo)request.getSession().getAttribute("authUser");
				int no = authUser.getNo();
				System.out.println("수정 번호 : " + no);

				// 유저 정보
				UserDao userDao = new UserDao();
				UserVo userVo = userDao.userSelect(no);
				System.out.println("기존 정보 : " + userVo);
				
				boolean isFemale = false;
				if(userVo.getGender().equals("female")) {
					isFemale = true;
				}
				
				request.setAttribute("userVo", userVo);
				request.setAttribute("isFemale", isFemale);
				
				// 수정폼 - 포워드
				WebUtil.forward(request, response, "WEB-INF/views/user/modifyForm.jsp");
				break;
			}
			case "modify": {
				
				System.out.println("action : " + action);
				
				String no = request.getParameter("no");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String gender = request.getParameter("gender");
				
				int userNo = Integer.parseInt(no);
				UserVo userVo = new UserVo(userNo, password, name, gender);
				System.out.println("변경 정보 : " + userVo);
				
				// 회원 수정
				UserDao userDao = new UserDao();
				int count = userDao.userUpdate(userVo);
				if(count == 1) {
					System.out.println("수정 성공");
					
					// 세션 정보 변경
					UserVo authUser = userDao.userSelect(userNo);
					System.out.println("authUser : " + authUser);
					
					request.getSession().setAttribute("authUser", authUser);
					
				} else {
					System.out.println("수정 실패");
				}
				
				// 메인 - 리다이렉트
				WebUtil.redirect(request, response, "/MySite3/main");
				break;
			}
		}
	   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
