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
import com.kh.product.model.service.ReviewSerivce;
import com.kh.product.model.vo.Review;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ProductReviewInsertController
 */
@WebServlet("/reviewInsert.bo")
public class ProductReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductReviewInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10 * 10 * 1024;	// 10mb
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/review_upfiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			
			String rvTitle = multiRequest.getParameter("title");
			String rvContent = multiRequest.getParameter("content");
			String rvWriter = multiRequest.getParameter("userId");
			int rvPro = Integer.parseInt(multiRequest.getParameter("prNo"));
			
			Review r = new Review();
			
			r.setRvTitle(rvTitle);
			r.setRvContent(rvContent);
			r.setRvWriter(rvWriter);
			r.setRvPro(rvPro);
			
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("upfile") != null) {
				
				at = new Attachment();
				
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				
				at.setFilePath("resources/review_upfiles/");
				
				at.setProductNo(rvPro);
				
			}
			
			int result = new ReviewSerivce().insertReview(r, at);
			
			if(result > 0) {
				
				request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/detail.pr?pno=" + rvPro);
				
				
				
			} else { // 실패
				
				// 첨부파일이 있었는데 실패했다면 이미 업로드된 첨부파일을 굳이 서버에 보관할 필요는 없다(용량만차지)
				
				if(at != null) {
					// delete()호출 => 삭제시키고자 하는 파일 객체 생성
					new File(savePath + at.getChangeName()).delete();
				}
				
				request.setAttribute("errorMsg", "게시글 작성 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				
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
