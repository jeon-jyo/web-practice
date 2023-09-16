package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PersonDao;
import com.javaex.vo.PersonVo;
import com.javaex.vo.PersonVo2;

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
	
	/******************************/
	/* Map 사용 예제 */
	
	// 등록
	@RequestMapping(value="/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@RequestParam(value="name") String name) {
		System.out.println("PhoneController.insert()");
		/*
		 * name 파라미터 + hp, company 는 insert 내에서 계산된 값
		 */
		String hp = "010-0000-0000";
		String company = "02-000-0000";
		
		// ex1) vo로 묶는다
//		PersonVo personVo = new PersonVo();	// PersonVo가 없으면 만든다
//		personVo.setName(name);
//		personVo.setHp(hp);
//		personVo.setCompany(company);
		
//		int count = personDao.insert(personVo);
		
		/*
		 * Map은 순서가 없음
		 * Value에 int, String 등 섞여있으면 Object
		 */
		// ex2) vo를 안 만든다 -> 이번 한 번만 사용 => Map 사용
		Map<String, String> personMap = new HashMap<String, String>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		int count = personDao.insert(personMap);
		if(count == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
		
		return "redirect:/list";
	}
	
	// 수정폼
	@RequestMapping(value="/updateFormGo", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateFormGo(@RequestParam(value="id") int personId, Model model) {
		System.out.println("PhoneController.updateFormGo()");
		
		Map<String,Object> personMap = personDao.selectOne(personId);
		/*
		 * 컬럼명을 key 값으로 하는데 대문자임
		 */
		System.out.println("name : " + personMap.get("NAME"));
		
		model.addAttribute("personMap", personMap);
		
		return "updateFormGo";
	}
	
	/******************************/
	/* resultType 사용 예제 */
	
	// 주소록 목록
	@RequestMapping(value="/listGo", method= {RequestMethod.GET, RequestMethod.POST})
	public String listGo(Model model) {
		System.out.println("PhoneController.listGo()");
		
		List<PersonVo2> personList = personDao.select();
		System.out.println(personList);
		
		model.addAttribute("personList", personList);
		
		return "listGo";
	}
	
}
