package com.teamproject.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LikesDAO {

	// 좋아요 정보를 처리하는 DAO - 조문주

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 디비연결
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mochadb");
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
	
	
	// 좋아요 디비 저장
	public void likeUpdate(String cd, String class_cd) {
		
		try {
			con = getCon();
			
			sql = "insert into likes(likes_class_cd, likes_member_cd) values(?, ?)";
	    	pstmt = con.prepareStatement(sql);
	    	
	    	pstmt.setString(1, class_cd);
	    	pstmt.setString(2, cd);
	    	
	    	pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		    
	}
	
	// 좋아요 추천 여부 검사
	public int likeCheck(String cd, String class_cd){
		int result = 0;
			try {
				con = getCon();
				
				sql = "select * from likes where likes_class_cd=? and likes_member_cd=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, class_cd);
				pstmt.setString(2, cd);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					result = 0;
				}else{
					result = 1;
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				CloseDB();
			}
		
		return result;
	}
	
	// 좋아요 제거
	public void likeDelete(String cd, String class_cd){
		try {
			con = getCon();
			
			sql = "select * from likes where likes_class_cd=? and likes_member_cd=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, class_cd);
			pstmt.setString(2, cd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				sql = "delete from likes where likes_class_cd=? and likes_member_cd=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, class_cd);
				pstmt.setString(2, cd);
				
				pstmt.executeUpdate();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CloseDB();
		}
	}
	
	// 추천수
	public int likeCount(String class_cd){
		int ccnt = 0;
		try {
			con = getCon();
			
			sql = "select count(*) from likes where likes_class_cd=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, class_cd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				ccnt = rs.getInt(1);
			}
			System.out.println("클래스 좋아요 몇개?" +ccnt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CloseDB();
		}
		
		return ccnt;
	}
	
	// 내가 찍은 좋아요 확인
	public List getLikeList(String cd){
		List likeList = new ArrayList();
		System.out.println(cd);
		try {
			con = getCon();
			
			sql = "select l.likes_cd, l.likes_class_cd, l.likes_member_cd, c.class_name, c.class_date "
					+ "from likes l join class c "
					+ "on l.likes_class_cd = c.class_cd "
					+ "where likes_member_cd=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, cd);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				LikesDTO ldto = new LikesDTO();
				
				ldto.setLikes_cd(rs.getInt("likes_cd"));
				ldto.setLikes_class_cd(rs.getString("likes_class_cd"));
				ldto.setLikes_member_cd(rs.getString("likes_member_cd"));
				ldto.setLikes_class_date(rs.getString("class_date"));
				ldto.setLikes_class_name(rs.getString("class_name"));
				
				likeList.add(ldto);
				System.out.println("while"+ldto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		System.out.println(likeList);
		return likeList;
	}
}
