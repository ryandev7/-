<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.kh.member.model.vo.Member" %>
<%
	String userName =(String)request.getAttribute("userName");
	
	String successMsg = (String)request.getAttribute("successMsg");

	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 성공</title>
<meta http-equiv="refresh" content="2; url=<%=contextPath%>">
</head>
<style>
	img{
	  width : 300px;
	  height: 300px;
	  display: block;
	  margin-left: auto;
	  margin-right: auto;
	}
	
	div{
	  text-align: center;
	}
</style>
<body>
	<div>
	
		<img src='http://drive.google.com/uc?export=view&id=1OTqofLgms8T10JJIOAHHWaqTr84ZC6mu'>
		<h1 align="center"><%= userName %><%= successMsg %></h1>	

	</div>
</body>
</html>