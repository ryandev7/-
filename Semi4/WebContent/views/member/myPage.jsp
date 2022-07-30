<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
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
	
	.recipe{
		margin: auto;
		margin-top : 200px;
		text-align:center;
		margin-left : 300px;
		width : 1200px;
	}
	
	.recipe p{
		font-size : 20px;
	}
	
	#enrollRecipe_btn{
		background-color : #596c9c;
		color : #fff;
		width : 180px;
		height : 50px;
		border : 0;
	}
	

</style>
<body>

<%@ include file="../common/menubar.jsp" %>
	<br><br>
<div class="wrap">
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
	
	<div class="recipe">
		<img src="https://recipe1.ezmember.co.kr/img/none_recipe.png">
		<br><br>
		<p>레시피를 직접 올려보세요!</p>
		<p style="font-size : 9px;">자랑하고 싶은 나만의 레시피! 공유하고 싶은 멋진 레시피를 올려 주세요.</p>
		<br>
		<button type="button" onclick="location.href='<%=contextPath%>/enrollForm.pan'" id="enrollRecipe_btn">레시피 등록하기</button>
	</div>
</div>

</body>
</html>