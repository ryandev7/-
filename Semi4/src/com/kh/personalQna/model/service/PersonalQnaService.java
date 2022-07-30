package com.kh.personalQna.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.personalQna.model.dao.PersonalQnaDao;
import com.kh.personalQna.model.vo.PersonalQna;

public class PersonalQnaService {

	public int insertPqna(PersonalQna p) {

		Connection conn = getConnection();

		int result = new PersonalQnaDao().insertPqna(conn, p);

		if (result > 0) { // 성공
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;

	}
	
	public ArrayList<PersonalQna> selectPqnaList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<PersonalQna> list = new PersonalQnaDao().selectPqnaList(conn, pi);
		
		close(conn);
		
		return list;
		
	}
	
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new PersonalQnaDao().selectListCount(conn);
		// SELECT문의 결과는 ResultSet
		// 상식적으로 생각해보면 게시글의 총 갯수는 정수형
		
		close(conn);
		
		return listCount;
		
	}
	public int selectUserListCount(String userId) {
		
		Connection conn = getConnection();
		
		int listCount = new PersonalQnaDao().selectUserListCount(conn, userId);
		// SELECT문의 결과는 ResultSet
		// 상식적으로 생각해보면 게시글의 총 갯수는 정수형
		
		close(conn);
		
		return listCount;
		
	}

	
	public ArrayList<PersonalQna> selectUserPqnaList(String userId, PageInfo pi){
		
		Connection conn = getConnection();

		ArrayList<PersonalQna> list = new PersonalQnaDao().selectUserPqnaList(conn, userId, pi);

		close(conn);

		return list;
	}
	
	public PersonalQna selectPqna(int pqNo) {
		
		Connection conn = getConnection();
		
		PersonalQna p = new PersonalQnaDao().selectPqna(conn, pqNo);
		
		close(conn);
		
		return p;
	}

	public int updatePqna(PersonalQna p) {
		Connection conn = getConnection();

		int result = new PersonalQnaDao().updatePqna(conn, p);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}
	
	public int updateAsStatus(int pqno) {
		Connection conn = getConnection();

		int result = new PersonalQnaDao().updateAsStatus(conn, pqno);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}
	
	public int deletePqna(int pqNo) {
		
		Connection conn = getConnection();

		int result = new PersonalQnaDao().deletePqna(conn, pqNo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}
	
}
