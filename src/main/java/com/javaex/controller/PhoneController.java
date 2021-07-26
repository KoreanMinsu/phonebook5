package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {
	//field
	@Autowired
	private PhoneDao phoneDao;
	
	//constructor
	//method-g/s
	//method-generic
	
	//LIST
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[PhonebookController.list]");
		
		//Dao사용 -> autowired를 통해 repository 이용
		//PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getList();
		
		//model담기-dispatcherServlet-request.setAttribute
		model.addAttribute("pList", personList);
		
		return "/list";
		//직접포워드가 아닌 DispatcherServlet 에게 return값 포워딩 요청
	}
	
	
	//WRITEFORM
	@RequestMapping(value="/writeform", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("[PhoneController.writeForm]");
		
		return "/writeForm";
	}
	
	
	//INSERT
			

		@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
		public String insert(@ModelAttribute PersonVo personVo) {
				
				//@ModelAttribute -> new PersonVo()
				//				  -> 기본생성자 + setter
			System.out.println("[PhoneController.insert]");
			
			//PhoneDao phoneDao = new PhoneDao();
			phoneDao.insert(personVo);
	
			return "redirect:/list";
		}
	
	//INSERT 2
		
		@RequestMapping(value="/write2", method= {RequestMethod.GET, RequestMethod.POST})
		public String insert2(@RequestParam("name") String name
						,      @RequestParam("hp") String hp
						,	  @RequestParam("company") String company){
			
			System.out.println("[PhoneController.insert2]");

			phoneDao.insert2(name, hp, company);
			
			return "redirect:/list";
		}
		
		
	//DELETE
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("id") int personId) {
		
		//PhoneDao phoneDao = new PhoneDao();
		phoneDao.delete(personId);
		
		return "redirect:/list";
	}
			
			
	//UPDATEFORM
	@RequestMapping(value="/updateForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(Model model, @RequestParam("person_id") int person_id) {
		
		//DB서 정보 가져오기-> autowired 사용
		//PhoneDao phoneDao = new PhoneDao();
		phoneDao.getPerson(person_id);
		
		//model담기-dispatcherServlet-request.setAttribute
		model.addAttribute("personInfo", phoneDao);
		
		return "/updateFrom";
	}
	
	//UPDATEFORM 2 (map 사용)
		@RequestMapping(value="/updateForm", method= {RequestMethod.GET, RequestMethod.POST})
		public String updateForm2(Model model, @RequestParam("person_id") int person_id) {
			
			//DB서 정보 가져오기-> autowired 사용
			Map<String, Object> personMap = phoneDao.getPerson2(person_id);
					
			//model담기-dispatcherServlet-request.setAttribute
			model.addAttribute("pMap", personMap);
			
			return "/updateFrom2";
		}
		
	
	//UPDATE
	@RequestMapping(value ="/update", method= {RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personVo) {
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.update(personVo);
		
		return "redirect:/list";
	}
	
	
	/* PathVariable 예제
		@RequestMapping(value="/board/read/{no}", method= {RequestMethod.GET, RequestMethod.POST})	
			public String read(@PathVariable("no") int boardNo) {
			System.out.println("[Pathvariable.no]");
			
			return "";
		}
	*/
	
}
