<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<%@ include file="includes/header.jsp"%>

<div class="slider movie-items">
	<div class="container">
		<div class="row">
	    	<div class="slick-multiItemSlider">
	    	
	    	<!-- 슬라이드 반복 시작 -->
	    		<c:forEach items="${rankingmovie}" var="movie" begin="0" end="9" step="1">
	    		<div class="movie-item">
	    			<div class="mv-img">
	    				<img src="<c:url value="/resources/images/uploads/${movie.mi_thumbnail}"/>" alt="" width="285" height="437">
	    			</div>
	    			<div class="title-in">
	    				<div class="cate">
	    					<span class="green">comedy</span>
	    				</div>
	    				<h6><a href="<c:out value="/movie/${movie.mi_num}"/>"><c:out value="${movie.mi_title}"/></a></h6>
	    				<p><i class="ion-android-star"></i><span><c:out value="${movie.mi_total_point}"></c:out></span> /10</p>
	    			</div>
	    		</div>
	    		</c:forEach>
	    		<!-- 슬라이드 반복 끝 -->
	    		
	    	</div>
	    </div>
	</div>
</div>
		<div class="buster-light">
<div class="movie-items">
	<div class="container">
		<div class="row ipad-width">
			<div class="col-md-8">
				<div class="title-hd">
					<h2>now playing</h2>
					<a href="/movie/now" class="viewall">View all <i class="ion-ios-arrow-right"></i></a>
				</div>
				<div class="tabs">
					<ul class="tab-links">                        
					</ul>
				   <div class="tab-content">
				        <div id="tab1" class="tab active">
				            <div class="row">
				            	<div class="slick-multiItem">
				            	
				            		<!-- 슬라이드 반복 시작 -->
				            		<c:forEach items="${now}" var="now">
				            		<div class="slide-it">
				            			<div class="movie-item">
					            			<div class="mv-img">
					            				<img src="<c:url value="/resources/images/uploads/${now.mi_thumbnail}"/>" alt="" width="185" height="284">
					            			</div>
					            			<div class="hvr-inner">
					            				<a href="<c:out value="/movie/${now.mi_num}"/>"> Read more <i class="ion-android-arrow-dropright"></i> </a>
					            			</div>
					            			<div class="title-in">
					            				<h6><a href="<c:out value="${now.mi_num}"/>"><c:out value="${now.mi_title}"/></a></h6>
					            				<p><i class="ion-android-star"></i><span><c:out value="${now.mi_total_point}"/></span> /10</p>
					            			</div>
					            		</div>
				            		</div>
				            		</c:forEach>
									<!-- 슬라이드 반복 끝 -->
				            		
				            	</div>
				            </div>
				        </div>
				    </div>
				</div>
				<div class="title-hd">
					<h2>CommingSoon</h2>
					<a href="/movie/comming" class="viewall">View all <i class="ion-ios-arrow-right"></i></a>
				</div>
				<div class="tabs">
					<ul class="tab-links">                        
					</ul>
				    <div class="tab-content">
				        <div id="tab21" class="tab active">
				            <div class="row">
				            	<div class="slick-multiItem">
				            		
								<!-- 슬라이드 반복 시작 -->
								<c:forEach items="${comming}" var="comming">
				            		<div class="slide-it">
				            			<div class="movie-item">
					            			<div class="mv-img">
					            				<img src="<c:url value="/resources/images/uploads/${comming.mi_thumbnail}"/>" alt="" width="185" height="284">
					            			</div>
					            			<div class="hvr-inner">
					            				<a  href="<c:out value="/movie/${comming.mi_num }"/>"> Read more <i class="ion-android-arrow-dropright"></i> </a>
					            			</div>
					            			<div class="title-in">
					            				<h6><a href="<c:out value="/movie/${comming.mi_num }"/>"><c:out value="${comming.mi_title}"/> </a></h6>
					            				<p><i class="ion-android-star"></i><span><c:out value="${comming.mi_total_point }"/></span> /10</p>
					            			</div>
					            		</div>
				            		</div>
				            	</c:forEach>	
				            		<!-- 슬라이드 반복 끝 -->
				            	</div>
				            </div>
				        </div>
				    </div>
				</div>
			</div>
		</div>
	</div>
</div>
		</div>
<%@ include file="includes/footer.jsp"%>