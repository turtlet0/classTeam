package dto;


public class ScheduleDTO {
	
	private String schedule_startDate;
	private String schedule_endDate;
	private String schedule_startTime;
	private String schedule_endTime;
	private String schedule_class_cd;
	private int schedule_cd;
	
	public int getSchedule_cd() {
		return schedule_cd;
	}
	public void setSchedule_cd(int schedule_cd) {
		this.schedule_cd = schedule_cd;
	}
	public String getSchedule_class_cd() {
		return schedule_class_cd;
	}
	public void setSchedule_class_cd(String schedule_class_cd) {
		this.schedule_class_cd = schedule_class_cd;
	}

	public String getSchedule_startDate() {
		return schedule_startDate;
	}
	public void setSchedule_startDate(String schedule_startDate) {
		this.schedule_startDate = schedule_startDate;
	}
	public String getSchedule_endDate() {
		return schedule_endDate;
	}
	public void setSchedule_endDate(String schedule_endDate) {
		this.schedule_endDate = schedule_endDate;
	}
	public String getSchedule_startTime() {
		return schedule_startTime;
	}
	public void setSchedule_startTime(String schedule_startTime) {
		this.schedule_startTime = schedule_startTime;
	}
	public String getSchedule_endTime() {
		return schedule_endTime;
	}
	public void setSchedule_endTime(String schedule_endTime) {
		this.schedule_endTime = schedule_endTime;
	}
	
	

	
	
	
	
	
	
	

}
