package com.kh.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Category;
import com.kh.common.model.vo.PageInfo;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import com.kh.product.model.vo.QNA;
import com.kh.product.model.vo.Review;
import com.kh.recipan.model.vo.Recipan;

public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		JDBCTemplate.close(conn);
		
		return m;
	}
	
	public int insertMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		if(result > 0) { // 성공했다면
			JDBCTemplate.commit(conn);
		} else { // 실패했다면
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public Member updateMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result > 0) { // 성공
			
			JDBCTemplate.commit(conn);
			updateMem = new MemberDao().selectMember(conn,m.getUserId());
			
		} else { //실패
			
			JDBCTemplate.rollback(conn);
			
		}
		JDBCTemplate.close(conn);
		
		return updateMem;
	}
	
	public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
		
		Member updateMem = null;
		
		if(result > 0) { 
			JDBCTemplate.commit(conn);
			updateMem = new MemberDao().selectMember(conn, userId);
		} else { 
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return updateMem;
	}
	
	public int deleteMember(String userId, String userPwd) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	public ArrayList<Category> selectCategoryList() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Category> list = new MemberDao().selectCategoryList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public Member selectMember(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().selectMember(conn, userId);
		
		JDBCTemplate.close(conn);
		
		return m;
	}
	
	public int idCheck(String checkId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new MemberDao().idCheck(conn, checkId);
		
		JDBCTemplate.close(conn);
		
		return count;
	}

	public int nickNameCheck(String checkNickName) {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new MemberDao().nickNameCheck(conn, checkNickName);
		
		JDBCTemplate.close(conn);
		
		return count;
	}

	public int emailCheck(String checkEmail) {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new MemberDao().emailCheck(conn, checkEmail);
		
		JDBCTemplate.close(conn);
		
		return count;
	}

	public int selectReviewCount(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new MemberDao().selectReviewCount(conn,userId);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}

	public ArrayList<Review> selectReview(String userId, PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Review> list = new MemberDao().selectReview(conn, userId, pi);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	
	public int selectMyProductQnaCount(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new MemberDao().selectMyProductQnaCount(conn, userId);
		
		JDBCTemplate.close(conn);
		
		System.out.println(listCount);
		
		return listCount;
	}
	
	public ArrayList<QNA> selectMyProductQna(PageInfo pi, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<QNA> list = new MemberDao().selectMyProductQna(conn, pi, userId);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public int selectRecipanCount(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new MemberDao().selectMyLikeRecipanCount(conn,userId);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}

	public ArrayList<Recipan> selectRecipan(String userId, PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Recipan> list = new MemberDao().selectMyLikeRecipan(conn, userId, pi);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public int selectMemberListCount() {
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new MemberDao().selectMemberListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}

	public ArrayList<Member> selectMemberList(PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> list = new MemberDao().selectMemberList(conn , pi);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public Member id_search(String userName, String phone) {
		Connection conn = JDBCTemplate.getConnection();

		Member m = new MemberDao().id_search(conn , userName, phone);
		
		JDBCTemplate.close(conn);
		
		return m;
		
	}

	public Member pwd_search(String userId, String userName, String phone) {
		Connection conn = JDBCTemplate.getConnection();

		Member m = new MemberDao().pwd_search(conn , userId, userName, phone);
		
		JDBCTemplate.close(conn);
		
		return m;
	}

	

}
