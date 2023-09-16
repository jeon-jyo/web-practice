package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserDao userDao;

	// 로그인폼
	@RequestMapping(value="/loginForm", method= { RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		
		return "user/loginForm";
	}
	
	// 로그인
	@RequestMapping(value="/login", method= { RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session, Model model) {
		System.out.println("UserController.login()");
		
		UserVo authUser = userDao.userLogin(userVo);
		
		if(authUser != null) {
			System.out.println("로그인 성공");
			System.out.println("authUser : " + authUser);
			
			// 로그인 - 세션에 값 넣기
			session.setAttribute("authUser", authUser);
			
			// 메인 - 리다이렉트
			return "redirect:/";

		} else {
			System.out.println("로그인 실패");
			
			model.addAttribute("result", "fail");
			
			// 로그인폼 - 리다이렉트
			return "redirect:/user/loginForm";
		}
		
	}
	
}
