package com.kh.recipan.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.RecipanCookStep;
import com.kh.common.model.vo.RecipanProSau;
import com.kh.product.model.vo.Product;
import com.kh.recipan.model.service.RecipanService;
import com.kh.recipan.model.vo.Recipan;

/**
 * Servlet implementation class DetailFormController
 */
@WebServlet("/detail.pan")
public class RecipanDetailFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipanDetailFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int peNo = Integer.parseInt(request.getParameter("peNo"));
		
		Recipan recipan = new RecipanService().selectReciapn(peNo);
		ArrayList<RecipanProSau> product =new RecipanService().selectRecipanPro(peNo);

		ArrayList<RecipanProSau> sauce = new RecipanService().selectRecipanSau(peNo);
//		Attachment titleImg;
		ArrayList<RecipanCookStep> cookStep = new RecipanService().selectRecipanCookStep(peNo);
		
		ArrayList<Product> withPr = new RecipanService().selectRecipanWithProduct(product, sauce);
		request.setAttribute("recipan", recipan);		
		request.setAttribute("product", product);
		request.setAttribute("sauce", sauce);
		request.setAttribute("cookStep", cookStep);
		request.setAttribute("withPr", withPr);
		request.getRequestDispatcher("views/recipan/recipanDetailView.jsp").forward(request, response);;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
