package com.kh.recipan.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.model.vo.Category;
import com.kh.common.model.vo.PageInfo;
import com.kh.recipan.model.service.RecipanService;
import com.kh.recipan.model.vo.Recipan;

/**
 * Servlet implementation class JqAjaxRecipanListView
 */
@WebServlet("/list.ajax")
public class JqAjaxRecipanListView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxRecipanListView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		int listCount; // 현재 일반게시판의 게시글 총 갯수 BOARD로부터 조회 COUNT(*)활용 (STATUS='Y')
		int currentPage; //현재페이지(즉 사용자가 요청한 페이지) -> request.getParameter("cpage");
		int pageLimit; //페이지 하단에 보여질 페이징바의 최대 갯수 -> 10개고정
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수 - > 10개고정
		
		int maxPage; //가장 마지막 페이지가 몇번 페이지인지 <총페이지 갯수>
		int startPage; //페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지하단에 보여질 페이징바의 끝수
		int order = Integer.parseInt(request.getParameter("orderBy"));
		String orderBy = "";
		if(order == 1) {
			orderBy = "PE_NO DESC";
		}else if(order == 2) {
			orderBy = "PE_TITLE ASC";
		}else {
			orderBy = "PE_LIKECOUNT DESC";
		}
		
		
		int proNo = Integer.parseInt(request.getParameter("product"));
		int kindNo = Integer.parseInt(request.getParameter("kind"));
		int natNo = Integer.parseInt(request.getParameter("nation"));
		
		
		String userId = request.getParameter("userId");
		
		listCount = new RecipanService().selectListCount(natNo, kindNo, proNo);
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		pageLimit = 10;
		boardLimit = 9;
		maxPage=(int) Math.ceil((double)listCount/boardLimit);
		startPage = ((currentPage/pageLimit)) * pageLimit + 1;
		endPage = startPage + pageLimit-1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		ArrayList<PageInfo> pi = new ArrayList<>();
		pi.add(new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage));
		 
		ArrayList<Recipan> list = new RecipanService().selectList(pi.get(0), natNo, kindNo, proNo, orderBy, userId);
		
		ArrayList<ArrayList> recipan = new ArrayList<ArrayList>();
		
		recipan.add(list);
		recipan.add(pi);
		
		
		
		new Gson().toJson(recipan, response.getWriter());		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
