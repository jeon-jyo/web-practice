package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@WebServlet("/GBC")
public class GuestBookController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("GuestBookController");
		
		String action = request.getParameter("action");
		
		if("addList".equals(action)) {
			
			System.out.println("action : " + action);
			
			GuestDao guestDao = new GuestDao();
			List<GuestVo> guestList = guestDao.guestSelect();
			
			System.out.println("guestList : " + guestList);

			request.setAttribute("guestList", guestList);
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/addList.jsp");
		    rd.forward(request, response);
		    
		} else if("insert".equals(action)) {
			
			System.out.println("action : " + action);
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestVo guestVo = new GuestVo();
			guestVo.setName(name);
			guestVo.setPassword(password);
			guestVo.setContent(content);
			
			System.out.println("입력 값 : " + guestVo);

			GuestDao guestDao = new GuestDao();
			int count = guestDao.guestInsert(guestVo);
			
			if(count == 1) {
				System.out.println("등록 성공");
			} else {
				System.out.println("등록 실패");
			}
			
			response.sendRedirect("/GuestBook3/GBC?action=addList");
			
		} else if("deleteForm".equals(action)) {
			
			System.out.println("action : " + action);
			
			String id = request.getParameter("id");

			System.out.println("삭제 번호 : " + id);
			
			/*
			 * request.setAttribute("personId", personId);
			 * 
			 * 이미 request Parameter에 id가 있기 때문에 할 필요 x
			 * -> 받은 Parameter 외에 새롭게 가공한 data가 있을 땐 Attribute에 넣음
			 */
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/deleteForm.jsp");
		    rd.forward(request, response);
		    
		} else if("delete".equals(action)) {
			
			System.out.println("action : " + action);
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			int personId = Integer.parseInt(id);
			GuestDao guestDao = new GuestDao();
			GuestVo guestVo = guestDao.guestSelectOne(personId);
			
			System.out.println("기존 정보 : " + guestVo);
			
			if(guestVo.getPassword().equals(password)) {
				
				System.out.println("비밀번호 일치 : " + password);
				
				int count = guestDao.guestDelete(personId);
				
				if(count == 1) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}
			} else {
				System.out.println("비밀번호 불일치 : " + password);
			}
			
			response.sendRedirect("/GuestBook3/GBC?action=addList");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
