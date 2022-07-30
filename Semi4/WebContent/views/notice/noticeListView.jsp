<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice, com.kh.common.model.vo.PageInfo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	ArrayList<Notice> list = (ArrayList<Notice>) request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");

	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항테스트 (사용하는버전)</title>
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
	color: rgb(89, 108, 156);
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
	background-color: yellow;
	color: #000;
	transition: all 0.35s;
}

#writeNotice {
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
	<jsp:include page="../common/menubar.jsp"></jsp:include>

	<div class="container">
		<h3 style="text-align: left; padding-top: 50px;">고객센터</h3>
		<a href="${pageContext.request.contextPath}/list.no?cpage=1" class="btn-default btn-sm">공지사항</a>
		<c:choose>
			<c:when test="${ not empty sessionScope.loginUser }">
				<c:choose>
					<c:when test="${ sessionScope.loginUser.userId == 'admin' }">		
						<a href="${pageContext.request.contextPath}/list.pq?cpage=1&userId=${loginUser.userId}" class="btn-default btn-sm">문의내역</a><br>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/userList.pq?cpage=1&userId=${loginUser.userId}" class="btn-default btn-sm">문의내역</a><br>
					</c:otherwise>
				</c:choose>
				
			</c:when>
		</c:choose>

		<hr>

		<!-- 관리자만 글작성 버튼이 보이게끔-->
		<c:if test="${ not empty loginUser and loginUser.userId == 'admin' }">
			<a href="${pageContext.request.contextPath}/enrollForm.no" id="writeNotice"
				class="btn-default btn-sm">공지사항작성</a>
		</c:if>

		<br>
		<br>
		<table class="list-area table table-bordered table-striped table-white table-hover">
			<caption>Notice List of HonjaSAJO</caption>
			<thead class="thead-light text-center">
				<tr>
					<th width="50px">No</th>
					<th colspan="2">공지사항</th>
				</tr>
			</thead>
			<tbody class="text-center">
				
				<c:choose>
					<c:when test="${ requestScope.list.isEmpty() }">
						<tr>
							<td colspan="5">공지사항이 존재하지 않습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="n" items="${ requestScope.list }">
							<tr>
								<td><c:out value="${ n.noticeNo }"/></td>
								<td class="text-left" width="70%">
									<div class="panel-faq-container">
										<p class="panel-faq-title"><!-- 질문제목 -->
											<strong><c:out value="${ n.noticeTitle }"/></strong>
										</p>
										<p><c:out value="${ n.createDate }"/></p>
									</div>
									<div class="panel-faq-answer"><!-- 답변본문 -->
									<br>
										<p><c:out value="${n.noticeTitle }"/></p>
										<p><c:out value="${n.noticeContent }"/></p>
									</div>
								</td>
								<c:choose>
								<c:when test="${ not empty loginUser and loginUser.userId == 'admin' }">
									<td colspan="3">
										<button id="updatebt" class="update btn btn-sm btn-warning">수정하기</button>
										<button id="deletebt" data-toggle="modal" data-target="#myModal" class="btn btn-sm btn-danger">삭제하기</button>
									</td>
								</c:when>
								<c:otherwise><td></td></c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="3">
								<div align="center" class="paging-area">	
									<c:if test="${ requestScope.pi.currentPage ne 1 }">
										<button class="btn-default btn-sm" onclick="location.href='${pageContext.request.contextPath}/list.no?cpage=${ pi.currentPage - 1 }'"> &lt; </button>
									</c:if>
									<c:forEach var="i" begin="${ pi.startPage }" end="${ pi.endPage }" step="1">
										<c:choose>
											<c:when test="${ i ne pi.currentPage }">
												<button class="btn-default btn-sm" onclick="location.href='${pageContext.request.contextPath}/list.no?cpage=${ i }'"><c:out value="${ i }"/></button>
											</c:when>
											<c:otherwise>
												<button class="btn-default btn-sm" disabled><c:out value="${ i }"/></button>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${ requestScope.pi.currentPage ne pi.maxPage }">
										<button class="btn-default btn-sm" onclick="location.href='${pageContext.request.contextPath}/list.no?cpage=${ pi.currentPage + 1 }'"> &lt; </button>
									</c:if>
								</div>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
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
      
	<!-- The Modal 공지사항삭제 -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">공지사항을 삭제하시겠습니까?</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>


				<!-- Modal footer -->
				<div class="modal-footer">
					<button id= "deletebtm" class="btn btn-sm btn-danger">삭제하기</button>
					<button type="button" class="btn btn-secondary btn-sm"
						data-dismiss="modal">취소</button>
				</div>

			</div>
		</div>
	</div>
	
      <script>

        $(function(){ // 수정하기 버튼

            $(".list-area>tbody>tr>td>#updatebt").click(function(){

                // 클릭했을 때 해당 공지사항의 번호를 넘겨야함
                // 해당 tr태그의 자손 중에서도 첫번째 td태그의 값만 필요함
                
                // 선택한 버튼의 가까운 closest('태그') 부모선택자를 tr로 선택 그리고 그 자식의 0번째 인덱스(eq()) -> noticeNo의 text값을 var변수 nno에 넣음
                var nno = $($(this).closest('tr')).children().eq(0).text();
                
                location.href="${pageContext.request.contextPath}/updateForm.no?nno=" + nno;
                 
            })
            

             // 삭제하기 모달로 가는 버튼 클릭시 함수 실행
            $(".list-area>tbody>tr>td>#deletebt").click(function(){

                var nno = $($(this).closest('tr')).children().eq(0).text();

            	$(".modal-footer>#deletebtm").click(function(){
            		
            		location.href="${pageContext.request.contextPath}/delete.no?nno=" + nno;
            		
            	})
        	})
        })

        
        

    </script>
      





</body>
</html>