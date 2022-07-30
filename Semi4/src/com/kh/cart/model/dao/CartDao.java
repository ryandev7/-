package com.kh.cart.model.dao;

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

import com.kh.cart.model.vo.Cart;
import com.kh.cart.model.vo.Order;
import com.kh.common.model.vo.Attachment;

public class CartDao {

	private Properties prop = new Properties();

	public CartDao() {

		String fileName = CartDao.class.getResource("/sql/cart/cart-mapper.xml").getPath();

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

	public int insertCart(Connection conn, String userId, int pno, int amount) {

		// INSERT문 => 처리된 행의 갯수

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertCart");
		// INSERT INTO CART(CART_NO, CART_AMOUNT, USER_ID, PRO_NO) VALUES(SEQ_CART_NO,
		// ?, ?, ?);

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, amount);
			pstmt.setString(2, userId);
			pstmt.setInt(3, pno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	
	public ArrayList<Cart> selectAllCartList(Connection conn, String userId){
		
		ArrayList<Cart> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAllCartList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Cart c = new Cart();
				c.setCartNo(rset.getInt("CART_NO"));
				c.setCartAmount(rset.getInt("CART_AMOUNT"));
				c.setCartStatus(rset.getString("CART_STATUS"));
				c.setUserId(rset.getString("USER_ID"));
				c.setProNo(rset.getInt("PRO_NO"));
				c.setPrName(rset.getString("PR_NAME"));
				c.setPrPrice(rset.getInt("PR_PRICE"));
				
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		} return list;
		
	}

	public int CartDelete(Connection conn, int cartNo) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("CartDelete");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		
		return result;
	}


	public int UpdateCartOrderItem(Connection conn, int cartNo, int count) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("CartCheckedItem");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, cartNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Attachment> selectProductAttachment(Connection conn, ArrayList<Cart> cList) {
		
		ArrayList<Attachment> proAt = new ArrayList<Attachment>();
		ArrayList<Integer> proNoArr = new ArrayList<Integer>();
		
		for(int i = 0; i < cList.size(); i++) {
			proNoArr.add(new Integer(cList.get(i).getProNo()));
		}
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProductAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i : proNoArr) {
				pstmt.setInt(1, i);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					proAt.add(new Attachment(rset.getInt("FILE_NO"),
											 rset.getString("ORIGIN_NAME"),
											 rset.getString("CHANGE_NAME"),
											 rset.getString("FILE_PATH"),
											 rset.getInt("PRODUCT_NO")));
				}
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return proAt;
	}

	public ArrayList<Cart> SelectOrderedCart(Connection conn, int cartNo) {
		
		ArrayList<Cart> orderedCart = new ArrayList<Cart>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectOrderedCart");
		

	
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cartNo);
			
			rset = pstmt.executeQuery();
			
			
						
			while(rset.next()) {
				orderedCart.add(new Cart(rset.getInt("CART_NO"),
										 rset.getInt("CART_AMOUNT"),
										 rset.getString("CART_STATUS"),
										 rset.getString("USER_ID"),
										 rset.getInt("PRO_NO"),
										 rset.getString("PR_NAME"),
										 rset.getInt("PR_PRICE")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
				
		return orderedCart;
	}
	
	public int InsertOrder(Connection conn, ArrayList<Order> orderList) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("InsertOrder");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, orderList.get(0).getOrderPro());
			pstmt.setInt(2, orderList.get(0).getOrderPrice());
			pstmt.setInt(3, orderList.get(0).getOrderAmount());
			pstmt.setString(4, orderList.get(0).getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int MakeOrderNo(Connection conn) {
		
		int on = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("MakeOrderNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			on = rset.getInt("A");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return on;
	}


	
}











