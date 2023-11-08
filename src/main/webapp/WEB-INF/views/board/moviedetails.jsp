<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>
<style>
@media (min-width: 992px) {
  .col-md-8 {
    width: 66.66666667%;
  }
}

#c_point {
	background-color: white;
}
.star_rating {
  width: 100%; 
  box-sizing: border-box; 
  display: inline-flex; 
  float: left;
  flex-direction: row; 
  justify-content: flex-start;
}
.star_rating .star {
  width: 25px; 
  height: 25px; 
  margin-right: 10px;
  display: inline-block; 
  background: url('/resources/images/star/star3_blank.png') no-repeat; 
  background-size: 100%; 
  box-sizing: border-box; 
}
.star_rating .star.on {
  width: 25px; 
  height: 25px;
  margin-right: 10px;
  display: inline-block; 
  background: url('/resources/images/star/star3_fill.png') no-repeat;
  background-size: 100%; 
  box-sizing: border-box; 
}

.star_box {
  width: 400px;
  box-sizing: border-box;
  display: inline-block;
  margin: 15px 0;
  background: #F3F4F8;
  border: 0;
  border-radius: 10px;
  height: 100px;
  resize: none;
  padding: 15px;
  font-size: 13px;
  font-family: sans-serif;
}
.btn02 {
  display:block;
  width: 400px;
  font-weight: bold;
  border: 0;
  border-radius: 10px;
  max-height: 50px;
  padding: 15px 0;
  font-size: 1.1em;
  text-align: center;
  background:bisque;
}
</style>

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		var mi_num = '<c:out value="${movie.mi_num}"/>';
		var operForm = $("#operForm");
		
		// comment 별점
		$('.star_rating > .star').click(function() {
		  $(this).parent().children('span').removeClass('on');
		  $(this).addClass('on').prevAll('span').addClass('on');
		  var c_point = $(this).attr('data-value');
		  $("#point").val(c_point); // 히든 인풋에 값 저장.
		})

		$('button[data-oper="modify"]').on("click", function(e){
			operForm.attr("action", "/movie/modify").submit();
		});
		
		$('button[data-oper="list"]').on("click", function(e){
			operForm.find("#m_num").remove();
			operForm.attr("action", '/movie/"${movie.mi_num}"').submit();
		});
		
		var mi_numValue = '<c:out value="${movie.mi_num}"/>';
		var replyComment = $(".comment");

		
		var modal = $(".modal");
		var modalInputReply = modal.find("textarea[name='reply']");
		var modalInputReplyer = modal.find("input[name='replyer']");
		var modalInputReplyDate = modal.find("input[name='replyDate']");
		//var modalInputRate = $('.ui.rating');
		//var modalInputRate = getRating();
		//var modalInputRate = $(".star_rating > span.on").length;
		var modalInputRate = $("#point")
		
		var modalModBtn = $("#modalModBtn");
		var modalRemoveBtn = $("#modalRemoveBtn");
		var modalRegisterBtn = $("#modalRegisterBtn");
		
		var replyer = null;
	<sec:authorize access="isAuthenticated()">		
		replyer = '<sec:authentication property="principal.username"/>';
	</sec:authorize>
		
		var csrfHeaderName = "${_csrf.headerName}";
		var csrfTokenValue = "${_csrf.token}"; 
	
		$("#addComment").on("click", function(e){
			modal.find("input").val("");
			modal.find("input[name='replyer']").val(replyer);
			modalInputReplyDate.closest("div").hide();
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalRegisterBtn.show();
			modal.modal("show");
		});
		
		$("#modalCloseBtn").on("click", function(e){
			modal.modal("hide");
		});
		
	    
	  /* $(document).ajaxSend(function(e, xhr, options) { 
	    xhr.setRequestHeader(csrfHeaderName, csrfTokenValue); 
	  }); */

		
		modalRegisterBtn.on("click", function(e){
			var reply = {
				c_comment : modalInputReply.val(),
				m_id : modalInputReplyer.val(),
				mi_num : mi_numValue,
				c_point: modalInputRate.val()
			}
			
			replyService.add(reply, function(result){
				console.log(modalInputRate);
				//alert(result);
				alert("comment가 등록되었습니다.");
				modal.find("input").val("");
				modal.modal("hide");
				/* showList(-1); */
			});			
		});
		

		modalModBtn.on("click", function(e){
			var originalReplyer = modalInputReplyer.val();
			
			var reply = {c_num:modal.data("c_num"),
										reply: modalInputReply.val(),
										c_point: $('.ui.rating').rating('get rating'),
										replyer: originalReplyer};
			
			if(!replyer){
				alert("로그인 후 수정이 가능합니다.");
				modal.modal("hide");
				return;
			}
			
			console.log("Original Replyer: " + originalReplyer);
			
			if(replyer != originalReplyer){
				alert("자신이 작성한 코멘트만 수정 가능합니다.");
				modal.modal("hide");
				return;
			}		
			
			replyService.update(reply, function(result){
				alert(result);
				modal.modal("hide");
				/* showList(pageNum); */
			});
		});
		
		modalRemoveBtn.on("click", function(e){
			var c_num = modal.data("c_num");
			
			console.log("c_num: " + c_num);
			console.log("REPLYER: " + replyer);
			
			if(!replyer){
				alert("로그인 후 삭제가 가능합니다.");
				modal.modal("hide");
				return;
			}
			
			var originalReplyer = modalInputReplyer.val();
			
			console.log("Original Replyer: " + originalReplyer);
			
			if(replyer != originalReplyer){
				alert("자신이 작성한 코멘트만 삭제 가능합니다.");
				modal.modal("hide");
				return;
			}
			
			replyService.remove(c_num, originalReplyer, function(result){
				alert(result);
				modal.modal("hide");
				/* showList(pageNum);				 */
			});
		});
		
	});
