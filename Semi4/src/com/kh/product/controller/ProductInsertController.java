package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.product.model.service.ProductService;
import com.kh.common.model.vo.Attachment;
import com.kh.product.model.vo.Product;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ProductInsertController
 */
@WebServlet("/insert.pr")
public class ProductInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) POST => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 파일 보내기
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// MultipartRequest객체 생성
			// 객체 생성 전
			// 1_1. 전송 용량 제한(10Mbyte)
			int maxSize = 1024 * 1024 * 10;
			
			// 1_2. 저장할 서버의 물리적 경로 제시
			String savePath = request.getServletContext().getRealPath("/resources/product_upfiles/");
			
			// 2. MultipartRequest 객체 생성(* 파일명 수정해서 올리기) / 파일을 올리는 구문
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 2) multiRequest로부터 값 뽑기 => getParameter 메소드
			String prName = multiRequest.getParameter("name");
			String prIntroduce = multiRequest.getParameter("introduce");
			int proCatNo = Integer.parseInt(multiRequest.getParameter("category"));
			String prWeight = multiRequest.getParameter("weight");
			String prOrigin = multiRequest.getParameter("origin");
			int prPrice = Integer.parseInt(multiRequest.getParameter("price"));
			String prContent = multiRequest.getParameter("content");
			
			// 3) VO로 가공
			// Product
			Product p = new Product();
			p.setPrName(prName);
			p.setPrIntroduce(prIntroduce);
			p.setProCatNo(proCatNo);
			p.setPrWeight(prWeight);
			p.setPrOrigin(prOrigin);
			p.setPrPrice(prPrice);
			p.setPrContent(prContent);
			
			// 여러개의 VO객체를 묶어서 다룰 경우 ArrayList();
			ArrayList<Attachment> list = new ArrayList();
			
			// 키 값 : file1~2
			for(int i = 1; i <= 2; i++) {
				
				// 키 값만 미리 변수로 셋팅
				String key = "file" + i;
				
				// 원본파일명이 존재하는지 메소드를 이용해서 파악
				if(multiRequest.getOriginalFileName(key) != null) { // 원본 파일이 존재한다
					
					// 첨부파일이 존재한다면 Attachment객체 생성
					// 필드 : 원본명, 수정명, 파일경로, 파일레벨**(1: 대표, 2: 상세)
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key)); // 원본명
					at.setChangeName(multiRequest.getFilesystemName(key)); // 수정명
					at.setFilePath("resources/product_upfiles/"); // 경로명

					// 파일레벨
					if(i == 1) {
						// 대표이미지
						at.setFileLevel(1);
					} else {
						// 상세이미지
						at.setFileLevel(2);
					}
					list.add(at);
				}
				
			} 
			// 4) Service단으로 토스 (테이블에 데이터를 넣는 구문)
			int result = new ProductService().insertProduct(p, list);
		
			// 5) 결과에 따른 응답뷰 지정
			if(result > 0) { // 성공 => list.pr로 요청(sendRedirect)
				request.getSession().setAttribute("alertMsg", "식재료 등록 성공");
				response.sendRedirect(request.getContextPath() + "/list.pr?cpage=1");
							
			} else {
				request.setAttribute("errorMsg", "식재료 등록 실패");
				request.getRequestDispatcher("views/common/ErrorPage.jsp").forward(request, response);
			}
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
