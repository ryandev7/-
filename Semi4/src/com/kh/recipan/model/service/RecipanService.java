package com.kh.recipan.model.service;


import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.Category;
import com.kh.common.model.vo.Comment;
import com.kh.common.model.vo.PageInfo;
import com.kh.common.model.vo.RecipanCookStep;
import com.kh.common.model.vo.RecipanProSau;
import com.kh.product.model.vo.Product;
import com.kh.recipan.model.dao.RecipanDao;
import com.kh.recipan.model.vo.Recipan;
public class RecipanService {

	public ArrayList<ArrayList<Category>> selectCategoryList() {
		Connection conn = getConnection();
		ArrayList<Category>proList = new RecipanDao().selectProCategoryList(conn);
		ArrayList<Category>kindList = new RecipanDao().selectKindCategoryList(conn);
		ArrayList<Category>natList = new RecipanDao().selectNatCategoryList(conn);
		
		ArrayList<ArrayList<Category>> list = new ArrayList<ArrayList<Category>>();
		
		list.add(proList);
		list.add(kindList);
		list.add(natList);
		
		close(conn);
		
		return list;
	}

	public int insertRecipan(Recipan r, RecipanProSau product, RecipanProSau sauce, RecipanCookStep rcs,
			ArrayList<Attachment> list) {
		Connection conn = getConnection();
		RecipanDao rd = new RecipanDao();
		int recipanResult = rd.insertRecipan(conn, r);
		recipanResult *= rd.insertRecipanProduct(conn, product);
		recipanResult *= rd.insertRecipanSauce(conn, sauce);
		recipanResult *= rd.insertRecipanCookStep(conn, rcs);
		recipanResult *= rd.insertRecipanAttachment(conn, list);
		
		
		if(recipanResult > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return recipanResult;
	}

	public int selectListCount(int natNo, int kindNo, int proNo) {		
		Connection conn = getConnection();
		int count = new RecipanDao().selectListCount(conn, natNo, kindNo, proNo);
		close(conn);
		return count;
	}

	public ArrayList<Recipan> selectList(PageInfo pi, int natNo, int kindNo, int proNo, String orderBy, String userId) {
		Connection conn = getConnection();
		
		ArrayList<Recipan> recipan = new RecipanDao().selectList(conn, pi, natNo, kindNo, proNo, orderBy);
		
		if(userId != null) new RecipanDao().selectLike(conn, recipan, userId);
		
		
		close(conn);
		return recipan;
	}

	public int likeRecipan(int peNo, String userId) {
		Connection conn = getConnection();

		int result = new RecipanDao().likeRecipan(conn, peNo); // Recipan Count ++ 
		
		boolean isLikeRecipan = new RecipanDao().hasLikeRecipan(conn, peNo, userId);
//		LikeTable에 해당 값이 없을 경우 정보를 추가한다.
		if(!isLikeRecipan) { 
			result *= new RecipanDao().likeRecipanMember(conn,peNo,userId);// Like Table 추가
		}else { // 해당 값이 있을경우 
			result *= new RecipanDao().likeUpdateRecipanMember(conn, peNo, userId); //Like Table Update 
		}
		
		if(result > 0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int likeCancelRecipan(int peNo, String userId) {
		Connection conn = getConnection();
		int result = new RecipanDao().likeCancelRecipan(conn, peNo); // Recipan Count --
		
		
		result *= new RecipanDao().likeCancelRecipanMember(conn,peNo,userId); //LikeTable Update 
		if(result > 0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public Recipan selectReciapn(int peNo) {
		Connection conn = getConnection();
		Recipan recipan = new RecipanDao().selectRecipan(conn, peNo);
		
		close(conn);
		return recipan;
	}

	public ArrayList<RecipanProSau> selectRecipanPro(int peNo) {
		Connection conn = getConnection();
		ArrayList<RecipanProSau> pro = new RecipanDao().selectRecipanPro(conn, peNo);
		close(conn);
		return pro;
	}

	public ArrayList<RecipanProSau> selectRecipanSau(int peNo) {
		Connection conn = getConnection();
		ArrayList<RecipanProSau> sau = new RecipanDao().selectRecipanSau(conn, peNo);
		close(conn);
		return sau;
	}

	public ArrayList<RecipanCookStep> selectRecipanCookStep(int peNo) {
		Connection conn = getConnection();
		ArrayList<RecipanCookStep> cookStep = new RecipanDao().selectRecipanCookStep(conn, peNo);
		new RecipanDao().selectRecipanCookStepImg(conn, peNo, cookStep);
		close(conn);
		
		return cookStep;
	}

	public ArrayList<Comment> selectComment(int peNo) {
		Connection conn = getConnection();
		ArrayList<Comment> list = new RecipanDao().selectComment(conn, peNo);
		close(conn);
		return list;
	}

	public int insertComment(int peNo, String content, String writer) {
		Connection conn = getConnection();
		int result = new RecipanDao().insertComment(conn, peNo, content, writer);
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<Recipan> myRecipeSelect(String userId) {
		Connection conn = getConnection();
		ArrayList<Recipan>list = new RecipanDao().myRecipeSelect(conn, userId);
		close(conn);
		return list;
	}
	public String selectOriginImg(int peNo) {
		Connection conn = getConnection();
		String originFileArr = new RecipanDao().selectOriginImg(conn,peNo);
		close(conn);
		return originFileArr;
	}
	public int selectOriginImgFileNo(int peNo) {
		Connection conn = getConnection();
		int fileNo = new RecipanDao().selectOriginImgFileNo(conn,peNo);
		close(conn);
		return fileNo;
	}
	
	public int deleteOriginRecipanAndUpdateRecipan(int peNo) {
		Connection conn = getConnection();
		RecipanDao da = new RecipanDao();
		int result = 1;
		result *= da.deleteRecipanPro(conn, peNo);
		result *= da.deleteRecipanSau(conn, peNo);
		result *= da.deleteRecipanCookStep(conn, peNo);
		
		
		
		return result;
	}

	public int updateRecipanTest(Recipan r, RecipanProSau product, RecipanProSau sauce, RecipanCookStep rcs,
			ArrayList<Attachment> list) {
		Connection conn = getConnection();
		RecipanDao rd = new RecipanDao();
		int recipanResult = rd.updateRecipan(conn, r);
		recipanResult *= rd.insertRecipanProduct(conn, r.getPeNo(),product);
		recipanResult *= rd.insertRecipanSauce(conn, r.getPeNo(), sauce);
		recipanResult *= rd.insertRecipanCookStep(conn,r.getPeNo(), rcs);
				
		
		rollback(conn);
		
		
		close(conn);
		
		return recipanResult;
	}
	public int updateRecipan(Recipan r, RecipanProSau product, RecipanProSau sauce, RecipanCookStep rcs,
			ArrayList<Attachment> list) {
		Connection conn = getConnection();
		RecipanDao rd = new RecipanDao();
		int recipanResult = rd.updateRecipan(conn, r);
		recipanResult *= rd.insertRecipanProduct(conn, r.getPeNo(),product);
		recipanResult *= rd.insertRecipanSauce(conn, r.getPeNo(), sauce);
		recipanResult *= rd.insertRecipanCookStep(conn,r.getPeNo(), rcs);
		
		
		
		if(recipanResult > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return recipanResult;
	}

	public int deleteRecipan(int peNo) {
		Connection conn = getConnection();
		
		int deleteResult = new RecipanDao().deleteRecipan(conn, peNo);
		
		if(deleteResult > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return deleteResult;
	}

	public int deleteOriginFile(int fileNo) {
		Connection conn = getConnection();
		int deleteResult = new RecipanDao().deleteRecipanAttachment(conn, fileNo);
		if(deleteResult > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return deleteResult;
	}

	public int updateAttachment(int ThumnnailOriginFileNo, Attachment at) {
		Connection conn = getConnection();
		int recipanResult = new RecipanDao().updateAttachment(conn,ThumnnailOriginFileNo,at);
		if(recipanResult > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return recipanResult;
	}
	
	
	public int insertAttachment(int peNo, Attachment at) {
		Connection conn = getConnection();
		int recipanResult = new RecipanDao().insertRecipanAttachment(conn,peNo,at);
		if(recipanResult > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return recipanResult;
	}

//	public int deleteThumbnailFile(int peNo) {
//		Connection conn = getConnection();
//		int deleteResult = new RecipanDao().deleteRecipanAttachmentThumbnail(conn, peNo);
//		if(deleteResult > 0) commit(conn);
//		else rollback(conn);
//		close(conn);
//		return deleteResult;
//	}

	public ArrayList<String> selectStepOriginImg(int peNo) {
		Connection conn = getConnection();
		ArrayList<String> originFileArr = new RecipanDao().selectCookStepImg(conn,peNo);
		close(conn);
		return originFileArr;
	}
	
	public ArrayList<Integer> selectCookStepImgFileNo(int peNo) {
		Connection conn = getConnection();
		ArrayList<Integer> originFileNoArr = new RecipanDao().selectCookStepImgFileNo(conn,peNo);
		close(conn);
		return originFileNoArr;
	}

	public ArrayList<Product> selectRecipanWithProduct(ArrayList<RecipanProSau> product,
			ArrayList<RecipanProSau> sauce) {
		Connection conn = getConnection();
		ArrayList<Product> withPr = new RecipanDao().selectRecipanWithProduct(conn, product, sauce);		
		close(conn);
		return withPr;
	}
	
}
