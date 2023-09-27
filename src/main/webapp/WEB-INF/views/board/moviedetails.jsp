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

.c_point {
	background-color: white;
}
</style>

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		var operForm = $("#operForm");
		
		$('#readonlyRate')
		  .rating('disable')
		;
		
		$('button[data-oper="modify"]').on("click", function(e){
			operForm.attr("action", "/movie/modify").submit();
		});
		
		$('button[data-oper="list"]').on("click", function(e){
			operForm.find("#m_num").remove();
			operForm.attr("action", "/movie/list").submit();
		});
		
		var mi_numValue = '<c:out value="${movie.mi_num}"/>';
		var replyComment = $(".comment");
		
		showList(1);
		
		function showList(page){
			replyService.getList({mi_num:mi_numValue,page:page||1}, function(replyCnt, list){
				console.log("list: " + list);
				
				if(page == -1){
					pageNum = Math.ceil(replyCnt/10.0);
					showList(pageNum);
					return;
				}
				
				var str="";
				if(list == null || list.length == 0){
					replyComment.html("");
					return;
				}
				for(var i=0, len=list.length||0; i<len; i++) {
					str+="<div class='mv-user-review-item'><div class='user-infor'><div>";
					str+="<h3>작성자: " + list[i].m_num +"</h3>";
					str+="<div><div class='ui star rating' data-rating='" + list[i].mi_total_point + "' data-max-rating='5'></div></div>"
					str+="<p class='time'>등록일: "+replyService.displayTime(list[i].c_regdate)+"</p>";
					str+="</div></div>";
					str+="<p>" + list[i].comment + "</p>";
					str+="</div>"
				}
				replyComment.html(str);
				
				showReplyPage(replyCnt);
			});
		}
		
		var modal = $(".modal");
		var modalInputReply = modal.find("textarea[name='reply']");
		var modalInputReplyer = modal.find("input[name='replyer']");
		var modalInputReplyDate = modal.find("input[name='replyDate']");
		var modalInputRate = modal.find("div[name='c_point']");
		
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
			//modalInputReplyer.removeAttr("readonly");
			modal.modal("show");
		});
		
		$("#modalCloseBtn").on("click", function(e){
			modal.modal("hide");
		});
		
	    
	  $(document).ajaxSend(function(e, xhr, options) { 
	    xhr.setRequestHeader(csrfHeaderName, csrfTokenValue); 
	  });

		
		modalRegisterBtn.on("click", function(e){
			var reply = {
				reply : modalInputReply.val(),
				replyer : modalInputReplyer.val(),
				mi_num : mi_numValue
				c_point: modalInputRate
			}
			
			replyService.add(reply, function(result){
				alert(result);
				modal.find("input").val("");
				modal.modal("hide");
				showList(-1);
			});			
		});
		
		replyComment.on("click", "li", function(e){
			var c_num = $(this).data("c_num");
			
			replyService.get(c_num, function(reply){
				modalInputReply.val(reply.reply);
				modalInputReplyDate.closest("div").show();
				//modalInputReplyer.val(reply.replyer).attr("readonly", "readonly");
				modalInputReplyer.val(reply.replyer);
				modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
				modal.data("c_num", reply.c_num);
				modal.find("button[id != 'modalCloseBtn']").hide();
				modalModBtn.show();
				modalRemoveBtn.show();
				
				modal.modal("show");
			});
		});
		
		modalModBtn.on("click", function(e){
			var originalReplyer = modalInputReplyer.val();
			
			var reply = {c_num:modal.data("c_num"),
										reply: modalInputReply.val(),
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
				showList(pageNum);
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
				showList(pageNum);				
			});
		});
		
		var pageNum = 1;
		var replyPageFooter = $(".topbar-filter");
		
		function showReplyPage(replyCnt){
			var endNum = Math.ceil(pageNum / 10.0) * 10;
			var startNum = endNum - 9;
			
			var prev = startNum != 1;
			var next = false;
			
			if(endNum * 10 >= replyCnt) {
				endNum = Math.ceil(replyCnt / 10.0);
			} else {
				next = true;
			}
			
			var str = "<div class='pagination2'>";
			
			if(prev){
				str+="<a href='"+(startNum-1)+"'>Previous</a>";
			}
			
			for(var i=startNum; i<=endNum; i++){
				var active = pageNum == i ? " active" : "";
				str+="<a class='page-link' href='"+i+"'>"+i+"</a>";
			}
			
			if(next){
				str+="<a class='page-link' href='"+(endNum+1)+"'>Next</a>";
			}
			
			str+="</div>";
			
			console.log(str);
			
			replyPageFooter.html(str);
		}
		
		replyPageFooter.on("click", "div a", function(e){
			e.preventDefault();
			console.log("page click");
			
			var targetPageNum = $(this).attr("href");
			console.log("targetPageNum: " + targetPageNum);
			pageNum = targetPageNum;
			showList(pageNum);	
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
					<img src="<c:out value="${movie.mi_thumbnail}"/>" alt="">
					<div class="movie-btn">	
						<div class="btn-transform transform-vertical">
							<div><a href="#" class="item item-1 yellowbtn"> <i class="ion-card"></i> Buy ticket</a></div>
							<div><a href="#" class="item item-2 yellowbtn"><i class="ion-card"></i></a></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-8 col-sm-12 col-xs-12">
				<div class="movie-single-ct main-content">
				<br>
					<h1 class="bd-hd"><c:out value="${movie.mi_title}"/> <span>2015</span></h1>
					<div class="social-btn">
						<sec:authorize access="hasAuthority('3')">
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
							<p><span><c:out value="${movie.mi_total_point}" /></span> /5<br>
							</p>
						</div>
						<div class="rate-star">
							<p>Rate This Movie:  </p>
							<div class="ui star rating" data-rating="<c:out value="movie.mi_total_point"/>" data-max-rating="5" id="readonlyRate"></div>
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
						            		<p>Tony Stark creates the Ultron Program to protect the world, but when the peacekeeping program becomes hostile, The Avengers go into action to try and defeat a virtually impossible enemy together. Earth's mightiest heroes must come together once again to protect the world from global extinction.</p>
											<div class="title-hd-sm">
												<h4>User reviews</h4>
											</div>
											<!-- movie user review -->
											<div class="mv-user-review-item">
												<h3>작성자: hawaiipierson</h3>
												<div class="no-star">
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star"></i>
													<i class="ion-android-star last"></i>
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
						            			<p><c:out value="${movie.g_num}"/></p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>분류등급:</h6>
						            			<p><c:out value="${movie.md_grade}"/></p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>개봉여부:</h6>
						            			<p><c:out value="${movie.mi_contition}"/></p>
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
						            	<div class="rv-hd">
						            		<div class="div">
							            		<h3>Related Movies To</h3>
						       	 				<h2><c:out value="${movie.mi_title}"/></h2>
							            	</div>
							            	<sec:authorize access="isAuthenticated()">
							            	<button id="addComment" class="redbtn">Add Comment</button>
							            	</sec:authorize>
						            	</div>
						            	<div class="comment">
						            	<!-- 코멘트 리스트 공간  -->
						            	
						            	<!-- 위치 확인용 더미 시작 -->
											<div class="mv-user-review-item">
												<div class="user-infor">
													<div>
														<h3>작성자: hawaiipierson</h3>
														<div class="no-star">
															<i class="ion-android-star"></i>
															<i class="ion-android-star"></i>
															<i class="ion-android-star"></i>
															<i class="ion-android-star"></i>
															<i class="ion-android-star"></i>
															<i class="ion-android-star"></i>
															<i class="ion-android-star"></i>
															<i class="ion-android-star"></i>
															<i class="ion-android-star"></i>
															<i class="ion-android-star last"></i>
														</div>
														<p class="time">
															등록일: 2016년 12월 17일
														</p>
													</div>
												</div>
												<p>This is by far one of my favorite movies from the MCU. The introduction of new Characters both good and bad also makes the movie more exciting. giving the characters more of a back story can also help audiences relate more to different characters better, and it connects a bond between the audience and actors or characters. Having seen the movie three times does not bother me here as it is as thrilling and exciting every time I am watching it. In other words, the movie is by far better than previous movies (and I do love everything Marvel), the plotting is splendid (they really do out do themselves in each film, there are no problems watching it more than once.</p>
											</div>
										<!-- 위치 확인용 더미 끝 -->	
											
										</div>
										<div class="topbar-filter">
										<!-- 페이지 버튼  -->
										</div>	
										</div>
										<form id='operForm' action="/movie/modify" method="get">
					 						<input type='hidden' id='m_num' name='m_num' value='<c:out value="${movie.m_num}"/>'>
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
				<label>Rate (max 5)</label><p><div name="c_point" id="c_point" class="ui star rating" data-rating="5" data-max-rating="5"></div>
			</div>	
			<div class="form-group">
				<label>Comment</label><textarea class="form-control" name='reply' value='New Reply!!!'></textarea>
			</div>
			<div class="form-group">
				<label>Writer</label><input class="form-control" name='replyer' value='replyer' readonly="readonly">
			</div>
			<div class="form-group">
				<label>Comment Date</label><input class="form-control" name='replyDate' value=''>
			</div>
		</div>
		<div class="modal-footer">
			<button id='modalModBtn' type='button' class='ui inverted primary button'>Modify</button>
			<button id='modalRemoveBtn' type='button' class='ui inverted negative button'>Remove</button>
			<button id='modalRegisterBtn' type='button' class='ui inverted positive button'>Register</button>
			<button id='modalCloseBtn' type='button' class='ui inverted button'>Close</button>
		</div>
  			
  </div>	

<!-- Modal 끝 -->
					
<%@ include file="../includes/footer.jsp" %>