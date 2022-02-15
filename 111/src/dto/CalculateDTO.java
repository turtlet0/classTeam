package dto;

public class CalculateDTO {
	
	// 정산 DTO 조문주
	
	private String calculate_cd;
	private String calculate_tutor_cd;
	private String calculate_class_cd;
	
	public String getCalculate_cd() {
		return calculate_cd;
	}
	public void setCalculate_cd(String calculate_cd) {
		this.calculate_cd = calculate_cd;
	}
	public String getCalculate_tutor_cd() {
		return calculate_tutor_cd;
	}
	public void setCalculate_tutor_cd(String calculate_tutor_cd) {
		this.calculate_tutor_cd = calculate_tutor_cd;
	}
	public String getCalculate_class_cd() {
		return calculate_class_cd;
	}
	public void setCalculate_class_cd(String calculate_class_cd) {
		this.calculate_class_cd = calculate_class_cd;
	}
	
	
}
