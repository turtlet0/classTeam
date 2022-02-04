package com.test.tclass.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ClassDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql="";
	
	// DB연결
	private Connection getCon() throws Exception {
		
		// Context(프로젝트) 객체 생성(javax.naming)
		Context initCTX = new InitialContext();
		
		// 프로젝트 안에 DB연결에 필요한 정보 가져오기
		// => context.xml 파일의 정보를 읽어와서 DataSource파일의 형태로
		// 변경해서 저장해놓기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/test1");
		
		// 연결정보를 사용해서 DB연결
		con = ds.getConnection();
		
		return con;
	}
	
	//자원해제
	public void closeDB() {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(con != null) {con.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	// 강의 수확인
	
	// getClassCount() 
	public int getClassCount() {
		int count = 0;
		
		try {
			
			// DB연결
			con = getCon();
			
			//sql작성(select) & pstmt 객체
			sql = "select count(*) from class";
			pstmt = con.prepareStatement(sql);
			
			//sql실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//데이터 있을때 (글개수)
				count = rs.getInt(1);
			}
			System.out.println("DAO : 글개수 ("+count+")");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return count;
	}
	// getClassCount() 
	
	//keyFieldLocal, keyword, Category
	// getClassCount(keyFieldLocal, keyword, category)
	public int getClassCount(String keyFieldLocal, String keyWord, String classCategory) {
		int count = 0;
		
		try {
			
			con = getCon();
			if(keyWord == null || "".equals(keyWord.trim())) {
				
				if(classCategory.equals("all")) {
					sql = "select count(*) from class";
					pstmt = con.prepareStatement(sql);
					System.out.println(sql);
				}else { 
					sql = "select count(*) from class where category=?";
					pstmt = con.prepareStatement(sql);
					System.out.println(sql);
					pstmt.setString(1, classCategory);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return count;
	}
	// getClassCount(keyFieldLocal, keyword, category)
	

}
