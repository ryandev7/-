<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
	
	String alertMsg = (String)session.getAttribute("alertMsg");
	
	String contextPath = request.getContextPath();

    String pwdAlertMsg = (String)session.getAttribute("pwdAlertMsg");
    String updateAlertMsg = (String)session.getAttribute("updateAlertMsg");
    String updateErrorMsg = (String)session.getAttribute("updateErrorMsg"); 
    String adminAlertMsg = (String)session.getAttribute("adminAlertMsg"); 

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menubar</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
        div{
            box-sizing: border-box;
                           
        }
       
        
        #navigator{height : 5%; border-bottom: 1px solid gray;}

        #header{
            width: 1200px;
            height:140px;    
            margin : 0 auto;
        }
        #navigator{
            margin: 0 auto;
            width: 1200px;
            height: 40px;
        }
        #header>div{
            height : 100%;
            float:left;                        
        }

        #logo:hover{
            cursor : pointer;
        }
        #logo_area{
            width : 30%;
            
        }
        #search_bar{
            width : 40%;
            
        }
        #header_3{
            width:30%;
        }


        #searchBarform > div{
            float:left
        }
        


        /* 검색창 */
        #search_form{
            width:80%;
            height: 100%;      
        }
        #search_form>input{
            margin-left: 50px;
        }
        #searchBtn{
            width: 10%;
            height: 100%;
        }
        #search_form> input, #login-form{
            width: 100%;
            height: 100%;
            box-sizing: border-box;
            padding: 0;
            margin-top: 50px;
            text-align: center;
        }
        #searchBtn > a{
            width: 100%;
            height: 100%;
            box-sizing: border-box;
            padding: 0;
            margin-top: 53px;
            text-align: center;
        }
        #search_form> input{
            padding-bottom: 5px;
        }
        #searchBtn > a{
            color :rgb(41, 35, 68)
        }
        #searchBtn > a:hover{
            color :rgb(237, 111, 101);
            text-decoration: none;
            
            
        }

        /* <!-- menubar --> */
       
        #menubar{
            list-style-type: none;
            margin : 0px;
            padding : 0px;
            height : 100%;
            margin-left: 350px;
            
        }
        #menubar>li{
            
            float:left;
            width : 14%;
            text-align: center;
            margin-left: 35px;
            height:100%;
            display: inline-block;
            
        }
        #menubar a{
            text-decoration: none;
            color : rgb(41, 35, 68);
            font-size: 15px;
            font-weight: 900;
            width: 100%;
            height: 100%; /*a태그는 이라인 요소이기때문에 100% 가 안먹음*/
            display: block; /*그래서 블록으로 바꿈*/
            line-height: 35px;
            /*화면 통합시 이슈가 발생함*/

            transform: scale(1);
        }
        #menubar a:hover{
            color :rgb(237, 111, 101);
            font-size:17px;
            
            
        }
        #menubar>li>ul{
            list-style-type: none; /*불릿 삭제*/
            padding : 0px; /*padding 삭제*/
            display: none;
            
        }

        #menubar>li>a:hover+ul{
            display: block;
            
        }
        #menubar>li>ul:hover{
            display: block;
        }

        #menubar>li>ul a{
            font-size : 13px; 
            background-color: white;
            border-left: 1px solid lightgray;
            border-right: 1px solid lightgray;

        }


        #menubar>li>ul a:hover{font-size: 13px;}
        
        
        #user-info{
        	margin-top : 50px;
        }
		
		
		#user-info a{
			text-decoration : none;
			color : black;
			font-size : 14px;
		}
        
        /* 아이콘 관련 */
        .material-symbols-outlined {
        font-variation-settings:
            'FILL' 0,
            'wght' 500,
            'GRAD' 0,
            'opsz' 48; 

        }
        
        .enroll{
        	background-color : white;
        	border : 0;
        	outline : 0;
        	cursor : pointer;
            font-size: 15px;
        }
        #qna11:hover{
            cursor: pointer;
        }


      
		
    </style>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
<script>
var msg = "<%= alertMsg %>";  //메세지 문구 or null
var pwdmsg = "<%=pwdAlertMsg %>";
var udtmsg = "<%=updateAlertMsg %>";
var udtErmsg = "<%=updateErrorMsg %>";
var adminAlertMsg = "<%=adminAlertMsg%>";
if(msg != "null"){
alert(msg);
}
if(pwdmsg != "null"){
   alert(pwdmsg);
   }
if(udtmsg != "null"){
   alert(udtmsg);
   }
if(udtErmsg != "null"){
   alert(udtErmsg);
   }
if(adminAlertMsg != "null"){
   alert(adminAlertMsg);
   }
