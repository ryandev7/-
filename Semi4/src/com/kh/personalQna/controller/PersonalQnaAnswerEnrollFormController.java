package com.kh.personalQna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.personalQna.model.service.PersonalQnaService;
import com.kh.personalQna.model.vo.PersonalQna;
import com.kh.personalQnaAnswer.model.service.PersonalQnaAnswerService;
import com.kh.personalQnaAnswer.model.vo.PersonalQnaAnswer;

/**
 * Servlet implementation class PersonalQnaAnswerEnrollFormController
 */
@WebServlet("/enrollForm.pqa")
public class PersonalQnaAnswerEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalQnaAnswerEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int qno = Integer.parseInt(request.getParameter("qno"));
		
		
		PersonalQnaService pService = new PersonalQnaService();
		PersonalQna p = pService.selectPqna(qno);
		
		
		request.setAttribute("p", p);
		
		request.getRequestDispatcher("views/personalQuestion/personalQuestionAnswerEnrollForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
