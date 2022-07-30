package com.kh.recipan.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.Category;
import com.kh.common.model.vo.Comment;
import com.kh.common.model.vo.PageInfo;
import com.kh.common.model.vo.RecipanCookStep;
import com.kh.common.model.vo.RecipanProSau;
import com.kh.product.model.vo.Product;
import com.kh.recipan.model.vo.Recipan;


public class RecipanDao {
	private Properties prop = new Properties();
	
	public RecipanDao() {
		String fileName = RecipanDao.class.getResource("/sql/recipan/recipan-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Category> selectProCategoryList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProCategoryList");
		ArrayList<Category> list = new ArrayList<Category>(); 
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Category(
						rset.getInt("PRO_CAT_NO"),
						rset.getString("CATE_VALUE")));				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);			
		}
		return list;
	}

	public ArrayList<Category> selectKindCategoryList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectKindCategoryList");
		ArrayList<Category> list = new ArrayList<Category>(); 
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Category(
						rset.getInt("KIND_CAT_NO"),
						rset.getString("CATE_VALUE")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);			
		}
		return list;
	}

	public ArrayList<Category> selectNatCategoryList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectNatCategoryList");
		ArrayList<Category> list = new ArrayList<Category>(); 
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Category(
						rset.getInt("NAT_CAT_NO"),
						rset.getString("NAT_VALUE")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);			
		}
		return list;
	}

	public int insertRecipan(Connection conn, Recipan r) {
		PreparedStatement pstmt = null;		
		int result = 0;
		String sql = prop.getProperty("insertRecipan");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getPeTitle());
			pstmt.setString(2, r.getPeIntroduce());
			pstmt.setString(3,r.getPeWriter());
			pstmt.setString(4, r.getPeFoodAmount());
			pstmt.setString(5, r.getPeCookTime());
			pstmt.setString(6, r.getPeCookLevel());
			pstmt.setInt(7, r.getNatCatNo());
			pstmt.setInt(8, r.getKindCatNo());
			pstmt.setInt(9, r.getProCatNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {		
			close(pstmt);
		}
		
		return result;
	}

	public int insertRecipanProduct(Connection conn, RecipanProSau product) {
		PreparedStatement pstmt = null;
		int result = 1;
		String sql = prop.getProperty("insertRecipanProduct");
		if(product.getName().length == 0) result = 0; 
//				값 없을때는 0으로 함수 종료
		try {
			
			
			for(int i = 0; i < product.getName().length; i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, product.getName()[i]);
				pstmt.setString(2, product.getAmount()[i]);
				result *= pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertRecipanSauce(Connection conn, RecipanProSau sauce) {
		PreparedStatement pstmt =null;
		int result = 1;
		String sql = prop.getProperty("insertRecipanSauce");
		if(sauce.getName().length == 0) result = 0; 
//		값 없을때는 0으로 함수 종료
		try {
			for(int i = 0; i < sauce.getName().length; i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sauce.getName()[i]);
				pstmt.setString(2, sauce.getAmount()[i]);
				result *= pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertRecipanCookStep(Connection conn, RecipanCookStep rcs) {
		PreparedStatement pstmt = null;
		int result = 1;
		String sql = prop.getProperty("insertRecipanCookStep");
		if(rcs.getStepContent().length == 0) result = 0;
		try {
			for(int i = 0; i < rcs.getStepContent().length; i++) {
				pstmt = conn.prepareStatement(sql);			
				pstmt.setInt(1, (i+1)); //요리순서				
				pstmt.setString(2, rcs.getStepContent()[i]); 
				result *= pstmt.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertRecipanAttachment(Connection conn, ArrayList<Attachment> list) {
		PreparedStatement pstmt = null;
		int result = 1;
		String sql = prop.getProperty("insertRecipanAttachment");
		if(list.isEmpty())return 0;
		
		try {
			for(Attachment at : list) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());
				pstmt.setInt(5, at.getStepNo());
				result *= pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int selectListCount(Connection conn, int natNo, int kindNo, int proNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				if(proNo > 0 && proNo != rset.getInt("PRO_CAT_NO")) {
					continue;
				}
				if(kindNo > 0 && kindNo != rset.getInt("KIND_CAT_NO")) {
					continue;
				}
				if(natNo > 0 && natNo != rset.getInt("NAT_CAT_NO")) {
					continue;
				}
				result++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Recipan> selectList(Connection conn ,PageInfo pi, int natNo, int kindNo, int proNo, String orderBy) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String pro ="";
		String kind ="";
		String nat ="";
		
		if(proNo > 0 ) {
			pro = " AND PRO_CAT_NO = " + proNo + " ";
		}
		if(kindNo > 0 ) {
			kind = " AND KIND_CAT_NO = " + kindNo + " ";
		}
		if(natNo > 0 ) {
			nat = " AND NAT_CAT_NO = " + natNo + " ";
		}
		
		String sql = 
				"SELECT *" +
				" FROM(SELECT ROWNUM RNUM, A.*" +
				" FROM (SELECT PE_NO, PE_TITLE, PE_INTRODUCE, NICKNAME, FILE_PATH||CHANGE_NAME TITLEIMG, PRO_CAT_NO, KIND_CAT_NO, NAT_CAT_NO, PE_WRITER, PE_LIKECOUNT" +
							  " FROM RECIPE" +
							  " JOIN ATTACHMENT USING (PE_NO)"+
							  " JOIN MEMBER ON (MEMBER.USER_ID = PE_WRITER)"+
							  " WHERE BOARD_TYPE = 2"+
							  " AND PE_STATUS = 'Y'"+
							  pro + kind + nat +
							  " AND FILE_LEVEL = 1 ORDER BY "+ orderBy + ") A)"+
					" WHERE RNUM BETWEEN ? AND ?"+
					" ORDER BY RNUM ASC";
		System.out.println(sql);
		ArrayList<Recipan> recipan = new ArrayList<Recipan>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit()-1;		
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
//				if(proNo > 0 && proNo != rset.getInt("PRO_CAT_NO")) {
//					continue;
//				}
//				if(kindNo > 0 && kindNo != rset.getInt("KIND_CAT_NO")) {
//					continue;
//				}
//				if(natNo > 0 && natNo != rset.getInt("NAT_CAT_NO")) {
//					continue;
//				}
				
				recipan.add(new Recipan(rset.getInt("PE_NO"),
									  rset.getString("PE_TITLE"),
									  rset.getString("PE_INTRODUCE"),
									  rset.getString("TITLEIMG"),
									  rset.getString("NICKNAME"),
									  rset.getString("PE_WRITER"),
									  rset.getInt("PE_LIKECOUNT")
									  ));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);			
		}
		return recipan;
	}

	public int likeRecipan(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("likeRecipan");
		int result = 0; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int likeCancelRecipan(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("likeCancelRecipan");
		int result = 0; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public boolean hasLikeRecipan(Connection conn, int peNo, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean hasLikeRecipan = false;
		String sql = prop.getProperty("hasLikeRecipan");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			pstmt.setString(2,userId);
			rset = pstmt.executeQuery();
			if(rset.next()) hasLikeRecipan = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);			
		}
		
		return hasLikeRecipan;
	}

	public int likeRecipanMember(Connection conn, int peNo, String userId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("likeRecipanMember");
		int result = 0; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, peNo);			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int likeUpdateRecipanMember(Connection conn, int peNo, String userId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("likeUpdateRecipanMember");
		int result = 0; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, peNo);			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int likeCancelRecipanMember(Connection conn, int peNo, String userId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("likeCancelRecipanMember");
		int result = 0; 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, peNo);			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Recipan selectRecipan(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Recipan recipan = new Recipan();
		String sql = prop.getProperty("selectRecipan");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recipan.setPeNo(peNo);
				recipan.setPeTitle(rset.getString("PE_TITLE"));		
				recipan.setPeIntroduce(rset.getString("PE_INTRODUCE"));
				recipan.setPeWriter(rset.getString("PE_WRITER"));
				recipan.setPeFoodAmount(rset.getString("PE_FOOD_AMOUNT"));
				recipan.setPeCookTime(rset.getString("PE_COOK_TIME"));
				recipan.setPeCookLevel(rset.getString("PE_COOK_LEVEL"));
				recipan.setPeLikeCount(rset.getInt("PE_LIKECOUNT"));			
				recipan.setTitleImg(rset.getString("TITLEIMG"));
				recipan.setNatCatNo(rset.getInt("NAT_CAT_NO"));
				recipan.setKindCatNo(rset.getInt("KIND_CAT_NO"));
				recipan.setProCatNo(rset.getInt("PRO_CAT_NO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return recipan;
	}

	public ArrayList<RecipanProSau> selectRecipanPro(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectRecipanPro");
		ArrayList<RecipanProSau> pro = new ArrayList<RecipanProSau>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				pro.add(new RecipanProSau(
						rset.getString("PRO_NAME"),
						rset.getString("PRO_AMOUNT")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return pro;
	}

	public ArrayList<RecipanProSau> selectRecipanSau(Connection conn, int peNo) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectRecipanSau");
		ArrayList<RecipanProSau> sau = new ArrayList<RecipanProSau>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				sau.add(new RecipanProSau(
						rset.getString("SAUCE_NAME"),
						rset.getString("SAUCE_AMOUNT")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return sau;
	}

	public ArrayList<RecipanCookStep> selectRecipanCookStep(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRecipanCookStep");
		
		ArrayList<RecipanCookStep> cookStep = new ArrayList<RecipanCookStep>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			
			rset = pstmt.executeQuery();
			
			
			
			while(rset.next()) {
				cookStep.add(new RecipanCookStep(rset.getInt("STEP_COOKNO"), rset.getString("STEP_CONTENT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return cookStep;
	}

	public void selectRecipanCookStepImg(Connection conn, int peNo, ArrayList<RecipanCookStep> cookStep) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int index = 0;
		String sql = prop.getProperty("selectRecipanCookStepImg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				
				cookStep.get(index).setStepImg(rset.getString("STEPIMG"));
				index++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
	}

	public ArrayList<Comment> selectComment(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectComment");
		ArrayList<Comment> list = new ArrayList<Comment>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Comment(
						rset.getString("COMMENT_CONTENT"),
						rset.getString("COMMENT_WRITER"),
						rset.getDate("CREATE_DATE")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertComment(Connection conn, int peNo, String content, String writer) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertComment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, writer);

			pstmt.setInt(3, peNo);
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Recipan> myRecipeSelect(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Recipan> list = new ArrayList<Recipan>();
		String sql = prop.getProperty("myRecipeSelect");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Recipan(
							rset.getInt("PE_NO"),
							rset.getString("PE_TITLE"),
							rset.getString("PE_INTRODUCE"),
							rset.getString("TITLEIMG")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public String selectOriginImg(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOriginImg");
		String origin = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				origin = rset.getString("CHANGE_NAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return origin;
	}
	public int selectOriginImgFileNo(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOriginImgFileNo");
		int fileNo = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				fileNo = rset.getInt("FILE_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return fileNo;
	}
	public ArrayList<String> selectCookStepImg(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCookStepImg");
		ArrayList<String> originFileArr = new ArrayList<String>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				originFileArr.add(rset.getString("CHANGE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return originFileArr;
	}
	public ArrayList<Integer> selectCookStepImgFileNo(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCookStepImgFileNo");
		ArrayList<Integer> originFileNoArr = new ArrayList<Integer>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				originFileNoArr.add(rset.getInt("FILE_NO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return originFileNoArr;
	}
	public int deleteRecipanPro(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteRecipanPro");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteRecipanSau(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteRecipanSau");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteRecipanCookStep(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteRecipanCookStep");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteRecipanAttachment(Connection conn, int fileNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteRecipanAttachment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fileNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateRecipan(Connection conn, Recipan r) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateRecipan");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getPeTitle());
			pstmt.setString(2, r.getPeIntroduce());
			
			pstmt.setString(3, r.getPeFoodAmount());
			pstmt.setString(4, r.getPeCookTime());
			pstmt.setString(5, r.getPeCookLevel());
			pstmt.setInt(6, r.getNatCatNo());
			pstmt.setInt(7, r.getKindCatNo());
			pstmt.setInt(8, r.getProCatNo());
			
			pstmt.setInt(9, r.getPeNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;		
	}

	
	
	public int insertRecipanProduct(Connection conn, int peNo, RecipanProSau product) {
		PreparedStatement pstmt = null;
		int result = 1;
		String sql = prop.getProperty("updateRecipanProduct");
		if(product.getName().length == 0) result = 0; 
//				값 없을때는 0으로 함수 종료
		try {
			
			
			for(int i = 0; i < product.getName().length; i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, peNo);
				pstmt.setString(2, product.getName()[i]);
				pstmt.setString(3, product.getAmount()[i]);
				result *= pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertRecipanSauce(Connection conn, int peNo, RecipanProSau sauce) {
		PreparedStatement pstmt =null;
		int result = 1;
		String sql = prop.getProperty("updateRecipanSauce");
		if(sauce.getName().length == 0) result = 0; 
//		값 없을때는 0으로 함수 종료
		try {
			for(int i = 0; i < sauce.getName().length; i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, peNo);
				pstmt.setString(2, sauce.getName()[i]);
				pstmt.setString(3, sauce.getAmount()[i]);
				result *= pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertRecipanCookStep(Connection conn, int peNo, RecipanCookStep rcs) {
		PreparedStatement pstmt = null;
		int result = 1;
		String sql = prop.getProperty("updateRecipanCookStep");
		if(rcs.getStepContent().length == 0) result = 0;
		try {
			for(int i = 0; i < rcs.getStepContent().length; i++) {
				pstmt = conn.prepareStatement(sql);			
				pstmt.setInt(1, peNo);
				pstmt.setInt(2, (i+1)); //요리순서				
				pstmt.setString(3, rcs.getStepContent()[i]); 
				result *= pstmt.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int insertRecipanAttachment(Connection conn, int peNo, Attachment at) {
		PreparedStatement pstmt = null;
		int result = 1;
		String sql = prop.getProperty("updateFormInsertRecipanAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileLevel());
			pstmt.setInt(5, peNo);
			pstmt.setInt(6, at.getStepNo());
			result *= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int updateAttachment(Connection conn, int ThumnnailOriginFileNo, Attachment at) {
		PreparedStatement pstmt = null;
		int result = 1;
		String sql = prop.getProperty("updateAttachment");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, ThumnnailOriginFileNo);
			result *= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int deleteRecipan(Connection conn, int peNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteRecipan");
				
		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, peNo);
			
			result = pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;	
	}

//	public int deleteRecipanAttachmentThumbnail(Connection conn, int peNo) {
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("deleteRecipanAttachment");
//		int result = 0;
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, peNo);			
//			result = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}
//		return result;
//	}

	public void selectLike(Connection conn, ArrayList<Recipan> recipan, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLike");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				for(Recipan r : recipan) {
					if(rset.getInt("PE_NO") == r.getPeNo()) {
						r.setLikeStatus(rset.getString("LIKE_STATUS"));
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}		
	}

	public ArrayList<Product> selectRecipanWithProduct(Connection conn, ArrayList<RecipanProSau> product,
			ArrayList<RecipanProSau> sauce) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT PR_NO, PR_NAME, FILE_PATH||CHANGE_NAME TITLEIMG "
				+ "FROM PRODUCT JOIN ATTACHMENT A ON(PR_NO = A.PRODUCT_NO) "
				+ "WHERE (A.STATUS = 'Y' AND A.FILE_LEVEL = 1) ";
		ArrayList<RecipanProSau> productSauceList = new ArrayList<RecipanProSau>();
		ArrayList<Product> withPr = new ArrayList<Product>();

		productSauceList.addAll(product);
		productSauceList.addAll(sauce);
		
		if(!productSauceList.isEmpty()) {
			sql += "AND (";
		}		
		for(int i = 0; i < productSauceList.size(); i++) {
			sql += "(PR_NAME LIKE '%'||'"+ productSauceList.get(i).getOutputName() + "'||'%') ";
			if(i+1 != productSauceList.size()) {
				sql += "OR ";
			}
		}
		if(!productSauceList.isEmpty()) {
			sql += ")";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				withPr.add(new Product(
						rset.getInt("PR_NO"),
						rset.getString("PR_NAME"),
						rset.getString("TITLEIMG")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
							
		return withPr;
	}


}
