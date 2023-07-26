<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link href="../resources/css/home.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	
		$(document).ready(function(){
			$('#btn_check_id').on('click',function(e){
				let memberId = $('#memberId').val();
				
				console.log(memberId);				
				
				if(memberId!=null || memberId!=''){
					 $.ajax({
				    	    type: "GET", 
				    	    url: "./id_validation/" + memberId, 
				    	    dataType: "json",
				    	    success: function (data) {
				    		  console.log("success");
				    	      console.log(data);
				    	      
				    	      const borrowResult = data.result;
				    	  	const flags = document.getElementById("flag");
				    	      
				    	      console.log(borrowResult);
			    	      
 				    	      if (borrowResult==true) {
				    	        document.getElementById("message").innerHTML = "아이디가 존재합니다";
				    	        flags.value = false;

				    	      } else {
				    	        document.getElementById("message").innerHTML = "아이디 생성 가능합니다";
				    	        flags.value = true;
				    	      } 
				    	    },error: function(err){
				    	    	
				    	    	console.log(err);
				    	  
				    	    }
				    	    
				    	    
				    	  });
				} else {
					$('#message').html("id가 비어있거나 형식에 맞지 않습니다.");
				}

			});
			
			
			
			let checkId = function(){
				return $('#flag').val();
			}
			
		
			let validateData = function(){
				let flag = false;
				
	    	  	const flags = document.getElementById("flag");

	    	  	
				if(checkId()=='true'){
					flag = true;
					$('#message').html('id가 형식에 올바릅니다.');

				} else {
					$('#message').html('아이디 중복 확인을 해주세요!');
				}
				return flag;
			};
			
			
			$('#sending').on('click',function(e){
				console.log("버튼체크");
				let result = validateData();
				if(!result){
					console.log("아직 등록 못함");
					e.preventDefault();
				}else{
                    $('#frm').attr('action','./regist');
			        $('#frm').submit();	                
				
				}			
				
			});
		});
	</script>
</head>
<body>
<form action="" method="post" id="frm">
    <table>
        <tr><th colspan="3" id="form">회원가입양식</th></tr>
        <tr>
        	<th>구분</th>
        	<th class="data_ui" colspan="1">데이터입력</th>
        	<th>비고</th>
        </tr>
        <tr>
            <td>아이디</td>
            <td colspan="1">
            	<input type="text" id="memberId" name="memberId">
            	<button type="button" id="btn_check_id">아이디확인</button>
            </td>
            <td id="message"></td>
        </tr>
        <tr>
        	<td>비밀번호</td>
        	<td colspan="1"><input type="password" id="memberPw" name="memberPw"></td>
        	<td></td>
        </tr>
        <tr>
            <td>핸드폰번호</td>
            <td colspan="1">
                <input type="text" name="memberName" placeholder="010-0000-0000"/>
            </td>
            <td></td>
        </tr>
 
        <tr id="footer">
        	<td colspan="3"><button type="button" id="sending">가입</button> <button type="reset">초기화</button></td>
        </tr>
    </table>
    <input type="hidden" id="flag" value="false">
</form>
</body> 
</html>