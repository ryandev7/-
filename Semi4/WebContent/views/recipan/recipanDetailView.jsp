<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.recipan.model.vo.Recipan"
    import = "java.util.ArrayList" import = "com.kh.common.model.vo.RecipanProSau"
    import = "com.kh.common.model.vo.RecipanCookStep"
    import = "com.kh.product.model.vo.Product"
    %>
<%
Recipan recipan = (Recipan)request.getAttribute("recipan"); 
ArrayList<RecipanProSau> product = (ArrayList<RecipanProSau>)request.getAttribute("product");
ArrayList<RecipanProSau> sauce = (ArrayList<RecipanProSau>)request.getAttribute("sauce");
ArrayList<RecipanCookStep> cookStep = (ArrayList<RecipanCookStep>)request.getAttribute("cookStep");
ArrayList<Product> withPr =  (ArrayList<Product>)request.getAttribute("withPr");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일한 레시판</title>
<style>
    
    body *{
        margin : 0 auto;
        
        /* border : 1px solid black; */
        
        box-sizing :border-box;
    }
    #maincotent{
        width:1200px;        
    }
    #reciapnIntroForm, #productSauceForm, #cookStepForm, #commentForm, #withProductForm, .list-area{
        width: 710px;
        overflow: auto;    
    }
    #reciapnIntroForm > *{
        width : 100%
    }
    #thumbnailImg{                
        overflow: auto;
    }
    #recipanTitle{        
        height : 30px;
        font-weight: bolder;
    }
    #reciapnWriter{
        width : 50%;
        height:30px;
        float : left;
    }
    #likeCount{
        width : 50%;
        height:30px;
        float : left;
    }
    #reciapnIntro{
        clear:both;
        overflow: auto;
    }
    #reipanPerson,#reipanTime,#reipanLevel{
        float:left;
        width: 60px;
        height: 60px;
        
    }
    
    #productForm, #sauceForm{
        width:50%;
        float: left;
    }
    .cookStep{
        width: 100%;
        height: 200px;
        
    }
    .stepIcon{
        display: inline-block;
        width: 25px;
        height: 25px;
        border-radius: 50%;
        background-color: rgb(239, 228, 215);        
        margin-right: 90%;
        margin-top : 11%;
        
    }
    #commentForm{
        overflow: auto;
        border-bottom : 1px solid gray;
    }
    #commentListForm{
        width : 100%;
        overflow: auto;       
    }
    
	#withProductListForm{
		width : 100%;
        overflow: auto;    
	}
    #thumbnailImgDiv{
        border : 1px solid gray;
        width : 365px;
        height : 185px;
    }
    .imgdiv{
        width : 180px;
        height : 130px;
        border : 1px solid gray;
    }
    .withProductDiv{
        width: 200px;
        height : 150px;
        display: inline-block;
    }
    .imgdiv>img:hover{
		cursor: pointer;
		opacity: 0.6
	}
    .cartForm{
        width : 180px;
        height: 30px;
        /* border : 1px solid gray; */
    }
    .cartForm > button{
        width : 20%;
        float : left;        
        background-color : rgb(89, 108, 156);
        border: 2px solid rgb(89, 108, 156);
    }
    .cartForm > input[type = text]{
        width : 60%;
        float : left;
    }
    .list-area{
        height: 300px;
    }
</style>

