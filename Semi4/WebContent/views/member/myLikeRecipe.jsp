<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList,com.kh.common.model.vo.Category, com.kh.recipan.model.vo.Recipan, com.kh.common.model.vo.PageInfo" %> 
<%
	ArrayList<Recipan> list = (ArrayList<Recipan>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내가 좋아요한 레시피</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="./sidebar.css">
</head>
<style>


	.menu {
		    display: block;
		    overflow: hidden;
		    width: 200px;
		    background-color:#292344;
		    color: #fff;
		    transition: all 0.5s ease;
		    border-radius: 20px;
		    padding: 10px;
		    box-sizing: border-box;
		    margin-left : 400px;
		    float:left;
		    margin-top : 200px;
		}
		.menu ul {
		    list-style: none;
		    margin: 0;
		    padding: 0;
		}
		.menu a {
		    display: block;
		    height: 60px;
		    padding: 8px;
		    cursor: pointer;
		    color: #fff;
		    text-decoration: none;
		}
		
		.menu > label {
			display: block;
		    height: 60px;
		    padding: 10px;
		    color: #fff;
		    text-decoration: none;
		}
		
		.menu a:hover {
		    color: #000;
		}
		.menu ul li:hover {
		    background-color: #ed6f65;
		    color: #000;
		    border-radius: 10px;
		}
		.menu div {
		    position: absolute;
		    left: 450px;
		    line-height: 2.4;
		    font-size: 1em;
		    font-family: 'Noto Sans KR';
		    padding: 0 0 0 20px;
		}
		
		#expand-menu {
		    display: none;
		}
		#expand-menu:checked ~ ul {
		    display: block;
		    height: auto;
		}
		
		.menu ::before {
		    font-family: 'Material Icons';
		    font-size: 1.5em;
		    float: left;
		    clear: left;
		}
		.menu label::before{ content: '\e5d2'; }
		.menu li:nth-child(1) a::before{ content: '\f02e'; }
		.menu li:nth-child(2) a::before{ content: '\e8d6'; }
		.menu li:nth-child(3) a::before{ content: '\e88a'; }
		.menu li:nth-child(4) a::before{ content: '\e8b8'; }
		.menu li:nth-child(5) a::before{ content: '\e87d'; }
		.menu li:nth-child(6) a::before{ content: '\e8cc'; }
		
		@media screen and (max-width:1023px) {
		    .menu {
		        width: 60px;
		    }
		}
		@media screen and (max-width:560px) {
		    .menu #expand-menu:not(:checked) ~ ul {
		        display: none;
		    }
		}
	
	.outer{
        width: 1000px;
		height: 600px;
		margin : auto;
		margin-top : 50px;
		margin-left : 500px;
    }
    
    
    .list-area{
    	width : 70%;
        border-top : 1px solid rgb(161, 161, 161);
        border-collapse : collapse;
        text-align: center;
        margin : auto;
        
    }
    
    .list-area th{
    	height : 50px;
    	background-color : #efe4d7;
    	border-bottom : 1px solid rgb(161, 161, 161);
    }
    
    .list-area td {
    	height : 40px;
    	border-bottom : 1px solid rgb(161, 161, 161);
    }

    .list-area>tbody>tr:hover{
        cursor: pointer;
    }
</style>
<body>
	<%@include file="../common/menubar.jsp" %>
	<br><br>
	<div class="menu">
	
	    <label for="expand-menu"><div>마이페이지</div></label><input type="checkbox" id="expand-menu" name="expand-menu">
	    <ul>
       		<li><a href="<%=contextPath%>/updateForm.me" class="item"><div>개인정보 수정</div></a></li>
	           <li><%if(loginUser !=null ) { %><a href="<%=contextPath%>/myReview.me?userId=<%=loginUser.getUserId() %>&cpage=1" class="item"><div>나의 리뷰</div></a><% } %></li>
	           <li><%if(loginUser !=null ) { %><a href="<%=contextPath%>/myProductQna.me?userId=<%=loginUser.getUserId() %>&cpage=1" class="item"><div>나의 상품 문의</div></a><% } %></li>
	           <li><a href="<%= contextPath %>/userList.pq?cpage=1&userId=<%=loginUser.getUserId()%>"><div>1대 1문의</div></a></li>
	           <li><%if(loginUser !=null ) { %><a href="<%=contextPath%>/myLikeRecipe.me?userId=<%=loginUser.getUserId() %>&cpage=1" class="item"><div>좋아요한 레시피</div></a><% } %></li>  
	           <li><a href="<%=contextPath%>/purchaseProduct.me" class="item"><div>구매내역 조회</div></a></li> 
	    </ul>
	</div>
	
	<div class="outer">
		<br>
		<h2>내가 좋아요한 레시피</h2>
		<hr>
		<br><br>
		<div class="list-table">
			<table class="list-area">
				<thead>
					<tr>
						<th>레시피/레시판</th>
						<th>제목</th>
						<th>카테고리</th>
						<th>작성 날짜</th>
					</tr>
				</thead>
				<tbody>
					<% if(list.isEmpty()) { %> <!-- 조회글 없음 -->
					<tr>
						<td colspan="4">좋아요한 레시피가 없습니다.</td>					
					</tr>
					<% } else {%>
						<!-- 반복 : list에 있는 값을 순차적으로 접근해서 뽑아오기 -->
						<% for(Recipan r : list) { %>
							<tr>
								<td>레시판</td>						
								<td><%= r.getPeTitle() %></td>						
								<td><%= r.getNatValue()%></td>		
								<td><%= r.getPeCreateDate() %></td>				
							</tr>
						<% } %>
					<% } %>
				</tbody>
			</table>
			
			
			<script>

			$(function(){
	
				$(".list-area>tbody>tr").click(function(){

					var bno = $(this).children().eq(0).text();

					location.href="<%= contextPath %>/detail.bo?bno=" + bno;
				})
	
			})
	
			</script>
	
			<br><br>
	
			<div align="center" class="paging-area">
	
				<% if(currentPage != 1) {%> 
					<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/myLikeRecipe.me?userId=<%=loginUser.getUserId() %>&cpage=<%= currentPage - 1 %>'"> &lt; </button>
				<% } %>
	
				<% for(int i = startPage; i <= endPage; i++) { %>
					<% if(i != currentPage) {%>
						<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/myLikeRecipe.me?userId=<%=loginUser.getUserId() %>&cpage=<%= i %>'"><%= i %></button>
					<% } else { %>
						<button class="btn btn-sm btn-primary" disabled><%= i %></button>
					<% } %>
				<% } %>
	
				<% if(currentPage != maxPage) {%>
					<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/myLikeRecipe.me?userId=<%=loginUser.getUserId() %>&cpage=<%= currentPage + 1 %>'"> &gt; </button>
				<% } %>
				
			</div>
		</div>
	</div>
</body>
</html>