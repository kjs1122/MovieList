<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/movie.css">
</head>
<body>
	<div id="wrap">
		<h1 align="center">정보 수정</h1>
		<form method="post" enctype="multipart/form-data" name="frm">
		<input type="hidden" value="${mVo.code}" name="code">
		<input type="hidden" value="${mVo.poster}" name="posterNull">
			<table>
				<tr>
					<td><c:choose>
							<c:when test="${empty mVo.poster}">
								<img alt="noImg" src="images/noimage.gif">
							</c:when>
							<c:otherwise>
								<img alt="img" src="images/${mVo.poster}">
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<table>
							<tr>
								<th>제 목</th>
								<td><input type="text" name="title" size="80" value="${mVo.title}"></td>
							</tr>
							<tr>
								<th>가 격</th>
								<td><input type="text" name="price" size="80" value="${mVo.price}"></td>
							</tr>
							<tr>
								<th>감 독</th>
								<td><input type="text" name="director" size="80" value="${mVo.director}"></td>
							</tr>
							<tr>
								<th>배 우</th>
								<td><input type="text" name="actor" size="80" value="${mVo.actor}"></td>
							</tr>
							<tr>

								<th>설 명</th>
								<td><textarea rows="10" cols="100" name="synopsis" >${mVo.synopsis}</textarea></td>


							</tr>
							<tr>
								<th>사 진</th>
								<td><input type="file" name="poster"></td>
							</tr>

						</table>
					</td>
				</tr>
			</table>
			<br>
			<div id="button">
				<input type="submit" value="확인"> 
				<input type="reset" value="취소"> 
				<input type="button" value="목록" onclick="location.href='movieList.do'">
			</div>
		</form>
	</div>

</body>
</html>