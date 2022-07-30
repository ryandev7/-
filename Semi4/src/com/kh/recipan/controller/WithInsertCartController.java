package com.kh.recipan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.model.service.CartService;

/**
 * Servlet implementation class JqAjaxWithInsertCart
 */
@WebServlet("/insertCartWithPr.pan")
public class WithInsertCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithInsertCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int result = 1;
		String userId = request.getParameter("userId");
		if(userId != null) {
									
			int[] prNoArr = new int[request.getParameterValues("prNo").length];
			
			for(int i = 0; i < prNoArr.length; i++) {			
				prNoArr[i] = Integer.parseInt(request.getParameterValues("prNo")[i]);
			}
							
			int[] prAmountArr = new int[request.getParameterValues("prAmount").length];
			
			for(int i = 0; i < prAmountArr.length; i++) {			
				prAmountArr[i] = Integer.parseInt(request.getParameterValues("prAmount")[i]);
			}
			
			
			for(int i = 0; i < prAmountArr.length; i++) {
				if(prAmountArr[i] != 0) {
					result *= new CartService().insertCart(userId, prNoArr[i], prAmountArr[i]); 
				}
				
			}
			
	
			if(result > 0) {			
				request.getSession().setAttribute("alertMsg", "상품을 장바구니에 담았어요!");
				response.sendRedirect(request.getContextPath()+"/list.ca?userId=" + userId);
				
			} else { // 실패			
				request.setAttribute("errorMsg", "상품 장바구니 추가 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				
			}
		}else {
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
