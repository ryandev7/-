<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 상세 페이지</title>
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
	
	
	#orderNum{
		margin-left: 50px;
	}
    
	table{
        border-top: 1px solid;
        border-bottom: 1px solid;
		float: left;
		margin-left: 20px;
	}

	td{
		padding: 30px;
	}

	#img{
		width: 180px;
		text-align: center;
	}

    #product{
        width: 400px;
    }

    .btnPart{
        float: right;
        width: 200px;
        margin-top: -140px;
        margin-right : -150px;
    }
	
	.review_btn{
		background-color : #292344;
		color:white;
		font-size : 15px;
		border-style: none;
        width: 130px;
        height: 40px;
        border-radius: 12px;
        vertical-align: middle;
	}
	
	#review_btn2{
		margin-top : 80px;
	}
	
	.basket_btn{
		
		background-color : #ed6f65;
        color:white;
		font-size : 15px;
		border-style: none;
        width: 130px;
        height: 40px;
        border-radius: 12px;
        vertical-align: middle;
        margin-top: 10px;
	}
	
	
	
	#allProduct_btn{
		background-color: #596c9c;
        color:white;
		font-size : 15px;
		border-style: none;
        margin-top:30px;
        margin-left: 900px;
        width: 150px;
        height: 50px;
        border-radius: 12px;
	}
	
</style>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="menu">
	
	    <label for="expand-menu"><div>마이페이지</div></label><input type="checkbox" id="expand-menu" name="expand-menu">
	    <ul>
       		<li><a href="<%=contextPath%>/updateForm.me" class="item"><div>개인정보 수정</div></a></li>
	           <li><%if(loginUser !=null ) { %><a href="<%=contextPath%>/myReview.me?userId=<%=loginUser.getUserId() %>&cpage=1" class="item"><div>나의 리뷰</div></a><% } %></li>
	           <li><%if(loginUser !=null ) { %><a href="<%=contextPath%>/myProductQna.me?userId=<%=loginUser.getUserId() %>&cpage=1" class="item"><div>나의 상품 문의</div></a><% } %></li>
	           <li><a href="" class="item"><div>1대 1문의</div></a></li>
	           <li><%if(loginUser !=null ) { %><a href="<%=contextPath%>/myLikeRecipe.me?userId=<%=loginUser.getUserId() %>&cpage=1" class="item"><div>좋아요한 레시피</div></a><% } %></li>  
	           <li><a href="<%=contextPath%>/purchaseProduct.me" class="item"><div>구매내역 조회</div></a></li> 
	    </ul>
	</div>
	<div class="outer">
	    <br><br>
	    <h2>구매내역 상세</h2>
	    <hr><br>
	    <p id="orderNum">&nbsp;&nbsp;주문번호 1234816891</p>
	    <div class="content">
	        <table>
	            <tr>
	                <td rowspan="2" id="img">이미지</td>
	                <td id="product">양파</td>
	                <td rowspan="2" id="delivery">배송완료</td>
	            </tr>
	            <tr>
	            	<td>800원 | 1개</td>
	            </tr>
	        </table>
	
	        <table>
	            <tr>
	                <td rowspan="2" id="img">이미지</td>
	                <td id="product">감자</td>
	                <td rowspan="2" id="delivery">배송완료</td>
	            </tr>
	            <tr>
	            	<td>1600원 | 2개</td>
	            </tr>
	        </table>
	
	        <div class="btnPart">
	            <button  class="review_btn" id="review_btn">후기쓰기</button>
	            <button class = "basket_btn" id="basket_btn">장바구니 담기</button>
	            
	            <button class="review_btn" id="review_btn2">후기쓰기</button>
	            <button class = "basket_btn" id="basket_btn2">장바구니 담기</button>
	        </div>
	   
	
	    </div>
	    <div>
	        <button id="allProduct_btn">전체상품 다시 담기</button>
	    </div>
	    
    </div>
</body>
</html>