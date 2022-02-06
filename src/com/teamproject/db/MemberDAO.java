package com.teamproject.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MemberDAO {

	// 회원 정보를 처리하는 DAO - 조문주

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
	
	// loginCheck(id,pass)
		public int loginCheck(String id, String pw) {
			int result = -1;
			try {
				// 1. 드라이버로드
				// 2. 디비연결
				// => getCon() 실행
				con = getCon();

				// 3. sql 작성 & pstmt 객체 생성
				sql = "select member_pw from member where member_id=?";
				pstmt = con.prepareStatement(sql);

				// ?
				pstmt.setString(1, id);

				// 4. sql 실행
				rs = pstmt.executeQuery();

				// 아이디가 없는경우 (-1)리턴
				// 아이디는 있지만, 비밀번호 오류 (0)리턴
				// 아이디 있으면서, 비밀번호도 맞는경우 (1)리턴
				// 5. 최종 결과 리턴
				if (rs.next()) {
					// 회원정보 있음
					if (pw.equals(rs.getString("member_pw"))) {
						// 회원정보가 있으면서, 비밀번호 동일
						result = 1;
					} else {
						// 회원정보가 있음, 비밀번호 다름
						result = 0;
					}
				} else {
					// 회원정보 없음
					result = -1;
				}

				System.out.println(" SQL구문 실행 완료! ");
				System.out.println(" 실행결과 : " + result);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return result;
		}
		// loginCheck(id,pass)
		
		// getMember(id)
		public MemberDTO getMember(String id) {

			MemberDTO dto = null;

			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql 작성(select) & pstmt 객체
				// sql- 전달받은 아이디에 해당하는 회원정보 모두가져오기
				sql = "select * from member where member_id=?";
				pstmt = con.prepareStatement(sql);

				// ?
				pstmt.setString(1, id);

				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터처리
				if (rs.next()) {
					// 데이터가 있음 -> 회원정보를 저장
					dto = new MemberDTO();

					dto.setMember_cd(rs.getString("member_cd"));
					dto.setMember_id(rs.getString("member_id"));
					dto.setMember_pw(rs.getString("member_pw"));
				}
				System.out.println(" SQL구문 실행완료! ");
				System.out.println(" 회원정보 : " + dto);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return dto;
		}
		// getMember(id)
	
}
