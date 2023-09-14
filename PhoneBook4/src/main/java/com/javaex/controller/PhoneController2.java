package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PersonDao2;
import com.javaex.vo.PersonVo;

/*
 * * 무조건 get 방식
 * http://localhost:8000/PhoneBook4/list
 * http://localhost:8000/PhoneBook4/writeForm
 * 
 * /list -> PhoneController.list()
 * /writeForm -> PhoneController.writeForm()
 */

// 핸들러 매핑이 되는 대상 - @Controller
//@Controller
// 이 Controller의 공통 Path 설정 : @RequestMapping(value="/user")
public class PhoneController2 {
	
	/*
	 * * Model And View 개념
	 * Model은 데이터개념 - model.addAttribute() 사용
	 * return은 뷰개념 - return "/WEB-INF/list.jsp"	/ return "redirect:/list"
	 */
	
	// 필드
	// 메모리를 자동으로 올려주고 안쓰면 삭제해줌 - @Autowired
	@Autowired
	private PersonDao2 personDao;
	
	// 메서드 - 일반
	// 주소록 목록
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController.list()");
		
		// personDao.personSelect()
		// PersonDao personDao = new PersonDao();
		List<PersonVo> personList = personDao.personSelect();
		System.out.println(personList);
		
		// model 주소를 받고 addAttribute() 메소드를 이용해서 담는다
		// DS에게 request의 attribute에 담기 위해 model로 전달
		model.addAttribute("personList", personList);
		
		// DS에게 포워딩을 위한 response jsp 전달
		/*
		 * viewResolver
		 * 추가 전 -> /WEB-INF/views/list.jsp
		 * 추가 후 -> list
		 */
		return "list";
	}
	
	// 주소록 등록폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(Model model) {
		System.out.println("PhoneController.writeForm()");
		
		return "writeForm";
	}
	
	// 주소록 등록 - 원리
	@RequestMapping(value="/writePrinciple", method= {RequestMethod.GET, RequestMethod.POST})
	public String writePrinciple(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {	// 파라미터 하나씩 받기
		System.out.println("PhoneController.writePrinciple()");
		
		// vo로 묶고 setter로 넣는다
		PersonVo personVo = new PersonVo();
		personVo.setName(name);
		personVo.setHp(hp);
		personVo.setCompany(company);
		System.out.println(personVo);
		
		// dao를 통해 저장한다
		int count = personDao.personInsert(personVo);
		if(count == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
		
		// DS에게 리다이렉트를 위한 response url 전달
		return "redirect:/list";
	}
	
	// 주소록 등록
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo) {	// 파라미터 한 번에 받기
						// DS가 write()에서 PersonVo personVo 필요한 것을 확인 후 만들어서 보내줌
		System.out.println("PhoneController.write()");
		
		// vo로 묶고 setter로 넣는다 -> DS가 해준다 -> 파라미터로 받는다
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
	
	// 주소록 삭제 - @RequestParam : 파라미터가 반드시 있어야 한다
	// deleteParam?id=10
	@RequestMapping(value="/deleteParam", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteParam(@RequestParam(value="id") int personId) {
		System.out.println("PhoneController.deleteParam()");
		
		// dao를 통해 삭제한다
		int count = personDao.personDelete(personId);
		if(count == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		
		return "redirect:/list";
	}
	
	// 주소록 삭제 - @PathVariable
	// delete/10
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
	
	// 주소록 수정폼
	@RequestMapping(value="/updateForm/{id}", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(@PathVariable(value="id") int personId, Model model) {
		System.out.println("PhoneController.updateForm()");
		
		// personDao.personSelectOne()
		PersonVo personVo = personDao.personSelectOne(personId);
		
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
