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
					<h1> Ranking Chart</h1>
					<ul class="breadcumb">
						<li class="active"><a href="/">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> Ranking</li>
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
				<c:forEach items="${rankingmovie}" var="rankingmovie"> 
                <div class="movie-item-style-2">    
                	<img src="<c:url value="/upload/${rankingmovie.thumbnail}"/>" style="width: 60%" />  
                	<div class="mv-item-infor">                 
	                    <h6><a href="<c:url  value="${rankingmovie.mi_num}"/>"><c:out value="${rankingmovie.mi_title}"/> <span>(2012)</span></a></h6>
	                    <p class="rate"><i class="ion-android-star"></i><span><c:out value="${rankingmovie.mi_total_point}"/></span> /5</p>
	                    <p class="describe"><c:out value="${rankingmovie.describe}"/></p>
	                    <p class="run-time"> Run Time: 2h21’    .     <span>심의등급: <c:out value="${rankingmovie.md_grade}"/> </span>    .     <span><c:out value="${rankingmovie.regdate}"/></span></p>

	                </div>
                </div>
            	</c:forEach>
            <!-- 반복 끝  -->	
            	 
				</div>
			</div>
		</div>
	</div>
</div>
		</div>



<%@ include file="../includes/footer.jsp" %>