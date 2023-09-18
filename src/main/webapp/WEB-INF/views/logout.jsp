<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
							<h1>Logout Page</h1>
							<ul class="breadcumb">
								<li class="active"><a href="/">Home</a></li>
								<li><span class="ion-ios-arrow-right"></span> login</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="loginbox">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-sm-12 col-xs-12">
						<div class="login-content">
							<h3>Login</h3>
							<form method="post" action="/logout">
								<div class="row">
									<label for="username"> Username: <input type="text"
										name="username" id="username" placeholder="Hugh Jackman"
										pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{8,20}$" required="required" />
									</label>
								</div>

								<div class="row">
									<label for="password"> Password: <input type="password"
										name="password" id="password" placeholder="******"
										pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
										required="required" />
									</label>
								</div>
								<div class="row">
									<div class="remember">
										<div>
											<input type="checkbox" name="remember" value="Remember me"><span>Remember me</span>
										</div>
										<a href="#">Forget password ?</a>
									</div>
								</div>
								<div class="row">
									<button type="submit" class="btn-success">Login</button>
								</div>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<footer>
<div class="container">
		<div class="flex-parent-ft">
			<div class="flex-child-ft item1">
				 <a href="index-2.html"><img class="logo" src="/resources/images/logo1.png" alt=""></a>
				 <p>5th Avenue st, manhattan<br>
				New York, NY 10001</p>
				<p>Call us: <a href="#">(+01) 202 342 6789</a></p>
			</div>
			<div class="flex-child-ft item2">
				<h4>Resources</h4>
				<ul>
					<li><a href="#">About</a></li> 
					<li><a href="#">Blockbuster</a></li>
					<li><a href="#">Contact Us</a></li>
					<li><a href="#">Forums</a></li>
					<li><a href="#">Blog</a></li>
					<li><a href="#">Help Center</a></li>
				</ul>
			</div>
			<div class="flex-child-ft item3">
				<h4>Legal</h4>
				<ul>
					<li><a href="#">Terms of Use</a></li> 
					<li><a href="#">Privacy Policy</a></li>	
					<li><a href="#">Security</a></li>
				</ul>
			</div>
			<div class="flex-child-ft item4">
				<h4>Account</h4>
				<ul>
					<li><a href="#">My Account</a></li> 
					<li><a href="#">Watchlist</a></li>	
					<li><a href="#">Collections</a></li>
					<li><a href="#">User Guide</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="ft-copyright">
		<div class="ft-left">
			<p>
				<a target="_blank" href="https://www.templateshub.net">Templates
					Hub</a>
			</p>
		</div>
		<div class="backtotop">
			<p>
				<a href="#" id="back-to-top">Back to top <i
					class="ion-ios-arrow-thin-up"></i></a>
			</p>
		</div>
	</div>
	
	<script type="text/javascript">
		$(".btn-success").on("click", function(e) {
			e.preventDefault();
			$("form").submit();
		});
	</script>
	<c:if test="${param.logout != null }"></c:if>
	<script>
		$(document).ready(function() {
			alert("로그아웃 했습니다.");
		})
	</script>
	
</footer>