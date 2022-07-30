<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,com.kh.common.model.vo.PageInfo, com.kh.personalQna.model.vo.PersonalQna, com.kh.personalQnaAnswer.model.vo.PersonalQnaAnswer"%>
<%
	ArrayList<PersonalQna> list = (ArrayList<PersonalQna>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<PersonalQnaAnswer> paList = (ArrayList<PersonalQnaAnswer>)request.getAttribute("paList");

	String alertMsg2 = (String)session.getAttribute("alertMsg2");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1대1문의</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
html, body {
	font-family: Helvetica, Arial, sans-serif;
	margin: 0;
}

.panel-faq-container {
	margin-bottom: -16px;
}

.panel-faq-title {
	color: black;
	cursor: pointer;
}

.panel-faq-answer {
	height: 0;
	overflow: hidden;
	/* 변화가 시작되는 쪽에다가 transition 적용해준다 0 -> 300px 
  왜? 닫기 버튼을 누를 때 변화가 티남 */
	transition: all 1s;
}

#btn-all-close {
	margin-bottom: 1px;
	background-color: lightgray;
	border: none;
	color: black;
	cursor: pointer;
	padding: 1px 1px;
	float: center;
}

#btn-all-close:hover {
	background-color: #ed6f65;
	color: #000;
	transition: all 0.35s;
}

#writeQna {
	float: right;
}

.active {
	display: block;
	/* 높이를 정해줘야지만 transition이 적용됨 */
	height: 300px;
}
.btn-default
 
{
background-color:rgb(89, 108, 156) ;
color:#FFF;
border-color: #2F3E48;
border: none;
}
 
