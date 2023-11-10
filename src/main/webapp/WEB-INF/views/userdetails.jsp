<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="includes/header.jsp"%>

<script type="text/javascript">
/* function remove(m_id, m_pw, callback, errorfn) {
	$.ajax({
		type : 'delete',
		url : '/삭제url /' + m_id,
		data: JSON.stringify({rno:rno, replyer:replyer}),
		contentType: "application/json; charset=utf-8",
		success : function(deleteResult, status, xhr) {
			if(callback) {
				callback(deleteResult);
			}
		},
		error : function(xhr, status, err) {
			if(errorfn) {
				errorfn(err);
			}
		}
	});	
}
 */
</script>
<div class="hero user-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1>${member.m_id}'s profile</h1>
					<ul class="breadcumb">
						<li class="active"><a href="/movie">Home</a></li>
						<li><span class="ion-ios-arrow-right"></span>Profile</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="buster-light">
	<div class="page-single">
		<div class="container">
			<div class="tabs">
				<ul class="tab-links tabs-mv">
					<li class="active"><a href="#userinfo">프로필 정보</a></li>
					<li><a href="#userpassword">비밀번호 변경</a></li>
				</ul>
				<div class="col-md-9 col-sm-12 col-xs-12">
					<div class="form-style-1 user-pro" action="#">
					<div class="tab-content">
						<form action="/movie/member/memberInfo" class="tab active" id="userinfo" method="post">
							<h4>01. 프로필 정보</h4>
							<div class="row">
								<div class="col-md-6 form-it">
									<label>ID</label> 
									<input type="text" name="m_id" readonly="readonly" value='<c:out value="${member.m_id}"/>'>
								</div>
								<div class="col-md-6 form-it">
									<label>이메일</label> <input type="text" name="m_mail" value='<c:out value="${member.m_mail}"/>'>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 form-it">
									<label>전화번호</label> 
									<input type="text" name="m_tel" value='<c:out value="${member.m_tel}"/>'>
								</div>
								<div class="col-md-6 form-it">
								
									<label>선호장르</label> 
									<select name="g_num" value='<c:out value="${member.g_num}"/>'>
										<option value="1" <c:if test="${member.g_num eq 1}">selected</c:if>>드라마</option>
										<option value="2" <c:if test="${member.g_num eq 2}">selected</c:if>>로맨스</option>
										<option value="3" <c:if test="${member.g_num eq 3}">selected</c:if>>애니메이션</option>
										<option value="4" <c:if test="${member.g_num eq 4}">selected</c:if>>액션</option>
										<option value="5" <c:if test="${member.g_num eq 5}">selected</c:if>>어드벤쳐</option>
										<option value="6" <c:if test="${member.g_num eq 6}">selected</c:if>>호러</option>
										<option value="7" <c:if test="${member.g_num eq 7}">selected</c:if>>스릴러</option>
										<option value="8" <c:if test="${member.g_num eq 8}">selected</c:if>>범죄</option>
										<option value="9" <c:if test="${member.g_num eq 9}">selected</c:if>>멜로</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 form-it">
									<label>닉네임</label> 
									<input type="text" name="m_nick" value='<c:out value="${member.m_nick}"/>'>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<input class="submit" type="submit" value="save">
								</div>
							</div>
						</form>
						
						<form action="/movie/member/memberPassword" class="userpassword tab" id="userpassword" method="post">
							
							<h4>02. 비밀번호 변경</h4>
							<div class="col-md-6 form-it">
									<input type="hidden" name="m_id" readonly="readonly" value='<c:out value="${member.m_id}"/>'>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 form-it">
									<label>이전 비밀번호</label> 
									<input type="text" name="m_pwx" placeholder="**********">
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 form-it">
									<label>새 비밀번호</label> <input type="text" name="m_pw" placeholder="***************">
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 form-it">
									<label>새 비밀번호 확인</label> <input type="text" name="rm_pw"
										placeholder="*************** ">
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<input class="submit" type="submit" value="save">
								</div>
							</div>
						</form>
					</div>
					</div>
					<div class="form-style-1 user-pro">
						<div class="row">
							<div>
								<form action="/" method="post">
									<input class="submit" type="submit" value="회원 탈퇴">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">정말 탈퇴하시겠습니까?</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>비밀번호 입력</label> <input class="form-control" name='reply'
						value='New Reply!!!' name="m_pw">
				</div>
			</div>
			<sec:authorize access=""></sec:authorize>
			<div class="modal-footer">
				<button id='modalRemoveBtn' type='button' class='btn btn-danger'>Remove</button>
			</div>

		</div>
	</div>
</div>
<%@ include file="includes/footer.jsp"%>