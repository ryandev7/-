package com.kh.main.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.Attachment;
import com.kh.main.model.dao.MainDao;
import com.kh.recipan.model.vo.Recipan;

public class MainService {

	public ArrayList<Recipan> selectRankList() {
		
		Connection conn = getConnection();
		
		ArrayList<Recipan> list = new MainDao().selectRankList(conn);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Attachment> selectRecipanList() {
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> atList = new MainDao().selectRecipanList(conn);
		
		close(conn);
		
		return atList;
	}

}
