package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	//JDBC과정중 반복적으로 쓰이는 구문들을 각각의 메소드로 정의해둘곳
	//"재사용할 목적"으로 공동 템플릿 작업진행
	
	
	
	// 이 클래스에서의 모든 메소드는 전부 다 static 메소드로 만들것
	//싱글톤 패턴 : 메모리영역에 단 한번만 올라간것을 재사용한 개념
	
	//공통적인 부분 뽑아내기
	//1.DB 와 접손된 Connection 객체를 생성해서 반환시켜주는 메소드
	
	public static Connection getConnection() {
		Connection conn = null;
		
		/*
		 * 기존의 방식 : JDBC Driver 구문, 내가 접속할 DB의 url 정보, 계정명, 비밀번호등을
		 * 자바 소스코드 내부에 명시적으로 작성 -> 정적코딩 방식(하드코딩)
		 * 
		 * -문제점 : DBMS가 변경되었을 경우 / 접속할 url, 계정명, 비밀번호가 변경되었을 경우
		 * -> 자바 소스코드를 수정해야한다.
		 * 수정한 코드 내용을 반영시키고자 한다면 프로그램을 재 구동해야한다.
		 * (사용자 입장에서 프로그램을 잘 사용하다가 비정상적으로 종료되었다가 다시 구동될수가 있다.)
		 * + 유지보수가 불편하다.
		 * - 해결방식 : DB관련된 정보들을 별도로 관리하는 외부파일(.properties)로  만들어 관리
		 * 	외부 파일로 key에대한 value를 읽어들여서 반영시킬것. - > 동적 코딩방식
		 */
		//동적 코딩 방식을 적용하기 위해 Properties객체 생성
		
		Properties prop = new Properties();
		//파일의 물리적 경로
		String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
		
		try {
			//prop 객체로부터 load 메소드를 이용하여 각 키에 해당하는 밸류를 가져오겟다 !
			prop.load(new FileInputStream(fileName));
			
			//prop로 부터 getProperty메소드를 이용하여 각 키에 해당하는 벨류를 뽑아서 배치
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
			
			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
//					"JDBC", "JDBC");
			conn = DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("id"),
											   prop.getProperty("pwd"));			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		}				
		return conn;
	}
	
	//2_1)Connction 객체를 전달받아서 반납시켜주는메소드
	public static void close(Connection conn) {
		try {
			//만약 conn 이 null이라면 nullPointerException 발생할 수 있음.
			if(conn != null && !conn.isClosed())
				conn.close();				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//2_2 Statment 객체 반납 메소드 --> 다형성으로 인해 preparedstatement객체도 반환간으
	public static void close(Statement stmt) {
		try {
			if(stmt != null && stmt.isClosed())
				stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//2_3 ResultSet 객체 반납
	public static void close(ResultSet rset) {
		try {
			if(rset != null && rset.isClosed())
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
