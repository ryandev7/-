package com.kh.personalQna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.personalQna.model.service.PersonalQnaService;
import com.kh.personalQna.model.vo.PersonalQna;

/**
 * Servlet implementation class PersonalQnaInsertController
 */
@WebServlet("/insert.pq")
public class PersonalQnaInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalQnaInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// post 인코딩
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String pqTitle = request.getParameter("pqnatitle");
		String pqContent = request.getParameter("pqnacontent");
		
		PersonalQna pq = new PersonalQna();
		pq.setPqnaWriter(userId);
		pq.setPqnaTitle(pqTitle);
		pq.setPqnaContent(pqContent);
		
		int result = new PersonalQnaService().insertPqna(pq);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMessage1", "문의사항이 등록되었습니다.");
			response.sendRedirect(request.getContextPath() + "/userList.pq?cpage=1&userId="+ userId);
		}else {
			request.setAttribute("errorMsg", "문의사항 등록 실패!");
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
