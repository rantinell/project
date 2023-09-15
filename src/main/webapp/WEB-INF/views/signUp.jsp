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
						<div class="login-content">
							<h3>sign up</h3>
							<form method="post" action="#">
								<div class="row">
									<label for="username-2"> 아이디 <input type="text"
										name="username" id="username-2"
										pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{8,20}$" required="required" />
									</label>
								</div>
								<div class="row">
									<label for="password-2"> 비밀번호: <input type="password"
										name="password" id="password-2" placeholder=""
										pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
										required="required" />
									</label>
								</div>
								<div class="row">
									<label for="repassword-2"> 비밀번호 확인: <input
										type="password" name="password" id="repassword-2"
										placeholder=""
										pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
										required="required" />
									</label>
								</div>
								<div class="row">
									<label for="name-2"> 이름 <input type="text" name="name"
										id="name-2" required="required" />
									</label>
								</div>
								<div class="row">
									<label for="email-2"> 이메일: <input type="password"
										name="email" id="email-2" placeholder=""
										pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
										required="required" />
									</label>
								</div>
								<div class="row">
									<button type="submit">sign up</button>
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