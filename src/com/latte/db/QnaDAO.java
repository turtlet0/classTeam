package com.latte.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class QnaDAO {
	// 큐엔에이 DAO

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

	// insertQna(dto)
	public void insertQna(QnaDTO dto) {
		int qno = 0;

		try {
			con = getCon();
			sql = "select max(qno) from QnA";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				qno = rs.getInt(1) + 1; // 1번 인덱스 컬럼
			}

			System.out.print("QnA글번호: " + qno);
			///////////////////////////////////////////////////////////////////////
			sql = "insert into QnA(qno,class_cd,member_cd,content,q_ref,q_lev,q_seq,reg_date) "
					+ "values(?,?,?,?,?,?,?,now())";
			// pstmt 객체 생성
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, dto.getQno());
			pstmt.setInt(2, dto.getClass_cd());
			pstmt.setInt(3, dto.getMember_cd());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, qno);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);

			pstmt.executeUpdate();

			System.out.println(" / Q-작성완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	// insertQnA(dto)

	// updateQna(dto)
	public int updateQna(QnaDTO dto) {
		int result = 1;

		try {
			con = getCon();
			sql = "select member_cd from QnA where qno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getQno());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (dto.getMember_cd() == (rs.getInt("member_cd"))) {
					sql = "update QnA set content=? where qno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getContent());
					pstmt.setInt(2, dto.getQno());
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
	// updateQnA(dto)

	// deleteQnA
	public int deleteQna(int qno, int member_cd) {
		int result = 1;

		try {
			con = getCon();
			sql = "select member_cd from QnA where qno=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, qno);

			rs = pstmt.executeQuery();
			System.out.println("sql1번실행완료");
			if (rs.next()) {
				if (member_cd == rs.getInt("member_cd")) {
					sql = "delete from QnA where qno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, qno);

					result = pstmt.executeUpdate();
					System.out.println("sql2번실행완료");

				} else {
					result = 0;
				}
			}

			System.out.println("삭제결과  " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return result;
	}
	// deleteQnA
	
	// reDeleteQna
	public int reDeleteQna(int qno, int member_cd,int q_ref) {
		int result = 1;
		
		try {
			con = getCon();
			sql = "select member_cd from QnA where qno=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, qno);
			
			rs = pstmt.executeQuery();
			System.out.println("re - sql1번실행완료");
			if (rs.next()) {
				if (member_cd == rs.getInt("member_cd")) {
					sql = "delete from QnA where q_ref=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, q_ref);
					
					result = pstmt.executeUpdate();
					System.out.println("re - sql2번실행완료");
					
				} else {
					result = 0;
				}
			}
			
			System.out.println("DAO : 삭제결과  " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}
	// reDeleteQna

	// getreviewList//////////////////////////////////////////수정예정
	public ArrayList getQnaList(int num) {
		// 가변길이 배열
		ArrayList qnaList = new ArrayList();

		try {
			con = getCon();
			sql = "select * from QnA where class_cd=? order by q_ref desc, q_seq asc";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 데이터 있을때 마다 글 1개의 정보를 저장하는 객체 생성
				QnaDTO qdto = new QnaDTO();

				qdto.setQ_lev(rs.getInt("q_lev"));
				qdto.setQ_ref(rs.getInt("q_ref"));
				qdto.setQ_seq(rs.getInt("q_seq"));
				qdto.setClass_cd(rs.getInt("class_cd"));
				qdto.setContent(rs.getString("content"));
				qdto.setMember_cd(rs.getInt("member_cd"));
				qdto.setReg_date(rs.getTimestamp("reg_date"));
				qdto.setQno(rs.getInt("Qno"));

				// DTO 객체를 ArrayList 한칸에 저장
				qnaList.add(qdto);
			}
			System.out.println("리뷰list저장완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return qnaList;
	}
	// getreviewList

	// reinsertQna(rdto)
	public void reInsertQna(QnaDTO dto) {
		int qno = 0;

		try {
			con = getCon();
			sql = "select max(qno) from QnA";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				qno = rs.getInt(1) + 1;
			}
			System.out.println("----------------------------Q번호" + qno);

			sql = "update QnA set q_seq = q_seq + 1 " + "where q_ref=? and q_seq>?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, dto.getQ_ref());
			pstmt.setInt(2, dto.getQ_seq());
			pstmt.executeUpdate();
			System.out.println("----------------------------update문");

			sql = "insert into QnA(qno,class_cd,member_cd,content,q_ref,q_lev,q_seq,reg_date) "
					+ "values(?,?,?,?,?,?,?,now())";
			// pstmt 객체 생성
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, qno);
			pstmt.setInt(2, dto.getClass_cd());
			pstmt.setInt(3, dto.getMember_cd());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getQ_ref());
			pstmt.setInt(6, dto.getQ_lev() + 1);
			pstmt.setInt(7, dto.getQ_seq() + 1);
			pstmt.executeUpdate();

			System.out.println("A입력완료");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// reinsertQna

	// getQna
	public QnaDTO getQna(int qno) {
		QnaDTO qdto = null;

		try {
			con = getCon();
			sql = "select * from QnA where qno=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, qno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				qdto = new QnaDTO();

				qdto.setQ_lev(rs.getInt("q_lev"));
				qdto.setQ_ref(rs.getInt("q_ref"));
				qdto.setQ_seq(rs.getInt("q_seq"));
				qdto.setClass_cd(rs.getInt("class_cd"));
				qdto.setContent(rs.getString("content"));
				qdto.setMember_cd(rs.getInt("member_cd"));
				qdto.setReg_date(rs.getTimestamp("reg_date"));
				qdto.setQno(rs.getInt("qno"));
			}

			System.out.println("정보가져오기");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return qdto;
		}
		// getQna
	
	
	
	
	
	
	
	
	
	
}//QnaDAO
