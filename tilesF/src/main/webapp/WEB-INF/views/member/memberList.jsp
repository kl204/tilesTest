<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="bitedu.bipa.tiles.vo.MemberVO"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
<link href="../resources/css/basic_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
${param.flag=='true'?"<script>alert('탈퇴성공');</script>":""}
<table>
	<tr><th colspan="6" id="title">회원리스트</th></tr>
	<tr><td id='seq'>순번</td><td>아이디</td><td>이름</td><td>등급</td></tr>
<c:forEach var="copy" items="${data}">
	<tr>
		<td>${copy.memberSeq}</td>
 		<td><a href="viewDetail.do?memberSeq=${copy.memberSeq}">${copy.memberId}</a></td>
		<td>${copy.memberName}</td>
		<td>${copy.memberGrade}</td>
     <% MemberVO member = (MemberVO)session.getAttribute("login");

		String memberId ="";
		String memberGrade = "";
		boolean memGrade = false;
		boolean flag = false;
		
		if(member !=null){
			memberGrade = member.getMemberGrade();
			
			memGrade = memberGrade.equals("admin");
		}
		
		%>

          <%if(memGrade){ %>

            <td><a href="./viewUpdate.do?memberSeq=${copy.memberSeq}"><button>회원수정</button></a></td>
			<% } else { %>

	        <% } %>
	
	</tr>
</c:forEach>

	   	
</table>
<script type="text/javascript" src="../resources/js/book.js"></script> 
</body>
</html>