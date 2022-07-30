<%@page import="com.kh.product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Product p = (Product)request.getAttribute("p"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>리뷰 작성</title>
<style>
	.outer{
        width: 1200px;
        margin: auto;
    }
	.title{

		margin-top: 30px;
		margin-bottom: 10px;
		padding-left: 50%;
		font-size: 25px;
		font-weight: 600;
	}
	input[type="text"]{
		width: 435px;
	}
	#contentInput{
		text-align: top;
	}
	td{
		vertical-align: top;
	}
	.productName{
		text-align: right;
		color: slategray;
	}

</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

	<form id="enroll-form" enctype="multipart/form-data" action="<%= contextPath %>/reviewInsert.bo" method="post">

		<div class="outer">
		
			<div class="title">
				리뷰작성
			</div>
			<input type="hidden" name="userId" value="<%= loginUser.getUserId() %>">
			<input type="hidden" name="prNo" value="<%= p.getPrNo() %>">
			<table align="center">
				<tr>
					<td colspan="2" class="productName"><%= p.getPrName() %></td>
				</tr>
				<tr>
					<td>제목&nbsp;&nbsp;</td>
					<td><input type="text" name="title" id="titleInput" placeholder="제목을 입력해주세요"></td>
				</tr>
				<tr>
					<td>내용&nbsp;&nbsp;</td>
					<td><textarea name="content" id="contentInput" cols="56" rows="10" placeholder="내용을 입력해주세요"></textarea></td>
				</tr>
				<tr>
					<td>첨부파일&nbsp;&nbsp;</td>
					<td><input type="file"name="upfile"></td>
				</tr>
				<tr>
					<td></td>
					<td align="center"><button type="submit">확인</button>&nbsp;&nbsp;<button type="reset">취소</button></td>
				</tr>
			</table>
			<%@ include file="../common/footer.jsp" %>
		</div>
	</form>
	
</body>
</html>