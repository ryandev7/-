<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.product.model.vo.Product, com.kh.common.model.vo.PageInfo" %>
<%
	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식재료 목록보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
    
    #cat{
        margin-top: 20px;
        margin-left: auto;
        margin-right: auto;
        width: 1000px;
        table-layout: fixed;
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

    
    
    /* 이미지 */
	img {
		width: 200px;
		height: 150px;
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

	.thumbnail:hover{
		cursor: pointer;
		opacity: 0.6
	}

    .thumbnail>p{
        font-size: 13px;
    }

    /* 작성 */
    .enroll-btn{
        padding-right: 10px;
        padding-left: 10px;
        padding-top: 3px;
        padding-bottom: 3px;        
        text-decoration: none !important;
        font-size: 15px;
        background-color: #292344;
        color: white;
        border-radius: 5px;
    }
	.enroll-btn:hover {
		text-decoration: none;
		color: #ed6f65;
	} 

    /* 페이징바 */
    .preBtn {
        border-radius: 5px;
        outline: none;
        background-color: lightgray;
        border: none;
        width: 25px;
        height: 35px;
    }

    .pageBtn {
        border-radius: 5px;
        outline: none;
        background-color: lightgray;
        border: none;
        width: 25px;
        height: 35px;
    }
   
    .nextBtn {
        border-radius: 5px;
        outline: none;
        background-color: lightgray;
        border: none;
        width: 25px;
        height: 35px;
    }

    .nowBtn {
        border-radius: 5px;
        outline: none;
        background-color: #596c9c;
        color: white;
        border: none;
        width: 25px;
        height: 35px;
    }

</style>
</head>
<body>
	<%@include file="../common/menubar.jsp" %>

<!-- 카테고리 -->
<div id="content">
    <table id="cat">
        <tr>
            <div id="cate">
                <th>재료별</th>
                <td><input type="radio" class="category1" name="pro" id="proAll" checked="checked"><label for="proAll" id="proAll" onclick="cpage(0)">전체</label></td>
                <td><input type="radio" class="categor2" name="pro" id="vegetable"><label for="vegetable" id="vegetable" onclick="cpage(-1)">야채·채소</label></td>
                <td><input type="radio" class="category3" name="pro" id="fruit"><label for="fruit" id="fruit" onclick="cpage(-2)">과일</label></td>
                <td><input type="radio" class="category4" name="pro" id="sea"><label for="sea" id="sea" onclick="cpage(-3)">해·수산</label></td>
                <td><input type="radio" class="category5" name="pro" id="meat"><label for="meat" id="meat" onclick="cpage(-4)">정육·계란</label></td>
                <td><input type="radio" class="category6" name="pro" id="proEct"><label for="proEct" id="proEct" onclick="cpage(-5)">기타</label></td>
            </div>
        </tr>
        <tr>
            <td colspan="7" style = "text-align: right;">   
                <select name="list">
                  <option value="최신순">최신순</option>
                  <option value="제목순">제목순</option>
                  <option value="추천순">추천순</option>
              </select>
            </td>
        </tr>
        <tr>
            <td colspan="7" style = "text-align: right;"> 
            <% if(loginUser != null && loginUser.getUserId().equals("admin")) { %>
                    <a href="<%= contextPath %>/enrollForm.pr" class="enroll-btn">식재료 등록</a>
            <% } %>
        </tr>
    </table>




    <!-- 목록 -->
    <div class="list-content">
		<div class="list-area" align="center">
            <div class="here">

            </div>
		</div>
    </div>

    <br>
    <br>


    <!-- 페이징 바 -->
    <div align="center" class="paging-area">

    </div>

</div>
<script>
	$(function(){
				console.log("이건 전체 카테고리 클릭시 불러오는 ajax");
				$.ajax({
					url : "plist.all?cpage=" + 1,
					success :  function(product){
						var list = "";
						if(product[0] != null) {
							for(var i=0; i<product[0].length; i++) {
								list +=
								'<div class="thumbnail" align="center">' +
								'<input type="hidden" value=' + product[0][i].prNo + '>'+
								'<img src="' + product[0][i].titleImg + '">' +
								'<p>'+
								product[0][i].prName+
								'<br>' +
								'</p>' +
								'</div>'
							}
						} else {
							list = "등록된 식재료가 없습니다"
						}
						$(".here").html(list);

						var page = "";
						
						if(product[1][0].currentPage != 1) {
						page += '<button class="preBtn" onclick="cpage('+ (product[1][0].currentPage - 1) +')">' + '&lt;' + '</button>';
						} 
						for(var i = product[1][0].startPage; i <= product[1][0].endPage; i++) { 
							if(i != product[1][0].currentPage) {
								page += '<button class="pageBtn" onclick="cpage('+ i +')">' + i + '</button>'; 
							} else {
								page += '<button class="nowBtn" disabled>'+ i +'</button>'; 
							} 
						} 
						if(product[1][0].currentPage != product[1][0].maxPage) {
							page += '<button class="nextBtn" onclick="cpage('+ (product[1][0].currentPage + 1) +')">' + '&gt;' +'</button>';
						}
						
						$(".paging-area").html(page);
					},
					error : function(){
						console.log("게시글 읽어오기 실패")
					}
			
		

		});
	});

		function cpage(a){
			if(a == 0) {
				console.log("이건 전체 카테고리 클릭시 불러오는 ajax");
				$.ajax({
					url : "plist.all?cpage=" + 1,
					success :  function(product){
						var list = "";
						if(product[0] != null) {
							for(var i=0; i<product[0].length; i++) {
								list +=
								'<div class="thumbnail" align="center">' +
								'<input type="hidden" value=' + product[0][i].prNo + '>'+
								'<img src="' + product[0][i].titleImg + '">' +
								'<p>'+
								product[0][i].prName+
								'<br>' +
								'</p>' +
								'</div>'
							}
						} else {
							list = "등록된 식재료가 없습니다"
						}
						$(".here").html(list);

						var page = "";
						
						if(product[1][0].currentPage != 1) {
						page += '<button class="preBtn" onclick="cpage('+ (product[1][0].currentPage - 1) +')">' + '&lt;' + '</button>';
						} 
						for(var i = product[1][0].startPage; i <= product[1][0].endPage; i++) { 
							if(i != product[1][0].currentPage) {
								page += '<button class="pageBtn" onclick="cpage('+ i +')">' + i + '</button>'; 
							} else {
								page += '<button class="nowBtn" disabled>'+ i +'</button>'; 
							} 
						} 
						if(product[1][0].currentPage != product[1][0].maxPage) {
							page += '<button class="nextBtn" onclick="cpage('+ (product[1][0].currentPage + 1) +')">' + '&gt;' +'</button>';
						}
						
						$(".paging-area").html(page);
					},
					error : function(){
						console.log("게시글 읽어오기 실패")
					}
				})
			}
			else if(a == -1){
				console.log("이건 야채 카테고리 클릭시 불러오는 ajax");
				$.ajax({
	            url : "plist.veg?cpage=" + 1,
				success :  function(product){
	                var list = "";
					if(product[0] != null) {
							for(var i=0; i<product[0].length; i++) {
								list +=
								'<div class="thumbnail" align="center">' +
								'<input type="hidden" value=' + product[0][i].prNo + '>'+
								'<img src="' + product[0][i].titleImg + '">' +
								'<p>'+
								product[0][i].prName+
								'<br>' +
								'</p>' +
								'</div>'
							}
						} else {
							list = "등록된 식재료가 없습니다"
						}


	                $(".here").html(list);


					var page = "";
					
					if(product[1][0].currentPage != 1) {
						page += '<button class="preBtn" onclick="cpage('+ (product[1][0].currentPage - 1) +')">' + '&lt;' + '</button>';
					} 
					for(var i = product[1][0].startPage; i <= product[1][0].endPage; i++) { 
						if(i != product[1][0].currentPage) {
							page += '<button class="pageBtn" onclick="cpage('+ i +')">' + i + '</button>'; 
						} else {
							page += '<button class="nowBtn" disabled>'+ i +'</button>'; 
						} 
					} 
					if(product[1][0].currentPage != product[1][0].maxPage) {
						page += '<button class="nextBtn" onclick="cpage('+ (product[1][0].currentPage + 1) +')">' + '&gt;' +'</button>';
					}
					
	                $(".paging-area").html(page);	                
	                
	            },
				error : function(){
						console.log("게시글 읽어오기 실패")
					}
				});
			}
		
			else if(a == -2){
				console.log("이건 과일 카테고리 클릭시 불러오는 ajax");
				$.ajax({
	            url : "plist.fru?cpage=" + 1,
				success :  function(product){
	                var list = "";
						if(product[0] != null) {
							for(var i=0; i<product[0].length; i++) {
								list +=
								'<div class="thumbnail" align="center">' +
								'<input type="hidden" value=' + product[0][i].prNo + '>'+
								'<img src="' + product[0][i].titleImg + '">' +
								'<p>'+
								product[0][i].prName+
								'<br>' +
								'</p>' +
								'</div>'
							}
						} else {
							list = "등록된 식재료가 없습니다"
						}


	                $(".here").html(list);

					var page = "";
					
					if(product[1][0].currentPage != 1) {
						page += '<button class="preBtn" onclick="cpage('+ (product[1][0].currentPage - 1) +')">' + '&lt;' + '</button>';
					} 
					for(var i = product[1][0].startPage; i <= product[1][0].endPage; i++) { 
						if(i != product[1][0].currentPage) {
							page += '<button class="pageBtn" onclick="cpage('+ i +')">' + i + '</button>'; 
						} else {
							page += '<button class="nowBtn" disabled>'+ i +'</button>'; 
						} 
					} 
					if(product[1][0].currentPage != product[1][0].maxPage) {
						page += '<button class="nextBtn" onclick="cpage('+ (product[1][0].currentPage + 1) +')">' + '&gt;' +'</button>';
					}
					
	                $(".paging-area").html(page);	                
	                
	            },
				error : function(){
						console.log("게시글 읽어오기 실패")
					}
				});
			}
			else if(a == -3){
				console.log("이건 해수산 카테고리 클릭시 불러오는 ajax");
				$.ajax({
	            url : "plist.sea?cpage=" + 1,
				success :  function(product){
	                var list = "";
						if(product[0] != null) {
							for(var i=0; i<product[0].length; i++) {
								list +=
								'<div class="thumbnail" align="center">' +
								'<input type="hidden" value=' + product[0][i].prNo + '>'+
								'<img src="' + product[0][i].titleImg + '">' +
								'<p>'+
								product[0][i].prName+
								'<br>' +
								'</p>' +
								'</div>'
							}
						} else {
							list = "등록된 식재료가 없습니다"
						}


	                $(".here").html(list);

					var page = "";
					
					if(product[1][0].currentPage != 1) {
						page += '<button class="preBtn" onclick="page('+ (product[1][0].currentPage - 1) +')">' + '&lt;' + '</button>';
					} 
					for(var i = product[1][0].startPage; i <= product[1][0].endPage; i++) { 
						if(i != product[1][0].currentPage) {
							page += '<button class="pageBtn" onclick="cpage('+ i +')">' + i + '</button>'; 
						} else {
							page += '<button class="nowBtn" disabled>'+ i +'</button>'; 
						} 
					} 
					if(product[1][0].currentPage != product[1][0].maxPage) {
						page += '<button class="nextBtn" onclick="cpage('+ (product[1][0].currentPage + 1) +')">' + '&gt;' +'</button>';
					}
					
	                $(".paging-area").html(page);	                
	                
	            },
				error : function(){
						console.log("게시글 읽어오기 실패")
					}
				});
			}
			else if(a == -4){
				console.log("이건 정육계란 카테고리 클릭시 불러오는 ajax");
				$.ajax({
	            url : "plist.meat?cpage=" + 1,
				success :  function(product){
	                var list = "";
						if(product[0] != null) {
							for(var i=0; i<product[0].length; i++) {
								list +=
								'<div class="thumbnail" align="center">' +
								'<input type="hidden" value=' + product[0][i].prNo + '>'+
								'<img src="' + product[0][i].titleImg + '">' +
								'<p>'+
								product[0][i].prName+
								'<br>' +
								'</p>' +
								'</div>'
							}
						} else {
							list = "등록된 식재료가 없습니다"
						}


	                $(".here").html(list);

					var page = "";
					
					if(product[1][0].currentPage != 1) {
						page += '<button class="preBtn" onclick="page('+ (product[1][0].currentPage - 1) +')">' + '&lt;' + '</button>';
					} 
					for(var i = product[1][0].startPage; i <= product[1][0].endPage; i++) { 
						if(i != product[1][0].currentPage) {
							page += '<button class="pageBtn" onclick="cpage('+ i +')">' + i + '</button>'; 
						} else {
							page += '<button class="nowBtn" disabled>'+ i +'</button>'; 
						} 
					} 
					if(product[1][0].currentPage != product[1][0].maxPage) {
						page += '<button class="nextBtn" onclick="cpage('+ (product[1][0].currentPage + 1) +')">' + '&gt;' +'</button>';
					}
					
	                $(".paging-area").html(page);	                
	                
	            },
				error : function(){
						console.log("게시글 읽어오기 실패")
					}
				});
			}
			else if(a == -5){
				console.log("이건 기타 카테고리 클릭시 불러오는 ajax");
				$.ajax({
	            url : "plist.etc?cpage=" + 1,
				success :  function(product){
	                var list = "";
						if(product[0] != null) {
							for(var i=0; i<product[0].length; i++) {
								list +=
								'<div class="thumbnail" align="center">' +
								'<input type="hidden" value=' + product[0][i].prNo + '>'+
								'<img src="' + product[0][i].titleImg + '">' +
								'<p>'+
								product[0][i].prName+
								'<br>' +
								'</p>' +
								'</div>'
							}
						} else {
							list = "등록된 식재료가 없습니다"
						}


	                $(".here").html(list);

					var page = "";
					
					if(product[1][0].currentPage != 1) {
						page += '<button class="preBtn" onclick="page('+ (product[1][0].currentPage - 1) +')">' + '&lt;' + '</button>';
					} 
					for(var i = product[1][0].startPage; i <= product[1][0].endPage; i++) { 
						if(i != product[1][0].currentPage) {
							page += '<button class="pageBtn" onclick="cpage('+ i +')">' + i + '</button>'; 
						} else {
							page += '<button class="nowBtn" disabled>'+ i +'</button>'; 
						} 
					} 
					if(product[1][0].currentPage != product[1][0].maxPage) {
						page += '<button class="nextBtn" onclick="cpage('+ (product[1][0].currentPage + 1) +')">' + '&gt;' +'</button>';
					}
					
	                $(".paging-area").html(page);	                
	                
	            },
				error : function(){
						console.log("게시글 읽어오기 실패")
					}
				});
			} 


		} 



		$(document).on("click",".thumbnail",function(){
		var pno = $(this).children().eq(0).val();
		console.log(pno);
		location.href = "<%=contextPath %>/detail.pr?pno=" + pno
	})
	;

</script>


<br>
<br>

	<%@include file="../common/footer.jsp" %>
</body>
</html>