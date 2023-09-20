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
function passtest() {
    var p1 = document.getElementById('password-2').value;
    var p2 = document.getElementById('repassword-2').value;
    
    if(p1.length < 6) {
            alert('입력한 글자가 6글자 이상이어야 합니다.');
            return false;
        }
        
        if( p1 != p2 ) {
          alert("비밀번호불일치");
          return false;
        } else{
          alert("비밀번호가 일치합니다");
          return true;
        }
  }
</script>

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
							<form method="post" action="/signUp" name="signupForm">
								<div class="row">
									<label for="username-2"> 아이디 * <input type="text" name="username" id="username-2"required="required" placeholder="사용할 아이디를 입력하세요."/>
									</label>
								</div>
								<div class="row">
									<label for="password-2"> 비밀번호 * <input type="password" name="password" id="password-2" required="required" placeholder="6자리 이상 입력하세요." />
									</label>
								</div>
								<div class="row">
									<label for="repassword-2"> 비밀번호 확인 * <input type="password" name="password" id="repassword-2" required="required" placeholder="비밀번호를 다시 입력해주세요."/>
									</label>
								</div>
								<div class="row">
									<label for="name-2"> 이름 * <input type="text" name="name" id="name-2" required="required" />
									</label>
								</div>
								<div class="row">
									<label for="tel-2"> 전화번호 * <input type="text" name="tel" id="tel-2" required="required" />
									</label>
								</div>
								<div class="row">
									<label for="email-2"> 이메일 * <input type="text" name="email" id="email-2" required="required" />
									</label>
								</div>
								<div class="row">
									<button type="button" onclick="passtest">sign up</button>
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