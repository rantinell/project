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
	<meta name="_csrf" content="${_csrf.token}">
	<meta name="_csrf_header" content="${_csrf.headerName}">
	<link rel="profile" href="#">


    <!--Google Font-->
    <link rel="stylesheet" href='http://fonts.googleapis.com/css?family=Dosis:400,700,500|Nunito:300,400,600' />
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
	
	<!-- sementic ui -->
	<link rel="stylesheet" type="text/css" href="/resources/semantic/dist/semantic.min.css">
	<script src="/resources/semantic/dist/semantic.min.js"></script>
	
	
<script type="text/javascript">
$(document).ready(function(){
	/* $(function() {
		$("#keyword").keypress(function(e){
			//검색어 입력 후 엔터키 입력하면 조회버튼 클릭
			if(e.keyCode && e.keyCode == 13){
				$("#searchBtn").trigger("click");
				return false;
			}
		});
	}); */
	
	// All your normal JS code goes in here
    $(".rating").rating();

	var searchForm = $("#searchForm");
	
	$("#searchForm button").on("click", function(e){
		if(!searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하세요");
			return false;
		}
		e.preventDefault();
		
		searchForm.submit();
	});
});
</script>

</head>
<body>
<!-- BEGIN | Header -->
<header class="ht-header">
	<div class="container">
		<nav class="navbar navbar-default navbar-custom">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header logo">
				    <div class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					    <span class="sr-only">Toggle navigation</span>
					    <div id="nav-icon1">
							<span></span>
							<span></span>
							<span></span>
						</div>
				    </div>
				    <a href="/"><img class="logo" src="/resources/images/logo1.png" alt="" width="119" height="58"></a>
			    </div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse flex-parent" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav flex-child-menu menu-left">
						<li class="hidden">
							<a href="#page-top"></a>
						</li>
						<li class="dropdown first">
							<a class="btn btn-default dropdown-toggle lv1" href="/">Home </a>
						</li>
						<li class="dropdown first">
							<a class="btn btn-default dropdown-toggle lv1" data-toggle="dropdown" data-hover="dropdown">movies<i class="fa fa-angle-down" aria-hidden="true"></i></a>
							<ul class="dropdown-menu level1">
								<li><a href="/movie/rank" >영화 랭킹</a></li>			
								<li><a href="/movie/now">상영중</a></li>
								<li><a href="/movie/comming">상영예정</a></li>
								<li class="it-last"><a href="/movie/recommend">추천영화</a></li>
							</ul>
						</li>
					</ul>
					<ul class="nav navbar-nav flex-child-menu menu-right">          
						<sec:authorize access="isAnonymous()">
							<li class="loginLink"><a href="/movie/loginForm">LOG In</a></li>
							<li class="btn signupLink"><a href="/movie/createMemberForm">sign up</a></li>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
							<li class="myPage"><a href="/userdetails">My Account</a></li>
							<sec:authorize access="hasAuthority('3')">
								<li class="adminLink"><a href="/admin">Admin</a></li>
							</sec:authorize>
							<li class="logoutLink"><a href="/logout">LOG Out</a></li>
						</sec:authorize>	
					</ul>
				</div>
			<!-- /.navbar-collapse -->
	    </nav>
	    
	    <!-- top search form -->
	    <div class="top-search">
	    <form id="searchForm" class="searchForm" action="/movie/search" method="get">
	    	<input type="text" id="keyword" name="keyword" placeholder="검색하고 싶은 영화제목을 입력 후 엔터키를 누르세요.">

	    	<button type="submit" class="btn btn-default" id="searchBtn">&emsp;&emsp;&emsp;</button>

	    </form>
	    </div>
	</div>
</header>
<!-- END | Header -->