package com.kh.product.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;
import com.kh.product.model.dao.ProductDao;
import com.kh.product.model.vo.Review;

public class ReviewSerivce {

	public int insertReview(Review r, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new ProductDao().insertReview(conn, r);
		
		
		int result2 = 1;
		
		if(at != null) {
			result2 = new ProductDao().insertReviewAttachment(conn, at);
		}
		
		if((result1 * result2) > 0) {
			commit(conn);
		} else{
			rollback(conn);
		}
		
		close(conn);
		
		return (result1 * result2);
	}

	public ArrayList<Review> selectReviewList(int prNo, PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Review> list = new ProductDao().selectReviewList(conn, prNo, pi);
		
		return list;
	}


	public int selectReviewCount(int prNo) {
		
		Connection conn = getConnection();
		
		int count = new ProductDao().selectReviewCount(conn, prNo);
		
		close(conn);
		
		return count;
		
	}

	public ArrayList<Attachment> selectReviewAttachment(int prNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> at = new ProductDao().selectReviewAttachment(conn, prNo);
		
		close(conn);
		
		return at;
		
	}

	public int deleteReview(int rvNo) {
		
		Connection conn = getConnection();
		
		int result = new ProductDao().deleteReview(conn, rvNo);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteQna(int qaNo) {
		
		Connection conn = getConnection();
		
		int result = new ProductDao().deleteQna(conn, qaNo);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


}