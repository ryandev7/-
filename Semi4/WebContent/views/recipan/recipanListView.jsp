<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.util.ArrayList" import="com.kh.recipan.model.vo.Recipan"
    import="com.kh.common.model.vo.PageInfo" import="com.kh.common.model.vo.Category" %>

<% ArrayList<ArrayList<Category>> categoryList = (ArrayList<ArrayList<Category>>)request.getAttribute("categoryList");%>

    <!-- ArrayList<Recipan> recipan = (ArrayList<Recipan>)request.getAttribute("recipan"); -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 상세보기</title>
<style>
    
    #cat{
        margin-top: 20px;
        margin-left: auto;
        margin-right: auto;
        width: 1000px;
        table-layout: fixed;
        /* border-bottom: 1px solid grey; */
    }
    #cat td{
        text-align: center;
    }
    #cat th{
        border-right: 1px solid black;
    }
    input[type="radio"] {

        position: absolute;
        width: 1px;
        height: 1px;
        padding: 0;
        margin: -1px;
        overflow: hidden;
        clip:rect(0,0,0,0);
        border: 0

    }
    
    input[type="radio"] + label {
        text-align: center;
        font-size: 13px;
    }

    input[type='radio']:checked+label {
        padding-right: 7px;
        padding-left: 7px;
        background: #292344;
        border-radius: 5px;
        color: white;
        font-size: 15px;
    }
    /* 정렬 버튼 */
    .list-align>select {
       margin-right: 150px;
    }
    
    
    
	.list-area{
		width: 1000px;
		margin: auto;
	}

	.thumbnail{
		width: 200px;
		display: inline-block;
        margin-top: 20px;
        margin-left: 30px;
		margin-right: 30px;
	}

	.imgdiv>img:hover{
		cursor: pointer;
		opacity: 0.6
	}
    
    

    .like_btn{            
            color: rgb(237, 111, 101);
            text-align: center;
            border-radius: 50%;
            width: 25px;
            height: 25px;
            cursor: pointer;
            -webkit-user-select:none;
            -moz-user-select:none;
            -ms-user-select:none;
            user-select:none;
    }

    .imgdiv{
        border : 2px solid #292344;    
    }
</style>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
	<%@include file="../common/menubar.jsp" %>

<div id="content">
    <table id="cat">
        <tr>
             <th>나라별</th>
             <td><input type="radio" name="nation" id="natAll" value="0" checked="checked"><label for="natAll">전체</label></td>
             <%if(!categoryList.get(2).isEmpty()){ %>                
                <%for(Category c : categoryList.get(2)){ %>
                
                    <%if(c.getCategoryName().equals("기타")){%>
                        <td><input type = "radio" name = "nation" id="natEct" value="<%=c.getCategoryNo()%>">
                            <label for ="natEct"> <%=c.getCategoryName() %></label></td>
                            
                    <%}else{%>
                        <td><input type = "radio" name = "nation" id="<%=c.getCategoryName() %>" value=<%= c.getCategoryNo() %>>
                            <label for ="<%=c.getCategoryName() %>"> <%=c.getCategoryName() %></label></td>
                    <%}%>
                    
                <%} %>                                                                                
            <%} %>             
        </tr>
        <tr>
            <th>종류별</th>
            <td><input type="radio" name="kind" id="kindAll" value="0" checked="checked"><label for="kindAll">전체</label></td>
             <%if(!categoryList.get(1).isEmpty()){ %>                
                <%for(Category c : categoryList.get(1)){ %>
                    <%if(c.getCategoryName().equals("기타")){%>
                        <td><input type = "radio" name = "kind" id="kindEct" value="<%=c.getCategoryNo()%>">
                            <label for ="kindEct"> <%=c.getCategoryName() %></label></td>                            
                    <%}else{%>
                        <td><input type = "radio" name = "kind" id="<%=c.getCategoryName() %>" value=<%= c.getCategoryNo() %>>
                            <label for ="<%=c.getCategoryName() %>"> <%=c.getCategoryName() %></label></td>
                    <%}%>
                <%} %>                                                                                
            <%} %>      
        </tr>
        <tr>
            <th>재료별</th>
            <td><input type="radio" name="product" id="productAll" value="0" checked="checked"><label for="productAll">전체</label></td>
             <%if(!categoryList.get(0).isEmpty()){ %>                
                <%for(Category c : categoryList.get(0)){ %>
                    <%if(c.getCategoryName().equals("기타")){%>
                        <td><input type = "radio" name = "product" id="proEtc" value="<%=c.getCategoryNo()%>">
                            <label for ="proEtc"> <%=c.getCategoryName() %></label></td>
                    <%}else{%>                    
                        <td><input type = "radio" name = "product" id="<%=c.getCategoryName() %>" value=<%= c.getCategoryNo() %>>
                                <label for ="<%=c.getCategoryName() %>"> <%=c.getCategoryName() %></label></td>
                    <%}%>
                <%} %>                                                                                
            <%} %>
        </tr>
        <tr>
            <td colspan="7" style = "text-align: right;">
                
                <select id="orderBy">
                    <option value="1">최신순</option>
                    <option value="2">제목순</option>
                    <option value="3">추천순</option>
                </select>
                
            </td>
        </tr>
    </table>

    
    

    <%if(loginUser != null){%>
    <input type="hidden" id = "loginUserId" value = "<%=loginUser.getUserId()%>">
    <%}%>
    <div class="list-content" id = "list-content">
                    
	</div>
    
    
    <br>


    <div class="paging-area" id="paging-area" align="center">

    </div>

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


