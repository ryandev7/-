package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.model.vo.Category;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId"); // 필수입력
		String userName = request.getParameter("userName"); // 필수입력
		String email = request.getParameter("email"); // 빈 문자열이 들어갈 수 있음
		String address = request.getParameter("address"); // 빈 문자열이 들어갈 수 있음
		String phone = request.getParameter("phone"); // 빈 문자열이 들어갈 수 있음
		String nickName = request.getParameter("nickName");
		String cookLevel = request.getParameter("cookLevel"); // ["하","중","상"] / null
		String favoriteFood = request.getParameter("favoriteFood"); // ["한식", "일식", ...] / null
	
		Member m = new Member();
		
		m.setUserName(userName);
		m.setEmail(email);
		m.setAddress(address);
		m.setPhone(phone);
		m.setNickName(nickName);
		m.setCookLevel(cookLevel);
		m.setFavoriteFood(favoriteFood);
		m.setUserId(userId);
		
		Member updateMem = new MemberService().updateMember(m);
	
		
		
		if(updateMem != null) { 
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "성공적으로 회원정보를 수정했습니다.");
			session.setAttribute("loginUser", updateMem);
			
			request.getRequestDispatcher("views/member/myPage.jsp").forward(request, response);
			
		} else {
			request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
			request.getRequestDispatcher("views/common/ErrorPage.jsp").forward(request, response);
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
