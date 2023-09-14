package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Controller
public class GuestController {

	// 게시판 목록
	@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestController.addList()");
		
		GuestDao guestDao = new GuestDao();
		List<GuestVo> guestList = guestDao.guestSelect();
		
		model.addAttribute("guestList", guestList);
		
		/*
		 * viewResolver
		 * 추가 전 -> /WEB-INF/addList.jsp
		 * 추가 후 -> addList
		 */
		return "addList";
	}
	
	// 게시판 등록
	@RequestMapping(value="/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestController.insert()");
		
		GuestDao guestDao = new GuestDao();
		int count = guestDao.guestInsert(guestVo);
		if(count == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
		
		return "redirect:/addList";
	}
	
	// 게시판 삭제폼
	@RequestMapping(value="/deleteForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable(value="no") int no, Model model) {
		System.out.println("GuestController.deleteForm()");
		
		model.addAttribute("no", no);
		
		return "deleteForm";
	}
	
	// 게시판 삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestController.delete()");
		System.out.println(guestVo);
		
		GuestDao guestDao = new GuestDao();
		int count = guestDao.guestDelete(guestVo.getNo(), guestVo.getPassword());
		if(count == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		
		return "redirect:/addList";
	}
	
}
