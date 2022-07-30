<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=IBM+Plex+Sans+KR:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<title>혼자사조</title>
<style>
	div{
		box-sizing: border-box;
        border : solid 1px rgba(255, 0, 0, 0)
	}
	.wrap{
		height : 1200px;
		width : 1200px;          
		margin : auto;
	}
	#header{
		height:10%;    
	}
	#navigator2{height : 5%;}
	#content{
		height: 100%;
	}
	#header>div{
		height : 100%;
		float:left;                        
	}
    #footer{
        float: bottom;
        width: 1200px;
    }

	
	#content_1{
		height : 40%;
	}
	#content_2{
		height : 35%;
	}
	#content_3{
		width: 100%; height : 25%;
	}
	#content_3_1{height : 100%; width: 70%; float: left;}
	#content_3_2{height : 100%; width: 30%; float: left;}

    /*스와이퍼 관련 CSS */
	.swiper {
        width: 100%;
        height: 70%;
    }

    .swiper-slide {
        text-align: center;
        font-size: 18px;

        /* Center slide text vertically */
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
	}
    .swiper-slide img {
        display: block;
        width: 200px;
        height: 200px;
        object-fit: cover;
    }

    .swiper-button-prev {
        color: #ed6f65; 
    }

    .swiper-button-next {
        color: #ed6f65; 
    }
    
    /*그외*/
    .table {
        margin-top: 40px;
        text-align: center;
        font-family: "Do Hyeon";
    }
    th {
        font-size: 23px;
    }
    td{
        font-size: 18px;
        font-family: "IBM Plex Sans KR";
    }
    
    h1 {
        padding-top: 60px;
        margin-left: 30px;
        font-family: "Do Hyeon";
        color: #292344;

    }
    #recipePic {
        margin-top: 100px;
        margin-left: 380px;
        width: 450px;
        height: 380px;
    }

    #recipanBtn {
        position: absolute;
        margin-left: 70px;
        margin-top: 40px;
        width: 200px;
        height: 180px;
    }

    .peTitle :hover{
        cursor: pointer;
    }


</style>
</head>
<body>

	<div class="wrap">
        <div id ="header">
         <div id="navigator2">
            <%@ include file="views/common/menubar.jsp" %>
         </div>
        </div>
        <div id = "content">
            <div id = content_1>
                    <img id="recipePic" src="http://drive.google.com/uc?export=view&id=1OJprytLvHgBFizDGz_tQWPdpZ5oOeGlz">
            </div>
            <div id = content_2>
             <h1>새로운 레시피</h1>
             <!-- Swiper -->
             <div class="swiper mySwiper">
               <div class="swiper-wrapper" id="here">
               

               </div>
               <div class="swiper-button-next"></div>
               <div class="swiper-button-prev"></div>
               <div class="swiper-pagination"></div>
              </div>
            </div>
            <div id = content_3>
                <div id="content_3_1">
                    <table class="table">
                        <thead class="table-danger">
                          <tr>
                            <th>랭킹</th>
                            <th>제목</th>
                            <th>작성자</th>
                          </tr>
                        </thead>
                        <tbody id="rankList">
                        </tbody>
                      </table>
                </div>
                <div id="content_3_2">
                    <a href="<%=contextPath %>/list.pan?cpage=1">
                        <img id="recipanBtn" src="http://drive.google.com/uc?export=view&id=1kQo5whhYxMXBFS9OAkiDn3QypG5ijEIH">
                    </a>
                </div>
            </div>
                
        </div>

   
      <script>
        function selectRecipanList(){
            $.ajax({
                url : "pan.list",
                success : function(atList){
                    var result = "";
                    for(var i = 0; i < 8; i++){
                        result += '<div class="swiper-slide">' 
                                + '<img class="pic" src="'
                                + '<%= contextPath %>/'
                                + atList[i].filePath
                                + atList[i].changeName
                                + '">'
                                + '<input type="hidden" value=' + atList[i].peNo + '>'
                                + '</div>';
                    }
                    var swiper = new Swiper(".mySwiper", {
                    slidesPerView: 3.5,
                    spaceBetween: 30,
                    pagination: {
                        el: ".swiper-pagination",
                        clickable: true,
                    },
                    navigation: {
                        nextEl: ".swiper-button-next",
                        prevEl: ".swiper-button-prev",
                    },
                    });

					$("#here").html(result);

                },
                error : function(){
						console.log("게시글 읽어오기 실패")
				}
			
            });
        };

        function selectRecipanRank(){
            $.ajax({
				url : "rank.list",
				success : function(list){
					
					var result = "";
					for(var i = 0; i < 3; i++) {
                            result += "<tr>"
                                    + "<td>" + '<span>' + (parseInt(i)+1) + '</span>' + "</td>"
                                    + "<td class='peTitle'>" + '<span>' + list[i].peTitle + '</span>' + "</td>"
                                    + "<td>"+ '<span>' +list[i].nickName + '</span>' + "</td>"
                                    + '<input type="hidden" value=' + list[i].peNo + '>'+
                                    + "</tr>";
                    }
					
                    $("#rankList").html(result);
				},
				error : function(){
					console.log("읽어오기 실패");
				}
			});
        };
    
        $(function(){
            selectRecipanList()
			selectRecipanRank();
			setInterval(selectRecipanRank, 1000);
		});

        $(document).on("click",".pic",function(){
		var peNo = $(this).next().val();
		console.log(peNo);
		location.href = "<%=contextPath %>/detail.pan?peNo=" + peNo
	    })

        $(document).on("click",".peTitle",function(){
		var peNo = $(this).next().next().val();
		console.log(peNo);
		location.href = "<%=contextPath %>/detail.pan?peNo=" + peNo
	    })



      </script>

      <!-- content -->

    </div>
    <div id="footer" style="overflow: hidden;">
        <%@ include file="views/common/footer.jsp" %>
       </div>
</body>
</html>