package com.kh.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.model.service.CartService;
import com.kh.cart.model.vo.Cart;
import com.kh.cart.model.vo.Order;

/**
 * Servlet implementation class CartCheckedItemController
 */
@WebServlet("/orderItem.ca")
public class CartOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderNo = request.getParameter("orderNo");
		

			
			int cartNo = Integer.parseInt(request.getParameter("cartNo")); 
			
			int count = Integer.parseInt(request.getParameter("count"));
			

			String userId = request.getParameter("userId");
			
			int result1 = new CartService().UpdateCartOrderItem(cartNo, count);
			
			ArrayList<Cart> orderedCart = new CartService().SelectOrderedCart(cartNo); 
			
			
			
			ArrayList<Order> orderList = new ArrayList<Order>();
				
			
				Order o = new Order();
				
				o.setOrderPro(orderedCart.get(0).getPrName());
				o.setOrderPrice(orderedCart.get(0).getPrPrice() * orderedCart.get(0).getCartAmount());
				o.setOrderAmount(orderedCart.get(0).getCartAmount());
				o.setUserId(orderedCart.get(0).getUserId());
				orderList.add(o);
			
			
			int result2 = new CartService().InsertOrder(orderList);
			
			if(result1 * result2 > 0) {
				response.sendRedirect(request.getContextPath() + "/list.ca?userId=" + userId);
			} else {
				request.setAttribute("errorMsg", "체크에 실패했습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
