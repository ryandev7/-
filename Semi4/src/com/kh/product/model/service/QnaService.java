package com.kh.product.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.product.model.dao.ProductDao;
import com.kh.product.model.vo.QNA;
import com.kh.product.model.vo.Review;

public class QnaService {

	public int insertQna(QNA q) {
		
		Connection conn = getConnection();
		
		int result = new ProductDao().insertQna(conn, q);
		
	
		if(result  > 0) {
			commit(conn);
		} else{
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int selectQnaCount(int prNo) {
		
		Connection conn = getConnection();
		
		int count = new ProductDao().selectQnaCount(conn, prNo);
		
		close(conn);
		
		return count;
	}

	public ArrayList<QNA> selectQnaList(int prNo, PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<QNA> list = new ProductDao().selectQnaList(conn, prNo, pi);
		
		return list;
	}

	public QNA selectQna(int prNo) {
		
		Connection conn = getConnection();
		
		QNA q = new ProductDao().selectQna(conn, prNo);
		
		return q;
	}

	public int insertAns(int qaNo, String ansContent) {
		
		Connection conn = getConnection();
		
		int result = new ProductDao().insertAns(conn, qaNo, ansContent);

		if(result  > 0) {
			commit(conn);
		} else{
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int ansComplete(int qaNo) {
	
		Connection conn = getConnection();
		
		int result = new ProductDao().ansComplete(conn, qaNo);

		if(result  > 0) {
			commit(conn);
		} else{
			rollback(conn);
		}
		
		close(conn);
		
		return result;

	}

	public ArrayList<QNA> selectAnsList(ArrayList<QNA> list) {
		
		Connection conn = getConnection();
		
		ArrayList<QNA> answer = new ProductDao().selectAnsList(conn, list);
		
		return answer;
	}

}
