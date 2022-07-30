<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<h2>매출 목록</h2>
		<hr>
		<br><br>
		
		<table class="table table-sm">
		  	<thead>
					<tr>
						<th>회원아이디</th>
						<th>구매목록</th>
						<th>주문번호</th>
						<th>결제금액</th>
						<th>배송지</th>
						<th>주문일시</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>user01</td>	
						<td>양파 외 1건</td>						
						<td>1234816891</td>	
						<td>2400</td>						
						<td>경기도 안양</td>
						<td>2022-07-05 11:34</td>						
					</tr>
						
				</tbody>
		</table>
		
		<br><br>
		
	</div>
</div>

</body>
</html>