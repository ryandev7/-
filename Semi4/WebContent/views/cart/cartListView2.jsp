<%@page import="com.kh.common.model.vo.Attachment"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.cart.model.vo.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Cart> cList = (ArrayList<Cart>)request.getAttribute("cList");
	DecimalFormat formatter = (DecimalFormat)request.getAttribute("formatter");
	ArrayList<Attachment> proAt = (ArrayList<Attachment>)request.getAttribute("proAt");
	
%>

<!DOCTYPE html>
<html>
<head>
<style>
.basketdiv {
width: 73%;
border-top: 1px solid #e0e0e0;
float: left;
font-size: 0.9375em;
margin-bottom: 20px;
}
.basketdiv .row.head {
border-bottom: 2px solid #888;
box-sizing: border-box;
background-color: #f4f4f4;
font-weight: 500;
}
.basketdiv .data {
border-bottom: 1px dashed #888;
box-sizing: border-box;
cursor: pointer;
float: left;
width: 100%;
}
.basketdiv .data .empty{
text-align: center;
padding: 12px 0;
}
.basketdiv .row.head .subdiv {
background-color: #f4f4f4;
}
.basketdiv .row > .subdiv {
display: block;
float: left;
}
.basketdiv .row > .subdiv:nth-child(1) {
width: 50%;
}
.basketdiv .row > .subdiv:nth-child(2) {
width: 40%;
}
.basketdiv .row > .subdiv:nth-child(3) {
width: 10%;
}
.basketdiv2 .row > .subdiv:nth-child(1) {
width: 60%;
}
.basketdiv2 .row > .subdiv:nth-child(2) {
width: 40%;
}
.basketdiv .row > div > div {
display: block;
float: left;
text-align: center;
margin: 0;
padding: 12px 0;
}
.basketdiv .row.data > div > div {
height: 60px;
line-height: 30px;
}
.basketdiv .data .num .updown {
color: #0075ff;
letter-spacing: -5px;
}
.basketdiv .check {
width: 10%;
}
.basketdiv .check input[type=checkbox] {
transform: scale(1.5);
}
.basketdiv .img{
width: 20%;
}
.basketdiv .pname{
width: 70%;
}
.basketdiv2 .pname {
width: 80%;
}
.basketdiv .basketprice {
width: 33%;
}
.basketdiv .num {
width: 33%;
min-width: 105px;
}
.basketdiv .sum {
width: 34%;
max-width: 80px;
color: #0000aa;
}
.basketdiv .point {
width: 50%;
}
.basketdiv2 .basketprice {
width: 25%;
}

.basketdiv2 .num {
width: 25%;
}

.basketdiv2 .sum {
width: 25%;
color: #0000aa;
}

.basketdiv2 .point {
width: 25%;
}
.basketdiv .basketcmd{
width: 100%;
}
.basketdiv .data .pname {
text-align: left !important;
line-height: 1.2 !important;
}
.basketdiv .data .price, .basketdiv .data .sum, .basketdiv .data .point {
text-align: right;
}
.baseform > tbody > tr > td:first-child {
width: 100px;
}

.buttongroup {
    padding: 11px 0;
    margin: 50px 0;
}
.narrowbuttongroup{
    margin: 15px 0;
}
.buttongroup.center {
    text-align: center;
}
.buttongroup input[type=text], .buttongroup input[type=password] {
    height: 30px;
}
.buttongroup button, .buttongroup a {
    margin-right: 5px;
}
.buttongroup button:last-child, .buttongroup a:last-child {
    margin-right: 0;
}

.abutton, .abutton:link, .abutton:visited, .abutton:active, input[type=button] {
    background-color: #ed6f65;
    color: white;
    cursor: pointer;
    letter-spacing: -1px;
    padding: 3px 5px;
    margin: 2px 3px;
    width: auto;
    word-break: keep-all;
    border-radius: 5px;
    text-decoration: none;
    font-size: 0.9375em;
}
.Bbutton{
    background-color: #596c9c;
    color: white;
    cursor: pointer;
    letter-spacing: -1px;
    padding: 3px 5px;
    margin: 2px 3px;
    width: auto;
    word-break: keep-all;
    border-radius: 5px;
    text-decoration: none;
    font-size: 0.9375em;
    transform: scale(1.5);
}
.cbutton{
    color: darkgray;
    font-size: 1.0em;
    padding: 5px;
}
.blue {
  color: #596c9c;
}
.orange {
    color: #ed6f65
}
.basketrowcmd{
  text-align: left;
  margin-bottom: 10px;
}
.sumcount, .summoney{
  text-align: center;
  font-size: 1.25em;
  font-weight: bold;
}
.address{
  text-align: left;
  font-size: 1.2em;
  font-weight: bold;
  margin-top: 20px;
  margin-left: 20px;

}
.userAdress{
    font-size: 0.75em !important;
    color: black !important;
    margin-top: 20px;
}
.buttongroup{
  text-align: center;
}
.buttongroup a{
  text-decoration: none;
}
.cmd a, .cmd span {
    padding: 12px 30px;
    box-sizing: border-box;
    margin-top: 10px;
    font-size: 1.2em;
    color: white;
    background-color: #292344;
    border: 1px solid #e0e0e0;
    text-align: center;
    border-radius: 5px;
}
.cmd.small a, .cmd.small span {
    padding: 6px 20px;
    font-size: 0.8125em;
}

