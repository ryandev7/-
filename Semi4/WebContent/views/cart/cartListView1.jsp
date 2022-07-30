<%@page import="com.kh.cart.model.vo.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Cart> cList = (ArrayList<Cart>)request.getAttribute("cList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니창!</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
 <style>
div{
    border: 1px;
    box-sizing: border-box;
}
.wrap{
    width : 1000px;
    height : 800px;
    margin: auto;
}
/* 크게 세가지 영역*/
.wrap > div{
    width: 100%;
}

#content{
    height: 100%;
}

/*더 세부영역*/
#content > div{
    height: 100%;
}
/* 각 세부영역의 가로 비율 정해주기*/

#content1{
    width : 75%;
    float: left;

}
#content2{
    border: 1px solid black;
    width : 25%;
    float: left;

}
#content3{
    height : 50%;
    
}
#content4{
    height : 50%;
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

    </style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>

    <div class="wrap">
        <div id="content">
            <div id="content1">
                <form name="form" class="cartTable" method="post" action = "<%=contextPath %>/"">
                    <br><br>
                    <div class="cartlist">
                            <h2 align="center">장바구니</h2>
                            <input type="checkbox" class='batch' id="allChk"><label for="allChk">전체선택 </label>
                            <button>선택삭제</button>
                            <hr>
                    </div>
                        <table>
                            <%
					            if (cList.isEmpty()) {
				            %>
				            <tr>
					            <p align="center" style="color: #ed6f65; font-size:25px;"><b>장바구니가 비었어요.</b></p>
				            </tr>
				            <%
					        } else {
				            %>
                            
                                <%
                                 for (Cart c : cList) {
                                %>
                            <tr>
                                <td>
                                    <input type="checkbox" class='pro chk'></div>
                                </td>
                                <td>
                                    <img src="http://drive.google.com/uc?export=view&id=1z0ez_H_1ABSXJGNIhNy5QwH0Qe-81gGT" style="width:100px; height:100px">
                                </td>
                                <td width="250" align="center"><%= c.getPrName() %></td>
                                <td width="150">
                                    <input type="hidden" name="sell_price" value="<%= c.getPrPrice() %>">
                                    <input type="button" value=" - " class="qtyBtnL" onclick="del();">
                                    <input type="text" name="amount" class="qtyBtnC" value="<%= c.getCartAmount() %>" onchange="change();" style="text-align:center; width:30px;">
                                    <input type="button" value=" + " class="qtyBtnR" onclick="add();">
                                </td>
                                <td width="100">
                                    <input type="text" name="sum" size="11" readonly style="width:75px; height: 40px; font-size:20px; border:none;">원
                                </td>
                                <td>
                                    <button>삭제</button>
                                </td>
                            </tr>
                                <% } %>
                            <% } %>

                        </table>
                    </div>
                    <br><br><br><br><br><br>
                    <div id="content2">
                        <div id="content3">
                            <br>
                            <h2 align="center">배송지</h2>
                            <hr>
                            <br>
                            <p align="center">
                                <% if(loginUser != null) { %>
                                <%= loginUser.getAddress() %>
                                <% } else { %>
                                    로그인을 해주세요
                                <% } %>
                            </p>
                            <br>
                            <div align="center">
                                <button>배송지 변경</button>
                            </div>
                        </div>
                        <div id="content4">
                            <table align="center">
                                <tr>
                                    <th>배송비</th>
                                    <th>2500원</th>
                                </tr>
                                <tr>
                                    <th>상품금액</th>
                                </tr>
                            </table>
                            <hr>
                            <table align="center">
                                <tr>
                                    <th>결제금액</th>
                                </tr>
                            </table>
                            <br><br><br>
                            <div align="center">
                                <button id="cartbtn" style="width:100pt; height: 30pt;">결제하기</button>
                            </div>
                        </div>
                        <script>
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

                            
                            $(function(){

                                init ();

                                $('.batch').on('change', function(){
                                    if($(this).prop('checked') == false){
                                        $('#allchk').prop('checked', false);
                                    }
                                });

                                $('#allChk').on('change', function(){
                                var $all = $('#allChk').prop('checked');
                                if($all){
                                    $('.batch').prop('checked', true);
                                    $('.chk').prop('checked', true);
                                }
                                else{
                                    $('.batch').prop('checked', false);
                                    $('.chk').prop('checked', false);
                                }
                                });


                            })
                    
                            </script>
                </form>
                </div>
            </div>
        </div>

        <script>

        </script>
    
    <%@ include file="../common/footer.jsp" %>
</body>
