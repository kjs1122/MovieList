<%@page import="util.DBmanager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	DBmanager d = new DBmanager();
	d.getConnection();
	out.print("성공");
 %>
</body>
</html>