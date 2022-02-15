package com.latteclass.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
		
		private Connection con =null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";
		
		// 디비연결 커넥션풀 사용
		// /Model2/WebContent/META-INF/context.xml
		
		private Connection getCon() throws Exception{
			Context initCTX = new InitialContext();
			DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/itwillbs1");
			con = ds.getConnection();
			return con;
		
		}
		///////////////////////////////// 자원해제	/////////////////////////////////
		public void CloseDB(){
			try {
				if(rs !=null){rs.close();}
				if(pstmt !=null){pstmt.close();}
				if(con !=null){con.close();}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}//자원해제
		
		
		///////////////////////////////// memberJoin(DTO) ////////////////////////////
		public void memberJoin(MemberDTO dto){
			try {
				con = getCon();
				
				//sql 작성
				sql = "insert into member(email, password, join_date) values(?,?,?)";
				
				//pstmt 객체 생성
						pstmt = con.prepareStatement(sql);
						
				//??
				pstmt.setString(1,dto.getEmail());
				pstmt.setString(2,dto.getPassword());
				pstmt.setTimestamp(3,dto.getJoin_date());
				
				//sql 실행
				pstmt.executeUpdate();
				
				System.out.println("DAO : 회원가입완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
		}
		///////////////////////////////// memberKakaoJoin(id) ////////////////////////////
		public String memberKakaoJoin(MemberDTO dto){
			String result ="";
			try {
				con = getCon();
				
				//sql 작성
				sql = "insert into member(sns_id, nick_name, join_date) values(?,?,?)";
				//pstmt 객체 생성
				pstmt = con.prepareStatement(sql);
				
				//??
				pstmt.setString(1,dto.getSns_id());
				pstmt.setString(2,dto.getNick_name());
				pstmt.setTimestamp(3,dto.getJoin_date());
				
				//sql 실행
				pstmt.executeUpdate();
				
				sql = "select member_cd from member where sns_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getSns_id());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getString("member_cd");
				}
				
				
				
				System.out.println("DAO : 회원가입완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return result;
		}
		///////////////////////////////// memberNaverJoin(id) ////////////////////////////
		public String memberNaverJoin(MemberDTO dto){
			String result ="";
			try {
				con = getCon();
				
				//sql 작성
				sql = "insert into member(email, sns_id, nick_name, join_date) values(?,?,?,?)";
				//pstmt 객체 생성
				pstmt = con.prepareStatement(sql);
				
				//??
				pstmt.setString(1,dto.getEmail());
				pstmt.setString(2,dto.getSns_id());
				pstmt.setString(3,dto.getNick_name());
				pstmt.setTimestamp(4,dto.getJoin_date());
				
				//sql 실행
				pstmt.executeUpdate();
				
				sql = "select member_cd from member where sns_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getSns_id());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getString("member_cd");
				}
				System.out.println("DAO : 회원가입완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			return result;
		}

		///////////////////////////////// 이메일 중복체크 메서드(ajex)////////////////////////////
		public String memberMailDoubleCheck(String email) {
			String result = "n";
			try {
				// 드라이버 로드, 디비연결 ==> getCon() 실행
				con = getCon();
				// 3. sql작성 & pstmt 객체 생성
				sql = "select email from member where email=?";
				pstmt = con.prepareStatement(sql);
				// ?
				pstmt.setString(1, email);
				// 4. sql실행
				rs = pstmt.executeQuery();
				
				// 5. 최종 결과 리턴
				if (rs.next()) {
					// 회원정보 있음
					result="y";
					
				}
				
				System.out.println("SQL구문 실행 완료!");
				System.out.println("실행결과 : " + result);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return result;
		}// 이메일 중복체크 메서드(ajex) 
		
		
		///////////////////////////////// sns_id 중복체크 메서드(ajex)////////////////////////////
		public String MemberSnsIdCheck(String sns_id) {
			String result = "n";
			try {
				// 드라이버 로드, 디비연결 ==> getCon() 실행
				con = getCon();
				// 3. sql작성 & pstmt 객체 생성
				sql = "select email from member where sns_id=?";
				pstmt = con.prepareStatement(sql);
				// ?
				pstmt.setString(1, sns_id);
				// 4. sql실행
				rs = pstmt.executeQuery();

				// 5. 최종 결과 리턴
				if (rs.next()) {
					// 회원정보 있음
					sql = "select member_cd from member where sns_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, sns_id);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						result = rs.getString("member_cd");
					}
				}
				System.out.println("SQL구문 실행 완료!");
				System.out.println("실행결과 result : " + result);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return result;
		}// sns_id 중복체크 메서드(ajex)
		
		///////////////////////////////// 로그인 체크 메서드////////////////////////////
		public int loginCheck(String email, String password) {
			int result = -1;
			try {
				// 드라이버 로드, 디비연결 ==> getCon() 실행
				con = getCon();
				// 3. sql작성 & pstmt 객체 생성
				sql = "select password from member where email=?";
				pstmt = con.prepareStatement(sql);
				// ?
				pstmt.setString(1, email);
				// 4. sql실행
				rs = pstmt.executeQuery();
				// 아이디X : (-1) 리턴 // 아이디O 비밀번호X :(0) 리턴 // 아이디O 비밀번호O : (1) 리턴
				// 5. 최종 결과 리턴
				if(rs.next()){
					// 회원정보 있음
					if(password.equals(rs.getString("password"))){
						// 회원정보가 있으면서, 비밀번호동일
						sql = "select member_cd from member where email=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, email);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							result = rs.getInt("member_cd");
						}
					}else{
						// 회원정보가 있음, 비밀번호 다름.
						result = 0;
					}
				}else{
					// 회원정보 없음
					result = -1;
				}
					
				System.out.println("SQL구문 실행 완료!");
				System.out.println("실행결과 : " + result);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return result;
		}// loginCheck	
		
		///////////////////////////////// 회원정보 체크 getMember 메서드////////////////////////////
		public MemberDTO getMember(String member_cd) {
			MemberDTO dto = null;

			// 1.2 DB연결 / 3. sql 작성 & pstmt 객체 /

			try {
				con = getCon();
				sql = "select * from member where member_cd=?"; 
				pstmt = con.prepareStatement(sql);

				// ? 처리 // 4. sql구문 실행
				pstmt.setString(1, member_cd);
				rs = pstmt.executeQuery();

				// 5.데이터 처리
				if (rs.next()) {
					// 데이터 있음 -> 회원정보를 저장
					dto = new MemberDTO();
					dto.setMember_cd(rs.getInt("member_cd"));
					dto.setEmail(rs.getString("email"));
					dto.setSns_id(rs.getString("sns_id"));
					dto.setNick_name(rs.getString("nick_name"));
					dto.setPhone_num(rs.getInt("phone_num"));

				}
				System.out.println("SQL 구문 실행완료");
				System.out.println("회원정보 : " + dto);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return dto;
		}// getMember(String member_cd)

		///////////////////////////////// 회원정보 수정 메서드////////////////////////////
		public int updateMemberProfile(MemberDTO dto) {
			int result = -1;
			try {
				// 1.2 디비연결 / 3. sql 작성(select:user본인확인) & pstmt 객체생성 / ? 처리
				con = getCon();
				sql = "select * from member where member_cd=?"; 
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getMember_cd());
				// 4. sql구문 실행
				rs = pstmt.executeQuery();

				// 5.데이터 처리
				if (rs.next()) {// 데이터 있을때
						sql = "update member set nick_name=?, phone_num=? where member_cd=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getNick_name());
						pstmt.setInt(2, dto.getPhone_num());
						pstmt.setInt(3, dto.getMember_cd());

						result = pstmt.executeUpdate();
						// -> sql구문이 실행했을때 영향을 준 row수 리턴.
						// result = 1;
						System.out.println("회원정보 수정완료");

				} else {// 데이터 없을떄
					result = -1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return result;

		}// updateMemberProfile()		
		
}
