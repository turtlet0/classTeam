package com.latte.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class ReviewDAO {
	// 리뷰정보처리 DAO
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 디비연결
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/itwillbs1");
		con = ds.getConnection();
		return con;
			}
	// 디비연결

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
	// 자원해제
	
	// insertReview(dto)
	public void insertReview(ReviewDTO dto) {
		int cno = 0;

		try {
			con = getCon();
			sql = "select max(cno) from review";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cno = rs.getInt(1) + 1; // 1번 인덱스 컬럼
			}

			System.out.print("댓글 번호: " + cno);
			///////////////////////////////////////////////////////////////////////
			sql = "insert into review(cno,class_cd,member_cd,rating,content,c_ref,c_lev,c_seq,reg_date) "
					+ "values(?,?,?,?,?,?,?,?,now())";
			// pstmt 객체 생성
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, dto.getCno());
			pstmt.setInt(2, dto.getClass_cd());
			pstmt.setInt(3, dto.getMember_cd());
			pstmt.setString(4, dto.getRating());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, cno);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);

			pstmt.executeUpdate();

			System.out.println(" / 댓글작성완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	// insertReview(dto)
	
	// updateReview(dto)
	public int updateReview(ReviewDTO dto) {
		int result = 1;

		try {
			con = getCon();
			sql = "select member_cd from review where cno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getCno());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (dto.getMember_cd()==(rs.getInt("member_cd"))) {
					sql = "update review set content=? where cno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getContent());
					pstmt.setInt(2, dto.getCno());
					result = pstmt.executeUpdate();
				} else {
					result = 0;
				}
			}

			System.out.println("수정여부 : " + result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return result;
	}
	// updateReview(dto)
	
	// deleteReview
	public int deleteReview(int cno, int member_cd) {
		int result = 1;

		try {
			con = getCon();
			sql = "select member_cd from review where cno=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, cno);

			rs = pstmt.executeQuery();
			System.out.println("sql1번실행완료");
			if (rs.next()) {
				if (member_cd==rs.getInt("member_cd")) {
					sql = "delete from review where cno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, cno);

					result = pstmt.executeUpdate();
					System.out.println("sql2번실행완료");

				} else {
					result = 0;
				}
			}

			System.out.println("삭제결과  " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}

		return result;
	}
	// deleteReview
	
	// deleteReview
	public int reDeleteReview(int cno, int member_cd,int c_ref) {
		int result = 1;
		
		try {
			con = getCon();
			sql = "select member_cd from review where cno=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cno);
			
			rs = pstmt.executeQuery();
			System.out.println("re-sql1번실행완료");
			if (rs.next()) {
				if (member_cd==rs.getInt("member_cd")) {
					sql = "delete from review where c_ref=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, cno);
					
					result = pstmt.executeUpdate();
					System.out.println("re-sql2번실행완료");
					
				} else {
					result = 0;
				}
			}
			
			System.out.println("DAO : 삭제결과  " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		return result;
	}
	// reDeleteReview
	
	
	
	// getreviewList//////////////////////////////////////////수정예정
	public ArrayList getReviewList(int num) {
		// 가변길이 배열
		ArrayList reviewList = new ArrayList();

		try {
			con = getCon();
			sql = "select * from review where class_cd=? order by c_ref desc, c_seq asc";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 데이터 있을때 마다 글 1개의 정보를 저장하는 객체 생성
				ReviewDTO rdto = new ReviewDTO();

				rdto.setC_lev(rs.getInt("c_lev"));
				rdto.setC_ref(rs.getInt("c_ref"));
				rdto.setC_seq(rs.getInt("c_seq"));
				rdto.setClass_cd(rs.getInt("class_cd"));
				rdto.setContent(rs.getString("content"));
				rdto.setMember_cd(rs.getInt("member_cd"));
				rdto.setRating(rs.getString("rating"));
				rdto.setReg_date(rs.getTimestamp("reg_date"));
				rdto.setCno(rs.getInt("cno"));

				// DTO 객체를 ArrayList 한칸에 저장
				reviewList.add(rdto);
			}
			System.out.println("리뷰list저장완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return reviewList;
	}
	// getreviewList	
	
	// reinsertReview(rdto)
	public void reInsertReview(ReviewDTO dto) {
		int cno = 0;

		try {
			con = getCon();
			sql = "select max(cno) from review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cno = rs.getInt(1) + 1;
			}
			System.out.println("----------------------------글번호" + cno);

			sql = "update review set c_seq = c_seq + 1 " + "where c_ref=? and c_seq>?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, dto.getC_ref());
			pstmt.setInt(2, dto.getC_seq());
			pstmt.executeUpdate();
			System.out.println("----------------------------update문");

			sql = "insert into review(cno,class_cd,member_cd,content,c_ref,c_lev,c_seq,reg_date) "
					+ "values(?,?,?,?,?,?,?,now())";
			// pstmt 객체 생성
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, cno);
			pstmt.setInt(2, dto.getClass_cd());
			pstmt.setInt(3, dto.getMember_cd());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getC_ref());
			pstmt.setInt(6, dto.getC_lev() + 1);
			pstmt.setInt(7, dto.getC_seq() + 1);
			pstmt.executeUpdate();

			System.out.println("답글입력완료");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// reinsertReview(rdto)
	
	// getReview
	public ReviewDTO getReview(int cno) {
		ReviewDTO rdto = null;

		try {
			con = getCon();
			sql = "select * from review where cno=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, cno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rdto = new ReviewDTO();

				rdto.setC_lev(rs.getInt("c_lev"));
				rdto.setC_ref(rs.getInt("c_ref"));
				rdto.setC_seq(rs.getInt("c_seq"));
				rdto.setClass_cd(rs.getInt("class_cd"));
				rdto.setContent(rs.getString("content"));
				rdto.setMember_cd(rs.getInt("member_cd"));
				rdto.setRating(rs.getString("rating"));
				rdto.setReg_date(rs.getTimestamp("reg_date"));
				rdto.setCno(rs.getInt("cno"));
			}

			System.out.println("정보가져오기");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return rdto;
	}
	// getReview
	
}
