package bitedu.bipa.tiles.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.tiles.vo.BoardVO;
import bitedu.bipa.tiles.vo.BookCopy;
import bitedu.bipa.tiles.vo.MemberVO;

@Repository("visitorDao")
public class VisitorDao implements IVisitorDao{
	
	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public ArrayList<BoardVO> selectAllText(){
		ArrayList<BoardVO> list = null;
		
		list = (ArrayList)sqlSession.selectList("mapper.book.selectAllText");
		
		System.out.println("(dao)selectAllText content : " + list);
		System.out.println("(dao)image name : " + list.get(0).getDataImage());
		
		return list;
	}
	
	@Override
	public BoardVO selectOneText(int textNum){
		BoardVO boardVo = null;
		
		boardVo = (BoardVO)sqlSession.selectOne("mapper.book.selectOneText", textNum);
		
		if(boardVo!=null) {
			System.out.println("(dao)selectOnebook content : " + boardVo.getContent());
		}
		
		
		return boardVo;
	}
	
	@Override
	public int registText(BoardVO boardVo) {
		int flag = 0;
		
		flag = sqlSession.insert("mapper.book.registText", boardVo);
		
		System.out.println("(dao)registText : " + flag);
		
		
		return flag;
	}
	
	
	@Override
	public int updateView(int text) {
		int flag = 0;
		
		flag = sqlSession.update("mapper.book.updateView", text);
		
		System.out.println("(dao)updateView : " + flag);
		
		return flag;
	}
	
	@Override
	public int deleteText(int textNum) {
		int flag = 0;
		
		flag = sqlSession.delete("mapper.book.deleteText", textNum);
		
		System.out.println("(dao)deleteText : " + flag);
		
		
		return flag;
	}
	
	@Override
	public ArrayList<BoardVO> searchText(String text) {
		ArrayList<BoardVO> list = null;
		
		list = (ArrayList)sqlSession.selectList("mapper.book.searchText", text);
		
		if(list.isEmpty()) {
			System.out.println("no list!");
		}else {
			System.out.println("(dao)searchText content : " + list.get(0).getContent());
		}
		
		return list;
	}

	@Override
	public int loginCheck(MemberVO memberVo) {
		int flag = 0;
		ArrayList<MemberVO> mem = new ArrayList<MemberVO>();
		 
		mem = (ArrayList)sqlSession.selectList("mapper.book.CheckLogin", memberVo);
		
		if(mem.isEmpty()) {
			return flag;
		}else {
			if(mem.get(0).getMemberId().equals(memberVo.getMemberId()) && mem.get(0).getMemberPw().equals(memberVo.getMemberPw())) {
				flag = 1;
			}
		}
		
		System.out.println("(dao)loginCheck : " + flag);
		
		return flag;
	}

	@Override
	public int checkId(String memberId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
