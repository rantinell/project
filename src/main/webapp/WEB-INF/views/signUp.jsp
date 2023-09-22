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
<title>Open Pediatrics</title>
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

<!-- CSS files -->
<link rel="stylesheet" href="/resources/css/plugins.css">
<link rel="stylesheet" href="/resources/css/style.css">

<!-- Script files -->
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/plugins.js"></script>
<script src="/resources/js/plugins2.js"></script>
<script src="/resources/js/custom.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#submit").on("click", function(){
		if($("#m_id").val()==""){
			alert("아이디를 입력해주세요.");
			$("#m_id").focus();
			return false;
		}
		if($("#m_pw").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#m_id").focus();
			return false;
		}
		if($("#m_name").val()==""){
			alert("성명을 입력해주세요.");
			$("#m_name").focus();
			return false;
		}
		if($("#m_mail").val()==""){
			alert("이메일을 입력해주세요.");
			$("#m_mail").focus();
			return false;
		}
		if($("#m_tel").val()==""){
			alert("전화번호 입력해주세요.");
			$("#m_tel").focus();
			return false;
		}
		
		var idChkVal = $("#idChk").val();
		if(idChkVal == "N"){
			alert("중복확인 버튼을 눌러주세요.");
			return false;
		}else if(idChkVal == "Y"){
			$("#signupForm").submit();
		}
	});
})

function fn_idChk(){
		$.ajax({
			url : "/idChk",
			type : "post",
			dataType : "json",
			data : {"m_id" : $("#m_id").val()},
			success : function(data){
				if(data == 1){
					alert("중복된 아이디입니다.");
				}else if(data == 0){
					$("#idChk").attr("value", "Y");
					alert("사용가능한 아이디입니다.");
				}
			}
		})
}
	
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
							<form method="post" action="/signUp" name="signupForm" id="signupForm">
								<div class="row">
									<label for="username-2"> 아이디 * <input type="text" name="m_id" id="username-2" required="required" autocomplete="off" placeholder="사용할 아이디를 입력하세요."/>
									</label>
									<button class="idChk" type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
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
									<button type="submit" onclick="passtest">sign up</button>
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