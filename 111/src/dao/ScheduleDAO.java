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

import dto.ScheduleDTO;

public class ScheduleDAO {
	
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
		
		
		// insertschedule 
		public void insertShedule(ScheduleDTO sdto) {
			try {
				con = getCon();
				
				sql = "insert into schedule(schedule_startDate, schedule_endDate, schedule_class_cd, schedule_startTime, schedule_endTime) "
						+ "values(?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, sdto.getSchedule_startDate());
				pstmt.setString(2, sdto.getSchedule_endDate());
				pstmt.setString(3, sdto.getSchedule_class_cd());
				pstmt.setString(4, sdto.getSchedule_startTime());
				pstmt.setString(5, sdto.getSchedule_endTime());
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				CloseDB();
			}
		}
		
		
		// getschedule
		public List getSchedule(String cd, String class_cd){
			List scheduleList = new ArrayList();
			
			try {
				con = getCon();
				
				sql = "select s.schedule_startDate, s.schedule_endDate, s.schedule_startTime, s.schedule_endTime, s.schedule_cd "
						+ "from schedule s inner join class c "
						+ "on s.schedule_class_cd = c.class_cd "
						+ "inner join tutor t "
						+ "on c.class_tutor_cd = t.tutor_cd "
						+ "inner join member m "
						+ "on t.tutor_member_cd = m.member_cd "
						+ "where member_cd = ? and class_cd = ? "
						+ "order by schedule_startDate";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, cd);
				pstmt.setString(2, class_cd);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					ScheduleDTO sdto = new ScheduleDTO();
					sdto.setSchedule_startDate(rs.getString("schedule_startDate"));
					sdto.setSchedule_endDate(rs.getString("schedule_endDate"));
					sdto.setSchedule_startTime(rs.getString("schedule_startTime"));
					sdto.setSchedule_endTime(rs.getString("schedule_endTime"));
					sdto.setSchedule_cd(rs.getInt("schedule_cd"));
					
					scheduleList.add(sdto);
					System.out.println("??????????????"+sdto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return scheduleList;
		}
		
		// deleteSchedule
		public void deleteSchedule(int schedule_cd){
			try {
				con = getCon();

				sql = "delete from schedule where schedule_cd = ?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, schedule_cd);
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		// getScheduleDate(cd, class_cd)
		public List getScheduleDate(String cd, String class_cd){
			List scheduleDateList = new ArrayList();
			
			try {
				con = getCon();
				
				sql = "select s.schedule_startDate, s.schedule_endDate, s.schedule_startTime, s.schedule_endTime, s.schedule_cd "
						+ "from schedule s inner join class c "
						+ "on s.schedule_class_cd = c.class_cd "
						+ "inner join tutor t "
						+ "on c.class_tutor_cd = t.tutor_cd "
						+ "inner join member m "
						+ "on t.tutor_member_cd = m.member_cd "
						+ "where member_cd = ? and class_cd = ? "
						+ "order by schedule_startDate";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, cd);
				pstmt.setString(2, class_cd);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){

					scheduleDateList.add("'"+rs.getString("schedule_startDate")+"'");
					
					System.out.println("----------------------"+scheduleDateList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return scheduleDateList;
		}

		
}

