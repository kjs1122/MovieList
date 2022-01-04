<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/movie.css">
</head>
<body>
<div id="wrap" align="center">
	<h1> 리스트 </h1>
	<table align="center">
		<div id="navi">
		<a href="insertMovie.do">정보등록</a>
		</div>		
			<th>포스터</th>
			<th>제목</th>
			<th>감독</th>
			<th>배우</th>
			<th>가격</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="movie" items="${movieList}">
			<tr>
				<td align="center">
					
						<a href="fileDown.do?fileName=${movie.filename}">
							<img src="images/${movie.origfilename}" id="posterImg">
						</a>
					
				</td>
				<td align="center">${movie.title}</td>
				<td align="center">${movie.director}</td>
				<td align="center">${movie.actor}</td>
				<td align="center">${movie.price} 원</td>
				<td align="center"><a href="updateMovie.do?code=${movie.code}">정보 수정</a></td>
				<td align="center"><a href="deleteMovie.do?code=${movie.code}">정보 삭제</a></td>
			</tr>
		</c:forEach>	
		
	</table>
	</div>
</body>
</html>