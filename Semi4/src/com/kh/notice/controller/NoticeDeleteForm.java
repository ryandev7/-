package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDeleteForm
 */
@WebServlet("/delete.no")
public class NoticeDeleteForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get 방식 => 인코딩 x => 값을 뽑을 때 쿼리스트링에 키 : nno
		
		// 2) 값 뽑기
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		System.out.println(noticeNo);
		// 3) 가공 패스
		
		int result = new NoticeService().deleteNotice(noticeNo);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/list.no?cpage=1");
		} else {
			request.setAttribute("errorMsg", "삭제에 실패했습니다.");
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
