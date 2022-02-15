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

import dto.PaymentDTO;

public class PaymentDAO {

	// 결제 정보를 처리하는 DAO - 조문주

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
	
	// insertPayment(pdto)
	public void insertPayment(PaymentDTO pdto){
		try {
			con = getCon();
			
			sql = "insert into payment(payment_member_cd, payment_class_cd, payment_price) values(?,?,?*0.7)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, pdto.getPayment_member_cd());
			pstmt.setString(2, pdto.getPayment_class_cd());
			pstmt.setInt(3, pdto.getPrice());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	
	// getPayment
	public List getPaymentList(String cd){
		List paymentList = new  ArrayList();
		
		try {
			con = getCon();
			
			sql = "select c.class_name, c.class_price, p.payment_price, p.payment_date "
					+ "from class c join payment p "
					+ "on c.class_cd = p.payment_class_cd "
					+ "where c.class_tutor_cd =?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, cd);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PaymentDTO pdto = new PaymentDTO();
				
				pdto.setPay(rs.getString("class_price"));
				pdto.setClass_name(rs.getString("class_name"));
				pdto.setPrice(rs.getInt("payment_price"));
				pdto.setPayment_date(rs.getTimestamp("payment_date"));
				
				paymentList.add(pdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return paymentList;
	}
	
	
}
