<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "java.util.ArrayList, com.kh.common.model.vo.*" %>
<% 
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
	Member m = (Member)request.getAttribute("m");
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>개인정보 수정</title>
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
	
		input{
			margin-right : 10px;
			height: 30px;
		}
		
		
		input[type="checkbox"]{
			zoom : 1.5;
		}
		
		input[for="label"]{
			position:relative;
			top:20px;
		}
		
		
		
		.member_enroll{
			width : 90%;
		}
		
		
	
		#update-form table {margin : auto;}
		#update-form input {margin : 5px;}
		
	
		#logo_area>img{
		    	width : 150px;
		    	height: 150px;
		    	display: block; 
		    	margin: 0px auto;
		    	margin-top : 100px;
		}
		
		.check_btn{
			background-color: #596c9c; 
			color : white;
			width : 100px;
			border : 0;
			outline :0;
			height: 40px;
			font-size : 14px;
		}
		
		#complete_btn{
			background-color: #efe4d7 ;
			width: 170px;
			height:60px;
			text-decoration: solid;
			border : 0;
			outline :0;
			font-size: 20px;
			border-radius: 12px;
		}
		
		.changeInfo_btn{
			background-color : #ed6f65;
			color : white;
			font-size : 18px;
			border : 0;
			border-radius : 15px;
			width : 100px;
			height : 50px;
			
		}
		
		.changePwd_btn{
			background-color : #596c9c;
			color : white;
			font-size : 18px;
			border : 0;
			border-radius : 15px;
			width : 140px;
			height : 50px;
		}
		
		.withdrawMember_btn{
			background-color : red;
			color : white;
			font-size : 18px;
			border : 0;
			border-radius : 15px;
			width : 100px;
			height : 50px;
		}
			
