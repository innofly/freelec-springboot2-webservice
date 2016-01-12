<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp"%>
<script src="../common/js/jquery.validate.js"></script>
<link href="../common/css/signin.css" rel="stylesheet">
<script>
	// 폼체크
	$(function(){
		// 표현방법 셋팅
	 	$.validator.setDefaults({
	        onkeyup:false,
	        onclick:false,
	        onfocusout:false,
	        showErrors:function(errorMap, errorList){
	            if(this.numberOfInvalids()) { // 에러가 있을 때만..
	                alert(errorList[0].message);
	            }
	        }
	    });			
		
		$("#joinForm").validate({		
			rules:{
				"user_id": { required: true, minlength: 3 },
				"pass": { required: true },
				"pass_chk": { equalTo: "#pass" },
				"user_name": { required: true },
				"email": { required: true, email: true },
				"phone_num": { required: true },
				"birthday": { required: true },
				"address": { required: true },
				"location": { required: true }			
			},
			messages:{
				"user_id": { required: "아이디를 입력하세요.", minlength: jQuery.format("아이디는 {0}자 이상입니다.") },
                "pass": { required: "암호를 입력하세오." },
                "pass_chk": { equalTo: "암호를 다시 확인하세요." },
                "user_name": {required: "이름을 입력하세요."},
                "email": {required: "이메일을 주소를 입력하세요.", email: "올바른 이메일 주소를 입력하시오." },
                "phone_num": {required: "폰번호를 입력하세요."},
                "birthday": {required: "생년월일을 입력하세요."},
                "address": {required: "주소를 입력하세요."},
                "location": {required: "스터디 희망지역을 입력하세요."}				
			}

			
		});
		
	});

</script>
<body>

 <div class="container">

      <form name="joinForm" id="joinForm" class="form-signin" method="post" action="join_proc.do" class="form-signin">
        <h2 class="form-signin-heading">회원가입</h2>
        <input type="text" name="user_id" id="user_id"class="form-control" placeholder="아이디">
        <input type="password" name="pass" id="pass" class="form-control" placeholder="비밀번호">
        <input type="password" name="pass_chk" id="pass_chk" class="form-control" placeholder="비밀번호확인">
        <input type="text" name="user_name" id="user_name" class="form-control" placeholder="이름">
        <input type="text" name="email" id="email" class="form-control" placeholder="이메일">
        <input type="text" name="phone_num" id="phone_num" class="form-control" placeholder="휴대폰번호">
        <input type="text" name="birthday" id="birthday" class="form-control" placeholder="생년월일">
        <input type="text" name="address" id="address" class="form-control" placeholder="주소">
        <input type="text" name="location" id="location" class="form-control" placeholder="스터디 희망지역">
        <button class="btn btn-lg btn-primary btn-block" type="submit">가입하기</button>
      </form>

    </div> <!-- /container -->	
		
</body>

<%@include file="../inc/bottom.jsp"%>

