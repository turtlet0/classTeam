package com.latteclass.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/jspdb");
		
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
	
	//writeClass(dto)
	public void writeClass(ClassDTO dto) {
		
		int class_cd = dto.getClass_cd();
		
		try {
			con = getCon();
			
			
			// 글번호
			sql = "select max(class_cd) from class";
			pstmt = con.prepareStatement(sql);
			
			// sql 구문실행
			rs = pstmt.executeQuery();
			
			// 데이터 처리
			if(rs.next()) {
				//기존의 강의번호(저장된 최대값)+1
				class_cd = rs.getInt(1)+1;
			}
			
			System.out.println("강의 번호 : " + class_cd);
			
			
			sql = "insert into class(class_cd, title, class_rep_img, category, subcategory, sido, price) "
					+ "values(?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			//?
			pstmt.setInt(1, class_cd);
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getClass_rep_img());
			pstmt.setString(4, dto.getCategory());
			pstmt.setString(5, dto.getSubcategory());
			pstmt.setString(6, dto.getSido());
			pstmt.setString(7, dto.getPrice());
			
			pstmt.executeUpdate();
			System.out.println("DAO : 글 작성 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	
	// 강의 수확인
	
	
	
	// 아직 미완성 ( 클래스  간단설명에 대한 검색 추가??)
	// 클래스명, 시·도, 카테고리, 서브카테고리
	
	// getClassCount(keyword, sido, category, subcategory)
	public int getClassCount(String keyWord, String sido, String category, String subcategory) {
		int count = 0;
		
		try {
			
			con = getCon();
			if(keyWord == null || "".equals(keyWord.trim())) {
				if(sido.equals("all")) {
					if(category.equals("all")) {
						sql = "select count(*) from class";
						pstmt = con.prepareStatement(sql);
						System.out.println(sql);
					}else{
						if(subcategory.equals("전체")) {
							sql = "select count(*) from class where category=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, category);
							System.out.println(sql);
						}else {
							sql = "select count(*) from class where category=? and subcategory=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, category);
							pstmt.setString(2, subcategory);
							System.out.println(sql);
						}
					}
				}else {
					if(category.equals("all")) {
						sql = "select count(*) from class where sido=?";
						pstmt = con.prepareStatement(sql);
						System.out.println(sql);
						pstmt.setString(1, sido);
						
					}else{
						if(subcategory.equals("전체")) {
							sql = "select count(*) from class where sido=? and category=?";
							pstmt = con.prepareStatement(sql);
							System.out.println(sql);
							pstmt.setString(1, sido);
							pstmt.setString(2, category);
						}else {
							sql = "select count(*) from class where sido=? and category=? and subcategory=?";
							pstmt = con.prepareStatement(sql);
							System.out.println(sql);
							pstmt.setString(1, sido);
							pstmt.setString(2, category);
							pstmt.setString(3, subcategory);
						}
					}
				}
			}else {
				if(sido.equals("all")) {
					if(category.equals("all")) {
						sql = "select count(*) from class where title like ?";
						pstmt = con.prepareStatement(sql);
						System.out.println(sql);
						pstmt.setString(1, "%"+keyWord+"%");
						
					}else{
						if(subcategory.equals("전체")) {
							sql = "select count(*) from class where title like ? and category=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+keyWord+"%");
							pstmt.setString(2, category);
						}else {
							sql = "select count(*) from class where title like ? and category=? and subcategory=?";
							pstmt = con.prepareStatement(sql);
							System.out.println(sql);
							pstmt.setString(1, "%"+keyWord+"%");
							pstmt.setString(2, category);
							pstmt.setString(3, subcategory);
						}
					}
				}else {
					if(category.equals("all")) {
						sql = "select count(*) from class where title like ? and sido=?";
						pstmt = con.prepareStatement(sql);
						System.out.println(sql);
						pstmt.setString(1, "%"+keyWord+"%");
						pstmt.setString(2, sido);
						
					}else{
						if(subcategory.equals("전체")) {
							sql = "select count(*) from class where title like ? and sido=? and category=?";
							pstmt = con.prepareStatement(sql);
							System.out.println(sql);
							pstmt.setString(1, "%"+keyWord+"%");
							pstmt.setString(2, sido);
							pstmt.setString(3, category);
						}else {
							sql = "select count(*) from class where title like ? and sido=? and category=? and subcategory=?";
							pstmt = con.prepareStatement(sql);
							System.out.println(sql);
							pstmt.setString(1, "%"+keyWord+"%");
							pstmt.setString(2, sido);
							pstmt.setString(3, category);
							pstmt.setString(4, subcategory);
						}
					}
				}
			}
			
			//sql 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println("글개수 " + count);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			closeDB();
		}
		System.out.println(count);
		return count;
	}
	// getClassCount(keyword, sido, category, subcategory)
	

	
	// 아직 미완성
	// 강의 목록을 가져와서 List로 반환하는 분류별
	// 아직 미완성 ( 클래스  간단설명에 대한 검색 추가??)
	// 클래스명, 시·도, 카테고리, 서브카테고리
	// getClassList(startRow, pageSize, keyWord, sido, category,  subcategory)
	public List getClassList(int startRow, int pageSize, String keyWord, String sido, String category,  String subcategory) {
		
		List classlist = new ArrayList();
		
		try {
			con = getCon();
			
			
			if(keyWord == null || "".equals(keyWord.trim())) {
				if(sido.equals("all")) {
					if(category.equals("all")) {
						
						sql = "select * from class limit ?,?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
						pstmt.setInt(2, pageSize);		// 페이지 크기(한번에 출력되는 수)
						System.out.println(sql);
					}else {
						if(subcategory.equals("전체")) {
							sql = "select * from class where category=? limit ?,?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, category);
							pstmt.setInt(2, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
							pstmt.setInt(3, pageSize);		// 페이지 크기(한번에 출력되는 수)
							System.out.println(sql);
							
						}else {
							sql = "select * from class where category=? and subcategory=? limit ?,?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, category);
							pstmt.setString(2, subcategory);
							pstmt.setInt(3, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
							pstmt.setInt(4, pageSize);		// 페이지 크기(한번에 출력되는 수)
							
							System.out.println(sql);
						}
								
					}
				}else {
					
					if(category.equals("all")) {
						sql = "select * from class where sido=?";
						
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, sido);
						System.out.println(sql);
					}else {
						if(subcategory.equals("전체")) {
							
							sql = "select * from class where sido=? and category=? limit ?,?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, sido);
							pstmt.setString(2, category);
							pstmt.setInt(3, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
							pstmt.setInt(4, pageSize);		// 페이지 크기(한번에 출력되는 수)
							System.out.println(sql);
							
						}else {
							sql = "select * from class where sido=? and category=? and subcategory=? limit ?,?";
							pstmt = con.prepareStatement(sql);
							System.out.println(sql);
							pstmt.setString(1, sido);
							pstmt.setString(2, category);
							pstmt.setString(3, subcategory);
							pstmt.setInt(4, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
							pstmt.setInt(5, pageSize);		// 페이지 크기(한번에 출력되는 수)
							
						}
								
					}
					
				}
			}else {
				if(sido.equals("all")) {
					if(category.equals("all")) {
						System.out.println(keyWord);
						sql = "select * from class where title like ? limit ?,?";
						pstmt = con.prepareStatement(sql);
						System.out.println(sql);
						pstmt.setString(1, "%"+keyWord+"%");
						pstmt.setInt(2, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
						pstmt.setInt(3, pageSize);		// 페이지 크기(한번에 출력되는 수)
					}else {
						if(subcategory.equals("전체")) {
							sql = "select * from class where title like ? and category=? limit ?,?";
							System.out.println(sql);
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+keyWord+"%");
							pstmt.setString(2, category);
							pstmt.setInt(3, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
							pstmt.setInt(4, pageSize);		// 페이지 크기(한번에 출력되는 수)
	
							
						}else {
							sql = "select * from class where title like ? and category=? and subcategory=? limit ?,?";
							pstmt = con.prepareStatement(sql);
							System.out.println(sql);
							pstmt.setString(1, "%"+keyWord+"%");
							pstmt.setString(2, category);
							pstmt.setString(3, subcategory);
							pstmt.setInt(4, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
							pstmt.setInt(5, pageSize);		// 페이지 크기(한번에 출력되는 수)
							
						}
								
					}
				}else {
					
					if(category.equals("all")) {
						sql = "select * from class where title like ? and sido=? limit ?,?";
						
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "%"+keyWord+"%");
						pstmt.setString(2, sido);
						pstmt.setInt(3, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
						pstmt.setInt(4, pageSize);		// 페이지 크기(한번에 출력되는 수)
						System.out.println(sql);
					}else {
						if(subcategory.equals("전체")) {
							
							sql = "select * from class where title like ? and sido=? and category=? limit ?,?";
							pstmt = con.prepareStatement(sql);
							System.out.println(sql);
							pstmt.setString(1, "%"+keyWord+"%");
							pstmt.setString(2, sido);
							pstmt.setString(3, category);
							pstmt.setInt(4, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
							pstmt.setInt(5, pageSize);		// 페이지 크기(한번에 출력되는 수)
	
							
						}else {
							sql = "select * from class where title like ? and sido=? and category=? and subcategory=? limit ?,?";
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, "%"+keyWord+"%");
							pstmt.setString(2, sido);
							pstmt.setString(3, category);
							pstmt.setString(4, subcategory);
							pstmt.setInt(5, startRow-1);	// 시작행-1 (시작 ROW인덱스 번호)
							pstmt.setInt(6, pageSize);		// 페이지 크기(한번에 출력되는 수)
							
						}
								
					}
					
				}
				
			}
			
			rs = pstmt.executeQuery();
			
				while(rs.next()) {
					ClassDTO dto = new ClassDTO();
					
					dto.setClass_cd(rs.getInt("class_cd"));
					dto.setTitle(rs.getString("title"));
					dto.setClass_rep_img(rs.getString("class_rep_img"));
					dto.setCategory(rs.getString("category"));
					dto.setSubcategory(rs.getString("subcategory"));
					dto.setSido(rs.getString("sido"));
					dto.setPrice(rs.getString("price"));
					
					classlist.add(dto);
				}
				
				System.out.println("강의정보가져옴" + classlist.size());
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}finally {
			closeDB();
		}
		
		return classlist;
		
		
	}
	// getClassList(String keyWord, String sido, String category,  String subcategory)
	
	// 강의 내용을 가져오는 메서드
	// getClass(class_cd)
	public ClassDTO getClass(int class_cd) {
		ClassDTO dto = null;
		try {
			con=getCon();
			
			// sql(select) & pstmt 객체 생성
			// num에 해당하는 글정보를 가져오기
			sql = "select * from class where class_cd=?";
			pstmt=con.prepareStatement(sql);
			
			//?
			pstmt.setInt(1, class_cd);
			
			//sql실행
			rs = pstmt.executeQuery();
			
			// 데이터 처리
			if(rs.next()) {
				dto = new ClassDTO();
				
				dto.setClass_cd(rs.getInt("class_cd"));
				dto.setTitle(rs.getString("title"));
				dto.setClass_rep_img(rs.getString("class_rep_img"));
				dto.setCategory(rs.getString("category"));
				dto.setSubcategory(rs.getString("subcategory"));
				dto.setSido(rs.getString("sido"));
				dto.setPrice(rs.getString("price"));
			}
			System.out.println("DAO: 글정보 저장완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return dto;
	}
	
	// getClass(class_cd)
	
	
}
