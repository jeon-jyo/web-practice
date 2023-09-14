package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;

@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("main");
		
		// 내부디렉토리
		// 메인 - 포워드
		/*
		 * 1.
		 * RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/main/index.jsp");
		 * rd.forward(request, response);
		 * 
		 * 2.
		 * WebUtil webUtil = new WebUtil();
		 * webUtil.forward(request, response, "WEB-INF/views/main/index.jsp");
		 * 
		 * 3. WebUtil 을 static에 올린다
		 */
		WebUtil.forward(request, response, "WEB-INF/views/main/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
