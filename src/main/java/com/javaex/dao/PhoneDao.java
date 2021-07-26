package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;
	
	//List
	public List<PersonVo> getList() {
		
		//DB요청
		//List호출
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		return personList;
	}
	
	//Insert
	public void insert(PersonVo personVo) {
		sqlSession.insert("phonebook.personinsert", personVo);
	}
	
	//Insert2
	public void insert2(String name, String hp, String company) {
		
		Map<String, Object> personMap = new HashMap<String, Object>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);

		sqlSession.insert("phonebook.insert2", personMap);
	}
	
	//Delete
	public void delete(int psersonId) {
		
		sqlSession.selectOne("phonebook.delete", psersonId);
	}

	// 1명 List  
		public PersonVo getPerson(int person_id) {

			// Vo호출 
			PersonVo personVo = sqlSession.selectOne("phonebook.personInfo", person_id);
			return personVo;
		}

	// 1명 List- 2
		public Map<String, Object> getPerson2(int person_id) {

			// Map호출
			Map<String, Object> personMap = sqlSession.selectOne("phonebook.personInfo2", person_id);


			return personMap;
		}
	//Update
	public void update(PersonVo personVo) {

		sqlSession.update("phonebook.update", personVo);
	}
}
