package com.kh.recipan.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Category;
import com.kh.common.model.vo.RecipanCookStep;
import com.kh.common.model.vo.RecipanProSau;
import com.kh.recipan.model.service.RecipanService;
import com.kh.recipan.model.vo.Recipan;

/**
 * Servlet implementation class RecipanUpdateFormController
 */
@WebServlet("/updateForm.pan")
public class RecipanUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipanUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int peNo = Integer.parseInt(request.getParameter("peNo"));
		
		Recipan recipan = new RecipanService().selectReciapn(peNo);
		ArrayList<RecipanProSau> product = new RecipanService().selectRecipanPro(peNo); 
		ArrayList<RecipanProSau> sauce = new RecipanService().selectRecipanSau(peNo);
		ArrayList<RecipanCookStep> cookStep = new RecipanService().selectRecipanCookStep(peNo);
		ArrayList<ArrayList<Category>> categoryList = new RecipanService().selectCategoryList();
		
		request.setAttribute("recipan", recipan);		
		request.setAttribute("product", product);
		request.setAttribute("sauce", sauce);
		request.setAttribute("cookStep", cookStep);
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("views/recipan/recipanUpdateForm.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
