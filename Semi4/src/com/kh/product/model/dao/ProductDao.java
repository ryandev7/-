package com.kh.product.model.dao;

import static com.kh.common.JDBCTemplate.*;

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
import com.kh.common.model.vo.PageInfo;
import com.kh.common.model.vo.RecipanProSau;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.QNA;
import com.kh.product.model.vo.Review;

public class ProductDao {
	
	private Properties prop = new Properties();
	
	public ProductDao() {
		String fileName = ProductDao.class.getResource("/sql/product/product-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertReview(Connection conn, Review r) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getRvTitle());
			pstmt.setString(2, r.getRvContent());
			pstmt.setString(3, r.getRvWriter());
			pstmt.setInt(4, r.getRvPro());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReviewAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReviewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getProductNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Review selectReivew(Connection conn, int rvPro) {
		
		Review r = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReivew");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rvPro);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = new Review(rset.getInt("RV_NO"),
							   rset.getString("RV_TITLE"),
							   rset.getString("RV_CONTENT"),
							   rset.getDate("CREATE_DATE"),
							   rset.getString("RV_WRITER"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return r;
	}
	
	public ArrayList<Category> selectCategory(Connection conn) {
		
		// SELECT문 => ResultSet / 여러행
		// 변수 선언
		ArrayList<Category> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCategoryList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			// 여러행 => while문 이용해서 값 담기
			while(rset.next()) {
				
				list.add(new Category(rset.getInt("PRO_CAT_NO"),
									  rset.getString("CATE_VALUE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int insertProduct(Connection conn, Product p) {
		
		// INSERT문 => int / 처리된 행의 개수
		// 변수 선언
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getPrName());
			pstmt.setString(2, p.getPrIntroduce());
			pstmt.setInt(3, p.getProCatNo());
			pstmt.setString(4, p.getPrWeight());
			pstmt.setString(5, p.getPrOrigin());
			pstmt.setInt(6, p.getPrPrice());
			pstmt.setString(7, p.getPrContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAttachmentList(Connection conn, ArrayList<Attachment> list) {
		
		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachmentList");
		
		try {
			// 리스트의 요소 만큼 Attachment테이블에 행을 추가
			for(Attachment at : list) {
				// 반복문이 돌 때마다 미완성된 SQL문을 담은 pstmt 객체 생성
				pstmt = conn.prepareStatement(sql);
				
				// 완성형태로 만들기 => at객체로부터 뽑아오기
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());
				
				result *= pstmt.executeUpdate();
			}
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
			
			if(rset.next()) {
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

	public ArrayList<Product> selectList(Connection conn, PageInfo pi) {
		ArrayList<Product> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Product p = new Product();
				
				p.setPrName(rset.getString("PR_NAME"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				p.setPrNo(rset.getInt("PR_NO"));
				
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

	/**
	 * 수정함
	 */
	public Product selectProductList(Connection conn, int prNo) {
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
	
		String sql = prop.getProperty("selectProductList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, prNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				p = new Product(rset.getInt("PR_NO"),
								rset.getString("PR_NAME"),
						        rset.getString("PR_INTRODUCE"),
						        rset.getString("CATE_VALUE"),
						        rset.getString("PR_WEIGHT"),
						        rset.getString("PR_ORIGIN"),
						        rset.getInt("PR_PRICE"),
						        rset.getString("PR_CONTENT"),
						        rset.getInt("PRO_CAT_NO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return p;
	}

	public ArrayList<Attachment> selectAttachment(Connection conn, int prNo) {
		ArrayList<Attachment> atList = new ArrayList<Attachment>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, prNo);
			
			rset = pstmt.executeQuery();
			//rset =  2개의 정보 
			int index = 0;
			while(rset.next()) {
				atList.add(new Attachment());
				atList.get(index).setFileNo(rset.getInt("FILE_NO"));
				atList.get(index).setOriginName(rset.getString("ORIGIN_NAME"));
				atList.get(index).setChangeName(rset.getString("CHANGE_NAME"));
				atList.get(index).setFilePath(rset.getString("FILE_PATH"));
				index++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return atList;
	}

	public Product selectProduct(Connection conn, int prNo) {
		
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new Product(rset.getInt("PR_NO"),
								rset.getString("PR_NAME"),
								rset.getString("PR_INTRODUCE"),
								rset.getString("PR_WEIGHT"),
								rset.getInt("PR_PRICE"),
								rset.getString("PR_ORIGIN"),
								rset.getString("PR_CONTENT"),
								rset.getInt("PR_LIKECOUNT"),
								rset.getInt("PRO_CAT_NO")
								);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return p;
	}


	public Attachment selectContentAttachment(Connection conn, int prNo) {
		
		Attachment atContent = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectContentAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prNo);
			rset = pstmt.executeQuery();
			if(rset.next()){
				atContent = new Attachment();
				atContent.setFileNo(rset.getInt("FILE_NO"));
				atContent.setOriginName(rset.getString("ORIGIN_NAME"));
				atContent.setChangeName(rset.getString("CHANGE_NAME"));
				atContent.setFilePath(rset.getString("FILE_PATH"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return atContent;
	}

	public Attachment selectTitleAttachment(Connection conn, int prNo) {
		
		Attachment atTitle = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTitleAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prNo);
			rset = pstmt.executeQuery();
			if(rset.next()){
				atTitle = new Attachment();
				atTitle.setFileNo(rset.getInt("FILE_NO"));
				atTitle.setOriginName(rset.getString("ORIGIN_NAME"));
				atTitle.setChangeName(rset.getString("CHANGE_NAME"));
				atTitle.setFilePath(rset.getString("FILE_PATH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return atTitle;
	}

	public ArrayList<Review> selectReviewList(Connection conn, int prNo, PageInfo pi) {
		
		ArrayList<Review> list = new ArrayList<Review>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReviewList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, prNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Review(rset.getInt("RV_NO"),
									rset.getString("RV_TITLE"),
									rset.getString("RV_CONTENT"),
									rset.getDate("CREATE_DATE"),
									rset.getString("USER_NAME")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectReviewCount(Connection conn, int prNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String sql = prop.getProperty("selectReviewCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Product> selectVegetableList(Connection conn, PageInfo pi) {
		ArrayList<Product> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectVegetableList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Product p = new Product();
				
				p.setPrName(rset.getString("PR_NAME"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				p.setPrNo(rset.getInt("PR_NO"));
				
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
		
		public int selectVegetableListCount(Connection conn) {
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectVegetableListCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
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
		
		public int selectFruitListCount(Connection conn) {
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectFruitListCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
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


		public ArrayList<Product> selectFruitList(Connection conn, PageInfo pi) {
			ArrayList<Product> list = new ArrayList();
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectFruitList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
				int endRow = startRow + pi.getBoardLimit() - 1;
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					Product p = new Product();
					
					p.setPrName(rset.getString("PR_NAME"));
					p.setTitleImg(rset.getString("TITLEIMG"));
					p.setPrNo(rset.getInt("PR_NO"));
					
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
		
		public int selectSeaListCount(Connection conn) {
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectSeaListCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
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

		public ArrayList<Product> selectSeaList(Connection conn, PageInfo pi) {
			ArrayList<Product> list = new ArrayList();
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectSeaList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
				int endRow = startRow + pi.getBoardLimit() - 1;
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					Product p = new Product();
					
					p.setPrName(rset.getString("PR_NAME"));
					p.setTitleImg(rset.getString("TITLEIMG"));
					p.setPrNo(rset.getInt("PR_NO"));
					
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
		
		public int selectMeatListCount(Connection conn) {
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectMeatListCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
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
		
		public ArrayList<Product> selectMeatList(Connection conn, PageInfo pi) {
			ArrayList<Product> list = new ArrayList();
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectMeatList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
				int endRow = startRow + pi.getBoardLimit() - 1;
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					Product p = new Product();
					
					p.setPrName(rset.getString("PR_NAME"));
					p.setTitleImg(rset.getString("TITLEIMG"));
					p.setPrNo(rset.getInt("PR_NO"));
					
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
		
		public int selectEtcListCount(Connection conn) {
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectEtcListCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
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

		public ArrayList<Product> selectEtcList(Connection conn, PageInfo pi) {
			ArrayList<Product> list = new ArrayList();
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectEtcList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
				int endRow = startRow + pi.getBoardLimit() - 1;
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					Product p = new Product();
					
					p.setPrName(rset.getString("PR_NAME"));
					p.setTitleImg(rset.getString("TITLEIMG"));
					p.setPrNo(rset.getInt("PR_NO"));
					
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
		
		public int updateProduct(Connection conn, Product p) {
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("updateProduct");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, p.getPrName());
				pstmt.setString(2, p.getPrIntroduce());
				pstmt.setInt(3, p.getProCatNo());
				pstmt.setString(4, p.getPrWeight());
				pstmt.setString(5, p.getPrOrigin());
				pstmt.setInt(6, p.getPrPrice());
				pstmt.setString(7, p.getPrContent());
				pstmt.setInt(8, p.getPrNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}

		public int updateAttachment(Connection conn, Attachment at) {
			int result = 0;
			
			PreparedStatement pstmt = null;

			String sql = prop.getProperty("updateAttachment");

			
			try {

				System.out.println(at.getFileNo());
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileNo());
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}

		
		

		public ArrayList<Attachment> selectReviewAttachment(Connection conn, int prNo) {
			
			ArrayList<Attachment> at = new ArrayList();
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectReviewAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, prNo);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					at.add(new Attachment(rset.getInt("FILE_NO"),
										  rset.getString("ORIGIN_NAME"),
										  rset.getString("CHANGE_NAME"),
										  rset.getString("FILE_PATH"),
										  rset.getInt("RV_NO")));
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return at;
		}

		public int insertQna(Connection conn, QNA q) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("insertQna");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, q.getQnaTitle());
				pstmt.setString(2, q.getQnaContent());
				pstmt.setString(3, q.getQnaWriter());
				pstmt.setInt(4, q.getProductNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		public int selectQnaCount(Connection conn, int prNo) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = 0;
			
			String sql = prop.getProperty("selectQnaCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, prNo);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					result = rset.getInt("COUNT");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return result;
		}

		public ArrayList<QNA> selectQnaList(Connection conn, int prNo, PageInfo pi) {
			
			ArrayList<QNA> list = new ArrayList<QNA>();
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectQnaList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
				int endRow = startRow + pi.getBoardLimit() - 1;
				
				pstmt.setInt(1, prNo);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new QNA(rset.getInt("QNA_NO"),
										rset.getString("QNA_TITLE"),
										rset.getString("QNA_CONTENT"),
										rset.getDate("CREATE_DATE"),
										rset.getString("USER_NAME"),
										rset.getString("QNA_ANSWER_ST")));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return list;
		}

		public ArrayList<Integer> selectRecipeNo(Connection conn, String pName) {
			
			ArrayList<Integer> peNoArr = new ArrayList();
					
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectRecipeNo");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pName);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					peNoArr.add(new Integer(rset.getInt("PE_NO")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return peNoArr;
		}

		public ArrayList<Attachment> selectRecipeAttachment(Connection conn, ArrayList<Integer> peNoArr) {
			
			ArrayList<Attachment> rcpAt = new ArrayList();
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectRecipeAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				for(int i : peNoArr) {
					pstmt.setInt(1, i);
					rset = pstmt.executeQuery();
					while(rset.next()) {
						rcpAt.add(new Attachment(rset.getInt("FILE_NO"),
												 rset.getString("ORIGIN_NAME"),
												 rset.getString("CHANGE_NAME"),
												 rset.getString("FILE_PATH"),
												 rset.getInt("PE_NO")));
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return rcpAt;
		}

		public int deleteReview(Connection conn, int rvNo) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("deleteReview");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rvNo);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		public int deleteQna(Connection conn, int qaNo) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("deleteQna");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, qaNo);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		public QNA selectQna(Connection conn, int prNo) {
			
			QNA q = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectQna");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, prNo);
				rset = pstmt.executeQuery();
				if(rset.next()){
					q = new QNA(rset.getString("QNA_TITLE"),
								rset.getString("QNA_CONTENT"),
								rset.getDate("CREATE_DATE"));
				}
								
					
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return q;
		}

		public int insertAns(Connection conn, int qaNo, String ansContent) {
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("insertAns");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, qaNo);
				pstmt.setString(2, ansContent);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		public int ansComplete(Connection conn, int qaNo) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("ansComplete");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, qaNo);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		public ArrayList<QNA> selectAnsList(Connection conn, ArrayList<QNA> list) {
			
			ArrayList<QNA> answer = new ArrayList<QNA>();
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectAnsList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				for(int i = 0; i < list.size(); i++) {
					pstmt.setInt(1, (Integer)list.get(i).getQnaNo());
					rset = pstmt.executeQuery();
					while(rset.next()){
						answer.add(new QNA(rset.getInt("QNA_NO"),
							  			 rset.getString("ANS_CONTENT"),
							 			 rset.getDate("CREATE_DATE")));
					}
				}
				
					
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return answer;
		}

		public int deleteProduct(Connection conn, int prNo) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("deleteProduct");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, prNo);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		


}
