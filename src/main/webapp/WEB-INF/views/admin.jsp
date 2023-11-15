<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
     
<%@ include file="includes/header.jsp"%>

<<style>
textarea {
	width:500px;
	height:210px;
}
</style>
  
<div class="hero user-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1>Admin Page</h1>
					<ul class="breadcumb">
						<li class="active"><a href="/movie">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span>admin</li>
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
				<li class="active"><a href="#movieRegist">영화등록</a></li>
				<li><a href="#userManagement"> 회원관리</a></li>                    
			</ul>
							
			<div class="col-md-9 col-sm-12 col-xs-12">
				<div class="form-style-1 user-pro" action="#">
					<div class="tab-content">
						<form action="/movie/upload/insertMovie" class="tab active" method="post" id="movieRegist" enctype="multipart/form-data">
							<h4>01. 영화등록 </h4>
							<div class="row">
								<div class="col-md-6 form-it">
									<label>영화제목</label>
									<input type="text" name="mi_title" required="required">
								</div>
								<div class="col-md-6 form-it">
									<label>개봉상태</label>
									<select name="mi_condition">
									  <option value="0">상영중</option>
									  <option value="1">개봉예정</option>
									</select>
								</div>
								<div class="col-md-6 form-it">
									<label>감독</label>
									<input type="text" name="md_director">
								</div>
								<div class="col-md-6 form-it">
									<label>출연진</label>
									<input type="text" name="md_actor">
								</div>
								<div class="col-md-6 form-it">
									<label>소개</label>
									<textarea name="md_text" rows="100"></textarea>
								</div>
								<div class="col-md-6 form-it">
									<label>장르</label>
									<select name="g_num" required>
									  <option value="0">장르선택</option>
									  <option value="1">드라마</option>
									  <option value="2">로맨스</option>
									  <option value="3">애니메이션</option>
									  <option value="4">액션</option>
									  <option value="5">어드벤쳐</option>
									  <option value="6">호러</option>
									  <option value="7">스릴러</option>
									  <option value="8">범죄</option>
									  <option value="9">멜로</option>
									</select>
								</div>
								<div class="col-md-6 form-it">
									<label>런타임</label>
									<input type="text" name="md_runtime">
								</div>
								<div class="col-md-6 form-it">
									<label>분류등급</label>
									<input type="text" name="md_grade">
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 form-it">
									<!-- <input type="file" name="mi_thumbnail" accept="image/gif, image/jpeg, image/png" multiple> -->
									<input type="file" name="fileName" accept="image/gif, image/jpeg, image/png" multiple>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-2">
									<input class="submit" type="submit" value="save">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								</div>
							</div>	
						</form>
				
						<form class="userManagement tab" id="userManagement" action="/movie/upload/memberSearch" method="post">
						<h4>02. 회원관리</h4>
						<div class="top-search">
	    						<input type="text" id="keyword" name="keyword" placeholder="회원 닉네임 or 아이디를 입력하세요.">
	    				</div>
							<c:forEach items="${member}" var="member">
								<div class="row">
									<div class="col-md-2 form-it">
										<label>회원 번호</label>
										<input type="text" name="m_num" value="<c:out value="${member.m_num}"/>" readonly="readonly">
									</div>
									<div class="col-md-2 form-it">
										<label>회원 아이디</label>
										<input type="text" name="m_id" value="<c:out value="${member.m_id}"/>" readonly="readonly">
									</div>
									<div class="col-md-2 form-it">
										<label>닉네임</label>
										<input type="text" name="m_nick" value="<c:out value="${member.m_nick}"/>">
									</div>
									<div class="col-md-2 form-it">
										<label>회원등급</label>
										<select name="m_lev" value="<c:out value="${member.m_lev}"/>">
											<option value="1" <c:if test="${member.m_lev eq 1}">selected</c:if>>member</option>
											<option value="2" <c:if test="${member.m_lev eq 2}">selected</c:if>>manager</option>
											<option value="3" <c:if test="${member.m_lev eq 3}">selected</c:if>>admin</option>
										</select>
									</div>
									<div class="col-md-2">
										<label>회원변경</label>
										<input class="submit modifyUser" type="submit" value="변경" id="modifyUser" onclick="javascript: form.action='/movie/upload/modify';">
									</div>
									<div class="col-md-2">
										<label>회원삭제</label>
										<input class="submit deleteUser" type="submit" value="삭제" id="deleteUser" onclick="javascript: form.action='/movie/upload/remove';">
									</div>
								</div>
							</c:forEach>
							<%-- <table>
							<tr>
								<td>회원 번호</td>
								<td>회원 아이디</td>
								<td>닉네임</td>
								<td>회원 등급</td>
								<td>회원 변경</td>
								<td>회원 삭제</td>
							</tr>
							<c:forEach items="${member}" var="member">
								<tr>
									<td><input type="text" name="m_num" value="<c:out value="${member.m_num}"/>" readonly="readonly"></td>
									<td><input type="text" name="m_id" value="<c:out value="${member.m_id}"/>" readonly="readonly"></td>
									<td><input type="text" name="m_nick" value="<c:out value="${member.m_nick}"/>"></td>
									<td><input type="text" name="m_lev" value="<c:out value="${member.m_lev}"/>"></td>
									<td><input class="submit modifyUser" type="submit" value="변경" id="modifyUser" onclick="javascript: form.action='/movie/upload/modify';"></td>
									<td><input class="submit deleteUser" type="submit" value="삭제" id="deleteUser" onclick="javascript: form.action='/movie/upload/remove';"></td>
								</tr>
							</c:forEach>
						</table> --%>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
		</div>
<%@ include file="includes/footer.jsp"%>
