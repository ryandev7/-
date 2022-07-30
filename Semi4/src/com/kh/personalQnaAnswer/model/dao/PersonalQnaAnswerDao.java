package com.kh.personalQnaAnswer.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;

import com.kh.personalQna.model.dao.PersonalQnaDao;
import com.kh.personalQna.model.vo.PersonalQna;
import com.kh.personalQnaAnswer.model.vo.PersonalQnaAnswer;

public class PersonalQnaAnswerDao {

	private Properties prop = new Properties();

	public PersonalQnaAnswerDao() {
		String fileName = PersonalQnaDao.class.getResource("/sql/personalQnaAnswer/personalQnaAnswer-mapper.xml")
				.getPath();

		try {
			prop.loadFromXML(new FileInputStream(fileName));

		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertPqnaAs(Connection conn, PersonalQnaAnswer p) {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertPqnaAs");

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, p.getPqnaNo());
			pstmt.setString(2, p.getPqnaContent());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public PersonalQnaAnswer selectPqnaAs(Connection conn, int pqNo) {

		PersonalQnaAnswer p = null;

		PreparedStatement pstmt = null;

		ResultSet rset = null;

		String sql = prop.getProperty("selectPqnaAs");

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pqNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				p = new PersonalQnaAnswer(rset.getInt("PQNA_NO"),
										  rset.getString("PQNA_WRITER"),
										  rset.getString("PQNA_CONTENT"),
										  rset.getDate("CREATE_DATE"),
										  rset.getDate("MODIFY_DATE"),
										  rset.getString("STATUS"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);

		}

		return p;
	}

	public int updatePqnaAs(Connection conn, PersonalQnaAnswer p) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePqnaAs");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, p.getPqnaContent());
			pstmt.setInt(2, p.getPqnaNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deletePqnaAs(Connection conn, int pqno) {
		
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deletePqna");

		try {
			pstmt = conn.prepareStatement(sql);
			// UPDATE PQNA SET STATUS = 'N' WHERE PQNA_NO = ?

			pstmt.setInt(1, pqno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	public ArrayList<PersonalQnaAnswer> selectPqnaAsList(Connection conn) {
		
		// select문 ResultSet 여러행 -> ArrayList
		
		ArrayList<PersonalQnaAnswer> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPqnaAsList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PersonalQnaAnswer pa = new PersonalQnaAnswer(rset.getInt("PQNA_NO"),
															rset.getString("PQNA_WRITER"),
															rset.getString("PQNA_CONTENT"),
															rset.getDate("CREATE_DATE"),
															rset.getDate("MODIFY_DATE"),
															rset.getString("STATUS"));
				list.add(pa);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

}