</script>

    	<div class="buster-light">
<div class="hero mv-single-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<!-- <h1> movie listing - list</h1>
				<ul class="breadcumb">
					<li class="active"><a href="#">Home</a></li>
					<li> <span class="ion-ios-arrow-right"></span> movie listing</li>
				</ul> -->
			</div>
		</div>
	</div>
</div>
<div class="page-single movie-single movie_single">
	<div class="container">
		<div class="row ipad-width2">
		
			<div class="col-md-4 col-sm-12 col-xs-12">
				<div class="movie-img sticky-sb">
					<img src="<c:url value="/resources/images/uploads/${movie.mi_thumbnail}"/>">
					<div class="movie-btn">	
						<div class="btn-transform transform-vertical">
							<div><a href="#" class="item item-1 yellowbtn"> <i class="ion-card"></i> Buy ticket</a></div>
							<div><a href="http://www.cgv.co.kr/ticket/" class="item item-2 yellowbtn"><i class="ion-card"></i></a></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-8 col-sm-12 col-xs-12">
				<div class="movie-single-ct main-content">
				<br>
					<h1 class="bd-hd"><c:out value="${movie.mi_title}"/> <span>2015</span></h1>
					<div class="social-btn">
						<sec:authorize access="hasRole('ADMIN')">
							<a href="./movieMod" class="parent-btn">영화정보 수정</a>
						</sec:authorize>
						<div >
							<a href="#" class="parent-btn"></a>
							<div class="hvr-item">
							</div>
						</div>		
					</div>
					<div class="movie-rate">
						<div class="rate">
							<i class="ion-android-star"></i>
							<p><span><c:out value="${movie.mi_total_point}" /></span> /10<br>
							</p>
						</div>
						<div class="rate-star">
							<p>Rate This Movie:  </p>
							<c:forEach begin="1" end="${movie.mi_total_point}">
								<i class="ion-android-star"></i>
							</c:forEach>
							<c:if test="${movie.mi_total_point != 10}">
							<c:forEach begin="${movie.mi_total_point + 1}" end="10">
								<i class="ion-android-star last"></i>
							</c:forEach>
							</c:if>
							
						</div>
					</div>
					<div class="movie-tabs">
						<div class="tabs">
							<ul class="tab-links tabs-mv">
								<li class="active"><a href="#overview">Overview</a></li>
								<li><a href="#reviews"> Reviews</a></li>                    
							</ul>
						    <div class="tab-content">
						        <div id="overview" class="tab active">
						            <div class="row comnent">
						            	<div class="col-md-8 col-sm-12 col-xs-12">
						            		<p><c:out value="${movie.md_text}"></c:out></p>
											<div class="title-hd-sm">
												<h4>User reviews</h4>
											</div>
											<!-- movie user review -->
											<div class="mv-user-review-item">
												<!-- 평점 가장 높은 작성자 노출시킬것 -->
												<h3>작성자: hawaiipierson</h3>
												<div class="no-star">
													<c:forEach begin="1" end="${movie.mi_total_point}">
													<i class="ion-android-star"></i>
													</c:forEach>
												</div>
												<p class="time">
													등록일: 2016년 12월 17일
												</p>
												<p>This is by far one of my favorite movies from the MCU. The introduction of new Characters both good and bad also makes the movie more exciting. giving the characters more of a back story can also help audiences relate more to different characters better, and it connects a bond between the audience and actors or characters. Having seen the movie three times does not bother me here as it is as thrilling and exciting every time I am watching it. In other words, the movie is by far better than previous movies (and I do love everything Marvel), the plotting is splendid (they really do out do themselves in each film, there are no problems watching it more than once.</p>
											</div>
						            	</div>
						            	<div class="col-md-4 col-xs-12 col-sm-12">
						            		<div class="sb-it">
						            			<h6>감독: </h6>
						            			<p><c:out value="${movie.md_director}"/></p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>배우: </h6>
						            			<p><c:out value="${movie.md_actor}"/></p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>장르: </h6>
						            			<p><c:out value="${movie.genre}"/></p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>분류등급:</h6>
						            			<p><c:out value="${movie.md_grade}"/></p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>개봉여부:</h6>
						            			<c:if test="${movie.mi_condition == 1}">
						            				<p>상영중</p>
						            			</c:if>
						            			<c:if test="${movie.mi_condition == 0}">
						            				<p>상영 예정</p>
						            			</c:if>
						            		</div>
						            		<div class="sb-it">
						            			<h6>런타임:</h6>
						            			<p><c:out value="${movie.md_runtime}"/></p>
						            		</div>
						            	</div>
						            </div>
						        </div>
						        <div id="reviews" class="tab review">
						           <div class="row">
						           		<!-- <form action="" method="get"></form> -->
						            	<div class="rv-hd">
						            		<div class="div">
						            			<h3>Related Movies To</h3>
							            		<h2><c:out value="${movie.mi_title}" /></h2>
							            	</div>
							            	<sec:authorize access="isAuthenticated()">
							            	<button id="addComment" class="redbtn">Add Comment</button>
							            	</sec:authorize>
						            	</div>
						            	
						            	<c:forEach items="${comment}" var="comment">
						            	<div class="comment">
						            	<!-- 코멘트 리스트 공간  -->
						            	
						            	<!-- 위치 확인용 더미 시작 -->
											<div class="mv-user-review-item">
												<div class="user-infor">
													<div>
														<h3>작성자: <c:out value="${comment.m_id}" /></h3>
														<div class="no-star">
															<c:forEach begin="1" end="${comment.c_point}">
															<i class="ion-android-star"></i>
															</c:forEach>
															<c:if test="${comment.c_point != 10}">
															<c:forEach begin="${comment.c_point + 1}" end="10">
															<i class="ion-android-star last"></i>
															</c:forEach>
															</c:if>
														</div>
														<p class="time">
															등록일: <c:out value="${comment.c_regdate}" />
														</p>
													</div>
												</div>
												<p><c:out value="${comment.c_comment}" /></p>
											</div>
										<!-- 위치 확인용 더미 끝 -->	
											
										</div>
										</c:forEach>
										<div class="topbar-filter">
										<!-- 페이지 버튼  -->
										</div>	
										</div>
										<form id='operForm' action="/movie/modify" method="get">
					 						<%-- <input type='hidden' id='m_num' name='m_num' value='<c:out value="${comment.m_num}"/>'> --%>
					         				<input type='hidden' id="md_num" name="md_num" value='<c:out value="${movie.md_num}"/>'/>
					 						<%-- <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
					 						<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
					 						<input type='hidden' name="type" value='<c:out value="${cri.type}"/>'/> --%>
 										</form>
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
		</div>
		

			<!-- Modal 시작  -->

<div class="ui basic modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  		
		<div class="header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">Comment Modal</h4>
		</div>
		<div class="content">
			<div class="form-group">
				<label>Comment Date</label><input class="form-control" name='replyDate' value=''>
			</div>
			<div class="form-group">
				<label>Rate (max 10)</label><p>
				<div class ="star_rating">
				  <span class="star on" data-value="1.5"> </span>
				  <span class="star" data-value="2"> </span>
				  <span class="star" data-value="3"> </span>
				  <span class="star" data-value="4"> </span>
				  <span class="star" data-value="5"> </span>
				  <span class="star" data-value="6"> </span>
				  <span class="star" data-value="7"> </span>
				  <span class="star" data-value="8"> </span>
				  <span class="star" data-value="9"> </span>
				  <span class="star" data-value="10"> </span>
				</div>
			</div>	
			<div class="form-group">
				<label>Comment</label><textarea class="form-control" name='reply'></textarea>
			</div>
			<div class="form-group">
				<label>Writer</label><input class="form-control" name='replyer' value='replyer' readonly="readonly">
			</div>
			<div class="form-group">
				<label>Comment Date</label><input class="form-control" name='replyDate'>
			</div>
			<input type="hidden" name="point" id="point" />
		</div>
		<div class="actions">
			<button id='modalModBtn' type='button' class='ui inverted primary button'>Modify</button>
			<button id='modalRemoveBtn' type='button' class='ui inverted negative button'>Remove</button>
			<button id='modalRegisterBtn' type='button' class='ui inverted positive button'>Register</button>
			<button id='modalCloseBtn' type='button' class='ui inverted button'>Close</button>
		</div>
  			
  </div>	

<!-- Modal 끝 -->
					
<%@ include file="../includes/footer.jsp" %>