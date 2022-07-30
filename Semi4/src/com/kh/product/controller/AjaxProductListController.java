package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.model.vo.PageInfo;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class AjaxProductListController
 */
@WebServlet("/plist.all")
public class AjaxProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxProductListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 필요한 변수들
		int listCount; // 현재 일반게시판의 게시글 총 개수 => BOARD로 부터 조회 COUNT(*)활용(STATUS = 'Y')
		int currentPage; // 현재 페이지(즉, 사용자가 요청한 페이지) => request.getParameter("cpage");
		int pageLimit; // 페이지 하단에 보여질 페이징 바의 최대 개수 => 5개로 고정
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수 => 5개로 고정
					
		int maxPage; // 가장 마지막 페이지가 몇 번 페이지 인지(총 페이지의 개수)
		int startPage; // 페이지 하단에 보여질 페이징 바의 시작 수
		int endPage; // 페이지 하단에 보여질 페에징 바의 끝 수
				
		// * listCount : 총 게시글 개수
		listCount = new ProductService().selectListCount();
				
		// * currentPage : 현재 페이지(== 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage"));
				
		// * pageLimit : 페이징 바의 페이지 최대 개수
		pageLimit = 5;
				
		// * boardLimit : 현 페이지에 보여질 게시글의 최대 개수
		boardLimit = 9;
				
		// * maxPage : 가장 마지막 페이지가 몇 번 페이지인지(총 페이지 개수)
		maxPage = (int)Math.ceil((double)listCount / boardLimit);

				
		// *startPage : 페이지 하단에 보여질 페이지바의 시작 수
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
				
		// * endPage : 페이지 하단에 보여질 페이징 바의 끝 수
		endPage = startPage + pageLimit - 1;
				
		if(endPage > maxPage) {
		endPage = maxPage;
		}
				
		ArrayList<PageInfo> pi = new ArrayList<>();
		pi.add(new PageInfo(listCount, currentPage, pageLimit, boardLimit,
							maxPage, startPage, endPage));
		ArrayList<Product> list = new ProductService().selectList(pi.get(0));
				
				
		request.setAttribute("list", list); // 우리가 실제로 조회한 페이지에 보일 9개의 게시글
		request.setAttribute("pi", pi); // 페이징바를 만들 때 필요한 변수
				
		ArrayList<ArrayList> product = new ArrayList<ArrayList>();
		product.add(list);
		product.add(pi);
				
		response.setContentType("application/json; charset=UTF-8");

		new Gson().toJson(product, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
