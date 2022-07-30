package com.kh.product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ProductUpdateForm
 */
@WebServlet("/update.pr")
public class ProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		// multipart/form-data로 잘 전송되었을 때만 내용이 수행되게끔 조건
				if(ServletFileUpload.isMultipartContent(request)) {
				
					int maxSize = 1024 * 1024 * 10;
					// 2. 전달된 파일을 저장시킬 서버폴더의 물리적인 경로를 알아내기 String savePath
					String savePath = request.getSession().getServletContext().getRealPath("/resources/product_upfiles/");
					
					// 전달된 파일명 수정 후 서버에 업로드
					// MultiRequest 객체를 생성함으로써 서버에 파일이 내려받아짐
 					MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
					
					int pno = Integer.parseInt(multiRequest.getParameter("pno"));
					String prName = multiRequest.getParameter("name");
					String prIntroduce = multiRequest.getParameter("introduce");
					int proCatNo = Integer.parseInt(multiRequest.getParameter("category"));
					String prWeight = multiRequest.getParameter("weight");
					String prOrigin = multiRequest.getParameter("origin");
					int prPrice = Integer.parseInt(multiRequest.getParameter("price"));
					String prContent = multiRequest.getParameter("content");
					
					
					Product p = new Product();
					p.setPrNo(pno);
					p.setPrName(prName);
					p.setPrIntroduce(prIntroduce);
					p.setProCatNo(proCatNo);
					p.setPrWeight(prWeight);
					p.setPrOrigin(prOrigin);
					p.setPrPrice(prPrice);
					p.setPrContent(prContent);
					
					Attachment at = null;
					Attachment at2 = null;
					
					
					String[] orginFileNo = multiRequest.getParameterValues("originFileNo");
					String[] orginFileName = multiRequest.getParameterValues("originFileName");
					
					
					if(multiRequest.getOriginalFileName("file1") != null ) {
						
						// 새로운 파일명이 존재한다면 객체 생성 후 원본이름, 수정된이름, 파일 경로
						at = new Attachment();
						at.setOriginName(multiRequest.getOriginalFileName("file1"));
						at.setChangeName(multiRequest.getFilesystemName("file1"));
						at.setFilePath("resources/product_upfiles/");
						at.setFileLevel(1);
						// => 여기까지는 새롭게 업로드한 새로운 첨부파일에 대한 내용
						// 첨부파일이 있을 경우 + 원본파일도 있을 경우
						
						// 기존파일이 존재함
						// 기존 파일에 대한 파일번호 at에 담을 것(WHERE절에 써야 하니까)
						at.setFileNo(Integer.parseInt(orginFileNo[0]));

						
						// 기존에 서버에 존재하던 첨부파일 삭제
						new File(savePath+orginFileName[0]).delete();
							
						
					}
					
					if(multiRequest.getOriginalFileName("file2") != null ) {
						// 새로운 파일명이 존재한다면 객체 생성 후 원본이름, 수정된이름, 파일 경로
						at2 = new Attachment();
						at2.setOriginName(multiRequest.getOriginalFileName("file2"));
						at2.setChangeName(multiRequest.getFilesystemName("file2"));
						at2.setFilePath("resources/product_upfiles/");
						at2.setFileLevel(2);
						// => 여기까지는 새롭게 업로드한 새로운 첨부파일에 대한 내용
							
						// 기존파일이 존재함
						// 기존 파일에 대한 파일번호 at에 담을 것(WHERE절에 써야 하니까)
						at2.setFileNo(Integer.parseInt(orginFileNo[1]));
						System.out.println(orginFileNo[1]);
						
						// 기존에 서버에 존재하던 첨부파일 삭제
						new File(savePath+orginFileName[1]).delete();
							
					}
				

					
				int result = new ProductService().updateProduct(p, at, at2);
					
					
					if(result > 0) { // 성공 => 상세보기 페이지 이동
						
						// localhost:8001/jsp/detail.bo?bno=X
						
						request.getSession().setAttribute("alert", "식재료 수정 성공");
						response.sendRedirect(request.getContextPath() + "/detail.pr?pno=" + pno);
						
					} else { // 실패 => 에러페이지 보여주기
						request.setAttribute("errorMsg", "식재료 수정 실패");
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
