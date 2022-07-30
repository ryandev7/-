package com.kh.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.product.model.service.QnaService;
import com.kh.product.model.vo.QNA;

/**
 * Servlet implementation class ProductReviewEnrollFormController
 */
@WebServlet("/ansEnrollform.bo")
public class ProductAnsEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAnsEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int qaNo = Integer.parseInt(request.getParameter("qaNo"));
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		QnaService qService = new QnaService();
		
		QNA q = qService.selectQna(qaNo);
		
		request.setAttribute("q", q);
		
		request.setAttribute("qaNo", qaNo);
		
		request.setAttribute("pno", pno);
		
		
		request.getRequestDispatcher("views/product/productAnswerEnrollForm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
