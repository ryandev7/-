package com.kh.product.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.product.model.service.QnaService;
import com.kh.product.model.service.ReviewSerivce;
import com.kh.product.model.vo.QNA;
import com.kh.product.model.vo.Review;

/**
 * Servlet implementation class ProductReviewInsertController
 */
@WebServlet("/qnaInsert.bo")
public class ProductQnaInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductQnaInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
			
		String qaTitle = request.getParameter("title");
		String qaContent = request.getParameter("content");
		String qaWriter = request.getParameter("userId");
		int rvPro = Integer.parseInt(request.getParameter("prNo"));
		
		QNA q = new QNA();
		
		q.setQnaTitle(qaTitle);
		q.setQnaContent(qaContent);
		q.setQnaWriter(qaWriter);
		q.setProductNo(rvPro);
		
		
		int result = new QnaService().insertQna(q);
		
		if(result > 0) {
			
			request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
			response.sendRedirect(request.getContextPath() + "/detail.pr?pno=" + rvPro);
			
			
		} else { // 실패
			
			request.setAttribute("errorMsg", "게시글 작성 실패");
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
