package bitedu.bipa.tiles.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.tiles.vo.MemberVO;

@Repository("memberDao")
public class MemberDao implements IMemberDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<MemberVO> selectAllMember(){
		ArrayList<MemberVO> list = null;
		
		list = (ArrayList)sqlSession.selectList("mapper.book.selectAllMember");
		
		return list;
	}
	
	@Override
	public MemberVO selectDetailMember(int memberSeq){
		MemberVO list = null;
		ArrayList<MemberVO> temp = (ArrayList)sqlSession.selectList("mapper.book.selectDetailMember",memberSeq);
		
		list = temp.get(0);
		
		return list;
	}
	@Override
	public int loginCheck(MemberVO memberVo) {
		int flag = 0;
		ArrayList<MemberVO> list = null;
		
		list = (ArrayList)sqlSession.selectList("mapper.book.CheckLogin",memberVo);
		
		System.out.println(list);
		
		if(list.isEmpty()) {
			System.out.println("no list");
		}else {
			if(list.get(0).getMemberId().equals("admin") && list.get(0).getMemberPw().equals("1234")) {
				flag =1;
			}	
		}	
		return flag;
	}
	
	@Override
	public int checkId(String memberId) {
		int flag = 0;
		
		MemberVO memberVo = null;
		
		memberVo = (MemberVO)sqlSession.selectOne("mapper.book.checkId", memberId);
		
		if(memberVo!=null) {
			System.out.println("(dao)check Id : " + memberVo.getMemberId());
			System.out.println("아이디가 중복됩니다!");
			flag = 1;
		}else {
			System.out.println("아이디를 사용할수 있습니다");
		}
		
		return flag;
	}
	
	@Override
	public int createMember(MemberVO memberVo) {
		int flag = 0;
		
		flag = sqlSession.insert("mapper.text.createMember", memberVo);
		
		return flag;
	}
	
	@Override
	public String selectMemberGrade(String id) {
		String grade;
		ArrayList<MemberVO> memGrade = new ArrayList<MemberVO>();
		
		memGrade = (ArrayList)sqlSession.selectList("mapper.book.selectMemberGrade", id);
		grade = memGrade.get(0).getMemberGrade();
		
		System.out.println("(dao)member grade : " + grade);
		
		return grade;
	}
	
	@Override
	public int removeMember(int memberId) {
		int flag = 0;
		
		flag = sqlSession.delete("mapper.book.removeMember", memberId);
		
		return flag;
	}
	
	@Override
	public ArrayList<MemberVO> selectMemberById(String id) {
		 ArrayList<MemberVO> member = null;
		
		member = (ArrayList)sqlSession.selectList("mapper.book.selectMemberMyId", id);
		
		return member;
	}

}
