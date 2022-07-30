package com.kh.personalQna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.model.vo.PageInfo;
import com.kh.personalQna.model.service.PersonalQnaService;
import com.kh.personalQna.model.vo.PersonalQna;
import com.kh.personalQnaAnswer.model.service.PersonalQnaAnswerService;
import com.kh.personalQnaAnswer.model.vo.PersonalQnaAnswer;

/**
 * Servlet implementation class PersonalQnaUserListController
 */
@WebServlet("/userList.pq")
public class PersonalQnaUserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalQnaUserListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int listCount; // 현재 일반게시판의 게시글 총 갯수 => BOARD로 부터 조회 COUNT(*)활용(STATUS = 'Y')
		int currentPage; // 현재 페이지(즉, 사용자가 요청한 페이지) => request.getParameter("cpage");
		int pageLimit; // 페이지하단에 보여질 페이징바의 최대 갯수 => 10개로 고정
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수 => 10개로 고정
		
		int maxPage; // 가장 마지막 페이지가 몇번 페이지 인지(총 페이지의 갯수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
		
		String id = request.getParameter("userId");
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		listCount = new PersonalQnaService().selectUserListCount(id);
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		pageLimit = 10;
		
		boardLimit = 10;
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo upi = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
				maxPage, startPage, endPage);
		
		
		ArrayList<PersonalQna> userlist = new PersonalQnaService().selectUserPqnaList(id, upi);
		
		// 답변정보 담긴 리스트
		PersonalQnaAnswerService paService = new PersonalQnaAnswerService();
		ArrayList<PersonalQnaAnswer> paList = paService.selectPqnaAsList();
		
		request.setAttribute("list", userlist);
		request.setAttribute("pi", upi);
		request.setAttribute("paList", paList);
		
		/*for(PersonalQna p : userlist) {
			System.out.println(p);
		}*/

		/*for(PersonalQnaAnswer p : paList) {
			System.out.println(p.getPqnaContent());
		}*/
		
		HttpSession session = request.getSession();
		
		// 주소창으로 로그인 안하고 바로 넘어오는거 차단
		if(session.getAttribute("loginUser") == null) {
			
			session.setAttribute("alertMsg", "로그인 후 이용가능한 서비스 입니다.");
			
			response.sendRedirect(request.getContextPath());
			
		} else {
			request.getRequestDispatcher("/views/personalQuestion/userPersonalQuestionList.jsp").forward(request, response);
			
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
