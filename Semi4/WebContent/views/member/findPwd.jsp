<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	String contextPath = request.getContextPath();
	String alertMsg = (String)session.getAttribute("alertMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div align="center">
		<h2>비밀번호 찾기</h2>
	</div>
	<hr>
	
	<div class="login_wrap">
	<form id="idSearch"  name="idSearch-form"  action="<%= contextPath %>/findPwd.me" method="post">
	<br>
	<table  align="center">
		<tr>
			<td>&nbsp;&nbsp;아이디</td>
			<td><input type="text" name="userId" maxlength="50" required class="find_id"></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;이름</td>
			<td><input type="text" name="userName" maxlength="50" required class="find_id"></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;휴대폰</td>
			<td><input type="text" name="phone" maxlength="50" required class="find_id"></td>
			<td></td>
		</tr>
	</table>
	
	<br>
	<hr>
	
	<div align="center" >
		<button type="submit" id="findId_btn">비밀번호찾기</button>
	</div>
	</form>
	
	</div>
	
	
	
</body>
</html>