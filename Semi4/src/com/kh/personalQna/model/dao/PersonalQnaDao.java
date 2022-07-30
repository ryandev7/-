package com.kh.personalQna.model.dao;

import static com.kh.common.JDBCTemplate.close;

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

import com.kh.common.model.vo.PageInfo;
import com.kh.personalQna.model.vo.PersonalQna;
import com.kh.personalQnaAnswer.model.vo.PersonalQnaAnswer;

public class PersonalQnaDao {

	private Properties prop = new Properties();
	
	public PersonalQnaDao() {
		
		String fileName = PersonalQnaDao.class.getResource("/sql/personalQna/personalQna-mapper.xml").getPath();

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
	
	public int insertPqna(Connection conn, PersonalQna p) {

		// INSERT문 => 처리된 행의 갯수
		// 필요한 변수
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertPqna");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, p.getPqnaTitle());
			pstmt.setString(2, p.getPqnaContent());
			pstmt.setString(3, p.getPqnaWriter());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	public int selectListCount(Connection conn) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt("COUNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<PersonalQna> selectPqnaList(Connection conn, PageInfo pi){
		
		ArrayList<PersonalQna> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPqnaList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				PersonalQna p = new PersonalQna(
							rset.getInt("PQNA_NO"),
							rset.getString("PQNA_TITLE"),
							rset.getString("PQNA_CONTENT"),
							rset.getString("STATUS"),
							rset.getDate("CREATE_DATE"),
							rset.getDate("MODIFY_DATE"),
							rset.getString("PQNA_ANSWER_ST"),
							rset.getString("PQNA_WRITER"));
				
						list.add(p);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int updatePqna(Connection conn, PersonalQna p) {
		// UPDATE문 => 처리행의 갯수
		// 변수
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePqna");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, p.getPqnaTitle());
			pstmt.setString(2, p.getPqnaContent());
			pstmt.setInt(3, p.getPqnaNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public int updateAsStatus(Connection conn, int pqno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAsStatus");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pqno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public PersonalQna selectPqna(Connection conn, int pqNo) {
		PersonalQna p = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPqna");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pqNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new PersonalQna(rset.getInt("PQNA_NO"),
									rset.getString("PQNA_TITLE"),
									rset.getString("PQNA_CONTENT"),
									rset.getDate("CREATE_DATE"),
									rset.getString("PQNA_ANSWER_ST"),
									rset.getString("PQNA_WRITER"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			
		}
		
		return p; 
	}
	
	public int deletePqna(Connection conn, int pqno) {
		
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

	

	public ArrayList<PersonalQna> selectUserPqnaList(Connection conn, String userId, PageInfo pi) {
		ArrayList<PersonalQna> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectUserPqnaList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
		
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				PersonalQna p = new PersonalQna(
							rset.getInt("PQNA_NO"),
							rset.getString("PQNA_TITLE"),
							rset.getString("PQNA_CONTENT"),
							rset.getString("STATUS"),
							rset.getDate("CREATE_DATE"),
							rset.getDate("MODIFY_DATE"),
							rset.getString("PQNA_ANSWER_ST"),
							rset.getString("PQNA_WRITER"));
				
						list.add(p);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectUserListCount(Connection conn, String userId) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectUserListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt("COUNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	
	
}
