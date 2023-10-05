<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html lang="en" class="no-js">

<!-- movielist_light16:30-->
<head>
<!-- Basic need -->
<title>Movie</title>
<meta charset="UTF-8">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<link rel="profile" href="#">

<!--Google Font-->
<link rel="stylesheet"
	href='http://fonts.googleapis.com/css?family=Dosis:400,700,500|Nunito:300,400,600' />
<!-- Mobile specific meta -->
<meta name=viewport content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone-no">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<!-- CSS files -->
<link rel="stylesheet" href="/resources/css/plugins.css">
<link rel="stylesheet" href="/resources/css/style.css">

<!-- Script files -->
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/plugins.js"></script>
<script src="/resources/js/plugins2.js"></script>
<script src="/resources/js/custom.js"></script>

<script type="text/javascript">
/* $(document).ready(function(){
	//아이디 중복검사
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}"; 
	$(document).ajaxSend(function(e, xhr, options) { 
	    xhr.setRequestHeader(csrfHeaderName, csrfTokenValue); 
	  });
	
	$('#username-2').keyup(function(){
		let m_id = $('#username-2').val();
			
		$.ajax({
			url : "/idChk",
			type : "post",
			data : {m_id: m_id},
			dataType : 'json',
			success : function(result){
				if(result == 1){
					$("#id_feedback").html('이미 사용중인 아이디입니다.');
					$("#id_feedback").attr('color','#dc3545');
					$("#regist").val('N')
					$("#regist").attr('disabled', true);
				} else{
					$("#id_feedback").html('사용할 수 있는 아이디입니다.');
					$("#id_feedback").attr('color','#2fb380');
					$("#regist").val('Y')
					$("#regist").attr('disabled', false);
				} 
			},
			error : function(){
				alert("서버요청실패");
			}
		})
			 
	})
}) */
$("#submit").on("click", function(){
		var idChkVal = $("#regist").val();
		if(idChkVal == "N"){
			alert("아이디를 다시 입력해주세요.");
			return false;
		} else if(idChkVal == "Y"){
			$("#signupForm").submit();
		}
});

</script>
<style type="text/css">
 .idChk {
 	width: 20% !important;
 	display: block !important;
 	margin: auto !important;
 }

 
</style>


</head>
<body>
	<div class="buster-light">
		<div class="customhero customcommon-hero">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="hero-ct">
							<h1>Sign Up Page</h1>
							<ul class="breadcumb">
								<li class="active"><a href="/">Home</a></li>
								<li><span class="ion-ios-arrow-right"></span> sign up</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="signupbox">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-sm-12 col-xs-12">
						<div class="signup-content">
							<h3>sign up</h3>
							<form method="post" action="/movie/signUp" name="signupForm" id="signupForm">
								<div class="row">
									<label for="username-2"> 아이디 * <input type="text" name="m_id" id="username-2" required="required" autocomplete="off" placeholder="사용할 아이디를 입력하세요."/>
									<div><font id="id_feedback" size="2"></font></div>
									</label>
								</div>
								<br>
								<div class="row">
									<label for="password-2"> 비밀번호 * <input type="password" name="m_pw" id="password-2" required="required" placeholder="비밀번호를 입력하세요." />
									</label>
								</div>
								<div class="row">
									<label for="repassword-2"> 비밀번호 확인 * <input type="password" name="repassword" id="repassword-2" required="required" placeholder="비밀번호를 다시 입력해주세요."/>
									</label>
								</div>
								<div class="row">
									<label for="name-2"> 이름 * <input type="text" name="m_name" id="name-2" required="required" />
									</label>
								</div>
								<div class="row">
									<label for="tel-2"> 전화번호 * <input type="text" name="m_tel" id="tel-2" required="required" />
									</label>
								</div>
								<div class="row">
									<label for="email-2"> 이메일 * <input type="text" name="m_mail" id="email-2" required="required" />
									</label>
								</div>
								<div class="row">
									<button id="regist" type="submit" value="N">sign up</button>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</body>
	<footer>
	<div class="ft-copyright">
		<div class="ft-left">
			<p><a target="_blank" href="https://www.templateshub.net">Templates Hub</a></p>
		</div>
		<div class="backtotop">
			<p><a href="#" id="back-to-top">Back to top  <i class="ion-ios-arrow-thin-up"></i></a></p>
		</div>
	</div>
	</footer>