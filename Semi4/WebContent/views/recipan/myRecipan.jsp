<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.recipan.model.vo.Recipan"
    import = "java.util.ArrayList"%>
<%ArrayList<Recipan>list = (ArrayList<Recipan>)request.getAttribute("list"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style>
        body *{
            /* border: 1px solid black; */
            box-sizing: border-box;
            margin : 0 auto;
        }
        #mainCotent{
            width: 1200px;
            overflow: auto;
        }
        .myRecipeForm{
            width: 600px;        
            overflow: auto;            
            margin-bottom : 15px;
            
        }
        .titleImgForm{
            width: 20%;
            height: 100px;
            float: left;
            border : 1px solid gray;
        }
        .introForm{
            width: 70%;
            height: 100px;
            float: left;
            text-align : left;
        }
        .updateForm{
            
            width:10%;
            height: 50px;
            float: left;
        }
        .deleteForm{
            width: 10%;
            height: 50px;
            float: left;
        }
        .updateBtn{
            width: 100%;
            height: 100%;
            background-color: rgb(89, 108, 156);
            color: white;
            border: 0px;
            border-radius : 15%;
            
        }
        .deleteBtn{
            width:100%;
            height: 100%;
            background-color : rgb(237, 111, 101);
            color: white;
            border: 0px;
            border-radius : 15%;
        }
        .titleImg{
            width: 100%;
            height: 100%;            
            
        }
        .introForm{
            font-size: small;
            color: gray;
            font-weight: bold;
        }
        #insertRecipan{
            border-radius: 50%;
            background-color: rgb(41, 35, 68);
            color: white;
            font-size:30pt;
        }
        .titleImgForm>img:hover{
            cursor: pointer;
            opacity: 0.6
	    }
    </style>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
    <div id="mainCotent" style="text-align: center;">
        <div style="width:600px; text-align: left;">
            나의레시판
        </div>
        <%if(list.isEmpty()){%>
        	<h2 style="color : red"> 게시글을 작성해주시면 이곳에 나타납니다 !</h2>
        <%} else{%>
        	<%for(Recipan r : list){%>
	        <div class="myRecipeForm">	        
	        	<input type="hidden" value =<%=r.getPeNo()%>>
	            <div class="titleImgForm">
	                <img class="titleImg" src="<%=r.getTitleImg()%>" alt="">
	            </div>
	            <div class="introForm">
	                	<%=r.getPeIntroduce() %>
	            </div>
	            <div class="updateForm">
	                <button class="updateBtn">수정</button>
	            </div>
	            <div class="deleteForm">
	                <button class="deleteBtn" data-toggle="modal" data-target="#myModal">삭제</button>
	            </div>	            
	        </div>    
            <hr>
	        <%} %>    
        <% }%>

        <div class="modal fade" id="myModal">
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">
              
                <!-- Modal Header -->
                <div class="modal-header">
                  <h4 class="modal-title">Recipan Delete</h4>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body">
                  정말로 삭제 하시겟습니까 ? 
                </div>
                
                <!-- Modal footer -->
                <div class="modal-footer">
                  <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="delteRecipan()">삭제</button>
                </div>
                
              </div>
            </div>
          </div>





		<script>
            var peNo = 0;
            $(function(){
                
                $(document).on("click",".updateBtn", function(){
                    peNo = ($(this).parent().siblings("input").val());
                    // console.log(peNo);
                    location.href = "<%=contextPath%>/updateForm.pan?peNo="+peNo;
                    // console.log("<%=contextPath%>/update.pan?peNo= " +  ($(this).parent().siblings("input").val()));
                })
                $(document).on("click",".deleteBtn", function(){
                    peNo = ($(this).parent().siblings("input").val());
                    
                    // location.href = "<%=contextPath%>/delete.pan?peNo="+peNo;
                    
                })
                $(document).on("click", ".titleImgForm", function(){
                    peNo = ($(this).siblings("input").val());
                    location.href = "<%=contextPath%>/detail.pan?peNo=" + peNo;
                })  
            })
            function delteRecipan(){
                console.log("<%=contextPath%>/delete.pan?peNo="+peNo);
                location.href = "<%=contextPath%>/delete.pan?peNo="+peNo;
            }
			
		</script>            
        <a href="<%=contextPath%>/enrollForm.pan" class="material-icons" id="insertRecipan">add</a>
    </div>
	<%@ include file="../common/footer.jsp" %>    
</body>
</html>