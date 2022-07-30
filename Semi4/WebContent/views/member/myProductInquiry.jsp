<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 문의 결과</title>
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
		    margin-left : 50px;
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
		    left: 100px;
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
	        <li><a href="<%=contextPath%>/myReview.me" class="item"><div>내가 쓴 리뷰</div></a></li>
	        <li><a href="<%=contextPath%>/myProductInquiry.me" class="item"><div>내가 쓴 상품 문의</div></a></li>
	        <li><a href="<%=contextPath%>/purchaseProduct.me" class="item"><div>구매내역 조회</div></a></li>
	        <li><a href="<%=contextPath%>/myRecipe.me" class="item"><div>좋아요한 레시피</div></a></li>  
	    </ul>
	</div>
	
	<div class="outer">
		<br>
		<h2>상품 문의 결과</h2>
		<hr>
		<br><br>
		<div class="list-table">
			<table class="list-area">
				<thead>
					<tr>
						<th>제목</th>
						<th>내용</th>
						<th>답변결과</th>
						<th>작성 날짜</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>배송언제옴</td>
						<td>ㅇ아니..</td>
						<td>문의중</td>
						<td>2022-05-20</td>
					</tr>
					<tr>
						<td>배송언제옴</td>
						<td>ㅇ아니..</td>
						<td>문의중</td>
						<td>2022-05-20</td>
					</tr>
					<tr>
						<td>배송언제옴</td>
						<td>ㅇ아니..</td>
						<td>문의중</td>
						<td>2022-05-20</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>