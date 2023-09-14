package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.PersonDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	@Autowired
	private PersonDao personDao;
	
	// 주소록 목록
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController.list()");
		
		// DB에서 리스트를 가져온다
		List<PersonVo> personList = personDao.personSelect();
		System.out.println(personList);
		
		// DS에게 request의 attribute에 담기 위해 model로 전달
		model.addAttribute("personList", personList);
		
		// DS에게 포워딩을 위한 response jsp 전달
		return "list";
	}
	
	// 삭제
	@RequestMapping(value="/delete/{id}", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable(value="id") int personId) {
		System.out.println("PhoneController.delete()");

		// dao를 통해 삭제한다
		int count = personDao.personDelete(personId);
		if(count == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		
		return "redirect:/list";
	}
	
	// 주소록 등록폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(Model model) {
		System.out.println("PhoneController.writeForm()");
		
		return "writeForm";
	}
	
	// 주소록 등록
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo) {
						// DS가 write()에서 PersonVo personVo 필요한 것을 확인 후 만들어서 보내줌
		System.out.println("PhoneController.write()");
		System.out.println(personVo);
		
		// dao를 통해 저장한다
		int count = personDao.personInsert(personVo);
		if(count == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
		
		return "redirect:/list";
	}
	
	// 주소록 수정폼
	@RequestMapping(value="/updateForm/{id}", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(@PathVariable(value="id") int personId, Model model) {
		System.out.println("PhoneController.updateForm()");
		
		// DB에서 한 명 정보를 가져온다
		PersonVo personVo = personDao.personSelectOne(personId);
		
		// DS에게 request의 attribute에 담기 위해 model로 전달
		model.addAttribute("personVo", personVo);
		
		return "updateForm";
	}
	
	// 주소록 수정
	@RequestMapping(value="/update", method= {RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController.update()");
		
		// dao를 통해 수정한다
		int count = personDao.personUpdate(personVo);
		if(count == 1) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
		
		return "redirect:/list";
	}
	
}
