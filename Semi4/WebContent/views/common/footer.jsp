<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        div{
            box-sizing: border-box;
        }
       

        #logo_area > img{
            width: 100px;
            height: 100px;
            float:right;
            margin-top: 20px;
            margin-right: 20px;
        }
        #searchBarform > div{
            float:left
        }
        


        /* 검색창 */
      
        

        /*푸터  */
        #footer{
            border-top : 1px solid gray;
            width: 1200px;
            overflow: auto;
            margin: 0 auto;
        }

        #footerLink{
            display: inline-block;
        }
        #footerLink a{
            text-decoration: none;
            color : rgb(41, 35, 68);
            font-size: small;            
        }
        #footerLink ul li{
            list-style-type: none;
            float: left;             
        }
        #footerLink > ul{
            display: inline-block;
            padding: 0px;
            margin-top: 5px;
            margin-left: 10px;
        }

        #company_information > p{
            font-size :3px;
            color : gray;
            
        }
        #company_information{            
            clear: both;
            text-align: center;
        }
        #logo_footer{     
            margin-top: 10px;     
            float:right;
            
        }
        #logo_footer > img{
            margin-right: 20px;
            width: 130px;
            height: 130px;
        }
        
        /* 아이콘 관련 */
        .material-symbols-outlined {
        font-variation-settings:
            'FILL' 0,
            'wght' 400,
            'GRAD' 0,
            'opsz' 48
        }

    </style>
</head>

<body>
	<div id="footer">
        <div id="footerLink">
            <ul>
                <li><a href="">회사소개</a></li>
                <li>|</li>
                <li><a href="">이용약관</a></li>
                <li>|</li>
                <li><a href="">개인정보처리방침</a></li>
                <li>|</li>
                <li><a href="">이용안내</a></li>
            </ul>
            <!-- <a href="">회사소개</a>
            <a href="">이용약관</a>
            <a href="">개인정보처리방침</a>
            <a href="">이용안내</a> -->
        </div>
        
            
        
        

        <div id = "logo_footer">
            <img src="http://drive.google.com/uc?export=view&id=1aOhgpM_Xb5CigtMKM3QUFFepfRo3Hkhg">
        </div>
        <div id="company_information">
            <p>
            (주)혼자사조 <br>
            사업자등록번호 123-456-7890 <br>
            통신판매업신고 : 제 2022-서울종로-0631호 <br>
            고객센터 : 123-456-73 <br>
            Copyright ©Alone Inc. All Rights Reserved <br>
            </p>
        </div>
    </div>
</body>
</html>