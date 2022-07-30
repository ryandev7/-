package com.kh.notice.model.dao;

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
import com.kh.notice.model.vo.Notice;

public class NoticeDao {

	private Properties prop = new Properties();

	public NoticeDao() {

		String fileName = NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath();

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

	public int insertNotice(Connection conn, Notice n) {

		// INSERT문 => 처리된 행의 갯수
		// 필요한 변수
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertNotice");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getNoticeWriter());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Notice> selectNoticeList(Connection conn, PageInfo pi) {
		// SELECT문 => ResultSet => 여러 행 => ArrayList<Notice>

		// 필요한 변수들
		ArrayList<Notice> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectNoticeList");

		try {
			pstmt = conn.prepareStatement(sql);
			// TOP-N분석 활용 : 인라인 뷰 활용
			// 1) ORDER BY 순서가 가장 마지막인데 맨 처음에 실행이 되어야함
			// 일단 정렬해주는 SELECT문을 만듬 => 서브쿼리
			// 2) 서브쿼리를 FROM 절에 넣음 + ROWNUM

			// 구멍 메꾸기
			/*
			 * boardLimit이 10이라는 가정하에 currentPage = 1 => 시작값 1, 끝값 10 currentPage = 2 => 시작값
			 * 11, 끝값 20 currentPage = 3 => 시작값 21, 끝값 30 ....
			 * 
			 * 시작값 = (currentPage - 1) * boardLimit + 1 끝값 = 시작값 + boardLimit -1
			 */

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {

				Notice n = new Notice(rset.getInt("NOTICE_NO"), rset.getString("NOTICE_TITLE"),
						rset.getString("NOTICE_CONTENT"), rset.getInt("NOTICE_COUNT"),  
						rset.getDate("CREATE_DATE"),rset.getDate("MODIFY_DATE"), rset.getString("STATUS"),rset.getString("NOTICE_WRITER"));
				list.add(n);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
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

	public int updateNotice(Connection conn, Notice n) {
		// UPDATE문 => 처리행의 갯수
		// 변수
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteNotice(Connection conn, int noticeNo) {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteNotice");

		try {
			pstmt = conn.prepareStatement(sql);
			// UPDATE NOTICE SET STATUS = 'N' WHERE NOTICE_NO = ?

			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Notice selectNotice(Connection conn, int noticeNo) {
		// SELECT => ResultSet => PK제약조건에 의해 한 행이 뽑힘 => Notice
		// 변수

		Notice n = null;

		PreparedStatement pstmt = null;

		ResultSet rset = null;

		String sql = prop.getProperty("selectNotice");

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, noticeNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				n = new Notice(rset.getInt("NOTICE_NO"), rset.getString("NOTICE_TITLE"),
						rset.getString("NOTICE_CONTENT"), rset.getString("NOTICE_WRITER"), rset.getDate("CREATE_DATE"));

				// NOTICE_NO은 화면에 안보이는데 왜 갖고오냐?
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return n;
	}

}