.orderform .p_num {
    text-align: center;
    width: 40px;
    font-size: 1em;
}
.outer{
        width: 1200px;
        margin: auto;
}
.rightSide{
    float: right;
    width: 24%;
    border: 1px solid gray;
}
.emptyCartBtn{
        border-radius: 10px;
        font-size: 15pt;
        padding: 10px 20px 10px 20px;
        border: none;
        color: white;
        background-color: lightgray;
}
.submitOrderBtn{
    border: 2px solid #292344;
        border-radius: 10px;
        background-color: #292344;
        color: white;
        font-size: 15pt;
        padding: 10px 40px 10px 40px;
}


</style>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width">
	<title>혼자사조 장바구니</title>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
    <form name="orderform" id="orderform" method="post" class="orderform" action="<%= contextPath %>/purchaseProduct.me">
        <div class="outer">
            <br><br>
            <h3 align="center"><b>장바구니</b></h3>
            <div class="right-align basketrowcmd">
                <input type='checkbox' name="selectall" value='selectall' class="Bbutton" checked="" onclick='javascript:basket.selectAll(this)'><span class="Bbutton">전체선택</span>
                <a href="javascript:void(0)" class="abutton" onclick="javascript:basket.delCheckedItem();">선택삭제</a>
            </div>
            <input type="hidden" name="cmd" value="order">
            <div class="basketdiv" id="basket">


                
                <% for(int i = 0; i < cList.size(); i++) { %>
                
        
                <div class="row data">
                    <div class="subdiv">
                        <div class="check"><input type="checkbox" name="buy" value="260" checked="" onclick="javascript:basket.checkItem(); javascript:basket.checkSelectAll();">&nbsp;</div>
                        <div class="img"><img src='<%= contextPath %>/<%= proAt.get(i).getFilePath() + proAt.get(i).getChangeName() %>' width="60"></div>
                        <div class="pname">
                            <%= cList.get(i).getPrName() %>
                            <input type="hidden" name="cartNo" value="<%= cList.get(i).getCartNo() %>">
                        </div>
                    </div>
                    <div class="subdiv">
                        <div class="basketprice"><input type="hidden" name="p_price" id="p_price<%=i+1%>" class="p_price" value="<%= cList.get(i).getPrPrice() %>"></div>
                        <div class="num">
                            <div class="updown">
                                <span onclick="javascript:basket.changePNum(<%=i+1%>);"><button type="button" class="down">-</button></span>
                                <input type="text" name="p_num<%=i+1%>" id="p_num<%=i+1%>" size="2" maxlength="4" class="p_num" value="<%= cList.get(i).getCartAmount() %>" onkeyup="javascript:basket.changePNum(<%=i+1%>);">
                                <span onclick="javascript:basket.changePNum(<%=i+1%>);"><button type="button" class="up">+</button></span>
                            </div>
                        </div>
                        <div class="sum"><%= formatter.format(cList.get(i).getPrPrice() * cList.get(i).getCartAmount()) %>원</div>
                    </div>
                    <div class="subdiv">
                        <div class="basketcmd"><a href="javascript:void(0)" class="cbutton" onclick="javascript:basket.delItem();">X</a></div>
                    </div>
                </div>
                <% } %>
            </div>
            
            <div class="rightSide">
                <div class="address"> <img width="15px" src='http://drive.google.com/uc?export=view&id=1zsfpkxqrxjxImNrhiB9u3i26duREXYge'/> 배송지 <br>                           
                    <% if(loginUser != null) { %>
                     <div class="userAdress"><%= loginUser.getAddress() %></div>   
                     <input type="hidden" name="userId" id="userId" value="<%= loginUser.getUserId() %>">
                    <% } else { %>
                        로그인을 해주세요
                    <% } %> 
                </div>
                <hr>
                <div class="bigtext right-align sumcount" id="sum_p_num" name="orderAmount">
                    <% int tAmount = 0; %>
                        <% for(int i = 0; i < cList.size(); i++) { %>
                            <% tAmount += cList.get(i).getCartAmount(); %>
                        <% } %>
                    상품갯수: <%= tAmount %>개
                </div>
                <br>
                <div class="bigtext right-align box orange summoney" id="sum_p_price" name="orderPrice">
                    <% int tPrice = 0; %>
                    <% for(int i = 0; i < cList.size(); i++) { %>
                        <% tPrice += cList.get(i).getPrPrice() * cList.get(i).getCartAmount(); %>
                    <% } %>
                    합계금액: <%= formatter.format(tPrice) %>원
                </div>
                    
                <div id="goorder" class="">
                    <div class="clear"></div>
                    <div class="buttongroup center-align cmd orderBtn">
                        <% if(cList.size() < 1){ %> 
                            <button class="emptyCartBtn" disabled>상품을 담아주세요</button>
                            
                        <% } else { %>
                        <button type="submit" class="submitOrderBtn" onclick="javascript:basket.submitOrder()">주문하기</button>
                        <% } %>
                    </div>
                </div>
            </div>
    </div>
