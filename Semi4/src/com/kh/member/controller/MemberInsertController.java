package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      request.setCharacterEncoding("UTF-8");
      
      String userId = request.getParameter("userId"); // 필수입력
      String userPwd = request.getParameter("userPwd"); //필수입력
      String userName = request.getParameter("userName"); // 필수입력
      String email = request.getParameter("email"); // 빈 문자열이 들어갈 수 있음
      String address = request.getParameter("address"); // 빈 문자열이 들어갈 수 있음
      String phone = request.getParameter("phone"); // 빈 문자열이 들어갈 수 있음
      String nickName = request.getParameter("nickName");
      String cookLevel = request.getParameter("cookLevel"); // ["하","중","상"] / null
      String favoriteFood = request.getParameter("favoriteFood");
      
      Member m = new Member(userId, userPwd, userName, email, address, phone, nickName, cookLevel, favoriteFood);
      
      int result = new MemberService().insertMember(m);
      
      if(result > 0) {
         request.setAttribute("userName", userName);
         request.setAttribute("successMsg", "님 환영합니다 !"); // 회원 가입 성공시 alertMsg에 추가
         request.getRequestDispatcher("views/member/successEnrollMember.jsp").forward(request, response);
         //response.sendRedirect(request.getContextPath());
         
      } else {
         request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
         request.getRequestDispatcher("views/member/memberEnrollForm.jsp").forward(request, response);
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