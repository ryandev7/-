<%@page import="com.kh.common.model.vo.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식재료 등록하기</title>
<style>
    #titleImg {
        width: 300px;
        height: 320px;
        display: block;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 10px;
    }

    #contentImg {
        width: 500px;
        height: 350px;
        display: block;
        margin-left: auto;
        margin-right: auto;
        margin-top: 15px;
        margin-bottom: 10px;
    }

    .enroll-pro{
        margin-top: 20px;
        margin-left: auto;
        margin-right: auto;
        width: 1000px;
    }

    #introduce{
        margin-top: -30;
    }

    select{
        width: 130px;
        height: 25px;
        font-size: 15px;
    }

    #file1{
        display: none;
    }

    .input-file-button1{
        padding: 4px 20px;
        background-color:#596c9c;
        border-radius: 4px;
        color: white;
        cursor: pointer;
    }

    #reset-file1{
        display: none;
    }

    .reset-file-button1{
        padding: 4px 20px;
        background-color:#ed6f65;
        border-radius: 4px;
        color: white;
        cursor: pointer;
    }


    #file2{
        display: none;
    }

    .input-file-button2{
        padding: 4px 20px;
        background-color:#596c9c;
        border-radius: 4px;
        color: white;
        cursor: pointer;
    }

    #reset-file2{
        display: none;
    }

    .reset-file-button2{
        padding: 4px 20px;
        background-color:#ed6f65;
        border-radius: 4px;
        color: white;
        cursor: pointer;
    }

    #submitBtn{
        width: 80px;
        height: 40px;
        background-color:#596c9c;
        border-radius: 4px;
        color: white;
        cursor: pointer;
        outline: none;
        border: 0;
        font-size: 20px;
        font-weight: bold;
    }

    #resetBtn{
        width: 80px;
        height: 40px;
        background-color:#ed6f65;
        border-radius: 4px;
        color: white;
        cursor: pointer;
        outline: none;
        border: 0;
        font-size: 20px;
        font-weight: bold;
    }
</style>
</head>
<body>
	<%@include file="../common/menubar.jsp" %>

    <form enctype="multipart/form-data" action="<%= contextPath %>/insert.pr" method="post">

        <table class="enroll-pro">
            <tr>
                <th colspan="5" style="text-align: left; font-size: 30px;">식재료 등록</th>
            </tr>
            <tr>
                <td style="height: 40px;"></td>
            </tr>
            </tr>
            <tr>
                <th style="height: 30px;">상품명</th>
                <td colspan="2"><input type="text" name="name" required placeholder="상품명을 입력해주세요." size="30"></td>
                <td rowspan="3" colspan="2"><img id="titleImg"></td>
            </tr>

            <tr>
                <th>상품소개</th>
                <td colspan="2"><textarea class="introduce" name="introduce" cols="30" rows="10" required style="resize:none;" placeholder="내용을 입력해주세요."></textarea></td>
            </tr>
            <tr>
                <th>카테고리</th>
                <td colspan="2">
                    <select name="category">
                        <% for(Category c : list) { %>
                        	<option value="<%= c.getCategoryNo() %>"><%= c.getCategoryName() %></option>
                        <% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <th>중량/용량</th>
                <td colspan="2"><input type="text" name="weight" required placeholder="중량/용량을 입력해주세요." size="30"></td>
                <td align="center">
                    <label class="input-file-button1" for="file1">
                        사진첨부
                    </label>
                    <input type="file" name="file1" id="file1" required onchange="loadImg(this, 1)"> 
    
                </td>
                <td align="center">
                    <label class="reset-file-button1" for="reset-file1">
                        첨부취소
                    </label>
                        <button name="reset-file1" id="reset-file1"></button>
                </td>
            </tr>
            <tr>
                <th>원산지</th>
                <td colspan="2"><input type="text" name="origin" required placeholder="원산지를 입력해주세요." size="30"></td>
            </tr>
            <tr>
                <th>상품금액</th>
                <td colspan="2"><input type="text" name="price" required placeholder="가격을 입력해주세요(숫자만)." size="30"></td>
            </tr>
            <tr>
                <th>상품설명</th>
                <td></td>
                <td></td>
                <td align="center">
                    <label class="input-file-button2" for="file2">
                        사진첨부
                    </label>
                    <input type="file" name="file2" id="file2" required onchange="loadImg(this, 2)"> 
                </td>
                <td align="center">
                    <label class="reset-file-button2" for="reset-file2">
                        첨부취소
                    </label>
                        <button name="reset-file2" id="reset-file2"></button>
                </td>
            </tr>
            <tr>
                <td colspan="5"><img id="contentImg"></td>
            </tr>
            <tr> 
                <td colspan="5">
                    <textarea name="content" rows="30" cols="150" required style="resize:none;" placeholder="상품설명을 입력해주세요."></textarea>
                </td>
            </tr>

        </table>

        <script> 
        
        function loadImg(inputFile, num) {

            if(inputFile.files.length == 1 ) {

                var reader = new FileReader();

                reader.readAsDataURL(inputFile.files[0]);

                reader.onload = function(e) {
                    switch(num) {
                        case 1 : $("#titleImg").attr("src", e.target.result); break;
                        case 2 : $("#contentImg").attr("src", e.target.result); break;
                    };
                };
            }
            else {
                switch(num) {
                    case 1 : $("#titleImg").attr("src", "http://drive.google.com/uc?export=view&id=1YQKQ3aAoZR2ahnQOo7_OMXF6FlcB9OuU"); break;
                    case 2 : $("#contentImg").attr("src", "http://drive.google.com/uc?export=view&id=1YQKQ3aAoZR2ahnQOo7_OMXF6FlcB9OuU"); break;
                };
            };
            
            $(document).on("click","#reset-file1", function(){
                $("#titleImg").attr("src","http://drive.google.com/uc?export=view&id=1YQKQ3aAoZR2ahnQOo7_OMXF6FlcB9OuU");
            });
            $(document).on("click","#reset-file2", function(){
                $("#contentImg").attr("src","http://drive.google.com/uc?export=view&id=1YQKQ3aAoZR2ahnQOo7_OMXF6FlcB9OuU");
            });


        }

        </script>

        <br>
        <div align="center">
            <button type="submit" id="submitBtn">등록</button>&nbsp;&nbsp;&nbsp;
            <button type="reset" id="resetBtn">초기화</button>&nbsp;&nbsp;&nbsp;

        </div>

    </form>

    <br><br><br>
    
    <%@include file="../common/footer.jsp" %>
</body>
</html>