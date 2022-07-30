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
<title>회원가입</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>

   .outer{
      display: block;
      font-size: 20px;
      width: auto;
      margin: 0 auto;
      margin-top: 30px;
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
   
   

   #enroll-form table {margin : auto;}
   #enroll-form input {margin : 5px;}
   

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
   
</style>
</head>
<body>

   <div id = "logo_area">
            <img src="<%=request.getContextPath() %>/views/common/resource/logo.png" alt="logo">
    </div>
        
   <div class="outer">
      <br>
      <form id="enroll-form" action="<%= contextPath %>/insert.me" method="post">
   
      <hr width="580px;">
      <br>
         <!-- 아이디, 비밀번호, 이름, 전화번호, 이메일주소, 주소, 취미-->
         <table>
            <tr>
               <td>&nbsp;&nbsp;아이디</td>
               <td><input type="text" required maxlength="50" name="userId" class="member_enroll" ></td>
               <td><button type="button" onclick="idCheck();" class="check_btn" id="idchkbtn">중복확인</button></td>
            </tr>
            <tr>
               <td>&nbsp;&nbsp;비밀번호</td>
               <td><input type="password" name="userPwd" maxlength="50" required class="member_enroll"></td>
               <td></td>
            </tr>
            <tr>
               <td>&nbsp;&nbsp;비밀번호확인</td>
               <td><input type="password" name = "checkuserPwd" maxlength="50" required class="member_enroll"></td>
               <td></td>
            </tr>
            <tr>
               <td>&nbsp;&nbsp;이름</td>
               <td><input type="text" name="userName" maxlength="50" required class="member_enroll"></td>
               <td></td>
            </tr>
            <tr>
               <td>&nbsp;&nbsp;이메일</td>
               <td><input type="email" name="email" maxlength="50" required class="member_enroll"></td>
               <td><button type="button" onclick="emailCheck()" class="check_btn" id="emailchkbtn">중복확인</button></td>
            </tr>
            <tr>
               <td>&nbsp;&nbsp;주소</td>
               <td><input type="text" name="address" class="member_enroll"></td>
               <td></td>
            </tr>
            <tr>
               <td>&nbsp;&nbsp;전화번호</td>
               <td><input type="text" name="phone" placeholder="- 포함해서 입력" class="member_enroll"></td>
               <td></td>
            </tr>
            <tr>
               <td>&nbsp;&nbsp;닉네임</td>
               <td><input type="text" name="nickName" class="member_enroll"></td>
               <td><button type="button" onclick="nickNameCheck()" class="check_btn" id="nicknamechkbtn">중복확인</button></td>
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
                  <label for="korean"><input type="radio" name="favoriteFood" value="1" id="korean" style="vertical-align:-9px;"> 한식</label>
                  <label for="chinese"><input type="radio" name="favoriteFood" value="2" id="chinese" style="vertical-align:-9px;">중식</label>
                  <label for="japanese"><input type="radio" name="favoriteFood" value="3" id="japanese" style="vertical-align:-9px;">일식</label>
                  <label for="western"><input type="radio" name="favoriteFood" value="4" id="western" style="vertical-align:-9px;">양식</label>
                  <label for="guitar"><input type="radio" name="favoriteFood" value="5" id="guitar" style="vertical-align:-9px;">기타</label>
               </td>
            </tr>
            <tr>
               <td>&nbsp;&nbsp;이용약관 동의&nbsp;</td>
               <td colspan="2">
               <label for="info"><input type="radio" name="agreement" value="provision" id="info" style="vertical-align:-9px;">&nbsp;무료배송,할인쿠폰 등 혜택/정보 수신 동의</label>
               </td>
            </tr>
            
            
         </table>
      
         <br><br>

         <div align="center" >
            <button type="button" onclick="userInfoCheck();" id="complete_btn" disabled>회원가입</button>
         </div>

      </form>
      
      <br><br><br><br>
   </div>
   <script>
   
      function idCheck(){
         
         var $userId = $("#enroll-form input[name=userId]");
         var id_rule = /^[a-zA-z0-9]{4,12}$/; 
         
         $.ajax({
            type : "POST",
            url : "idCheck.me",
            data : {checkId : $userId.val()},
            success : function(result){
               if(result == "NNNNN"){ 
                  alert("사용할 수 없는 아이디입니다.")
                  $userId.focus();
               }
               
               else{ 
                  if(!id_rule.test($userId.val())){
                     alert("영문 대소문자와 숫자를 사용하여 4~12 자리로 입력해주세요.");
                     $userId.focus();
                     $userId.css({"border":"3px solid red", "outline" : "none"});
                     
                     return false;
                  }
                  else{   
                     if(confirm("사용가능한 아이디입니다. 사용하시겠습니까?")){ 
                        $userId.attr("readonly", true);
                        $userId.css("border","none");
                        $("#idchkbtn").hide();
                     } 
                     else{
                        $userId.focus();
                     }
                  }
               }
               
            },
            error : function(){
               console.log("아이디 중복체크용 비동기요청 실패");               
            }
         });
         
      }
      
      
      function nickNameCheck(){
         
         var $nickName = $("#enroll-form input[name=nickName]");
         
         
         $.ajax({
            type : "POST",
            url : "nickNameCheck.me",
            data : {checkNickName : $nickName.val()},
            success : function(result){
               if(result == "NNNNN" ){ 
                  alert("이미 존재하는 닉네임입니다.")
                  $nickName.focus();
               }
               else{ 
                  if(confirm("사용가능한 닉네임입니다. 사용하시겠습니까?")){ 
                     $("#enroll-form button[id=complete_btn]").removeAttr("disabled");
                     $nickName.attr("readonly", true);
                     $nickName.css("border","none");
                  } 
                  else{
                     $nickName.focus();
                  }
                  
               }
            },
            error : function(){
               console.log("아이디 중복체크용 비동기요청 실패");               
            }
         });
         
      }
      
      
      function emailCheck(){
         
         var $email = $("#enroll-form input[name=email]");
         var email_rule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
         $.ajax({
            type : "POST",
            url : "emailCheck.me",
            data : {checkEmail : $email.val()},
            success : function(result){
               if(result == "NNNNN"){ 
                  alert("이미 존재하는 이메일입니다.")
                  $email.focus();
               }
               else{ 
                  if(!email_rule.test($email.val())){
                     alert("이메일을 형식에 맞게 입력해주세요. ex) 1234@naver.com");
                     $email.focus();
                     $email.css({"border":"3px solid red", "outline" : "none"});
                     return false;
                  }
                  else{   
                     if(confirm("사용가능한 이메일입니다. 사용하시겠습니까?")){ 
                        $email.attr("readonly", true);
                        $email.css("border","none");
                     } 
                     else{
                        $email.focus();
                     }
                  }
               }
               

            },
            error : function(){
               console.log("아이디 중복체크용 비동기요청 실패");               
            }
         });
         
      }
      
      
      function userInfoCheck(){
         
         
         var phone_rule = /^\d{2,3}-\d{3,4}-\d{4}$/; 
         if($('input[name=userId]').val() == ''){
            alert('아이디를 입력하세요');
            $('input[name=userId]').value="";
            $('input[name=userId]').focus();
            return false;
         }
         else if($('input[name=userPwd]').val() == ''){
            alert('비밀번호를 입력하세요');
            $('input[name=userPwd]').value="";
            $('input[name=userPwd]').focus();
            return false;
         }
         else if($('input[name=checkuserPwd]').val() == ''){
            alert('비밀번호 확인을 입력하세요');
            $('input[name=checkuserPwd]').value="";
            $('input[name=checkuserPwd]').focus();
            return false;
         }
         else if($('input[name=userPwd]').val() != $('input[name=checkuserPwd]').val()){
            alert('비밀번호와 비밀번호 확인이 일치하지 않습니다');   
            $('input[name=checkuserPwd]').value="";
            $('input[name=checkuserPwd]').focus();
            return false;
         }
         else if($('input[name=userName]').val() == ''){
            alert('이름을 입력하세요');
            $('input[name=userName]').value="";
            $('input[name=userName]').focus();
            return false;
         }
         else if($('input[name=email]').val() == ''){
            alert('이메일을 입력하세요');
            $('input[name=email]').value="";
            $('input[name=email]').focus();
            return false;
         }
         else if($('input[name=address]').val() == ''){
            alert('주소를 입력하세요');
            $('input[name=address]').value="";
            $('input[name=address]').focus();
            return false;
         }
         else if($('input[name=phone]').val() == ''){
            alert('핸드폰 번호를 입력하세요');
            $('input[name=phone]').value="";
            $('input[name=phone]').focus();
            return false;
         }
         else if($('input[name="cookLevel"]:checked').length == 0){
            alert('요리레벨를 선택하세요');
            $('input[name=cookLevel]').value="";
            $('input[name=cookLevel]').focus();
            return false;
         }
         else if($('input[name="favoriteFood"]:checked').length == 0){
            alert('선호요리를 선택하세요');
            $('input[name=favoriteFood]').value="";
            $('input[name=favoriteFood]').focus();
            return false;
         }
         
         $("#enroll-form").submit();
         
         
      }
   </script>

</body>
</html>