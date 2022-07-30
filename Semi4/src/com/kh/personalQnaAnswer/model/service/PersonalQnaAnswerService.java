package com.kh.personalQnaAnswer.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.personalQna.model.dao.PersonalQnaDao;
import com.kh.personalQnaAnswer.model.dao.PersonalQnaAnswerDao;
import com.kh.personalQnaAnswer.model.vo.PersonalQnaAnswer;

public class PersonalQnaAnswerService {

	public int insertPqnaAs(PersonalQnaAnswer p) {

		Connection conn = getConnection();

		int result = new PersonalQnaAnswerDao().insertPqnaAs(conn, p);

		if (result > 0) { // 성공
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;

	}
	public ArrayList<PersonalQnaAnswer> selectPqnaAsList(){
		
		Connection conn = getConnection();
		
		ArrayList<PersonalQnaAnswer> list = new PersonalQnaAnswerDao().selectPqnaAsList(conn);
		
		close(conn);
		
		return list;
	}
	
	public PersonalQnaAnswer selectPqnaAs(int pqNo) {

		Connection conn = getConnection();

		PersonalQnaAnswer p = new PersonalQnaAnswerDao().selectPqnaAs(conn, pqNo);

		close(conn);

		return p;
	}

	public int updatePqnaAs(PersonalQnaAnswer p) {
		Connection conn = getConnection();

		int result = new PersonalQnaAnswerDao().updatePqnaAs(conn, p);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public int deletePqnaAs(int pqNo) {

		Connection conn = getConnection();

		int result = new PersonalQnaAnswerDao().deletePqnaAs(conn, pqNo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}
}
