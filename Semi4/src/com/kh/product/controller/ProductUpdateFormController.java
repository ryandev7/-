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
@WebServlet("/updateForm.pr")
public class ProductUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ArrayList<Category> list = new ProductService().selectCategoryList();
		
		request.setAttribute("list", list);
		
		int prNo = Integer.parseInt(request.getParameter("pno"));
		
		System.out.println(prNo);
		
		Product p = new ProductService().selectProductList(prNo);
		
		ArrayList<Attachment> atList = new ProductService().selectAttachment(prNo);
		
		request.setAttribute("list", list);
		request.setAttribute("p", p);
		request.setAttribute("atList", atList);
		
		request.getRequestDispatcher("views/board/productUpdateForm.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