</head>
<body>
	<%@include file="../common/menubar.jsp" %>
	<div id="maincotent" style="text-align: center;">                        
        <div id="reciapnIntroForm" style="border-bottom : 1px solid gray;">
            <div id="thumbnailImg">
                <div id="thumbnailImgDiv"><img src="<%=recipan.getTitleImg() %>" alt="" width="100%" height="100%"></div>
            </div>

            <div id="recipanTitle">
            	<%=recipan.getPeTitle() %>		                
            </div>

            <div id="reciapnWriter" style="text-align:right">
                <%=recipan.getPeWriter() %>	
            </div>

            <div id="likeCount" style="text-align:left">
                <div class="material-symbols-outlined" style="color: rgb(237, 111, 101); height: 100%; float:left">favorite</div>
                <div><%=recipan.getPeLikeCount() %></div>
            </div>

            <div id="reciapnIntro" style="text-align: center; color :gray;font-size:5px;">
                <%=recipan.getPeIntroduce() %>
            </div>
            
        
            <div id="reipanPerson" style="margin-left : 37%">
                <div class="material-symbols-outlined">
                    group
                </div>
                <br>
                <%=recipan.getPeFoodAmount() %>
            </div>
            <div id="reipanTime">
                <div class="material-symbols-outlined">
                    timer
                </div>
                <br>
                <%=recipan.getPeCookTime() %>
            </div>
            <div id="reipanLevel">
                <div class="material-symbols-outlined">
                    star
                </div>
                <br>
                <%=recipan.getPeCookLevel() %>
            </div>
        </div>
        

        <div id="productSauceForm" style="border-bottom : 1px solid gray">
            <div id="productForm">
                <table style="text-align: center;" width="100%" height="200px;" id="proTable">
                    <tr>
                        <th colspan="2">재료</th>
                    </tr>
                    <%for(int i = 0; i < product.size(); i++){ %>
                    	<tr>
                    		<td><%= product.get(i).getOutputName()%></td>
                    		<td><%= product.get(i).getOutputAmount()%></td>
                    	</tr>
                    <%} %>
                    
                </table>
            </div>
            <div id="sauceForm">
                <table style="text-align: center;" width="100%" height="200px;" id="sauceTable">
                    <tr>
                        <th colspan="2">양념</th>
                    </tr>
                    <%for(int i = 0; i < sauce.size(); i++){ %>
                    	<tr>
                    		<td><%= sauce.get(i).getOutputName()%></td>
                    		<td><%= sauce.get(i).getOutputAmount()%></td>
                    	</tr>
                    <%} %>
                </table>
            </div>
        </div>

        <div id="cookStepForm" style="clear : both; border-bottom : 1px solid gray">
            <br>
            <h4 style="text-align: left;">조리법</h4>
            
            
            <%for(RecipanCookStep c : cookStep){ %>
				<div class="cookStep" style="position: relative;">           
	                <div class="stepIcon" >
	                    <%=c.getStepNo() %>
	                </div>
	                <div style="width: 200px; height : 170px; position:absolute; left: 100px; top : 12px; border : 1px solid gray;">
	                    <img src="<%=c.getStepImg() %>" style="width : 100%; height : 100%;">
	                </div>
	                <div class="cookStepIntro" style=" border : 1px solid gray; width : 350px; height : 170px; position:absolute; left: 320px; top : 12px; font-size: small; text-align: left;">
	                    	<%=c.getOutPutStepContent() %>
	                </div>
            	</div>            	
            <%} %>
            <br>
                        
        </div>
        <div id="commentForm">
            <br>
            <h4 style="text-align: left;">후기</h4>
            <div id="commentListForm">
 
                <table id="commentTable" style="font-size:small;" align="left">
                    <tr>
                        <td style="font-weight:bold; font-size:12pt;">김명래</td>
                        <td style="color: gray;">2022-06-02</td>
                    </tr>
                    <tr>
                        <td colspan="2">요리 너무 맛 있어요 ! 배고플때 너무 좋은듯여 ! </td>
                    </tr>
                </table>
            </div>
            <div id="commentInsertForm">
                <input id="commentInput"type="text" style="float:left; padding-right: 400px;" placeholder="댓글을 입력해주세요">
                <button onclick="insertComment();" style="width: 120px; background-color: rgb(89, 108, 156); color:white;">등록</button>
            </div>                
            <br>       
        </div>
        <div id ="withProductForm">
            <br>
        	<h4 style="text-align: left;">혼자사조와 같이하는 재료들</h4>   
        	<div id = "withProductListForm">
                <br><br><br><br>            
        		<form action="<%=contextPath%>/insertCartWithPr.pan" method="post">
                <%if(withPr.isEmpty()){%>        		        			
                    <h4 style ="color : red;">혼자사조와 함께하는 재료가 존재하지 않습니다 ㅜㅜ</h4>        			        		
                <%}else{%>
                    <%int count = 0;%>
                    <%for(int height = 0; height < (withPr.size()/3 + 1); height++){%>                        
                        <%if(count == withPr.size())break; %>
                        <div class="list-area" align="center">                        
                            <%for(int width = count; width < count + 3; width++){%>
                                <%if(width == withPr.size()) break;%>
                                <div class ="withProductDiv">
                                    <input type="hidden" name = "prNo" value="<%=withPr.get(width).getPrNo()%>">
                                    <div class="imgdiv" align="center">
                                        <img src="<%=withPr.get(width).getTitleImg()%>" alt="" width="100%" height="100%">                                        
                                    </div>
                                    <div class="withProductIntroForm">
                                        <h6><%=withPr.get(width).getPrName()%></h6>                                        
                                    </div>
                                    <div class="cartForm">                                         
                                    	<button class="minus">-</button>
                        				<input type="text" class="cartAmount" name="prAmount" value="0">
                                        <button class="plus">+</button>                        				
                                    </div>
                                </div>
                            <%}count+=3;%>                    
                        </div>                        
                    <%}%>
                <%}%>
                <input type="submit" hidden id="submitHi">
                <%if(loginUser != null){ %>
                	<input type="hidden" name="userId" value="<%=loginUser.getUserId()%>">
                <%} %>
                <button type="button" class="btn btn-sm btn-info" id ="addToCart">장바구니 담기</button>
                </form>
        	</div>     	
        </div>
    </form>
        
        <br><br><br><br><br><br>
        <script>
            var prNoArr = [];
            var prAmount = [];
            var i = 0;
            $(document).on("click",".imgdiv",function(){
                var pno = $(this).siblings("input").val();
                location.href = "<%=contextPath %>/detail.pr?pno=" + pno
            });
            function getPrNoAndAmount(){
                prNoArr = [];
                prAmount = [];
                i = 0;
                $(".cartAmount").each(function(index, item){
                    if($(this).val() > 0){
                        prNoArr[i] = $(this).parent().siblings("input").val();
                        prAmount[i] = $(this).val();
                        i++;
                    }                                        
                })
            }
            $(document).on("click","#addToCart",function(){
				getPrNoAndAmount();                
                if(prNoArr.length == 0){
                	alert("장바구니에 담을 재료가 존재하지 않아요 ㅠㅠ");                    
                }else{                	
                	$("#submitHi").click();
                }
            });
            function loadToComment(){
                $.ajax({
                    url : "comsele.pan",
                    data  : {peNo : <%=recipan.getPeNo()%>},
                    success : function(list){
                        result ="";
                        if(list.length == 0){
                            result = "조회할 리뷰가 없습니다."
                        }
                        for(var i in list){
                            result += '<tr>';
                            result += '<td style="font-weight:bold; font-size:12pt;">' + list[i].commentWriter + '</td>';
                            result += '<td style="color: gray;">' + list[i].createDate + '</td>';
                            result += "</tr>";
                            result += "<tr>";
                            result += '<td colspan="2">' + list[i].commentContent + '</td>';                                
                            result += "</tr>";
                        }
                        $("#commentTable").html(result);
                    },
                    error : function(){
                        alert("조회실패");
                    }
                });
            }
            $(function(){
                loadToComment();
                setInterval(loadToComment, 1000);
            })
            function insertComment(){
                <%if(loginUser == null){%>
                    alert("로긘 이후에만 가능한 설븨스.");
                <%}else{%>
                    $.ajax({
                        url : "cominsert.pan",
                        data : {
                            peNo : <%=recipan.getPeNo()%>,
                            content : $("#commentInput").val(),
                            writer : "<%=loginUser.getUserId()%>"
                        },
                        type : "post",
                        success : function(result){
                            if(result > 0){
                                alert("댓글 작성에 성공했습니다.")

                                loadToComment();
                                
                                $("#commentInput").val("");
                            }
                        },
                        error : function(){
                            alert("코멧크릿에잇풜스")
                        }
                    })
                <%}%>
            }
            $(document).ready(function() {
                $('.minus').click(function () {
                    var $input = $(this).parent().find('input');
                    var count = parseInt($input.val()) - 1;
                    count = count < 0 ? 0 : count;
                    $input.val(count);
                    $input.change();
                    return false;
                });
                $('.plus').click(function () {
                    var $input = $(this).parent().find('input');
                    $input.val(parseInt($input.val()) + 1);
                    $input.change();
                    return false;
                });
	    	});
           
        </script>
    </div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>