package bitedu.bipa.tiles.vo;

import java.sql.Timestamp;

public class MemberVO {
	private int memberSeq;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberGender;
	private Timestamp memberRegistDate;
	private String memberGrade;
	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public Timestamp getMemberRegistDate() {
		return memberRegistDate;
	}
	public void setMemberRegistDate(Timestamp memberRegistDate) {
		this.memberRegistDate = memberRegistDate;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	
	
	


	
}
