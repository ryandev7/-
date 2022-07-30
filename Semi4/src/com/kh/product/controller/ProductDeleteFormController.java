package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.Category;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class ProductUpdateFormController
 */
@WebServlet("/deleteForm.pr")
public class ProductDeleteFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDeleteFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int prNo = Integer.parseInt(request.getParameter("pno"));
		
		System.out.println(prNo);
		

		int result = new ProductService().deleteProduct(prNo);
		
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "식재료 삭제 성공");
			request.getRequestDispatcher("views/board/productListView.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("alertMsg", "식재료 삭제 실패");
			request.getRequestDispatcher("views/board/productListView.jsp").forward(request, response);
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