</style>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<br><br>
	<%
	String userId = loginUser.getUserId();
	String userName = loginUser.getUserName();
	String email = (loginUser.getEmail() == null) ? "" : loginUser.getEmail();
	String address = (loginUser.getAddress() == null) ? "" : loginUser.getAddress();
	String phone = (loginUser.getPhone() == null) ? "" : loginUser.getPhone();
	String nickName = (loginUser.getNickName() == null) ? "" : loginUser.getNickName();
	String cookLevel = (loginUser.getCookLevel() == null) ? "" : loginUser.getCookLevel();
	String favoriteFood = (loginUser.getFavoriteFood() == null) ? "" : loginUser.getFavoriteFood();
	%>
	
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
        	<h2>개인정보 수정</h2>
        	<hr>
			<br>
			<form id="update-form" action="<%= contextPath %>/update.me" method="post">
	
			<br>
			<!-- 아이디, 비밀번호, 이름, 전화번호, 이메일주소, 주소, 취미-->
			<table class="list-area">
				<tr>
					<td>&nbsp;&nbsp;아이디</td>
					<td><input readonly type="text" required maxlength="50" name="userId" class="member_enroll" value="<%=userId%>"></td>
					
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이름</td>
					<td><input type="text" name="userName" maxlength="50" required class="member_enroll" value="<%= userName %>"></td>
					
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td><input type="email" name="email" maxlength="50" required class="member_enroll" value="<%= email %>"></td>
					
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td><input type="text" name="address" class="member_enroll" value="<%= address %>"></td>
					
				</tr>
				<tr>
					<td>&nbsp;&nbsp;전화번호</td>
					<td><input type="text" name="phone" placeholder="- 포함해서 입력" class="member_enroll" value="<%= phone %>"></td>
					
				</tr>
				<tr>
					<td>&nbsp;&nbsp;닉네임</td>
					<td><input type="text" name="nickName" class="member_enroll" value="<%= nickName %>"></td>
					
				</tr>
				
				<tr>
					<td  style="border-bottom : 1px solid black">&nbsp;&nbsp;요리레벨</td>
					<td colspan="2"  style="border-bottom : 1px solid black">
						<label for="low"><input type="radio" name="cookLevel" value="하" id="low" style="vertical-align:-9px;">하</label>
						<label for="mid"><input type="radio" name="cookLevel" value="중" id="mid" style="vertical-align:-9px;">중</label>
						<label for="top"><input type="radio" name="cookLevel" value="상" id="top" style="vertical-align:-9px;">상</label>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;선호요리</td>
						<td colspan="2">
						<% for(Category c : list) { %>
							<%if(c.getCategoryName().equals(favoriteFood)) {%>
								<label for="<%=c.getCategoryName()%>"><input type="radio" name="favoriteFood" id="<%=c.getCategoryName()%>" value="<%=c.getCategoryNo()%>" style="vertical-align:-9px;" checked><%=c.getCategoryName()%></label>
							<%}else{ %>
								<label for="<%=c.getCategoryName()%>"><input type="radio" name="favoriteFood" id="<%=c.getCategoryName()%>" value="<%=c.getCategoryNo()%>" style="vertical-align:-9px;"><%=c.getCategoryName()%></label>
							<%} %>
						<%} %>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이용약관 동의&nbsp;</td>
					<td colspan="2">
					<label for="info"><input type="checkbox" name="agreement" value="provision" id="info" style="vertical-align:-9px;">&nbsp;무료배송,할인쿠폰 등 혜택/정보 수신 동의</label>
					</td>
				</tr>
				
				
			</table>
			
			<script>
				var cookLevel = "<%=cookLevel%>";
				var favoriteFood= "<%=favoriteFood%>";
				$('input[type=radio][name=cookLevel]').each(function(){
					
					if(cookLevel.search($(this).val()) != -1){ 
						$(this).attr("checked", true);
					}
				})
				
                  
			</script>
		
			<br><br>

			<div align="center" style="margin-left :200px; ">
				<button type="submit" class="changeInfo_btn">정보변경</button>
				<button type="button" class="changePwd_btn" style="color:white" data-toggle="modal" data-target="#updatePwdForm">비밀번호변경</button>
				<button type="button" class="withdrawMember_btn" data-toggle="modal" data-target="#deleteMember">회원탈퇴</button>
			</div>
			
		</form>
		
		<br><br><br><br>
	    </div>

    </div>
    
    
   <!-- The Modal -->
	<div class="modal" id="updatePwdForm">
		<div class="modal-dialog">
		<div class="modal-content">
	
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">비밀번호 변경</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
	
			<!-- Modal body -->
			<div class="modal-body">

				<form action="<%= contextPath %>/updatePwd.me" method="post">

					<!-- 현재 비밀번호, 변경할 비밀번호, 변경할 비밀번호 확인(재입력)-->
					
					<!-- 확실하게 주인은 판별할 수 있는 id값도 같이 넘겨줌 -->
					<input type="hidden" name="userId" value="<%= userId %>">

					<table>
						<tr>
							<td>현재 비밀번호</td>
							<td><input type="password" required name="userPwd"></td>
						</tr>
						<tr>
							<td>변경할 비밀번호</td>
							<td><input type="password" name="updatePwd" required></td>
						</tr>
						<tr>
							<td>변경할 비밀번호 재입력</td>
							<td><input type="password" name="checkPwd" required></td>
						</tr>
					</table>

					<br>

					<button type="submit" class="btn btn-sm btn-secondary" onclick="return validatePwd();">비밀번호 변경</button>
				</form>

				<script>
					function validatePwd(){

						if($("input[name=updatePwd]").val() != $("input[name=checkPwd]").val()){
							alert("비밀번호가 일치하지 않습니다 ~ ");
							return false;
						}
						else{
							return true;
						}

					}
				</script>
			</div>


		</div>
		</div>
	</div>
	
	
	
	<!-- 탈퇴만들자리 -->
	<div class="modal" id="deleteMember">
		<div class="modal-dialog">
		<div class="modal-content">
	
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">탈퇴하시겠습니까?</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
	
			<!-- Modal body -->
			<div class="modal-body">

				<form action="<%= contextPath %>/delete.me" method="post">

					<table>
						<tr>
							<td>비밀번호를 입력해주세요</td>
							<td><input type="password" required name="userPwd"></td>
						</tr>
					</table>

					<br>

					<button type="submit" class="btn btn-sm btn-danger">회원탈퇴</button>
				</form>
			</div>
		</div>
		</div>
	</div>
	
</body>
</html>