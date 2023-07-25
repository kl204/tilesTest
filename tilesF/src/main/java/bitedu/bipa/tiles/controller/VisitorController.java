package bitedu.bipa.tiles.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import bitedu.bipa.tiles.vo.BoardVO;
import bitedu.bipa.tiles.vo.BookCopy;
import bitedu.bipa.tiles.vo.MemberVO;
import bitedu.bipa.tiles.service.VisitorService;

@Controller("visitorController")
@RequestMapping("/visitor")
public class VisitorController {
	
	@Autowired
	private VisitorService visitorService;

	@RequestMapping("/list.do")
	public ModelAndView list(HttpSession session) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String flag = "false";
		
		ArrayList<BoardVO> data = visitorService.selectAllText();
		
		MemberVO member = (MemberVO)session.getAttribute("login");

		if(member!=null) {
			String grade = member.getMemberId();
			
			if(grade.equals("admin")) {
				mav.setViewName("/adminVisitor/list");		
				}else {
					mav.setViewName("/memberVisitor/list");
			
			}
		}else {
			mav.setViewName("/memberVisitor/list");
		}
		
		System.out.println("(controller)image name : " + data.get(0).getDataImage());
		
		mav.addObject("flag",flag);
		mav.addObject("data",data);

		return mav;
	}
	
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public ModelAndView contentView(@RequestParam int textNum ,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		BoardVO list = visitorService.selectOneText(textNum);
		visitorService.updateView(textNum);
		
		System.out.println("(Controller)controrller selectOne : " + list.getContent());
		
		MemberVO member = (MemberVO)session.getAttribute("login");

		if(member!=null) {
			String grade = member.getMemberId();
			
			if(grade.equals("admin")) {
				mav.setViewName("/adminVisitor/content");		
				}else {
					mav.setViewName("/content");
			
			}
		}else {
			mav.setViewName("/content");
		}
		
		mav.addObject("list", list );
		
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int textNum) {
		ModelAndView model = new ModelAndView();
		int success = 0;
		boolean flag = false;
		
		success = visitorService.removeText(textNum);	
		
		System.out.println("succecss : " + success);
		
		if(success!=0) {
			flag = true;
		}
		
		System.out.println("flag : " + flag);
	    model.setViewName("redirect:list.do");
		
		return model;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam String text, String type) {
		ModelAndView model = new ModelAndView();

		System.out.println("(Contorller type : )" + type);
		
		
		ArrayList<BoardVO> data = visitorService.searchText(text);
		
		for(BoardVO bv :data) {
			System.out.println(bv.getTitle());
			System.out.println(bv.getContent());
		}
		
		
		model.addObject("data", data );
		model.setViewName("/adminVisitor/list");
		
		return model;
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String path = "d:\\dev\\upload_files";

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024*1024*10);
		ServletFileUpload update = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = update.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BoardVO copy = null;
		try {
			copy = visitorService.upload(items);
			
			System.out.println("(controller)image name : " + copy.getDataImage());
			
			visitorService.createText(copy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("redirect:list.do");
		
		return mav;
	}
	
	@RequestMapping(value = "/regist_view", method = RequestMethod.GET)
	public ModelAndView regist_view(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO member = (MemberVO)session.getAttribute("login");

		String grade = member.getMemberId();		
		
		if(grade.equals("admin")) {
			mav.setViewName("/adminVisitor/regist");
		}else {
			mav.setViewName("/memberVisitor/regist");
		}


		return mav;
	}
	
	@RequestMapping(value="/download.do",method = RequestMethod.GET)
	public void download(@RequestParam("fileName") String fileName,HttpServletResponse resp) {
		
		File downloadFile = new File("d:\\dev\\upload_files\\images\\"+fileName);
		
		try {
			fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		resp.setContentType("text/html; charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.addHeader("Content-Disposition", "attatchment;filename="+fileName);
		
		try {
			FileInputStream fis = new FileInputStream(downloadFile);
			OutputStream os = resp.getOutputStream();
			byte[] buffer = new byte[256];
			int length = 0;

			while((length=fis.read(buffer))!=-1) {
				os.write(buffer, 0, length);
			}
			os.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
