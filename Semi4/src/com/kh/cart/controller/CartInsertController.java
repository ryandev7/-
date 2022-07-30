package com.kh.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.model.service.CartService;

/**
 * Servlet implementation class CartInsertController
 */
@WebServlet("/cartInsert.ca")
public class CartInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		
		if(userId != null) {
			
			int pno = Integer.parseInt(request.getParameter("pno"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			int result = new CartService().insertCart(userId, pno, amount);
			
			if(result > 0) {
				
				request.getSession().setAttribute("alertMsg", "상품을 장바구니에 담았어요!");
				response.sendRedirect(request.getContextPath() + "/detail.pr?pno=" + pno);
				
			} else { // 실패
				
				request.setAttribute("errorMsg", "상품 장바구니 추가 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				
				}
			
		} else {
			response.sendRedirect(request.getContextPath() + "/loginForm.me");
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
