package com.kh.personalQna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.personalQna.model.service.PersonalQnaService;
import com.kh.personalQnaAnswer.model.service.PersonalQnaAnswerService;
import com.kh.personalQnaAnswer.model.vo.PersonalQnaAnswer;

/**
 * Servlet implementation class PersonalQnaAnswerInsertController
 */
@WebServlet("/insert.pqa")
public class PersonalQnaAnswerInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalQnaAnswerInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String pqContent = request.getParameter("pqnacontentAs");
		int qno = Integer.parseInt(request.getParameter("qno"));
		
		PersonalQnaAnswer pqa = new PersonalQnaAnswer();
		pqa.setPqnaContent(pqContent);
		pqa.setPqnaNo(qno);
		
		int result = new PersonalQnaAnswerService().insertPqnaAs(pqa);
		
		
		PersonalQnaService pq = new PersonalQnaService();
		int result2 = pq.updateAsStatus(qno);
		
		
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "답변이 등록되었습니다.");
			response.sendRedirect(request.getContextPath() + "/list.pq?cpage=1");
		} else {
			request.setAttribute("errorMsg", "답변 등록 실패!");
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
