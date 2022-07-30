<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
	
	String alertMsg = (String)session.getAttribute("alertMsg");
	
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<style>

	div{
		display : block;
	}
    ul{
        list-style: none;
        display: block;
        text-align: center;
        margin-left : -50px;
    }
  	ul li{
  		position : relative;
  		display: inline-block;
        text-decoration: none;
  	}
  	
  	a{
  		color:#ccc;
  	}
  	
  	li::after{
		  position:absolute;
		  content:"|";
		  color : #ccc;
	}
	li:last-child::after{
		content : "";
	}
	
	
    a{
    	cursor : pointer;
    	text-decoration :none;
    }
    
    tr>th{
    	border: 1px solid white;
    	width : 50px;
    }
    .wrap{
    	position : relative;
    	width : 100%;
    	height : 100%;
    }
    .login_wrap{
    	height:auto;
    	position : relative;
    	background-color: #292344;
        height : auto;
        border-radius: 10px;
        margin-top: 300px;
        box-sizing : border-box;
        width : 600px;
      	margin : 0 auto;
    }
    
    #login{
        background-color: rgb(255, 248, 230);
        width: 90px;
        height : 95px;
        }
        
    input {
    	width : 270px;
    	height: 40px;
    }
    
    
    #logo_area>img{
    	width : 150px;
    	height: 150px;
    	display: block; 
    	margin: 0px auto;
    	margin-top : 100px;
    }

</style>
<body>
    <div class="wrap">
        <div id = "logo_area">
            <img src="<%=request.getContextPath() %>/views/common/resource/logo.png" alt="logo">
       	<br><br>
        </div>

		<div class="login_wrap">
			<br><br>
	        <form id="login-form" name="login-form" method="post" action="<%= contextPath %>/login.me">
	            <table align="center">
	                <tr>
	                    <th>&#x1F465 </th>
	                    <td><input type="text" name="userId" required placeholder="아이디" ></td>
	                    <td rowspan="2">
	                        <button type="submit" id="login">Login</button>
	                    </td>
	                </tr>
	                <tr>
	                    <th>🔑</th>
	                    <td><input type="password" name="userPwd" required placeholder="비밀번호"></td>
	                </tr>
	            </table>
	            <br>
	           <ul>
		            <li style="width:120px">
		                <a href="<%=contextPath%>/findPwdForm.me">비밀번호 찾기 &nbsp; </a>
		            </li>
		            <li>
		                <a href="<%=contextPath%>/findIdForm.me">아이디 찾기 &nbsp; </a>
		            </li>
		            <li>
		                <a href="<%= contextPath %>/enrollForm.me">&nbsp;회원가입</a>
		            </li>
	       		</ul>
	       		<br>
	        </form>
	
	    </div>    
    </div>
    
</body>
</html>