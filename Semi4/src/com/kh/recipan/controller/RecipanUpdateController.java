package com.kh.recipan.controller;

import java.io.File;
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
 * Servlet implementation class RecipanUpdateController
 */
@WebServlet("/update.pan")
public class RecipanUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipanUpdateController() {
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
			int peNo = Integer.parseInt(multiRequest.getParameter("peNo"));
												
			
			
			
			String peTitle = multiRequest.getParameter("recipanTitle");
			String peIntroduce = multiRequest.getParameter("recipanIntro");						
			
			int natCatNo = Integer.parseInt(multiRequest.getParameter("nationalCat"));
			int kindCat = Integer.parseInt(multiRequest.getParameter("kindCat"));
			int productCat = Integer.parseInt(multiRequest.getParameter("productCat"));						
			
			String peFoodAmount = multiRequest.getParameter("recipanPerson");
			String peCookTime = multiRequest.getParameter("recipanTime");
			String peCookLevel = multiRequest.getParameter("recipanDifficulty");			
						
			Recipan r = new Recipan();
			
			r.setPeTitle(peTitle);
			r.setPeIntroduce(peIntroduce);						
			r.setNatCatNo(natCatNo);
			r.setKindCatNo(kindCat);
			r.setProCatNo(productCat);			
			r.setPeFoodAmount(peFoodAmount);
			r.setPeCookTime(peCookTime);
			r.setPeCookLevel(peCookLevel);
			r.setPeNo(peNo);
			
			

			
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
			
			
			
			
			

			
			
			if(multiRequest.getOriginalFileName("thumbnailFile") != null) { //첨부파일이 있는지 확인
				String ThumnnailOrigin = new RecipanService().selectOriginImg(peNo);
				int ThumnnailOriginFileNo = new RecipanService().selectOriginImgFileNo(peNo);
				
				new File(savePath+ThumnnailOrigin).delete(); 
				//썸네일은 파일은 새로 생겼을 경우 반드시 원본파일도 존재한다. 따라서 원본파일을 삭제 											
				
				Attachment at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("thumbnailFile"));
				at.setChangeName(multiRequest.getFilesystemName("thumbnailFile"));
				at.setFilePath("resources/thumbnail_upfiles/");
	
				if( ! (new RecipanService().updateAttachment(ThumnnailOriginFileNo,at) > 0)) {
					request.setAttribute("errorMsg", "썸네일파일삽입  실패");
					request.getRequestDispatcher("views/common/ErrorPage.jsp").forward(request, response);
					return;
				}				
			}
			
			
			String key = "";
//			originFileArr <- 원래 존재했던 모든 파일들 index 는 cookStep을 의미한다.
			ArrayList<Integer> originFileNoArr = new RecipanService().selectCookStepImgFileNo(peNo);
			ArrayList<String> originFileArr = new RecipanService().selectStepOriginImg(peNo);
			for(int i = 0; i < cookStepIntro.length; i++) {
				key = "cookStepFile" + i;
				if(multiRequest.getOriginalFileName(key) != null) {
					
					
					if(originFileArr.size() > i) {					
						//첨부파일이 존재하며 원본파일이 있을경우(이때 cookStep은 i-1)
						//이 경우에 원본파일을 삭제하고 첨부파일의 데이터로 update 해야한다.
						new File(savePath+originFileArr.get(i)).delete(); //원본파일 삭제 
						Attachment at = new Attachment(); // 첨부파일의 정보를 받아올놈 생성
						at.setOriginName(multiRequest.getOriginalFileName(key));
						at.setChangeName(multiRequest.getFilesystemName(key));
						at.setFilePath("resources/thumbnail_upfiles/");
						at.setStepNo(i+1); // 스텝은 i보다 1 커야함 ~ 
						if( ! (new RecipanService().updateAttachment(originFileNoArr.get(i),at) > 0)) {
							request.setAttribute("errorMsg", "조건1 업데이트  실패");
							request.getRequestDispatcher("views/common/ErrorPage.jsp").forward(request, response);
							return;
						}
					}else {
						//원본파일보다 더 많은 데이터들은 ATTACHMENT 객체에 추가한다.
						Attachment at = new Attachment();
						at.setOriginName(multiRequest.getOriginalFileName(key));
						at.setChangeName(multiRequest.getFilesystemName(key));
						at.setFilePath("resources/thumbnail_upfiles/");
						at.setFileLevel(2); // 본문사진 의미
						at.setStepNo(i+1);
						if( ! (new RecipanService().insertAttachment(peNo,at) > 0)) {
							request.setAttribute("errorMsg", "쿡스텝 파일삽입  실패");
							request.getRequestDispatcher("views/common/ErrorPage.jsp").forward(request, response);
							return;
						}
					}
				}
				
			}
			// cookStepIntro 즉 입력된 데이터의 길이보다 원본파일이 적을경우
			
			if(cookStepIntro.length < originFileArr.size()) {
				for(int i = originFileArr.size(); i > cookStepIntro.length; i--) {
					new File(savePath+originFileArr.get(i-1)).delete(); //원본파일 삭제 
					if( ! (new RecipanService().deleteOriginFile(originFileNoArr.get(i-1)) > 0)) { // 원본 db데이터 삭제 
						request.setAttribute("errorMsg", "원본 db데이터 삭제  실패");
						request.getRequestDispatcher("views/common/ErrorPage.jsp").forward(request, response);
						return;
					}
				}
			}
			
			
			
	
			
			
 			int deleteResult = new RecipanService().deleteOriginRecipanAndUpdateRecipan(peNo);
			
			if(! (deleteResult > 0) ) {
				request.setAttribute("errorMsg", "기존데이터 삭제 실패");
				request.getRequestDispatcher("views/common/ErrorPage.jsp").forward(request, response);
				return;
			}
			
			
									
			int result = new RecipanService().updateRecipan(r,product,sauce,rcs,list);
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "게시글 수정 성공");
				response.sendRedirect(request.getContextPath());
			}
			else {
				request.setAttribute("errorMsg", "업데이트 실패");
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
