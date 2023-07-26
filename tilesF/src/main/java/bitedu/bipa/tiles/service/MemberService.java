package bitedu.bipa.tiles.service;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.tiles.dao.MemberDao;
import bitedu.bipa.tiles.vo.BoardVO;
import bitedu.bipa.tiles.vo.MemberVO;

@Service("memberService")
public class MemberService implements IMemeberService{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public ArrayList<MemberVO> selectAllMember(){
		ArrayList<MemberVO> list = null;
		
		list = memberDao.selectAllMember();
		
		return list;
	}
	
	@Override
	public  ArrayList<MemberVO> selectMemberById(String id) {
		 ArrayList<MemberVO> member = null;
		
		member = memberDao.selectMemberById(id);
		
		return member;
	}
	
	@Override
	public MemberVO selectDetailMember(int memberSeq){
		MemberVO member = null;
		
		member = memberDao.selectDetailMember(memberSeq);
		
		return member;
	}
	
	@Override
	public int loginCheck(MemberVO memberVo) {
		int flag = 0;
		
		flag = memberDao.loginCheck(memberVo);
		
		return flag;
	}
	
	@Override
	public int checkId(String memberId) {
		int flag = 0;
		
		flag = memberDao.checkId(memberId);
		
		return flag;
	}
	
	@Override
	public int createMember(MemberVO memberVo) {
		int flag = 0;
		
		flag = memberDao.createMember(memberVo);
		
		return flag;
	}
	
	@Override
	public String selectMemberGrade(String id) {
		String grade;
		
		grade = memberDao.selectMemberGrade(id);
		
		return grade;
	}
	
	@Override
	public int removeMember(int memberId) {
		int flag = 0;
		
		flag = memberDao.removeMember(memberId);
		
		return flag;
	}
	//----------------------------------------------------------------> 회원 수정 여기부터
//	@Override
//	public MemberVO upload(List<FileItem> items) throws Exception {
//		// TODO Auto-generated method stub
//		MemberVO copy = new MemberVO();
//		
//		for(FileItem item : items) {
//			if(item!=null&item.isFormField()) {
//				//일반적인 Form값 추출
//				String fieldName = item.getFieldName();
//				if(fieldName.equals("textNum")){
//					copy.setTextNum(Integer.parseInt(item.getString("UTF-8")));
//					
//				} else if(fieldName.equals("title")) {
//					copy.setTitle(item.getString("UTF-8"));
//					
//					
//				} else if(fieldName.equals("content")) {
//					copy.setContent(item.getString("UTF-8"));
//					
//					
//				} else if(fieldName.equals("writer")) {
//					copy.setWriter(item.getString("UTF-8"));
//					
//					
//				} else if(fieldName.equals("createdAt")) {
//					String date = item.getString("UTF-8");
//					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//					try {
//						Date now = df.parse(date);
//						copy.setCreatedAt(new Timestamp(now.getTime()));
//						
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}				}
//
//				
//			} else {
//				// uplode할 데이터에 대한 정보 추출
//				String fieldName = item.getFieldName();
//				if(fieldName.equals("dataImage")) {
//					String temp = item.getName();
//					System.out.println("dataImage "+temp);
//					int index = temp.lastIndexOf("\\");
//					String fileName = temp.substring(index+1);
//					copy.setDataImage(fileName);
//					
//					File uploadFile = new File("d:\\dev\\upload_files\\images\\"+fileName);
//					item.write(uploadFile);
//				}
//
//			}
//		}
//		return copy;
//	}
	
	
}
