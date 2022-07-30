package com.kh.recipan.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.RecipanCookStep;
import com.kh.common.model.vo.RecipanProSau;
import com.kh.recipan.model.service.RecipanService;
import com.kh.recipan.model.vo.Recipan;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class recipanInsertController
 */
@WebServlet("/insert.pan")
public class RecipanInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipanInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			ServletContext application = request.getSession().getServletContext();
			String savePath = application.getRealPath("/resources/thumbnail_upfiles/");
			MultipartRequest multiRequest = new MultipartRequest(request,savePath,maxSize,"UTF-8", new MyFileRenamePolicy());
			
			
			String peTitle = multiRequest.getParameter("recipanTitle");
			String peIntroduce = multiRequest.getParameter("recipanIntro");
			String peWriter = multiRequest.getParameter("peWriter");
			
			
			int natCatNo = Integer.parseInt(multiRequest.getParameter("nationalCat"));
			int kindCat = Integer.parseInt(multiRequest.getParameter("kindCat"));
			int productCat = Integer.parseInt(multiRequest.getParameter("productCat"));						
			
			String peFoodAmount = multiRequest.getParameter("recipanPerson");
			String peCookTime = multiRequest.getParameter("recipanTime");
			String peCookLevel = multiRequest.getParameter("recipanDifficulty");
			int recipanType = Integer.parseInt(multiRequest.getParameter("recipanType"));
						
			Recipan r = new Recipan();
			
			r.setPeTitle(peTitle);
			r.setPeIntroduce(peIntroduce);
			r.setPeWriter(peWriter);
			
			r.setNatCatNo(natCatNo);
			r.setKindCatNo(kindCat);
			r.setProCatNo(productCat);
			
			r.setPeFoodAmount(peFoodAmount);
			r.setPeCookTime(peCookTime);
			r.setPeCookLevel(peCookLevel);
			
			r.setBoardType(recipanType);
			

			
			RecipanProSau product = new RecipanProSau();
			String[] productName = multiRequest.getParameterValues("productName");					
			String[] productAmount = multiRequest.getParameterValues("productWeight");
			product.setName(productName);
			product.setAmount(productAmount);
			
			
			RecipanProSau sauce = new RecipanProSau();
			String[] sauceName = multiRequest.getParameterValues("sauceName");
			String[] sauceAmount = multiRequest.getParameterValues("sauceWeight");
			sauce.setName(sauceName);
			sauce.setAmount(sauceAmount);
			
			
			
			
			
			String[] cookStepIntro = multiRequest.getParameterValues("cookStepIntro");
			RecipanCookStep rcs = new RecipanCookStep(); 
			rcs.setStepContent(cookStepIntro);
			
			
			
			ArrayList<Attachment> list = new ArrayList<Attachment>();
			
//			thumbnail 			
			if(multiRequest.getOriginalFileName("thumbnailFile") != null) {
				Attachment at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("thumbnailFile"));
				at.setChangeName(multiRequest.getFilesystemName("thumbnailFile"));
				at.setFilePath("resources/thumbnail_upfiles/");
				at.setFileLevel(1); // thmbnail 의미
				
  				list.add(at);				
			}
			
			
			
			
			int i = 0;
			String key = "cookStepFile" + i;
			while(multiRequest.getOriginalFileName(key) != null) {
				Attachment at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName(key));
				at.setChangeName(multiRequest.getFilesystemName(key));
				at.setFilePath("resources/thumbnail_upfiles/");
				at.setFileLevel(2); // 본문사진 의미
				at.setStepNo(i+1);

				list.add(at);
				i++;
				key = "cookStepFile" + i;
			}
			int result = new RecipanService().insertRecipan(r,product,sauce,rcs,list);
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
				response.sendRedirect(request.getContextPath());
			}
			else {
				request.setAttribute("errorMsg", "실패");
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