<%session.removeAttribute("alertMsg");%>
<%session.removeAttribute("pwdAlertMsg");%>
<%session.removeAttribute("updateAlertMsg");%>
<%session.removeAttribute("updateErrorMsg");%>
<%session.removeAttribute("adminAlertMsg");%>
</script>
	<div id ="header">
        <div id = "logo_area">
           <img style="margin-right: 50px; margin-bottom: 10px; width: 110px; height: 110px;" id="logo" src="http://drive.google.com/uc?export=view&id=1aOhgpM_Xb5CigtMKM3QUFFepfRo3Hkhg">
        </div>
        <div id = "search_bar">
            <form action="test.do" id="searchBarform" method="get">

                <div id="search_form">
                    <input type="text">
                </div>
    
                <div id="searchBtn">
                    <a class="material-symbols-outlined" style="font-size: 30;">
                        search
                    </a>
                </div>
    
            </form>
        </div>
        <div id="header_3">
            <% if(loginUser == null) { %>
                <!-- 로그인 전에 보여지는 로그인 form -->
                    <form id="login-form" method="post" action="<%= contextPath %>/login.me">
                        <button type ="button" onclick ="enrollPage();" class="enroll">회원가입</button>
                        &nbsp;|&nbsp;
                        <button type = "button" onclick = "loginPage();" class="enroll">로그인</button>
                    </form>	
                    
                    <script>
                        function enrollPage(){
                        	location.href = "<%= contextPath %>/enrollForm.me";
                        }			
                        
                        function loginPage(){
                        	//window.open("<%= contextPath %>/loginForm.me");
                        	location.href = "<%= contextPath %>/loginForm.me";
                        	
                        }
                    </script>
                    
                    <% } else {%>
                <!-- 로그인 성공 후 화면 -->
                <div id="user-info" align="center">
                    <b><%= loginUser.getUserName() %></b>님 환영합니다.<br><br>
                    <div align="center" id="successLogin">
                        <% String userId = loginUser.getUserId(); %>
                    	<% if(loginUser != null && loginUser.getUserId().equals("admin")) { %>
                            <a href="<%= contextPath %>/list.ca?userId=<%=userId%>">장바구니</a>&nbsp;&nbsp;|&nbsp;
                        	<a href="<%= contextPath %>/userManage.me?cpage=1">관리자페이지</a>&nbsp;&nbsp;|&nbsp;
	                        <a href="<%= contextPath %>/logout.me">로그아웃</a>
                         <%} else{%>
                            <a href="<%= contextPath %>/list.ca?userId=<%=userId%>">장바구니</a>&nbsp;|&nbsp;
	                        <a href="<%= contextPath %>/myPage.me?userId=<%=loginUser.getUserId()%>">마이페이지</a>&nbsp;&nbsp;|&nbsp;
	                        <a href="<%= contextPath %>/logout.me">로그아웃</a>
                        <%} %> 
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    
    <div id = "navigator" >
        <ul id ="menubar">
            <li><a href="<%= contextPath %>/list.pr?cpage=1">식재료</a></li>

            <li>
                <a href="">레시판</a>
                <ul>
                	<li><a href="<%=contextPath %>/list.pan?cpage=1" style="border-top: 1px solid lightgray;">레시판</a></li>
                	<li><a onclick="myRecipe()" style="border-bottom:1px solid lightgray;">나의 레시피 조회</a></li>
                </ul>            
            </li>
            
			<script>
				function myRecipe(){
					<%if(loginUser == null){%>
						alert("로그인 이후 가능한 서비스 입니다.");
					<%}else{%>
						location.href = "<%=contextPath %>/myRecipe.pan?userId=<%=loginUser.getUserId()%>";
					<%}%>
				}
			</script>

            <li>
                <a href="">고객센터</a>
                <ul>
                    <li><a href="<%= contextPath %>/list.no?cpage=1" style="border-top: 1px solid lightgray;">공지사항</a></li>
                    <li><a onclick= "pQna();" id="qna11" style="border-bottom:1px solid lightgray;">1:1문의</a></li>
                </ul>
            </li>
        </ul>
    </div>

    <script>
        $("#logo_area").click(function(){
        location.href = "<%= contextPath %> "
    })    

    function pQna(){
                  <% if(loginUser == null) { %>
                     alert('로그인 후 이용할 수 있습니다.')
                     location.href="<%= contextPath %>/loginForm.me";
                  <% }else if(loginUser.getUserId().equals("admin")){%>
                     location.href = "<%= contextPath %>/list.pq?cpage=1&userId=<%=loginUser.getUserId()%>";
                  <%} else {%>
                  	location.href = "<%= contextPath %>/userList.pq?cpage=1&userId=<%=loginUser.getUserId()%>";
                  <% } %>
               }  
    </script>
</body>
</html>