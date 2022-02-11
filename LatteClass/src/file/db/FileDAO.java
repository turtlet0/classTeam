package file.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FileDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	/*커넥션 풀 이용한 DB 연결*/
	private java.sql.Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/itwillbs1");
		con = ds.getConnection();
		
		return con;
	} // getCon()
	
	/*DB 자원해제 동작 메서드(SELECT)*/
	public void closeDB() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*이미지 파일 업로드 동작 메서드 (클래스 최초 등록 시)*/
	// class_cd는 클래스 최초 등록 메서드에서 DB 저장
	public void insertImg(FileDTO fdto) {
		try {
			con = getCon();
			sql = "INSERT INTO class_img (member_cd, img_idx, img_name, img_orig_name, img_upload_path) "
					+ "VALUES (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, fdto.getMember_cd());
			pstmt.setString(2, fdto.getImg_idx());
			pstmt.setString(3, fdto.getImg_name());
			pstmt.setString(4, fdto.getImg_orig_name());
			pstmt.setString(5, fdto.getImg_upload_path());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
//				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
}
