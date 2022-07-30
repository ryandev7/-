<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.model.vo.Attachment"%>
<%@page import="com.kh.product.model.vo.Product"%>
<%@page import="com.kh.product.model.vo.Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Product p = (Product)request.getAttribute("p");
  Attachment atTitle = (Attachment)request.getAttribute("atTitle");
  Attachment atContent = (Attachment)request.getAttribute("atContent");
  ArrayList<Attachment> rcpAt =  (ArrayList<Attachment>)request.getAttribute("rcpAt");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<title>재료 상세페이지</title>

<style>

    .outer{
        width: 1200px;
        margin: auto;
    }

    #header1{
        width: 50%;
        height: 350px;
        float: left;
    }
    #header2{
        width: 50%;
        height: 350px;
        float: left;
    }

    #mainimg{
        border: 1px solid black;
    }

        
    a{
        text-decoration: none;
    }
    #together{
        width: 100%;
        float: left;
        
    }
    #cartbtn{
        border: 2px solid #292344;
        border-radius: 10px;
        background-color: #292344;
        color: white;
        font-size: 15pt;
        padding: 10px 35px 10px 35px;
      }


    .swiper {
        width: 100%;
        height: 100%;
      }

    /*스와이퍼 관련 CSS */
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
    .delQna, .delReview, .productDeleteBtn {
      margin-left: 10px;
      border: none;
      background-color: #ed6f65;
      color: white;
      border-radius: 5px;
    }
    .productEditBtn {
      margin-left: 10px;
      border: none;
      background-color: #596c9c;
      color: white;
      border-radius: 5px;
    }
    .ansInsert{
      margin-left: 10px;
      border: none;
      background-color: #596c9c;
      color: white;
      border-radius: 5px;
    }


    /* 아코디언 */
.panel-faq-container, .panel-faq-container2 {
  margin-bottom: -16px;
}
.panel-faq-title, .panel-faq-title2 {
  color: black;
  cursor: pointer;
}
.panel-faq-answer, .panel-faq-answer2 {
  height: 0;
  overflow: hidden;
  /* 변화가 시작되는 쪽에다가 transition 적용해준다 0 -> 300px 
  왜? 닫기 버튼을 누를 때 변화가 티남 */
  transition: all 1s;
}

.active {
  display: block;
  /* 높이를 정해줘야지만 transition이 적용됨 */
  height: 300px;
}
.text-muted{
  font-size: 15px;
}
#reviewInsert, #qnaInsert{
  float: right;
  background-color: #596c9c;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px;
}


.a {
  list-style-type: none;
  padding: 0;
  margin: 0;
  overflow: hidden;
  background-color: white;
  position: -webkit-sticky; /* Safari */
  position: sticky;
  top: 0;
  height: 50px;
  border-bottom: 1px solid black;

}

.navi {
  float: left;
}

.navi a {
  display: block;
  color: black;
  font-size: 20px;
  font-weight: 600;
  text-align: center;
  text-decoration: none;
  padding: 30px 60px 30px 60px;
  background-color: rgb(223, 223, 223);

  
}

.navi a:hover {
  background-color: white;
}

.navinfo{
  float: none;
  position: absolute;
  top: 50%;
  left: 400px;
  transform: translate(-50%, -50%);

}
.navreview{
  float: none;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-left: 1px solid black;
  border-right: 1px solid black;
}
.navqna{
  float: none;
  position: absolute;
  top: 50%;
  left: 801px;

  transform: translate(-50%, -50%);
}
.pagebtn{
  border : none;
  font-weight: 600;
  margin: 2px;
  width: 28px;
  border-radius: 10%;
  
}
#selectedPageBtn{
  background-color: #596c9c;
  border-radius: 10%;
}

.qtyBtnL{
  font-weight: 600;
  margin: -5px;
  width: 30px;
}
.qtyBtnC{
  font-weight: 600;
  text-align:center; 
  width:40px;
}
.qtyBtnR{
  font-weight: 600;
  margin: -5px;
  width: 30px;
}
.rcpImg{
  width: 200px;
  height: 200px;
}
.rcpImg:hover{
  cursor: pointer;
  opacity: 0.6;
  
}




   

