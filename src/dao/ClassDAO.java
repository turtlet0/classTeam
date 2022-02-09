package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ClassDTO;

public class ClassDAO {
	
	// 클래스 정보를 처리하는 DAO - 조문주

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
	
	// 클래스 본문 뿌리기
	public ClassDTO getClass(String class_cd){
		ClassDTO dto = new ClassDTO();
		
		try {
			con = getCon();
			sql = "select c.class_cd, c.class_date, c.class_name, c.class_price, c.class_tutor_cd, t.tutor_name "
					+ "from class c join tutor t "
					+ "on c.class_tutor_cd = t.tutor_cd "
					+ "where c.class_cd = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, class_cd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new ClassDTO();
				
				dto.setClass_cd(rs.getString("class_cd"));
				dto.setClass_date(rs.getString("class_date"));
				dto.setClass_name(rs.getString("class_name"));
				dto.setClass_price(rs.getString("class_price"));
				dto.setClass_tutor_cd(rs.getString("class_tutor_cd"));
				dto.setClass_tutor_name(rs.getString("tutor_name"));
			}
			
			System.out.println("클래스 디테일 sql 실행 완료");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return dto;
	}
	// 클래스 본문 뿌리기
	
	// 클래스 리스트
	public List getClassList(){
		List classList = new ArrayList();
		
		try {
			con = getCon();
			
			sql = "select c.class_cd, c.class_date, c.class_name, c.class_price, c.class_tutor_cd, t.tutor_name "
					+ "from class c join tutor t "
					+ "on c.class_tutor_cd = t.tutor_cd "
					+ "order by class_cd";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ClassDTO dto = new ClassDTO();
				dto.setClass_cd(rs.getString("class_cd"));
				dto.setClass_date(rs.getString("class_date"));
				dto.setClass_name(rs.getString("class_name"));
				dto.setClass_price(rs.getString("class_price"));
				dto.setClass_tutor_cd(rs.getString("class_tutor_cd"));
				dto.setClass_tutor_name(rs.getString("tutor_name"));
				
				classList.add(dto);
				
			}
			System.out.println("클래스 목록 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return classList;
	}

}
