<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.product.model.vo.Review, com.kh.common.model.vo.PageInfo" %> 
<%
	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
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
    <title>관리자페이지</title>
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
	.content{
		width : 900px;
		margin-left : 650px;
		
	}
	tbody>#body:hover{
        cursor: pointer;
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
<body>


<%@ include file="../common/menubar.jsp" %>
	<br><br>
<div class="wrap">
	<div class="menu">
	
	    <label for="expand-menu"><div>관리자페이지</div></label><input type="checkbox" id="expand-menu" name="expand-menu">
	    <ul>
       		<li><a href="<%=contextPath%>/userManage.me?cpage=1" class="item"><div>회원 관리</div></a></li>
	        <li><a href="<%=contextPath%>/salesManage.me" class="item"><div>매출 관리</div></a></li>
       		<li><a href="<%=contextPath %>/list.pq?cpage=1&userId=<%=loginUser.getUserId()%>" class="item"><div>1대1 문의</div></a></li>
	    </ul>
	</div>
	
	<div class="content">
		<br><br><br>
		<h2>회원 목록</h2>
		<hr>
		<br><br>
		
		<table class="table table-sm">
		  	<thead>
					<tr>
						<th>아이디</th>
						<th>이름</th>
						<th>주소</th>
						<th>연락처</th>
						<th>닉네임</th>
						<th>가입일</th>
						<th>회원여부</th>
					</tr>
				</thead>
				<tbody>
					<% if(list.isEmpty()) { %> <!-- 조회글 없음 -->
						<tr>
							<td colspan="9">조회된 회원이 없습니다.</td>					
						</tr>
					<% } else {%>
					
						<% for(Member m : list) { %>
							<tr id="body">
								<td><%= m.getUserId() %></td>	
								<td><%= m.getUserName() %></td>						
								<td><%= m.getAddress() %></td>	
								<td><%= m.getPhone() %></td>						
								<td><%= m.getNickName() %></td>						
								<td><%= m.getCreateDate() %></td>						
								<td><%= m.getStatus() %></td>			
							</tr>
						<% } %>
					<% } %>
				</tbody>
		</table>
		<script>

                        $(function(){
                
                            $("tbody>#body").click(function(){
            
                                var userId = $(this).children().eq(0).text();

                                console.log(userId);
            
                                location.href="<%= contextPath %>/adUpdateForm.me?userId=" + userId;
                            })
                
                        })
                
                    </script>
		
		<br><br>

		<div align="center" class="paging-area">

			<% if(currentPage != 1) {%> <!-- 페이징바에서 <를 담당 -->
				<button class="btn-default btn-sm" onclick="location.href='<%= contextPath %>/userManage.me?cpage=<%= currentPage - 1 %>'"> &lt; </button>
			<% } %>

			<% for(int i = startPage; i <= endPage; i++) { %>
				<% if(i != currentPage) {%>
					<button class="btn-default btn-sm" onclick="location.href='<%= contextPath %>/userManage.me?cpage=<%= i %>'"><%= i %></button>
				<% } else { %>
					<button class="btn-default btn-sm" disabled><%= i %></button>
				<% } %>
			<% } %>

			<% if(currentPage != maxPage) {%>
				<button class="btn-default btn-sm" onclick="location.href='<%= contextPath %>/userManage.me?cpage=<%= currentPage + 1 %>'"> &gt; </button>
			<% } %>
			
		</div>
		
	</div>
</div>

</body>
</html>