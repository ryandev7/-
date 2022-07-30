<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.personalQna.model.vo.PersonalQna" %>
<%
	PersonalQna p = (PersonalQna)request.getAttribute("p");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1대1 문의사항 답변</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
	.outer{
		background-color: lightgrey;
		color: white;
		width: 1000px;
		height: 800px;
		margin: auto;
		margin-top: 50px;
	}
	#enroll-form>table{
		 border: 1px solid white;
		}
	#enroll-form input, #enroll-form textarea{
		width: 100%;
		box-sizing: border-box;
	}
	.btn-default
 
 {
 background-color:rgb(89, 108, 156) ;
 color:#FFF;
 border-color: #2F3E48;
 border: none;
 }
  
 .btn-default:hover, .btn-default:focus, .btn-default:active, .btn-default.active, .open .dropdown-toggle.btn-default {
  
 background-color: rgb(41, 35, 68);
 color:#FFF;
 border-color: #31347B;
 border: none;
 text-decoration-line: none;
  
 }
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<div class="outer">
		<br>
		<h2 align="center" style="color:rgb(41, 35, 68);">1대1 문의사항 답변</h2>
		<br>

		<form action="<%= contextPath %>/insert.pqa" id="enroll-form" method="post">
			<input type="hidden" name="qno" value="<%= p.getPqnaNo() %>">
		
			<table align="center">
				<tr>
					<th width="80"><h6 align="center" style="color:rgb(41, 35, 68);">문의제목</h6></th>
					<td width="700"><input type="text" class="form-control" value="<%= p.getPqnaTitle() %>" required id="title" name="pqnatitle" readonly></td>
				</tr>
				<tr>
					<th width="80"><h6 align="center" style="color:rgb(41, 35, 68);">문의내용</h6></th>
					<td width="700"><textarea class="form-control"  style="resize:none" required rows="5" name="pqnacontent" readonly><%= p.getPqnaContent() %></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="container">
							  <div class="form-group">
								<label for="content"></label>
								<h4 style="color:rgb(41, 35, 68);">답변작성</h4><textarea class="form-control" rows="10" id="comment" style="resize:none" name="pqnacontentAs"></textarea>
							  </div>
							</div>
						</td>
					</tr>
				</table>
				<br><br>
				
				<div align="center">
				<button type="submit" class="btn-default btn-lg">제출하기</button>&nbsp;
				<button type="button" onclick="history.back();" class="btn btn-lg btn-secondary">뒤로가기</button>
				<!-- history.back() 이전페이지로 돌아가게 해주는 함수-->
			</div>

		</form>
	
	</div>
</body>
</html>