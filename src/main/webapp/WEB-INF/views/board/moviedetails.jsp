<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>
<style>
  .col-md-8 {
    width: 66.66666667% !important;
  }
</style>

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		var operForm = $("#operForm");
		
		$('button[data-oper="modify"]').on("click", function(e){
			operForm.attr("action", "/movie/modify").submit();
		});
		
		$('button[data-oper="list"]').on("click", function(e){
			operForm.find("#m_num").remove();
			operForm.attr("action", "/movie/list").submit();
		});
		
		var bnoValue = '<c:out value="${movie.m_num}"/>';
		var replyComment = $(".comment");
		
		showList(1);
		
		function showList(page){
			replyService.getList({bno:bnoValue,page:page||1}, function(replyCnt, list){
				console.log("replyCnt: " + replyCnt);
				console.log("list: " + list);
				
				if(page == -1){
					pageNum = Math.ceil(replyCnt/10.0);
					showList(pageNum);
					return;
				}
				
				var str="";
				if(list == null || list.length == 0){
					replyUL.html("");
					return;
				}
				for(var i=0, len=list.length||0; i<len; i++) {
					str+="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
					str+="<div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
					str+="<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
					str+="<p>"+list[i].reply+"</p></li>";
				}
				replyUL.html(str);
				
				showReplyPage(replyCnt);
			});
		}
		
		var modal = $(".modal");
		var modalInputReply = modal.find("input[name='reply']");
		var modalInputReplyer = modal.find("input[name='replyer']");
		var modalInputReplyDate = modal.find("input[name='replyDate']");
		
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
				bno : bnoValue
			}
			
			replyService.add(reply, function(result){
				alert(result);
				modal.find("input").val("");
				modal.modal("hide");
				showList(-1);
			});			
		});
		
		replyUL.on("click", "li", function(e){
			var rno = $(this).data("rno");
			
			replyService.get(rno, function(reply){
				modalInputReply.val(reply.reply);
				modalInputReplyDate.closest("div").show();
				//modalInputReplyer.val(reply.replyer).attr("readonly", "readonly");
				modalInputReplyer.val(reply.replyer);
				modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
				modal.data("rno", reply.rno);
				modal.find("button[id != 'modalCloseBtn']").hide();
				modalModBtn.show();
				modalRemoveBtn.show();
				
				modal.modal("show");
			});
		});
		
		modalModBtn.on("click", function(e){
			var originalReplyer = modalInputReplyer.val();
			
			var reply = {rno:modal.data("rno"),
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
			var rno = modal.data("rno");
			
			console.log("RNO: " + rno);
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
			
			replyService.remove(rno, originalReplyer, function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);				
			});
		});
		
		var pageNum = 1;
		var replyPageFooter = $(".panel-footer");
		
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
			
			var str = "<ul class='pagination pull-right'>";
			
			if(prev){
				str+="<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>Previous</a></li>";
			}
			
			for(var i=startNum; i<=endNum; i++){
				var active = pageNum == i ? " active" : "";
				str+="<li class='page-item"+active+"'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
			}
			
			if(next){
				str+="<li class='page-item'><a class='page-link' href='"+(endNum+1)+"'>Next</a></li>";
			}
			
			str+="</ul>";
			
			console.log(str);
			
			replyPageFooter.html(str);
		}
		
		replyPageFooter.on("click", "li a", function(e){
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
					<img src="/resources/images/uploads/movie-single.jpg" alt="">
					<div class="movie-btn">	
						<div class="btn-transform transform-vertical red">
							<div><a href="#" class="item item-1 redbtn"> <i class="ion-play"></i> Watch Trailer</a></div>
							<div><a href="https://www.youtube.com/embed/o-0hcF97wy0" class="item item-2 redbtn fancybox-media hvr-grow"><i class="ion-play"></i></a></div>
						</div>
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
					<h1 class="bd-hd">Skyfall: Quantum of Spectre <span>2015</span></h1>
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
							<p><span>8.1</span> /10<br>
								<span class="rv">56 Reviews</span>
							</p>
						</div>
						<div class="rate-star">
							<p>Rate This Movie:  </p>
							<i class="ion-ios-star"></i>
							<i class="ion-ios-star"></i>
							<i class="ion-ios-star"></i>
							<i class="ion-ios-star"></i>
							<i class="ion-ios-star"></i>
							<i class="ion-ios-star"></i>
							<i class="ion-ios-star"></i>
							<i class="ion-ios-star"></i>
							<i class="ion-ios-star-outline"></i>
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
						            			<p>Joss Whedon</p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>배우: </h6>
						            			<p>Joss Whedon</p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>장르: </h6>
						            			<p>Action</p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>분류등급:</h6>
						            			<p>15세</p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>개봉여부:</h6>
						            			<p>상영중</p>
						            		</div>
						            		<div class="sb-it">
						            			<h6>런타임:</h6>
						            			<p>141 분</p>
						            		</div>
						            	</div>
						            </div>
						        </div>
						        <div id="reviews" class="tab review">
						           <div class="row">
						            	<div class="rv-hd">
						            		<div class="div">
							            		<h3>Related Movies To</h3>
						       	 				<h2>Skyfall: Quantum of Spectre</h2>
							            	</div>
							            	<button id="addComment" class="redbtn">Write Review</button>
						            	</div>
						            	<c:forEach items="review">
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
										</c:forEach>
										<div class="topbar-filter">
											<div class="pagination2">
												<a class="active" href="#">1</a>
												<a href="#">2</a>
												<a href="#">3</a>
												<a href="#">4</a>
												<a href="#">5</a>
												<a href="#">6</a>
												<a href="#"><i class="ion-arrow-right-b"></i></a>
											</div>
										</div>	
										</div>
										<form id='operForm' action="/movie/modify" method="get">
					 						<input type='hidden' id='m_num' name='m_num' value='<c:out value="${movie.m_num}"/>'>
					         				<input type='hidden' id="md_num" name="md_num" value='<c:out value="${movie.md_num}"/>'/>
					 						<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
					 						<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
					 						<input type='hidden' name="type" value='<c:out value="${cri.type}"/>'/>
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
		
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  	<div class="modal-dialog">
  		<div class="modal-content">
  		
  			<div class="modal-header">
  				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  				<h4 class="modal-title" id="myModalLabel">Reply Modal</h4>
  			</div>
  			<div class="modal-body">
  				<div class="form-group">
  					<label>Reply Date</label><input class="form-control" name='replyDate' value=''>
  				</div>
  				<div class="form-group">
  					<label>Reply</label><input class="form-control" name='reply' value='New Repl!!!'>
  				</div>
  				<div class="form-group">
  					<label>Replyer</label><input class="form-control" name='replyer' value='replyer' readonly="readonly">
  				</div>
  				<div class="form-group">
  					<label>Reply Date</label><input class="form-control" name='replyDate' value=''>
  				</div>
  			</div>
  			<div class="modal-footer">
  				<button id='modalModBtn' type='button' class='btn btn-warning'>Modify</button>
  				<button id='modalRemoveBtn' type='button' class='btn btn-danger'>Remove</button>
  				<button id='modalRegisterBtn' type='button' class='btn btn-primary'>Register</button>
  				<button id='modalCloseBtn' type='button' class='btn btn-default'>Close</button>
  			</div>
  			
  		</div>
  	</div>
  </div>		
		
		
<%@ include file="../includes/footer.jsp" %>