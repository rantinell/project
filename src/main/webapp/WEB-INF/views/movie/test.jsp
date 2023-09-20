<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<div>
			<h2>test</h2>

			<c:choose>
				<c:when test="${rank != null}">
					<table>
						<c:forEach items="${rank}" var="movie">
							<tr>
								<td>장르번호 : <c:out value="${movie.g_num}" /></td>
								<td>장르 : <c:out value="${movie.genre}" /></td>
								<td>세부넘버 : <c:out value="${movie.md_num}" /></td>
								<td>감독 : <c:out value="${movie.md_director}" /></td>
								<td>배우 : <c:out value="${movie.md_actor}" /></td>
								<td>댓글 : <c:out value="${movie.md_text}" /></td>
								<td>상영시간 : <c:out value="${movie.md_runtime}" /></td>
								<td>등급 : <c:out value="${movie.md_grade}" /></td>
								<td>정보번호 : <c:out value="${movie.mi_num}" /></td>
								<td>제목 : <c:out value="${movie.mi_title}" /></td>
								<td>평점 : <c:out value="${movie.mi_total_point}" /></td>
								<td>포스터 : <c:out value="${movie.mi_thumbnail}" /></td>
								<td>등록일 : <fmt:formatDate pattern="yyyy-MM-dd"
										value="${movie.mi_regDate}" /></td>
								<td>수정일 : <fmt:formatDate pattern="yyyy-MM-dd"
										value="${movie.mi_updatedate}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:when test="${expected != null}">
					<table>
						<c:forEach items="${expected}" var="movie">
							<tr>
								<td>장르번호 : <c:out value="${movie.g_num}" /></td>
								<td>장르 : <c:out value="${movie.genre}" /></td>
								<td>세부넘버 : <c:out value="${movie.md_num}" /></td>
								<td>감독 : <c:out value="${movie.md_director}" /></td>
								<td>배우 : <c:out value="${movie.md_actor}" /></td>
								<td>댓글 : <c:out value="${movie.md_text}" /></td>
								<td>상영시간 : <c:out value="${movie.md_runtime}" /></td>
								<td>등급 : <c:out value="${movie.md_grade}" /></td>
								<td>정보번호 : <c:out value="${movie.mi_num}" /></td>
								<td>제목 : <c:out value="${movie.mi_title}" /></td>
								<td>평점 : <c:out value="${movie.mi_total_point}" /></td>
								<td>포스터 : <c:out value="${movie.mi_thumbnail}" /></td>
								<td>등록일 : <fmt:formatDate pattern="yyyy-MM-dd"
										value="${movie.mi_regDate}" /></td>
								<td>수정일 : <fmt:formatDate pattern="yyyy-MM-dd"
										value="${movie.mi_updatedate}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:when test="${recommend != null}">
					<table>
						<c:forEach items="${recommend}" var="movie">
							<tr>
								<td>장르번호 : <c:out value="${movie.g_num}" /></td>
								<td>장르 : <c:out value="${movie.genre}" /></td>
								<td>세부넘버 : <c:out value="${movie.md_num}" /></td>
								<td>감독 : <c:out value="${movie.md_director}" /></td>
								<td>배우 : <c:out value="${movie.md_actor}" /></td>
								<td>댓글 : <c:out value="${movie.md_text}" /></td>
								<td>상영시간 : <c:out value="${movie.md_runtime}" /></td>
								<td>등급 : <c:out value="${movie.md_grade}" /></td>
								<td>정보번호 : <c:out value="${movie.mi_num}" /></td>
								<td>제목 : <c:out value="${movie.mi_title}" /></td>
								<td>평점 : <c:out value="${movie.mi_total_point}" /></td>
								<td>포스터 : <c:out value="${movie.mi_thumbnail}" /></td>
								<td>등록일 : <fmt:formatDate pattern="yyyy-MM-dd"
										value="${movie.mi_regDate}" /></td>
								<td>수정일 : <fmt:formatDate pattern="yyyy-MM-dd"
										value="${movie.mi_updatedate}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
			</c:choose>
		</div>
	</form>
</body>
</html>