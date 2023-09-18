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
					<h1> Search Result</h1>
					<ul class="breadcumb">
						<li class="active"><a href="/">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> search</li>
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
				<c:forEach items="${movie}" var="movie"> 
                <div class="movie-item-style-2">    
                	<img src="<c:url value="/upload/${movie.thumbnail}"/>" style="width: 60%" />  
                	<div class="mv-item-infor">                 
	                    <h6><a href="<c:url  value="/board/movie?id=${movie.mi_id}"/>">oblivion <span>(2012)</span></a></h6>
	                    <p class="rate"><i class="ion-android-star"></i><span><c:out value="${movie.rate}"/></span> /10</p>
	                    <p class="describe"><c:out value="${movie.describe}"/></p>
	                    <p class="run-time"> Run Time: 2h21’    .     <span>MMPA: PG-13 </span>    .     <span>Release: 1 May 2015</span></p>
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