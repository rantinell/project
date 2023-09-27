<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>

<div class="buster-light">
<div class="hero common-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1> Recommended Movies</h1>
					<ul class="breadcumb">
						<li class="active"><a href="/">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> Recommended Movies</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="page-single movie_list">
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-sm-12 col-xs-12">
				
			<!-- 반복 시작  -->
			<c:forEach items="recommendmovie">
				<div class="movie-item-style-2">
					<img src="<c:out value="${recommentmovie.thumbnail}"/>" alt="">
					<div class="mv-item-infor">
						<h6><a href="<c:out value="${recommentmovie.mi_nulm}"/>"><c:out value="${recommentmovie.mi_title}"/><span> (2013)  </span></a></h6>
						<p class="rate"><i class="ion-android-star"></i><span><c:out value="${recommentmovie.mi_total_point}"/></span> /5</p>
						<p class="describe"><c:out value="${recommentmovie.md_text}"/></p>
						<p class="run-time"> 런타임: <c:out value="${recommentmovie.md_runtime}"/>’    .     <span>심의등급: <c:out value="${recommentmovie.md_grade}"/> </span></p>
						<p>Director: <c:out value="${recommentmovie.md_director}"/></p>
						<p>Stars: <c:out value="${recommentmovie.md_actor}"/></p>
					</div>
				</div>
			</c:forEach>	
			<!-- 반복 끝  -->	
				
			<!-- 더미 시작  -->	
				<div class="movie-item-style-2">
					<img src="/resources/images/uploads/mv5.jpg" alt="">
					<div class="mv-item-infor">
						<h6><a href="moviesingle_light.html">skyfall: evil of boss<span> (2013)  </span></a></h6>
						<p class="rate"><i class="ion-android-star"></i><span>7.0</span> /10</p>
						<p class="describe">When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution.</p>
						<p class="run-time"> Run Time: 2h21’    .     <span>MMPA: PG-13 </span></p>
						<p>Director: <a href="#">Alan Taylor</a></p>
						<p>Stars: <a href="#">Chris Hemsworth,  </a> <a href="#">  Natalie Portman,</a><a href="#">Tom Hiddleston</a></p>
					</div>
				</div>
			<!-- 더미 끝  -->
				
			</div>
		</div>
	</div>
</div>
		</div>



<%@ include file="../includes/footer.jsp" %>