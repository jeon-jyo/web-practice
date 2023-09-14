package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PersonDao;
import com.javaex.vo.PersonVo;

@WebServlet("/PBC")
public class PhoneBookController extends HttpServlet {
	
	// 필드
	private static final long serialVersionUID = 1L;
    
	// 생성자 - 기본 생성자
    
    // 메소드 - getter/setter
    
    // 메소드 - 일반
    // get 방식으로 요청이 들어왔을 때 실행되는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("PhoneBookController");
		
		// 파라미터 action의 값을 꺼내온다	업무구분을
		String action = request.getParameter("action");
		
		/*
		 * ?.equals() : 이 인스턴스와 지정한 다른 String 객체의 값이 같은지를 확인
		 * 
		 * "list" 하면 객체가 생김
		 * 
		 * action.equals("list");
		 * action 객체의 값과 "list"가 담긴 객체의 "list" 를 비교
		 * -> action 값이 만약 없으면 NullPointerException 발생
		 * 
		 * "list".equals(action);
		 * -> "list"의 객체가 이미 있으니 action=null 과 일치하지 않는다고 함
		 */
		if("list".equals(action)) {
			
			System.out.println("action : " + action);
			
			// 1. dao를 통해서 전체 리스트 데이터 가져오기
			PersonDao personDao = new PersonDao();
			List<PersonVo> personList = personDao.personSelect();
			
			System.out.println(personList);
			
			/* 리스트 화면(html) + data 응답을 해야된다 */
			// 2. request attribute에 data를 넣어둔다
			request.setAttribute("personList", personList);
			
			// 3. list.jsp 에게 시킨다 (포워드)
			// RequestDispatcher 객체가 만들어짐 (heap메모리에 올라감)
			// forward 하면 최종적으로 list.jsp 로 이관되고 list.jsp 실행
			// response로 list.jsp 의 html코드만 받아오고 화면에 그려줌
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/list.jsp");
		    rd.forward(request, response);
		    
		    /*
		     * WEB-INF 안에 넣으면 list.jsp 주소로 직접 쳐도 안들어간다
		     * -> 모델1 은 컨트롤러 자체가 없음, 모델2에서만 WEB-INF 안에 써야 함
		     * -> /list.jsp 의 루트/ 는 WebContent 부터이기 때문에
		     *    WEB-INF/list.jps 해야 함
		     *    
		     * 모델 2 : 
		     * jsp 는 자바코드로 뭔가를 안하려고 쓰는 거
		     * -> 컨트롤러로 자바코드하고 request 어트리뷰트에 담아서 jsp에 보내주면됨
		     * -> jsp 에서 어트리뷰트 담은걸 쓰는거임, Dao로 자바코드 쓰는 일 하면 안됨
		     */
		} else if("writeForm".equals(action)) {
			
			System.out.println("action : " + action);
			
			// writeForm.jsp 에게 시킨다 (포워드)
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/writeForm.jsp");
		    rd.forward(request, response);
		    
		} else if("insert".equals(action)) {
			
			System.out.println("action : " + action);
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
		    
			PersonVo personVo = new PersonVo(name, hp, company);
			
			System.out.println("입력 값 : " + personVo);

			PersonDao personDao = new PersonDao();
			int count = personDao.personInsert(personVo);
			
			if(count == 1) {
				System.out.println("등록 성공");
			} else {
				System.out.println("등록 실패");
			}

			response.sendRedirect("/PhoneBook3/PBC?action=list");

		} else if("delete".equals(action)) {
			
			String id = request.getParameter("id");
			int personId = Integer.parseInt(id);
			
			System.out.println("삭제 번호 : " + personId);
			
			PersonDao personDao = new PersonDao();
			int count = personDao.personDelete(personId);
			
			if(count == 1) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
			response.sendRedirect("/PhoneBook3/PBC?action=list");
			
		} else if("updateForm".equals(action)) {
			
			System.out.println("action : " + action);
			
			String id = request.getParameter("id");
			int personId = Integer.parseInt(id);
			
			System.out.println("수정 번호 : " + personId);
			
			PersonDao personDao = new PersonDao();
			PersonVo personVo = personDao.personSelectOne(personId);
			
			request.setAttribute("personVo", personVo);
			
			// updateForm.jsp 에게 시킨다 (포워드)
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/updateForm.jsp");
		    rd.forward(request, response);
		    
		} else if("update".equals(action)) {
			
			System.out.println("action : " + action);
			
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			int personId = Integer.parseInt(id);
			PersonVo personVo = new PersonVo(personId, name, hp, company);
			
			System.out.println("수정 값 : " + personVo);
			
			PersonDao personDao = new PersonDao();
			int count = personDao.personUpdate(personVo);
			
			if(count == 1) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
			
			response.sendRedirect("/PhoneBook3/PBC?action=list");
			
		} else {
			
			System.out.println("주소를 잘못 입력하셨습니다.");
			System.out.println("action : " + action);
			response.sendRedirect("/PhoneBook3/PBC?action=list");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