</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
        
    
	<div class="outer">
		<div id="header1" align="center">
            <br> 
            <br>
            <img src="<%= contextPath %>/<%= atTitle.getFilePath()%>/<%=atTitle.getChangeName()%>" id="mainimg" width="200px" height="200px"> <br><br>
            <% if (rcpAt.size() > 0){ %>
              <img src="http://drive.google.com/uc?export=view&id=13x_d8DhFUFL41nywn2Faga9R3lsLQLwu" width="200px" alt=""> 
              <% } %>

        </div>
        <div id="header2">
            <table width="300px">
                <tr>
                  <td>&nbsp;</td>
                </tr>
                <tr height="50px">
                    <td><h4><b><%= p.getPrName() %></b></h4></td>
                    <td>
                      <% if (loginUser != null && loginUser.getUserId().equals("admin")){ %>
                        <button class="productEditBtn">상품수정</button>
                        <button class="productDeleteBtn">상품삭제</button>
                        <% } %>
                    </td>
                </tr>
                <tr>
                  <form name="form" action="<%= contextPath %>/cartInsert.ca" method="post">
                    <% if (loginUser != null) { %>
                    <input type="hidden" name="userId" value="<%= loginUser.getUserId() %>">
                    <input type="hidden" name="pno" value="<%= p.getPrNo() %>">
                    <% } %>
                    <td>판매가</td>
                    <td id="proPrice"></td>
                </tr>
                <tr>
                    <td width="100px">중량/용량</td>
                    <td><%= p.getPrWeight() %></td>
                </tr>
                <tr>
                    <td>원산지</td>
                    <td><%= p.getPrOrigin() %></td>
                </tr>
                <tr>
                    <td>구매수량</td>
                    <td colspan="2">
                        <input type=hidden name="sell_price" value=<%= p.getPrPrice() %>>
                        <input type="button" value=" - " class="qtyBtnL" onclick="del();">
                        <input type="text" name="amount" class="qtyBtnC" value="1" onchange="change();">
                        <input type="button" value=" + " class="qtyBtnR" onclick="add();">
                    </td>
                </tr>
                <tr>
                    <td>총 상품금액</td>
                    <td><input type="text" name="sum" size="11" readonly style="width:70px; height: 40px; font-size:20px; font-weight: 700; color: red; border:none;">원</td>
                </tr>
                <tr>
                  <td colspan="2">
                    <br>
                    <button id="cartbtn">장바구니 담기</button>
                  </td>
                </tr>
              </table>
            </form>
            </div>


    <!-- Swiper -->
    <div class="swiper mySwiper">
        <div class="swiper-wrapper">
          <% for(int i = 0; i < rcpAt.size(); i++) { %>
            <div class="swiper-slide">
              <img class='rcpImg' src="<%= contextPath %>/<%= rcpAt.get(i).getFilePath()%>/<%=rcpAt.get(i).getChangeName()%>">
              <input type="hidden" name="getPeNo" value="<%= rcpAt.get(i).getRvNo()%>">
            </div>
          <% } %>
        </div>
        <div class="swiper-button-next"></div>
        <div class="swiper-button-prev"></div>
        <div class="swiper-pagination"></div>
      </div>

      <br><br>

          <ul class="a">
            <li class="navi navinfo"><a href="#info">상품설명</a></li>
            <li class="navi navreview"><a href="#review">상품후기</a></li>
            <li class="navi navqna"><a href="#qna">상품문의</a></li>
          </ul>

       
        <div id="info" align="center">
          <br><br>
            <img src="<%= contextPath %>/<%= atContent.getFilePath()%>/<%=atContent.getChangeName()%>" width="640px" height="480px">
            <br>
            
            <p>
               <%= p.getPrContent() %>
            </p>
        </div>

        <hr>
          <h3 style="text-align: center; padding-top: 50px; color: #ed6f65;" id="review">
            	상품후기 Review <br>
            <small class="text-muted">상품과 관련없는 후기는 삭제 될 수 있습니다.</small>
          </h3>
          <% if(loginUser != null) { %>
            <input type="hidden" name="userId" id="userId" value="<%= loginUser.getUserId() %>">
            <button id="reviewInsert">리뷰작성</button>
          <% } %>
            <input type="hidden" value="<%= p.getPrNo() %>" class="pno">
          <br>
          <table class="table table-bordered table-white table-hover">
            <caption>
              <div class="paging-area" align="center">
                <!-- 리뷰페이징 영역-->
              </div>
            </caption>
            <thead class="thead-light text-center">
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
              </tr>
            </thead>
            <tbody class="text-center" id="reviewList">
              <!-- 이곳에 리뷰리스트가 들어간다-->
            </tbody>
          </table>

          <hr>
          <br><br><br><br>
          <h3 style="text-align: center; padding-top: 50px; color: #ed6f65;" id="qna">
            상품문의 Q&A <br>
          <small class="text-muted">게시판 성격에 맞지 않는 글은 삭제 될 수 있습니다.</small>
        </h3>
        <% if(loginUser != null) { %>
          <input type="hidden" name="userId" value="<%= loginUser.getUserId() %>">
          <button id="qnaInsert">질문작성</button>
        <% } %>
          <input type="hidden" value="<%= p.getPrNo() %>" class="pno">
        <br>
        <table class="table table-bordered table-white table-hover">
          <caption>
            <div class="paging-area2" align="center">
              <!-- qna페이징 영역-->
            </div>
            <br>
            <%@ include file="../common/footer.jsp" %>
          </caption>
          <thead class="thead-light text-center">
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>작성일</th>
              <th>답변상태</th>
            </tr>
          </thead>
          <tbody class="text-center" id="qnaList">
            <!-- 이곳에 qna리스트가 들어간다-->
          </tbody>
        <br>
      </div>
    </div>

    <div class="modal" id="reviewModal">
      <div class="modal-dialog">
        <div class="modal-content">
  
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">해당 리뷰를 삭제하시겠습니까?</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
  
  
          <!-- Modal footer -->
          <div class="modal-footer">
            <button id= "delReviewFinal" class="btn btn-sm btn-danger">삭제하기</button>
            <button type="button" class="btn btn-secondary btn-sm"
              data-dismiss="modal">취소</button>
          </div>
  
        </div>
      </div>
    </div>  
  


    <div class="modal" id="delQnaModal">
      <div class="modal-dialog">
        <div class="modal-content">
  
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">해당 질문을 삭제하시겠습니까?</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
  
  
          <!-- Modal footer -->
          <div class="modal-footer">
            <button id= "delQnaFinal" class="btn btn-sm btn-danger">삭제하기</button>
            <button type="button" class="btn btn-secondary btn-sm"
              data-dismiss="modal">취소</button>
          </div>
  
        </div>
      </div>
    </div>
    
    

        <!-- Initialize Swiper -->
        <script>
          var swiper = new Swiper(".mySwiper", {
              slidesPerView: 4,
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

            
  
           // 수량 계산

          var prPrice = '<%= p.getPrPrice() %>'.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
          document.getElementById("proPrice").innerHTML = prPrice + '원';

          var sell_price;
          var amount;
          
          function init () {
              sell_price = document.form.sell_price.value;
              amount = document.form.amount.value;
              document.form.sum.value = sell_price;
              change();
          }
          
          function add () {
              hm = document.form.amount;
              sum = document.form.sum;
              hm.value ++;
  
              sum.value = (parseInt(hm.value) * sell_price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
          }
  
          function del () {
              hm = document.form.amount;
              sum = document.form.sum;
                  if (hm.value > 1) {
                      hm.value --;
                      sum.value = (parseInt(hm.value) * sell_price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                  }
          }
  
          function change () {
              hm = document.form.amount;
              sum = document.form.sum;
  
                  if (hm.value < 0) {
                      hm.value = 0;
                  }
              sum.value = (parseInt(hm.value) * sell_price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
          }



          


            // 리뷰리스트
          var cpage= 1;
          
          function selectReviewList(page){
            if(page){
                cpage = page;
            }
            $.ajax({
              url : "<%=contextPath%>/reviewList.pr?cpage=" + cpage,
              data : {pno : <%= p.getPrNo() %>},
              method : "GET",
              success : function(review){
                console.log(review);
                var result = "";
                var rvAt = "";
                for(var i in review[0]){
                  for(var j in review[2]){
                    if(review[0][i].rvNo == review[2][j].rvNo){
                      rvAt = '<img width="200px" height="150px" src="' + "<%= contextPath %>/" + review[2][j].filePath + review[2][j].changeName + '">';
                      break;
                    } else {
                      rvAt = "";
                    }
                  }
  
                    result += "<tr>"
                            + "<td>" + review[0][i].rvNo + "</td>"
                            + "<td class='text-left' width='50%'>"
                            + "<div class='panel-faq-container'>"
                            + "<p class='panel-faq-title'>" + review[0][i].rvTitle + "</p>"
                            + "<div class='panel-faq-answer'>"
                            + "<p>" + rvAt + "</p>"
                            + "<p>" + review[0][i].rvContent + "</p>"
                            + "</div>"
                            + "</div>"
                            + "</td>"
                            + "<td>" + review[0][i].rvWriter + "</td>"
                            + "<td>" + review[0][i].createDate 
                            + '<% if (loginUser != null && loginUser.getUserId().equals("admin")){ %>' 
                            + '<button class="delReview" data-toggle="modal" data-target="#reviewModal">리뷰삭제</button>'
                            + '<% } %>'   
                            + "</td>"
                            + "</tr>"
                  }
      
                  $("#reviewList").html(result);


                  $(".paging-area").empty();
                  
                  if(review[1][0].currentPage != 1){
                      $(".paging-area").append('<button class="pagebtn" onclick="selectReviewList(' + (review[1][0].currentPage - 1) + ')" >&lt;</button>');
                  }
                  var result = "";
                  for(var i = review[1][0].startPage; i <= review[1][0].endPage; i++){
                      if(i != review[1][0].currentPage){
                          result +=  '<button class="pagebtn" onclick="selectReviewList(' + i + ')">' + i + '</button>';
                      }else{
                          result +=  '<button class="pagebtn" id="selectedPageBtn" disabled>' + i + '</button>'
                      }
                  }
                  $(".paging-area").append(result);

                  if(review[1][0].currentPage < review[1][0].maxPage){
                      $(".paging-area").append('<button class="pagebtn" onclick="selectReviewList(' + (review[1][0].currentPage + 1) +')">&gt;</button>');
                  }


                    // panel-faq-container
                  const panelFaqContainer = document.querySelectorAll(".panel-faq-container"); // NodeList 객체

                  // panel-faq-answer
                  let panelFaqAnswer = document.querySelectorAll(".panel-faq-answer");
                    
                  // 반복문 순회하면서 해당 FAQ제목 클릭시 콜백 처리
                  for( let i = 0; i < panelFaqContainer.length; i++ ) {
                    panelFaqContainer[i].addEventListener('click', function() { // 클릭시 처리할 일
                      // FAQ 제목 클릭시 -> 본문이 보이게끔 -> active 클래스 추가
                      panelFaqAnswer[i].classList.toggle('active');
                    })
                    }

                    


               }
            })
          }

                      // qna리스트
                      var cpage= 1;
          
          function selectQnaList(page){
            if(page){
                cpage = page;
                }

            $.ajax({
              url : "<%=contextPath%>/qnaList.pr?cpage=" + cpage,
              data : {pno : <%= p.getPrNo() %>},
              method : "GET",
              success : function(qna){
                
                  var result = "";
                  var answerStatus = "";
                  var answerContent = "";
                  for(var i in qna[0]){
                    
                    if(qna[0][i].qnaAnswerSt == 'N'){
                      answerStatus = '미답변';
                      answerContent = "";
                    } else {
                      answerStatus = '<span style="color: navy;"><b>답변완료</b></span>'
                      for(var j in qna[2]){
                        if(qna[0][i].qnaNo == qna[2][j].qnaNo){
                          answerContent = '<hr><b>ㄴ답변</b><br>' +  qna[2][j].qnaContent
                        }
                      }
                    }
                    result += "<tr>"
                            + "<td>" + qna[0][i].qnaNo + "</td>"
                            + "<td class='text-left' width='50%'>"
                            + "<div class='panel-faq-container2'>"
                            + "<p class='panel-faq-title2'>" + qna[0][i].qnaTitle + "</p>"
                            + "<div class='panel-faq-answer2'>"
                            + "<p>" + qna[0][i].qnaContent + "</p>"
                            + "<p>" + answerContent + "</p>"
                            + "</div>"
                            + "</div>"
                            + "</td>"
                            + "<td>" + qna[0][i].qnaWriter + "</td>"
                            + "<td>" + qna[0][i].createDate + "</td>"                      
                            + "<td>" + answerStatus
                            + '<% if (loginUser != null && loginUser.getUserId().equals("admin")){ %>'
                            + '<button class="ansInsert">답변작성</button>'
                            + '<button class="delQna" data-toggle="modal" data-target="#delQnaModal">질문삭제</button>'
                            + '<% } %>'   
                            + "</td>"
                            + "</tr>"
                            
                  }
                  $("#qnaList").html(result);
                  
                  $(".paging-area2").empty();
                  if(qna[1][0].currentPage != 1){
                      $(".paging-area2").append('<button class="pagebtn" onclick="selectQnaList(' + (qna[1][0].currentPage - 1) + ')" >&lt;</button>');
                  }
                  var result = "";
                  for(var i = qna[1][0].startPage; i <= qna[1][0].endPage; i++){
                      if(i != qna[1][0].currentPage){
                          result +=  '<button class="pagebtn" onclick="selectQnaList(' + i + ')">' + i + '</button>';
                      }else{
                          result +=  '<button class="pagebtn" id="selectedPageBtn" disabled>' + i + '</button>'
                      }
                  }
                  $(".paging-area2").append(result);

                if(qna[1][0].currentPage < qna[1][0].maxPage){
                      $(".paging-area2").append('<button class="pagebtn" onclick="selectQnaList(' + (qna[1][0].currentPage + 1) +')">&gt;</button>');
                  }


                // panel-faq-container
                const panelFaqContainer2 = document.querySelectorAll(".panel-faq-container2"); // NodeList 객체

                  // panel-faq-answer
                  let panelFaqAnswer2 = document.querySelectorAll(".panel-faq-answer2");
      

                  
                  // 반복문 순회하면서 해당 FAQ제목 클릭시 콜백 처리
                  for( let i = 0; i < panelFaqContainer2.length; i++ ) {
                    panelFaqContainer2[i].addEventListener('click', function() { // 클릭시 처리할 일
                      // FAQ 제목 클릭시 -> 본문이 보이게끔 -> active 클래스 추가
                      panelFaqAnswer2[i].classList.toggle('active');
                    });
                    };

              }
                
            })

            
          }

            $(function(){

              init(); // 총상품금액 초기화

              $("#reviewInsert").click(function(){
                          
                var pno = $(".pno").val();

                location.href = "<%= contextPath %>/reviewEnrollform.bo?pno=" + pno
              })

              selectReviewList();

              
              $("#qnaInsert").click(function(){
                          
                          var pno = $(".pno").val();
          
                          location.href = "<%= contextPath %>/qnaEnrollform.bo?pno=" + pno
                        })

              selectQnaList();

             


              
            })
            
            // 리뷰삭제
            $(document).on("click", ".delReview", function(){
              var rvNum =  $(this).parents('tr').children().eq(0).text();
              var pno = $(".pno").val();

              $(document).on("click", "#delReviewFinal", function(){
              location.href="<%= contextPath %>/delete.rv?rvNum=" + rvNum + "&pno=" + pno;
              })

            })

            // 질문삭제
            $(document).on("click", ".delQna", function(){
              var qaNo =  $(this).parents('tr').children().eq(0).text();
              var pno = $(".pno").val();

              $(document).on("click", "#delQnaFinal", function(){
              location.href="<%= contextPath %>/delete.qa?qaNo=" + qaNo + "&pno=" + pno;
              })

            })

            $(document).on("click", ".ansInsert", function(){
                          
              var qaNo =  $(this).parents('tr').children().eq(0).text();
              var pno = $(".pno").val();
              
              location.href = "<%= contextPath %>/ansEnrollform.bo?qaNo=" + qaNo + "&pno=" + pno;
              })

              $(document).on("click", ".productEditBtn", function(){
                          
                var pno = $(".pno").val();
                
                location.href = "<%= contextPath %>/updateForm.pr?pno=" + pno;
              })

              $(document).on("click", ".productDeleteBtn", function(){
                          
                var pno = $(".pno").val();
                
                location.href = "<%= contextPath %>/deleteForm.pr?pno=" + pno;
              })
            
              $(document).on("click", ".rcpImg", function(){
                          
              var peNo = $(this).siblings('input').val();

              location.href = "<%= contextPath %>/detail.pan?peNo=" + peNo;
              })



        </script>

</body>
</html>