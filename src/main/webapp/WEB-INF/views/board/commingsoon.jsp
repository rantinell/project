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
					<h1>CommingSoon</h1>
					<ul class="breadcumb">
						<li class="active"><a href="/">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> CommingSoon</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="page-single">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="flex-wrap-movielist mv-grid-fw">
					<c:forEach items="${comming}" var="movie">
						<div class="movie-item-style-2 movie-item-style-1">
							<img src="/resources/images/uploads/mv1.jpg" alt="">
							<div class="hvr-inner">
	            				<a  href="moviesingle.html"> Read more <i class="ion-android-arrow-dropright"></i> </a>
	            			</div>
							<div class="mv-item-infor">
								<h6><a href="#"><c:out value="${movie.mi_title }"></c:out></a></h6>
								<p class="rate"><i class="ion-android-star"></i><span><c:out value="${movie.mi_total_point}"></c:out> </span> /10</p>
							</div>
						</div>					
					</c:forEach>
				</div>		
			</div>
		</div>
	</div>
</div>
		</div>
		
<%@ include file="../includes/footer.jsp" %>