</div>
<script>
	$(document).on("click", ".thumbnailImg", function(){
        location.href = "<%=contextPath%>/detail.pan?peNo=" + $(this).parent().siblings("input").val();
    })
        
    
    var nation = 0;
    var kind = 0;    
    var product = 0;
    var cpage = 1;
    var orderBy = 1;
    var peNo = 0;

    $("input[name=nation]").click(function(){
        nation = $(this).val();
        aJaxloadToList();
    })    
    $("input[name=kind]").click(function(){
        kind = $(this).val();
        aJaxloadToList();
    })
    $("input[name=product]").click(function(){
        product = $(this).val();
        aJaxloadToList();
    })
    $("#orderBy").change(function(){
        orderBy = $(this).val();
        aJaxloadToList();
    })


    
    
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
        

    function delteRecipan(){
        console.log("<%=contextPath%>/delete.pan?peNo="+peNo);
        location.href = "<%=contextPath%>/delete.pan?peNo="+peNo;
    }


    $(function(){        
        aJaxloadToList();        
    })
    function aJaxloadToList(page){
        $("#list-content").empty();
        var count = 0;
        var selectBtn = 0;
        var JqAjaxUrl ="";
        if(page){
            cpage = page;
        }
        
        <%if(loginUser != null){%>
        JqAjaxUrl = "<%=contextPath%>/list.ajax?cpage=" + cpage +"&nation=" + nation +"&kind="+ kind +"&product="+product + "&orderBy=" + orderBy + "&userId=" + "<%=loginUser.getUserId()%>";
        <%}else{%>
        JqAjaxUrl = "<%=contextPath%>/list.ajax?cpage=" + cpage +"&nation=" + nation +"&kind="+ kind +"&product="+product + "&orderBy=" + orderBy;
		<%}%>
        $.ajax({
            url : JqAjaxUrl,
            method : "GET",
            success : function(recipan){
            	var count = 0;
                var selectBtn = 0;
                var result;
                
                if(Array.isArray(recipan[0]) && recipan[0].length == 0){
                    $("#list-content").append('<div style="width:100%; height:100%; color:red;">조회된 게시글이 없습니다.</div>');
                }else{
                    for(var height = 0; height < (Math.floor(recipan[0].length/3) + 1); height++){
                        result = '<div class="list-area" align="center">';
                            for(var width = count; width < count + 3; width++){
                                if(width == recipan[0].length) break;
                                result += '<div class="thumbnail" align="center">';
                                result +='<input type="hidden" value = "' + recipan[0][width].peNo + '">';
                                result += '<div class = "imgdiv" style = "width : 200px; height : 150px;">';
                                result +='<img src="'+recipan[0][width].titleImg+'" class="thumbnailImg" style="width:100%; height:100%;">';
                                result += '</div>';
                                result +='<p style="margin-bottom: 0px;">'+recipan[0][width].peTitle +'</p>';
                                
                                result += '<div style = "height : 30px;">'
                                
                                
                                result +='<div class="material-symbols-outlined like_btn">favorite';
                                result +='<input type="hidden" value="true">';                               
                                result +='</div>'
                                result += '<div style = "height : 100%; display : inline-block;">' + recipan[0][width].peLikeCount + '</div>';
                                
                                <%if(loginUser != null){%>
                                	if((recipan[0][width].peWriter === "<%=loginUser.getUserId()%>") || ("<%=loginUser.getUserId()%>" === "admin")){
	                                	result += '<button class="updateBtn" style ="height : 100%; background-color : rgb(89, 108, 156); color : white; float : left; border-radius : 15%; border : 0px">수정</button>'
	                                	result += '<button class="deleteBtn" data-toggle="modal" data-target="#myModal" style ="height : 100%; background-color : rgb(237, 111, 101); color : white; float : right; border-radius : 15%; border : 0px;">삭제</button>'
                                	}
                                <%}%>
                                result += '</div>';
                                result +='</div>';
                                 
                                
                            }
                                                        
                            result += '</div>';                 
                            
                            $("#list-content").append(result);
                            for(var width = count; width < count + 3; width++){
                            	
                            	if(width == recipan[0].length) break;
                            	
	                            if(recipan[0][width].likeStatus != null){
	                            
	                                if(recipan[0][width].likeStatus == "Y" && <%=loginUser != null%>){
	                                    $(".like_btn").eq(selectBtn).css("font-variation-settings", "'FILL' 1");
	                                    $(".like_btn").eq(selectBtn).children("input").val("false");
	                                }                                    
	                            }
	                            selectBtn++;
                            }
                            count+=3;
                    }
                }
                
                
                $("#paging-area").empty();
                console.log(recipan[1][0]);
                if(recipan[1][0].currentPage != 1){
                    $("#paging-area").append('<button class = "btn btn-sm btn-info" style = "background-color : #292344" onclick="aJaxloadToList(' + (recipan[1][0].currentPage - 1) + ')" >&lt;</button>');
                }
                var result = "";
                for(var i = recipan[1][0].startPage; i <= recipan[1][0].endPage; i++){
                    console.log(i);
                    if(i != recipan[1][0].currentPage){
                        result +=  '<button class ="btn btn-sm btn-info" style = "background-color : #292344" onclick="aJaxloadToList(' + i + ')">'+ i +'</button>';
                    }else{
                        result +=  '<button class="btn btn-sm btn-primary" style = "background-color : #292344" disabled>' + i + '</button>'
                    }
                }
                $("#paging-area").append(result);
	            if(recipan[1][0].currentPage < recipan[1][0].maxPage){
                    $("#paging-area").append('<button class = "btn btn-sm btn-info" style = "background-color : #292344" onclick="aJaxloadToList(' + (recipan[1].currentPage + 1) +')">&gt;</button>');
                }
	            	        	            
                
                
            },
            error : function(){
                alert("통신 실패.")
            }
        });
    }

        
        
        // $("input[name=nation]").click(function(){
        //     console.log($(this).val());
        // })
        // $("input[name=kind]").click(function(){
        //     console.log($(this).val());
        // })
        // $("input[name=product]").click(function(){
        //     console.log($(this).val());
        // })
    
        
    
        
    
    $(document).on("click",".like_btn",function(){
        <%if(loginUser != null){%>
           if($(this).children("input").val() == "true"){            
               $(this).css("font-variation-settings", "'FILL' 1");
               $(this).children("input").val("false");
               console.log($(this).children("input").val());
               
               $.ajax({
                   url : "like.pan",
                   data : {
                       peNo : $(this).parent().siblings("input").val(),
                       userId : $("#loginUserId").val()
                   },
                   type : "post",
                   success : function(){             	   
                       alert("좋아요 성공 ~");
                       window.location.reload()
                   },
                   error : function(){
                       alert("실패 ㅠ");
                   }
               })
           }
           else{
               $(this).css("font-variation-settings", "'FILL' 0");
               $(this).children("input").val("true");

               $.ajax({
                   url : "likeCancel.pan",
                   data : {
                       peNo : $(this).parent().siblings("input").val(),                         
                       userId : $("#loginUserId").val()
                   },
                   type : "post",
                   success : function(){                	   
                       alert("취소 성공 ~");
                       window.location.reload()
                       
                   },
                   error : function(){
                	   
                       alert("실패 ㅠ");
                   }
               })
           }
        <%}else{%>
           alert("로그인을 하지않고 무언가를 좋아하는건, 무언가에게 상처가 될 수 있습니다.");
        <%}%>
    });  
    
</script>
<br>
<br>

	<%@include file="../common/footer.jsp" %>
</body>
</html>