package classes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class classDAO {
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
	
	/*튜터 정보 최초 등록 메서드*/
	public void insertTutor(TutorDTO tdto) {
		try {
			con = getCon();
			
			sql ="INSERT INTO tutor (company_name, instagram, blog, youtube, member_cd) "
					+ "VALUES (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			//tutor_cd : DB에서 AI 이용
			pstmt.setString(1, tdto.getCompany_name());
			pstmt.setString(2, tdto.getInstagram());
			pstmt.setString(3, tdto.getBlog());
			pstmt.setString(4, tdto.getYoutube());
			pstmt.setInt(5, 100); // 임시) member_cd
			
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
	
	/*클래스 최초 등록 메서드*/
		// 투터 최초 등록 메서드와 연달아 실행됨
	public void insertClass(ClassDTO cdto) {
		try {
//			con = getCon(); // 튜터 최초 등록 메서드와 동일 connection 사용 ( LAST_INSERT_ID() 이용 위함)
			
			// tutor_cd 최초 등록 : LAST_INSERT_ID() 이용 - 동일 Connection 내의 가장 최근 Auto Increment 값을 가져옴
				// tutor 테이블의 tutor_cd 컬럼 AI 이용 -> 이 값을 가져와 class 테이블의 tutor_cd 컬럼에 insert
				// 예정) 클래스 최초 등록이 아닌 경우 tutor_cd 를 member_cd 이용해 tutor 클래스에서 가져오는 코드 구현 
			sql = "INSERT INTO class (tutor_cd, title, introduce, category, sub_category, tutor_introduce, "
					+ "content, free_curriculum, duration_time, step1, step2, step3, step4, step5, recommend_people, tag, "
					+ "opening_area, postcode, road_address, jibun_address, detail_address, sido, sigungu, bname, directions,"
					+ " convenience_option, health_safety_option) "
					+ "VALUES (LAST_INSERT_ID(), ?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			// 글쓰기 등록 1 페이지 
			pstmt.setString(1, cdto.getTitle());
			pstmt.setString(2, cdto.getIntroduce());
			pstmt.setString(3, cdto.getCategory());
			pstmt.setString(4, cdto.getSub_category());
			pstmt.setString(5, cdto.getTutor_introduce());
			pstmt.setString(6, cdto.getContent());
			
			// 글쓰기 등록 2 페이지 
			pstmt.setString(7, cdto.getFree_curriculum());
			pstmt.setString(8, cdto.getDuration_time());
			pstmt.setString(9, cdto.getStep1());
			pstmt.setString(10, cdto.getStep2());
			pstmt.setString(11, cdto.getStep3());
			pstmt.setString(12, cdto.getStep4());
			pstmt.setString(13, cdto.getStep5());
			pstmt.setString(14, cdto.getRecommend_people());
			pstmt.setString(15, cdto.getTag());
			
			// 글쓰기 등록 3 페이지 
			pstmt.setString(16, cdto.getOpening_area());
			pstmt.setString(17, cdto.getPostcode());
			pstmt.setString(18, cdto.getRoad_address());
			pstmt.setString(19, cdto.getJibun_address());
			pstmt.setString(20, cdto.getDetail_address());
			pstmt.setString(21, cdto.getSido());
			pstmt.setString(22, cdto.getSigungu());
			pstmt.setString(23, cdto.getBname());
			pstmt.setString(24, cdto.getDirections());
			pstmt.setString(25, cdto.getConvenience_option());
			pstmt.setString(26, cdto.getHealth_safety_option());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
