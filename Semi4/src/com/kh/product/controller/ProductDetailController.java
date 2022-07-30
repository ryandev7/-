package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.RecipanProSau;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.Review;

/**
 * Servlet implementation class ProductDetailController
 */
@WebServlet("/detail.pr")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int prNo = Integer.parseInt(request.getParameter("pno"));
		
		ProductService pService = new ProductService();
		
		Product p = pService.selectProduct(prNo);
		
		Attachment atTitle = pService.selectTitleAttachment(prNo);
		Attachment atContent = pService.selectContentAttachment(prNo);
		
		String pName = p.getPrName();
		
		ArrayList<Integer> peNoArr = pService.selectRecipeNo(pName);
		// 해당 재료가 포함되는 레시피넘버를 담아온다.
		
		ArrayList<Attachment> rcpAt = pService.selectRecipeAttachment(peNoArr);
		
		request.setAttribute("p", p);
		request.setAttribute("atTitle", atTitle);
		request.setAttribute("atContent", atContent);
		request.setAttribute("rcpAt", rcpAt);
		
		request.getRequestDispatcher("views/product/productDetailView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
