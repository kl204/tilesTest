package bitedu.bipa.tiles.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import bitedu.bipa.tiles.vo.MemberVO;

public interface IMemeberService {

	ArrayList<MemberVO> selectAllMember();

	MemberVO selectDetailMember(int memberSeq);

	int loginCheck(MemberVO memberVo);

	int checkId(String memberId);

	int createMember(MemberVO memberVo);

	String selectMemberGrade(String id);

	int removeMember(int memberId);

	 ArrayList<MemberVO> selectMemberById(String id);

//	MemberVO upload(List<FileItem> items) throws Exception;

}
