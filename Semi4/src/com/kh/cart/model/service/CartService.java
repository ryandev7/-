package com.kh.cart.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cart.model.dao.CartDao;
import com.kh.cart.model.vo.Cart;
import com.kh.cart.model.vo.Order;
import com.kh.common.model.vo.Attachment;

public class CartService {

	public int insertCart(String userId, int pno, int amount) {

		Connection conn = getConnection();

		int result = new CartDao().insertCart(conn, userId, pno, amount);

		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;

	}

	
	
	public ArrayList<Cart> selectAllCartList(String userId){
		
		Connection conn = getConnection();
		
		ArrayList<Cart> list = new CartDao().selectAllCartList(conn, userId);
		
		close(conn);
		
		return list;
		
		
	}

	public int CartDelete(int cartNo) {
		
		Connection conn = getConnection();
		
		int result = new CartDao().CartDelete(conn, cartNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


	public int UpdateCartOrderItem(int cartNo, int count) {
		
		Connection conn = getConnection();
		
		int result = new CartDao().UpdateCartOrderItem(conn, cartNo, count);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}



	public ArrayList<Attachment> selectProductAttachment(ArrayList<Cart> cList) {
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> proAt = new CartDao().selectProductAttachment(conn, cList);
			
		close(conn);
		
		return proAt;
	}
	
	public ArrayList<Cart> SelectOrderedCart(int cartNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Cart> OrderedCart = new CartDao().SelectOrderedCart(conn, cartNo);
				
		return OrderedCart;
	}



	public int InsertOrder(ArrayList<Order> orderList) {
		
		Connection conn = getConnection();
		
		
		int result = new CartDao().InsertOrder(conn, orderList);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}



	public int MakeOrderNo() {
		
		Connection conn = getConnection();
		
		int on = new CartDao().MakeOrderNo(conn);
		
		close(conn);
		
		System.out.println("메이크오더넘버 실행");
		
		return on;
	}
}





	


