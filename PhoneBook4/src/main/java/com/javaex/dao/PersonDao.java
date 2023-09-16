package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;
import com.javaex.vo.PersonVo2;

@Repository
public class PersonDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 목록
	public List<PersonVo> personSelect() {
		System.out.println("PersonDao.personSelect()");
		
		// DB에서 리스트를 가져온다 - select문 id
		List<PersonVo> personList = sqlSession.selectList("phonebook.personSelect");
		
		return personList;
	}
	
	// 삭제
	public int personDelete(int personId) {
		System.out.println("PersonDao.personDelete()");
		
		int count = sqlSession.delete("phonebook.personDelete", personId);
		
		return count;
	}
	
	// 등록
	public int personInsert(PersonVo personVo) {
		System.out.println("PersonDao.personInsert()");
		
		int count = sqlSession.insert("phonebook.personInsert", personVo);
		
		return count;
	}
	
	// 한 명 조회
	public PersonVo personSelectOne(int personId) {
		System.out.println("PersonDao.personSelectOne()");
		
		PersonVo personVo = sqlSession.selectOne("phonebook.personSelectOne", personId);
		
		return personVo;
	}
	
	// 수정
	public int personUpdate(PersonVo personVo) {
		System.out.println("PersonDao.personUpdate()");
		
		int count = sqlSession.update("phonebook.personUpdate", personVo);
		
		return count;
	}
	
	/******************************/
	/* Map 사용 예제 */
	
	// 등록
	public int insert(Map<String, String> personMap) {
		System.out.println("PersonDao.insert()");
		
		int count = sqlSession.insert("phonebook.insert", personMap);
		
		return count;
	}
	
	// 수정폼
	public Map<String,Object> selectOne(int personId) {
		System.out.println("PersonDao.selectOne()");
		
		Map<String,Object> personMap = sqlSession.selectOne("phonebook.selectOne", personId);

		return personMap;
	}
	
	/******************************/
	/* resultType 사용 예제 */
	
	// 목록
	public List<PersonVo2> select() {
		System.out.println("PersonDao.select()");
		
		List<PersonVo2> personList = sqlSession.selectList("phonebook.select");
		
		return personList;
	}
	
}
