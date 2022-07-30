package com.kh.product.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.Category;
import com.kh.common.model.vo.PageInfo;
import com.kh.common.model.vo.RecipanProSau;
import com.kh.product.model.dao.ProductDao;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.Review;

import static com.kh.common.JDBCTemplate.*;

public class ProductService {

	
	/**
	 * 카테고리 불러오기
	 */
	public ArrayList<Category> selectCategoryList() {
		
		Connection conn = getConnection();
		
		 ArrayList<Category> list = new ProductDao().selectCategory(conn);
		 
		 close(conn);
		
		return list;
	}

	/**
	 * 식재료 등록하기
	 */
	public int insertProduct(Product p, ArrayList<Attachment> list) {
		
		Connection conn = getConnection();
		
		int result1 = new ProductDao().insertProduct(conn, p);
		
		int result2 = new ProductDao().insertAttachmentList(conn, list);
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return (result1 * result2);

	}

	/**
	 * 보여줄 게시글 개수
	 */
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new ProductDao().selectListCount(conn);
		// SELECT문의 결과는 ResultSet
		// 실질적으로 생각해보면 게시글의 총 개수는 정수형
		
		close(conn);
		
		return listCount;
	}

	/**
	 * 페이징바
	 */
	public ArrayList<Product> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public Product selectProductList(int prNo) {
		
		Connection conn = getConnection();
		
		Product p = new ProductDao().selectProductList(conn, prNo);
		
		close(conn);
		
		return p;
	}

	public Product selectProduct(int prNo) {
		
		Connection conn = getConnection();
		
		Product p = new ProductDao().selectProduct(conn, prNo);
		
		close(conn);

		return p;
	}
	
	public ArrayList<Attachment> selectAttachment(int prNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> atList = new ProductDao().selectAttachment(conn, prNo);
		
		close(conn);
		
		return atList;
		
	}


	public Attachment selectContentAttachment(int prNo) {
		
		Connection conn = getConnection();
		
		Attachment atContent = new ProductDao().selectContentAttachment(conn, prNo);
		
		close(conn);
				
		return atContent;
	}

	public Attachment selectTitleAttachment(int prNo) {
		Connection conn = getConnection();
		
		Attachment atTitle = new ProductDao().selectTitleAttachment(conn, prNo);
		
		close(conn);
				
		return atTitle;
	}
	
	/**
	 * 총 보여질 야채/채소 게시글 개수
	 */
	public int selectVegetableListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new ProductDao().selectVegetableListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Product> selectVegetableList(PageInfo pi) {
		Connection conn = getConnection();

		ArrayList<Product> list = new ProductDao().selectVegetableList(conn, pi);

		close(conn);

		return list;
	}
	
	public int selectFruitListCount() {
		Connection conn = getConnection();
		
		int listCount = new ProductDao().selectFruitListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Product> selectFruitList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDao().selectFruitList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public int selectSeaListCount() {
		Connection conn = getConnection();
		
		int listCount = new ProductDao().selectSeaListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Product> selectSeaList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDao().selectSeaList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public int selectMeatListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new ProductDao().selectMeatListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Product> selectMeatList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDao().selectMeatList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public int selectEtcListCount() {
		Connection conn = getConnection();
		
		int listCount = new ProductDao().selectEtcListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Product> selectEtcList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDao().selectEtcList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public int updateProduct(Product p, Attachment at, Attachment at2) {
		
		Connection conn = getConnection();
		
		int result1 = new ProductDao().updateProduct(conn, p);

		// ATTACHMENT테이블과 관련된 결과물
		int result2 = 1;
		int result3 = 1;
		

		// 새롭게 첨부파일이 있을 경우
		if(at != null) {
			// 기존에 첨부파일이 있었을 경우
			if(at.getFileNo() != 0) {
				result2 = new ProductDao().updateAttachment(conn, at);
				System.out.println("result2 : " + result2);
				System.out.println(at);
			}
		} // 아닐 경우 Attachment에다가 뭐 해줄 일이 없으니 else 구문 X
		
		if(at2 != null) {
			// 기존에 첨부파일이 있었을 경우
			if(at2.getFileNo() != 0) {
				result3 = new ProductDao().updateAttachment(conn, at2);
				System.out.println("result3 : " + result3);
				System.out.println(at2);
			}
		} // 아닐 경우 Attachment에다가 뭐 해줄 일이 없으니 else 구문 X
		
		if(result1 * result2 * result3 > 0) { // 둘 다 성공했을 경우에만 commit
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return (result1 * result2 * result3);
	}
	

	public ArrayList<Integer> selectRecipeNo(String pName) {
		
		Connection conn = getConnection();
		
		ArrayList<Integer> peNoArr = new ProductDao().selectRecipeNo(conn, pName); 
		
		close(conn);
		
		return peNoArr;
	}

	public ArrayList<Attachment> selectRecipeAttachment(ArrayList<Integer> peNoArr) {
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> rcpAt = new ProductDao().selectRecipeAttachment(conn, peNoArr);
		
		return rcpAt;
	}

	public int deleteProduct(int prNo) {
		
		Connection conn = getConnection();
		
		int result = new ProductDao().deleteProduct(conn, prNo);
		
		return result;
	}


}
