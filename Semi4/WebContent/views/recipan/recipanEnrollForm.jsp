<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.common.model.vo.Category"
    import="java.util.ArrayList"%>
<% ArrayList<ArrayList<Category>> categoryList = (ArrayList<ArrayList<Category>>)request.getAttribute("categoryList");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시판 입력</title>
<style>
        
        body *{
            /* border : 1px solid gray; */
            box-sizing: border-box;
            
        }
        #area{
            width: 1200px;
            overflow: auto;
            margin: 0 auto;
        }
        #recipanTitle{
            width: 70%;
            float: left;
            
        }
        #recipanContent1{
            overflow: auto;
        }
        #recipanForm1{
            float:left;
            width:70%;
            height: 260px;                        
        }
        #recipanForm2{
            float:left;
            width:30%;
            height:260px;                        
        }
        #recipanForm1 > *{
            width: 660px;
            height: 40px;
            float: right;
            
        }


        #recipanProductForm{
            overflow: auto;
            width: 100%;
        }
        #recipanSauceForm{
            overflow: auto;
            width: 100%;
        }


        #recipanCookStep{
            width: 100%;
            height: 350px;
        }
        

        
        #recipanContent1Table > tr > th,td{
            height: 50px;
        }
        #recipanContent2 input[type = text]{
            height: 60%;
            width: 100%;
        }
        #productTable, #sauceTable{
            margin-left: 500px;
        }
        #recipanCookStepTable{
            margin-left: 300px;
        }
        #addToProudctForm,#addToSauceForm,#addToCookStepForm{
            
            
            text-align: center;
        }
        #recipanCokkStep{
            overflow: auto;
        }


        .imgDiv{
            width : 200px;
            height : 246px;
            border : solid 1px gray;
            margin-bottom: 5px;
        }
    </style>
    
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>
	<div id="area">
        <form action="<%=contextPath%>/insert.pan" method="post" enctype="multipart/form-data">
        <div id ="recipanContent1">
            <!-- hr선 기준으로 첫 번째 Content -->
            <div id="recipanTitle">
                <h3 style="padding-left: 150px;">레시판 등록</h3>
            </div>
            
            <div id="recipanForm1">                
                <table id="recipanContent1Table">
                    <tr>
                        <th style="width: 100px;">레시판 제목</th>
                        <td><input type="text" name="recipanTitle" id="" placeholder="레시피 제목을 입력하세요" style="width: 200px;"></td>
                    </tr>
                    <tr>
                        <th>요리 소개</th>
                        <td><input type="text" name="recipanIntro" id="" placeholder="레시피 설명을 입력하세요" style="width: 380px;"></td>
                    </tr>
                    <tr>
                        <th>카테고리</th>
                        <td>
                        	<%if(!categoryList.get(2).isEmpty()){ %>
                            <select name="nationalCat" id="">
                            	<%for(Category c : categoryList.get(2)){ %>
                                <option value=<%= c.getCategoryNo() %> ><%=c.getCategoryName() %></option>
                                <%} %>                                                                
                            </select>
                            <%} %>
                            
                            <%if(!categoryList.get(1).isEmpty()){ %>
                            <select name="kindCat" id="">
                            	<%for(Category c : categoryList.get(1)){ %>
                                <option value=<%= c.getCategoryNo() %>><%=c.getCategoryName() %></option>
                                <%} %>
                            </select>
                            <%} %>
                            
                            <%if(!categoryList.get(0).isEmpty()){ %>
                            <select name="productCat" id="">
                            	<%for(Category c : categoryList.get(0)){ %>
                                <option value=<%= c.getCategoryNo() %>><%=c.getCategoryName() %></option>
                                <%} %>
                            </select>
                            <%} %>
                        </td>        
                               
                    </tr>
                    <tr>
                        <th>요리정보</th>
                        <td>
                            <input type="text" name="recipanPerson" id="" placeholder="인원(명수까지)">
                            <input type="text" name="recipanTime" id="" placeholder="시간(단위까지)">
                            <input type="text" name="recipanDifficulty" id="" placeholder="난이도(상/중/하)">
                        </td>
                    </tr>
                </table>
            </div>
            <div id="recipanForm2">
                <img src="<%=request.getContextPath()%>/views/resource/sam.PNG" width="180"height="200"alt="" id="titleImg">
                <input hidden type="file" name="thumbnailFile" id="thumbnailFile"  onchange="thumbNailLoadImg(this);">

                <!--사진 첨부 기능 -->
            </div>
        </div>
        <hr>
        <div id = "recipanContent2">
            <!-- 2번째 Content -->
            <h6 style="padding-left: 70px;">정확한 계량 정보를 적어주세요.</h6>
            <div id="recipanProductForm">
                <h3 style="margin-left: 400px;">재료</h3>
                <table id="productTable">
                    <tr>                        
                        <td><input type="text" name="" class="productName" placeholder="재료명"required></td>
                        <td><input type="text" name="" class="productWeight" placeholder="재료양(단위까지 입력)"required></td>
                        <td><button type="button" class="removeProductBtn btn btn-secondary">-</button></td>
                    </tr>
                    <tr>                        
                        <td><input type="text" name="" class="productName" placeholder="재료명"required></td>
                        <td><input type="text" name="" class="productWeight" placeholder="재료양(단위까지 입력)"required></td>
                        <td><button type="button" class="removeProductBtn btn btn-secondary">-</button></td>
                    </tr>
                    <tr>                
                        <td><input type="text" name="" class="productName" placeholder="재료명"required></td>
                        <td><input type="text" name="" class="productWeight" placeholder="재료양(단위까지 입력)"required></td>
                        <td><button type="button" class="removeProductBtn btn btn-secondary">-</button></td>
                    </tr>
                </table>
                <div id="addToProudctForm">
                    <button type="button" class = "btn btn-secondary" id="addProductTableBtn">+</button>
                </div>
            </div>
            <div id="recipanSauceForm">
                <!-- 소스 입력폼 -->
                <h3 style="margin-left: 400px;">양념</h3>
                <table id="sauceTable">
                    <tr>                        
                        <td><input type="text" name="" class="sauceName" placeholder="소스명"required></td>
                        <td><input type="text" name="" class="sauceWeight" placeholder="소스양(단위까지 입력)"required></td>
                        <td><button type="button" class="rmoveSauceBtn btn btn-secondary">-</button></td>
                    </tr>
                    <tr>                        
                        <td><input type="text" name="" class="sauceName" placeholder="소스명"required></td>
                        <td><input type="text" name="" class="sauceWeight" placeholder="소스양(단위까지 입력)"required></td>
                        <td><button type="button" class="rmoveSauceBtn btn btn-secondary">-</button></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="" class="sauceName" placeholder="소스명"required></td>
                        <td><input type="text" name="" class="sauceWeight" placeholder="소스양(단위까지 입력)"required></td>
                        <td><button type="button" class="rmoveSauceBtn btn btn-secondary">-</button></td>
                    </tr>
                    
                </table>
                <div id="addToSauceForm">
                    <button type="button" class="btn btn-secondary" id="addSauceTableBtn">+</button>
                </div>
            </div>            
        </div>
        <hr>
        <div id="recipanContent3">
            <h4 style="padding-left: 70px; display: inline-block;" >요리순서</h4>
            <h6 style="display: inline-block;">정확하고 자세히 레시피를 적어주세요.</h6>
            <div id="recipanCookStep">
                <table id="recipanCookStepTable">
                    <tr>
                        <th><div class="imgDiv"><img src="<%=request.getContextPath()%>/views/resource/sam2.PNG" alt="cookStep" width="100%" height="100%" class="cookStepImgTag"></div></th>
                        <td><input type="file" hidden name="" class="cookStepFile"  onchange="cookStepImg(this);"></td>
                        <td><textarea name="" class="cookStepIntro" cols="45" rows="10" style="resize: none;" required></textarea></td>
                        <td><button class="rmoveCookStepBtn btn btn-secondary">-</button></td>
                    </tr>                                                              
                </table>

                <div id="addToCookStepForm">
                    <button type="button" class = "btn btn-secondary" id="addRecipanCookStepBtn">+</button>
                    <br><br>
                    <button type="button" class ="btn btn-secondary" id="indexGiveNamebtn">레시피 완성</button>
                    <input type="submit" id="submitRecipan" hidden>
                </div>
                           
                <button type="button" id="modalBtn" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop" style="visibility: hidden;"></button>
            </div>
        </div>


        <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="staticBackdropLabel">이건 개발자가 원하지 않는 경우에요 !</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">

                  입력되지 않은 사진이 존재합니다 ㅠㅠ 
  
                </div>
                <div class="modal-footer">                  
                  <button type="button" class="btn btn-danger" data-dismiss="modal">사진 입력하러 가기 !</button>
                </div>
              </div>
            </div>
          </div>
        <script>
            var hasImg = true;
            function thumbNailLoadImg(inputFile){
                    if(inputFile.files.length == 1){ // 파일이 있냐
                        //선택된 파일을 읽어들여서 그 영역에 맞는 미리보기
                        //파일을 읽어들일 FileReader 객체 생성
                        var reader = new FileReader();
                        // FileReader객체로부터 파일을 읽어들이는 메소드를 호출
                        //인자값으로 어느 파일을 읽을건지 전달해줌
                        reader.readAsDataURL(inputFile.files[0]);
                        //해당 파일을 읽어들이는 순간 그 파일만의 고유한 url 이 부여된다.
                        //->해당 url을 src속성으로 부여할것(attr)                        
                        //파일 읽기가 완료되었을때 실행할 함수
                        reader.onload = function(e){
                            // e의 target -> e.target -> 이벤트를 발생한 요소
                            //e의 target.result에 각 파일의 url 이 담김.
                            //각 영역에 맞춰 이미지 미리보기
                            $("#titleImg").attr("src", e.target.result);                            
                        };
                    }else{                        
                            $("#titleImg").attr("src", null);                               
                    }
            }

            function cookStepImg(inputFile){
                
                if(inputFile.files.length == 1){
                    var reader = new FileReader();
                    reader.readAsDataURL(inputFile.files[0]);
                    reader.onload = function(e){
                        $(inputFile).parent().prev().children().children().attr("src",e.target.result);                                                        
                    }
                }else{
                    $(inputFile).parent().prev().children().attr("src",null);
                }
            }
            
            $(document).on("click", "#indexGiveNamebtn", function(){
                        
                        $(".cookStepImgTag").each(function(index, item){                        
                            if($(item).attr("src") == null){
                                $("#modalBtn").click();    
                                hasImg = false;
                                return false;
                            }
                        });
    
                        if(hasImg == false){                        
                            hasImg = true;
                            return false;
                        }
    
    
    
                        $(".cookStepFile").each(function(index, item){
                            $(item).attr("name", "cookStepFile" + index);
                            
                        })
    
    
                        $(".productName").each(function(index, item){
                            if($(item).val()){
                                $(item).attr("name", "productName");
                            }
                            
                        })
                        $(".productWeight").each(function(index, item){
                            if($(item).val()){
                                $(item).attr("name", "productWeight");
                            }
                        })
    
                        $(".sauceName").each(function(index, item){
                            if($(item).val()){
                                $(item).attr("name", "sauceName");
                            }
                        })
                        $(".sauceWeight").each(function(index, item){
                            if($(item).val()){
                                $(item).attr("name", "sauceWeight");
                            }
                        })
    
                        $(".cookStepIntro").each(function(index, item){
                            if($(item).val()){
                                $(item).attr("name", "cookStepIntro");
                            }
                        })
    
                        
                        $("#submitRecipan").click();
                    });
                    
            $(function(){
                
                $("#titleImg").click(function(){
                        $("#thumbnailFile").click(); 
                })                
                
                

                $(document).on("click", ".imgDiv", function(){
                    
                    $(this).parent().next().children().click();
                })


                $("#addProductTableBtn").on("click",function(){
                    $("#productTable").append('<tr><td><input type="text" name="" class="productName" placeholder="재료명" required></td><td><input type="text" name="" class="productWeight" placeholder="재료양(단위까지 입력)" required></td><td><button type="button" class="removeProductBtn btn btn-secondary">-</button></td></tr>');
                })   
                $("#addSauceTableBtn").on("click",function(){
                    $("#sauceTable").append('<tr><td><input type="text" name="" class="sauceName" placeholder="소스명" required></td><td><input type="text" name="" class="sauceWeight" placeholder="소스양(단위까지 입력)" required></td><td><button type="button" class="rmoveSauceBtn btn btn-secondary">-</button></td></tr>');
                })     
                $(document).on("click",".removeProductBtn", function(){
                    console.log($(this).parent());
                    $(this).parent().parent().remove();
                });
                $(document).on("click",".rmoveSauceBtn", function(){
                    console.log($(this).parent());
                    $(this).parent().parent().remove();
                });


                
                


                $("#addRecipanCookStepBtn").on("click",function(){
                    $("#recipanCookStepTable").append('<tr><th><div class = "imgDiv"><img src="<%=request.getContextPath()%>/views/resource/sam2.PNG" alt="cookStep" width="100%" height="100%" class="cookStepImgTag"></div></th><td><input type="file" hidden name="" class="cookStepFile"  onchange="cookStepImg(this);"></td><td><textarea name="" class="cookStepIntro" cols="45" rows="10" style="resize: none;" required></textarea></td><td><button class="rmoveCookStepBtn btn btn-secondary">-</button></td></tr>');

                })   
                   
                $(document).on("click",".rmoveCookStepBtn", function(){                    
                    $(this).parent().parent().remove();
                });
                
               
                         
            })
        </script>       
        <input type="text" name="peWriter" hidden value="<%=loginUser.getUserId()%>">
        <input type="text" name="recipanType" hidden value="2"> 
        
    </form>
    </div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>