.btn-default:hover, .btn-default:focus, .btn-default:active, .btn-default.active, .open .dropdown-toggle.btn-default {
 
background-color: rgb(41, 35, 68);
color:#FFF;
border-color: #31347B;
border: none;
text-decoration-line: none;
 
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<!-- 이상한 경로로 들어올 시 알림창 출력 -->
	<% if(loginUser == null || !loginUser.getUserId().equals("admin")) { %>
	<script>
		var msg = "<%= alertMsg2 %>";
		
		if(msg != "null"){
			alert(msg);
			
			<% session.removeAttribute("alertMsg2"); %>
		}
	</script>
	<% } %>
         

	<div class="container">
	
		<h3 style="text-align: left; padding-top: 50px; color: #ed6f65;">1대1문의</h3>
		<a href="<%= contextPath %>/list.no?cpage=1" class="btn-default btn-sm">공지사항</a>
		
		<%
			if (loginUser != null && !loginUser.getUserId().equals("admin")) {
		%>
		
		<a href="<%=contextPath%>/enrollForm.pq" id="writePqna" class="btn-default btn-sm">문의글쓰기</a> <br>
		
		<%
			}
		%>
		<br><br>

		<table class="list-area table table-bordered table-striped table-white table-hover">
			<caption>1:1 QnA List of HonjaSAJO</caption>
			<thead class="thead-light text-center">
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>답변여부</th>
					<th>날짜</th>
					<th>답변/글삭제</th>
				</tr>
			</thead>
			<tbody class="text-center">
				<!-- 리스트가 비어있는가 아닌가-->
				<!-- qna가 존재하지 않을 경우 -->
				<!-- 리스트가 비어있을 경우 tr>td 콜스판해서 존재하지 않습니다 만들기 -->
				<%
					if (list.isEmpty()) {
				%>
				<tr>
					<td colspan="5">1대1 문의가 존재하지 않습니다.</td>
				</tr>
				<%
					} else {
				%>
				<!-- 문의사항이 존재할 경우 -->
				<%
					for(PersonalQna p : list) {
						String str = "";
				%>
					<%
						for(PersonalQnaAnswer pa : paList ){
							
							if(p.getPqnaNo()==pa.getPqnaNo()){
								
								str = pa.getPqnaContent();
								break;
							}else{
								str = "";
							}
								%>
					<% }  %>
				<tr>
					<td><%=p.getPqnaNo()%></td>
					<td class="text-left" width="50%">
						<div class="panel-faq-container">
							<p class="panel-faq-title">
								<!-- 클릭 전 보이는 질문 제목 -->
								<strong>Q.&nbsp;&nbsp;<%=p.getPqnaTitle()%></strong><br>작성자 : <%=p.getPqnaWriter()%>
							</p>
							
						</div>
						<div class="panel-faq-answer">
							<br>
							<p>
								<!-- 본문 클릭시 나오는 질문제목 -->
								<strong>Q.</strong> <%=p.getPqnaTitle()%>
							</p>
							<p>
								<!-- 본문 클릭시 나오는 질문내용 -->
								<%=p.getPqnaContent()%>
								
							</p>
							<div class="qnaContent">
								<p><!-- 본문 클릭 시 나오는 답변내용 -->
										<strong>A. </strong><%=str %>
								</p>
							</div>
						</div>
					</td>
					<td><!-- 답변완료 PQA의 STATUS 에따라 답변완료/답변필요 표시   -->
						<h6><% if(p.getPqnaAnswerStatus().equals("Y")) {  %>답변완료
							<%}  else { %> <strong>답변요망</strong>
							<% } %></h6>
					</td>
					<!-- CREATE_DATE값으로 보여주기  -->
					<td><%=p.getCreateDate() %></td>
					<!-- 관리자만 보이는 화면으로 구성하기--> <!-- 만약 답변 완료이면 수정하기 버튼이 보이게하기--> <!-- <a href="" class="btn btn-sm btn-warning">답변하기</a> -->
					<td>
					<%
						if (loginUser != null && loginUser.getUserId().equals("admin")) {
					%>
						<% if(p.getPqnaAnswerStatus().equals("Y")) { %>
						<button id="deletebt" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal">문의글삭제</button>
						<% } else {%>
							<button id="enrollbt" class="btn btn-sm btn-warning">답변하기</button>
						<% } %>
					</td>
					<%
						} 
					%> 
				</tr>					
							
					
				
				<%
					} // 큰 for문 끝
				%>
				<tr>
					<td colspan="6">
						<div align="center" class="paging-area">

							<% if(currentPage != 1) {%> <!-- 페이징바에서 <를 담당 -->
								<button class="btn-default btn-sm" onclick="location.href='<%= contextPath %>/list.pq?cpage=<%= currentPage - 1 %>'"> &lt; </button>
							<% } %>
				
							<% for(int i = startPage; i <= endPage; i++) { %>
								<% if(i != currentPage) {%>
									<button class="btn-default btn-sm" onclick="location.href='<%= contextPath %>/list.pq?cpage=<%= i %>'"><%= i %></button>
								<% } else { %>
									<button class="btn-default btn-sm" disabled><%= i %></button>
								<% } %>
							<% } %>
				
							<% if(currentPage != maxPage) {%>
								<button class="btn-default btn-sm" onclick="location.href='<%= contextPath %>/list.pq?cpage=<%= currentPage + 1 %>'"> &gt; </button>
							<% } %>
							
							</div>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
		<script>
        window.onload = () => {
        // panel-faq-container
        const panelFaqContainer = document.querySelectorAll(".panel-faq-container"); // NodeList 객체
        
        // panel-faq-answer
        let panelFaqAnswer = document.querySelectorAll(".panel-faq-answer");

        // btn-all-close
        const btnAllClose = document.querySelector("#btn-all-close");
        
        // 반복문 순회하면서 해당 FAQ제목 클릭시 콜백 처리
        for( let i=0; i < panelFaqContainer.length; i++ ) {
            panelFaqContainer[i].addEventListener('click', function() { // 클릭시 처리할 일
            // FAQ 제목 클릭시 -> 본문이 보이게끔 -> active 클래스 추가
            panelFaqAnswer[i].classList.toggle('active');
            });
        };
        
        btnAllClose.addEventListener('click', function() {
            // 버튼 클릭시 처리할 일  
            for(let i=0; i < panelFaqAnswer.length; i++) {
                panelFaqAnswer[i].classList.remove('active');
            };
        });
        }
        
      </script>
	<!-- The Modal 답변삭제 -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">문의글을 삭제하시겠습니까?</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>


				<!-- Modal footer -->
				<div class="modal-footer">
					<button id="deletebtm" class="btn btn-sm btn-danger">삭제하기</button>
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal">취소</button>
				</div>

			</div>
		</div>
	</div>
	
	<script>

        $(function(){ // 답변하기
        	
        	$(".list-area>tbody>tr>td>#enrollbt").click(function(){


                // 클릭했을 때 해당 공지사항의 번호를 넘겨야함
                // 해당 tr태그의 자손 중에서도 첫번째 td태그의 값만 필요함
                
                // 선택한 버튼의 closest('태그') 부모선택자를 tr로 선택 그리고 그 자식의 0번째 인덱스(eq()) -> noticeNo의 text값을 var변수에 넣음
                var qno = $($(this).closest('tr')).children().eq(0).text();

                //console.log(nno); 숫자값 나옴

                // 글번호를 이용한 요청
                // => 대놓고 요청 => url에 키와 밸류를 대놓고 작성해서 요청을 보내버리겠다.
                // => GET방식 : 요청할url?키=밸류&키&밸류&키=밸류....
                // "쿼리 스트링" : ? 뒤의 내용들, 직접 쿼리스트링을 만들어서 요청
                // localhost:8001/jsp/updateForm.no?nno=글번호 -> 게시판 리스트에서 바로 접근 / 기존에 배운 방법은 detail 상세페이지에서 이동했음

                //console.log(qno);
                location.href="<%= contextPath %>/enrollForm.pqa?qno=" + qno;
                 
            })

            
            

             // 삭제하기 모달로 가는 버튼 클릭시 함수 실행
            $(".list-area>tbody>tr>td>#deletebt").click(function(){


                // 클릭했을 때 해당 공지사항의 번호를 넘겨야함
                // 해당 tr태그의 자손 중에서도 첫번째 td태그의 값만 필요함
                
                // 선택한 버튼의 closest('태그') 부모선택자를 tr로 선택 그리고 그 자식의 0번째 인덱스(eq(0)) -> noticeNo의 text값을 var변수에 넣음
                var pno = $($(this).closest('tr')).children().eq(0).text();

                // console.log(nno); //숫자값 나옴

                // 글번호를 이용한 요청
                
            	$(".modal-footer>#deletebtm").click(function(){
            		location.href="<%= contextPath %>/delete.pq?pno=" + pno;
            		
            	})

        	})

        	
          }
        )

        
        

    </script>

</body>
</html>