<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/2521001559.js" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>구매 내역 조회</title>
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
		margin-left : 400px;
    }
	.purchaseDate{
		margin-left : 80px;
	}
	
	
	#product{
		font-size: 26px;
		font-weight: bold;
		margin-left : 80px;
		
	}

	.icon{
		float: right;
		margin-right: 50px;
		background-color: white;
		border-style: none;
	}

	table{
		float: left;
		margin-left: 20px;
	}

	

	td{
		padding: 7px;
	}

	#img{
		width: 180px;
		text-align: center;
	}

	#inquiry{
		float: right;
		margin-right: 50px;
		margin-top: 50px;
	}

	#inquiry_btn{
		background-color: #596c9c;
		color: white;
		border-radius: 5px;
		width: 100px;
		height: 40px;
		border-style: none;
	}

</style>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<br><br>
	
		<div class="menu">
	
		    <label for="expand-menu"><div>마이페이지</div></label><input type="checkbox" id="expand-menu" name="expand-menu">
		    <ul>
			   <li><a href="<%=contextPath%>/updateForm.me" class="item"><div>개인정보 수정</div></a></li>
	           <li><%if(loginUser !=null ) { %><a href="<%=contextPath%>/myReview.me?userId=<%=loginUser.getUserId() %>&cpage=1" class="item"><div>나의 리뷰</div></a><% } %></li>
	           <li><%if(loginUser !=null ) { %><a href="<%=contextPath%>/myProductQna.me?userId=<%=loginUser.getUserId() %>&cpage=1" class="item"><div>나의 상품 문의</div></a><% } %></li>
	           <li><a href="<%= contextPath %>/userList.pq?cpage=1&userId=<%=loginUser.getUserId()%>" class="item"><div>1대 1문의</div></a></li>
	           <li><%if(loginUser !=null ) { %><a href="<%=contextPath%>/myLikeRecipe.me?userId=<%=loginUser.getUserId() %>&cpage=1" class="item"><div>좋아요한 레시피</div></a><% } %></li>  
	           <li><a href="<%=contextPath%>/purchaseProduct.me" class="item"><div>구매내역 조회</div></a></li> 
		    </ul>
		</div>
		
		<div class="outer">
			<br>
			<h2>구매내역 조회</h2>
			<hr><br>
			
			<div class="purchaseDate">&nbsp;2022.06.07 15:30</div>
			<form id="purchase-form" action="<%= contextPath %>/purchaseDetail.me" method="post">
			<hr>
			<span id="product">양파 외 2건</span>
			<button class="icon"><i class="fa-solid fa-chevron-right"></i></button>
			<hr><br>
			<div class="list-area">
				<table>
					<tr>
						<td rowspan="3" id="img">이미지</td>
						<td>주문번호</td>
						<td>1234811</td>
					</tr>
					<tr>
						<td>결제금액</td>
						<td>31,600원</td>
					</tr>
					<tr>
						<td>주문상태</td>
						<td>배송완료</td>
					</tr>
				</table>
				

			</div>

			<div id="inquiry">
				<button id="inquiry_btn">1:1문의</button>
			</div>
			

			</form>
			
		</div>
</body>
</html>