</form>

</body>

<script>
    var userId = '<%= loginUser.getUserId() %>'

let basket = {
    totalCount: 0, 
    totalPrice: 0,
    //체크한 장바구니 상품 비우기
    delCheckedItem: function(){
        document.querySelectorAll("input[name=buy]:checked").forEach(function (item) {
            item.parentElement.parentElement.parentElement.remove();

            cartNo = item.parentElement.nextElementSibling.nextElementSibling.firstElementChild.getAttribute('value')

            location.href = "<%= contextPath %>/checkedDelete.ca?cartNo=" + cartNo + "&userId=" + userId;
            
            
        });
        //AJAX 서버 업데이트 전송
    
        //전송 처리 결과가 성공이면
        this.reCalc();
        this.updateUI();
    },


    //재계산
    reCalc: function(){
        this.totalCount = 0;
        this.totalPrice = 0;
        document.querySelectorAll(".p_num").forEach(function (item) {
            if(item.parentElement.parentElement.parentElement.previousElementSibling.firstElementChild.firstElementChild.checked == true){
                var count = parseInt(item.getAttribute('value'));
                this.totalCount += count;
                var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
                this.totalPrice += count * price;
            }
        }, this); // forEach 2번째 파라메터로 객체를 넘겨서 this 가 객체리터럴을 가리키도록 함. - thisArg
    },
    //화면 업데이트
    updateUI: function () {
        document.querySelector('#sum_p_num').textContent = '상품갯수: ' + this.totalCount.formatNumber() + '개';
        document.querySelector('#sum_p_price').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원';

        
    },
    //개별 수량 변경
    changePNum: function (pos) {
        var item = document.querySelector('input[name=p_num'+pos+']');
        var p_num = parseInt(item.getAttribute('value'));
        var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? p_num-1 : event.target.value;
        
        if (parseInt(newval) < 1 || parseInt(newval) > 99) { return false; }

        item.setAttribute('value', newval);
        item.value = newval;

        var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
        item.parentElement.parentElement.nextElementSibling.textContent = (newval * price).formatNumber()+"원";
        //AJAX 업데이트 전송

        //전송 처리 결과가 성공이면    
        this.reCalc();
        this.updateUI();

    },
    checkItem: function () {
        this.reCalc();
        this.updateUI();

    },
    delItem: function () {
        event.target.parentElement.parentElement.parentElement.remove();
        this.reCalc();
        this.updateUI();

        var cartNo = (event.target.parentElement.parentElement.previousElementSibling.previousElementSibling.firstElementChild.nextElementSibling.nextElementSibling.firstElementChild.getAttribute('value'));
        
        location.href = "<%= contextPath %>/delete.ca?cartNo=" + cartNo + "&userId=" + userId;
        
    },

    
 checkSelectAll: function()  {
  // 전체 체크박스
  const checkboxes 
    = document.querySelectorAll('input[name="buy"]');
  // 선택된 체크박스
  const checked 
    = document.querySelectorAll('input[name="buy"]:checked');
  // select all 체크박스
  const selectAll 
    = document.querySelector('input[name="selectall"]');
  
  if(checkboxes.length === checked.length)  {
    selectAll.checked = true;
  }else {
    selectAll.checked = false;
  }
    
        this.reCalc();
        this.updateUI();
},

 selectAll: function(selectAll)  {
  const checkboxes 
     = document.getElementsByName('buy');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked
  })

        this.reCalc();
        this.updateUI();
    
},
 submitOrder: function(){
    
    // 체크된 ORDER_STATUS y해주고, 수량 수정해주고, status도 n해준다
    document.querySelectorAll("input[name=buy]:checked").forEach(function (item) {

    cartNo = item.parentElement.nextElementSibling.nextElementSibling.firstElementChild.getAttribute('value')

    count = item.parentElement.parentElement.nextElementSibling.firstElementChild.nextElementSibling.firstElementChild.firstElementChild.nextElementSibling.getAttribute('value')

    location.href = "<%= contextPath %>/orderItem.ca?cartNo=" + cartNo + "&userId=" + userId + "&count=" + count;

    });

 }

}

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};


</script>


</html>
