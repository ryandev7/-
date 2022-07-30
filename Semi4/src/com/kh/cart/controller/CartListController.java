package com.kh.cart.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cart.model.service.CartService;
import com.kh.cart.model.vo.Cart;
import com.kh.common.model.vo.Attachment;

/**
 * Servlet implementation class CartListController
 */
@WebServlet("/list.ca")
public class CartListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		
		ArrayList<Cart> cList = new CartService().selectAllCartList(userId);
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
		ArrayList<Attachment> proAt = new CartService().selectProductAttachment(cList);
		
		request.setAttribute("cList", cList);
		request.setAttribute("proAt", proAt);
		request.setAttribute("formatter", formatter);
		
		
		request.getRequestDispatcher("/views/cart/cartListView2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
