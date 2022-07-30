package com.kh.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.product.model.service.ReviewSerivce;

/**
 * Servlet implementation class ProductReviewDelete
 */
@WebServlet("/delete.qa")
public class ProductQnaDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductQnaDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int qaNo = Integer.parseInt(request.getParameter("qaNo"));
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		int result = new ReviewSerivce().deleteQna(qaNo);
		
		if(result > 0) {
			
			request.getSession().setAttribute("alertMsg", "게시글 삭제 성공");
			response.sendRedirect(request.getContextPath() + "/detail.pr?pno=" + pno);
			
			
		} else { // 실패
			
			request.setAttribute("errorMsg", "게시글 삭제 실패");
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
