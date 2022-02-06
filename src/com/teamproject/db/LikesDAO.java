package com.teamproject.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LikesDAO {

	// 좋아요 정보를 처리하는 DAO

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 디비연결
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mocha");
		con = ds.getConnection();
		return con;
	}

	// 자원해제
	public void CloseDB() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void bGood(int idx) {
		    try {
		      sql = "update likes l inner join class c "
		      		+ "on l.likes_class_cd = c.class_cd "
		      		+ "set likes_cd = likes_cd + 1 "
		      		+ "where likes_class_cd = ?";
		      pstmt = con.prepareStatement(sql);
		      pstmt.setInt(1, idx);
		      pstmt.executeUpdate();
		      
		    } catch (SQLException e) {
		      System.out.println("!!!!!좋아요 SQL 에러 : " + e.getMessage());
		      e.printStackTrace();
		    } finally {
		    	CloseDB();
		    }
	}
}
