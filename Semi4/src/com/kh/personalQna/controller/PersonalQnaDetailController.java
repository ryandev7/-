package com.kh.personalQna.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class PersonalQnaDetailController
 */
@WebServlet("/detail.pqa")
public class PersonalQnaDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalQnaDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int qno = Integer.parseInt(request.getParameter("qno"));
		String userId = request.getParameter("userId");
		
		PersonalQnaService pService = new PersonalQnaService();
		PersonalQnaAnswerService paService = new PersonalQnaAnswerService();
		
		// personalQna, personalQnaAnswer 조회
		PersonalQna p = pService.selectPqna(qno);
		ArrayList<PersonalQnaAnswer> paList = paService.selectPqnaAsList();
		
		// 조회했던 p, pa넘기기
		request.setAttribute("p", p);
		//request.setAttribute("paList", paList); // 이미 넘겨서 굳이 안넘겨도 될듯?
		
		if(userId.equals("admin")) {
			request.getRequestDispatcher("/list.pq?cpage=1").forward(request, response);
		} else {
			request.getRequestDispatcher("/userList.pq?cpage=1&userId="+ userId).forward(request, response);
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
