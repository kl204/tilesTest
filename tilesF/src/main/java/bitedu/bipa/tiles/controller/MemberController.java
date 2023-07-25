	package bitedu.bipa.tiles.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.tiles.service.MemberService;
import bitedu.bipa.tiles.vo.BoardVO;
import bitedu.bipa.tiles.vo.BookCopy;
import bitedu.bipa.tiles.vo.MemberVO;
import bitedu.bipa.tiles.vo.User;

@Controller("memberController")
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping("/list.do")
	public ModelAndView list(HttpSession session) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		ArrayList<MemberVO> memberVo = memberService.selectAllMember();
		
		MemberVO member = (MemberVO)session.getAttribute("login");

		String grade = member.getMemberId();	
		
		if(grade!=null) {
			if(grade.equals("admin")) {
								
				mav.setViewName("/admin/list");
			}else {
				mav.setViewName("/member/list");
			}	
		}else {
			mav.setViewName("/member/loginForm");
		}	
		
		mav.setViewName("/admin/list");
		
		mav.addObject("data",memberVo);
		
		return mav;
	}
	
	@RequestMapping(value="/viewDetail.do", method=RequestMethod.GET)
	public ModelAndView viewDetail(@RequestParam int memberSeq ,HttpSession session) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		MemberVO data = new MemberVO(); 
		
		data = memberService.selectDetailMember(memberSeq);
		
		MemberVO member = (MemberVO)session.getAttribute("login");

		String grade = member.getMemberId();		
		
		if(grade.equals("admin")) {
			mav.setViewName("/admin/detail");
		}else {
			mav.setViewName("/member/detail");
		}
				
		mav.addObject("data",data);
		return mav;
	}
	
	@RequestMapping(value="/viewRegist.do", method=RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		
		mav.addObject("data","MemberList");
		mav.setViewName("/member/regist");
		return mav;
	}
	
	@RequestMapping(value="/viewLogin.do", method=RequestMethod.GET)
	public ModelAndView viewLogin() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.addObject("data","LoginForm");
		mav.setViewName("/member/loginForm");
		return mav;
	}
	
	@RequestMapping(value="/viewUpdate.do", method=RequestMethod.GET)
	public ModelAndView viewUpdate(@RequestParam String memberSeq) {
		ModelAndView mav = null;
		mav = new ModelAndView();		
		MemberVO data = new MemberVO(); 
		
		int memberseq = Integer.parseInt(memberSeq);
		
		data = memberService.selectDetailMember(memberseq);
		
		mav.addObject("data",data);
		mav.setViewName("/member/update");
		return mav;
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(@RequestParam String flag) {		
		ModelAndView model = new ModelAndView();
		
		model.addObject("flag",flag);
		model.setViewName("main");
		
		return model;
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("id") String id, 
			@RequestParam("pass") String pass, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberVO memberVo = new MemberVO();
		
		 ArrayList<MemberVO> memberList = new  ArrayList<MemberVO>();
			
		 memberList = memberService.selectMemberById(id);
		String grade ="";
		if(memberVo!=null) {
			grade = memberList.get(0).getMemberGrade();
		}		
		
		memberVo.setMemberId(id);
		memberVo.setMemberPw(pass);
		memberVo.setMemberGrade(grade);
		
		System.out.println("(controller)id, pw, flags : " + id + " , " + pass);

		mav.addObject("flag","true");
		mav.addObject("id",id);
		mav.addObject("pw",pass);
		mav.addObject("memberVo",memberVo);
		
		String memberGrade = memberService.selectMemberGrade(id);
		
		if(memberGrade.equals("admin")) {
			mav.setViewName("/admin/main");			
		}else {
			mav.setViewName("/member/main");
		}		
		
		return mav;

	}
	
	//아이디 중복 검사
	@RequestMapping(value = "/id_validation", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> id_validation(@RequestParam String memberId) {
	    Map<String, Object> response = new HashMap<String, Object>();
	    
	    System.out.println("(controller)memberId : " + memberId);
	    
	    int flag = memberService.checkId(memberId);
	    
	    System.out.println("(controller)checkId : " + flag);
	    
	    if (flag == 1) {
	        response.put("result", true);
	    } else {
	        response.put("result", false);
	    }
	    
	    return response;
	}
	
	//회원가입
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ModelAndView regist(@ModelAttribute MemberVO memberVo) {
		ModelAndView model = new ModelAndView();
		
		System.out.println("regist in");
		
		memberService.createMember(memberVo);

		model.setViewName("/member/list");
		return model;
	}	
	
	@RequestMapping(value="/remove.do", method=RequestMethod.GET)
	public ModelAndView remove(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String memberSeq = request.getParameter("memberSeq");
		int flag = memberService.removeMember(Integer.parseInt(memberSeq));
		
		mav.setViewName("redirect:list.do");
		return mav;
	}
	
//	@RequestMapping(value="/update.do", method=RequestMethod.POST)
//	public ModelAndView update(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		System.out.println("update"); 
//		String path = "d:\\dev\\upload_files";
//
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		factory.setRepository(new File(path));
//		factory.setSizeThreshold(1024*1024*10);
//		ServletFileUpload update = new ServletFileUpload(factory);
//		List<FileItem> items = null;
//		try {
//			items = update.parseRequest(request);
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BookCopy copy = null;
//		try {
//			copy = memberService.upload(items);
//			System.out.println("(controller)change data : "+copy);
//			memberService.modifyBook(copy);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mav.setViewName("redirect:list.do");
//		
//		return mav;
//	}
	

	
}
