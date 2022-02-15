package classes.db;

import java.sql.Timestamp;

/**
 * @author lju06
 *
 */
public class ClassDTO {
	private int class_cd;
	
	private int tutor_cd;
	
	private String title;
	private String introduce;
	private String category;
	private String sub_category;
	private String tutor_introduce;
	
	private String content;
	private String free_curriculum;
	private String duration_time;
	private String step1;
	private String step2;
	private String step3;
	private String step4;
	private String step5;
	private String recommend_people;
	private String tag;
	
	private String opening_area;
	private String postcode;
	private String road_address;
	private String jibun_address;
	private String detail_address;
	private String sido;
	private String sigungu;
	private String bname;
	private String directions;
	private String convenience_option;
	private String health_safety_option;
	
	private int approval_condition; // NN default 0
	private Timestamp reg_date; // NN NOW()
	
	
	public int getClass_cd() {
		return class_cd;
	}
	public void setClass_cd(int class_cd) {
		this.class_cd = class_cd;
	}
	public int getTutor_cd() {
		return tutor_cd;
	}
	public void setTutor_cd(int tutor_cd) {
		this.tutor_cd = tutor_cd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSub_category() {
		return sub_category;
	}
	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}
	public String getTutor_introduce() {
		return tutor_introduce;
	}
	public void setTutor_introduce(String tutor_introduce) {
		this.tutor_introduce = tutor_introduce;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFree_curriculum() {
		return free_curriculum;
	}
	public void setFree_curriculum(String free_curriculum) {
		this.free_curriculum = free_curriculum;
	}
	public String getDuration_time() {
		return duration_time;
	}
	public void setDuration_time(String duration_time) {
		this.duration_time = duration_time;
	}
	public String getStep1() {
		return step1;
	}
	public void setStep1(String step1) {
		this.step1 = step1;
	}
	public String getStep2() {
		return step2;
	}
	public void setStep2(String step2) {
		this.step2 = step2;
	}
	public String getStep3() {
		return step3;
	}
	public void setStep3(String step3) {
		this.step3 = step3;
	}
	public String getStep4() {
		return step4;
	}
	public void setStep4(String step4) {
		this.step4 = step4;
	}
	public String getStep5() {
		return step5;
	}
	public void setStep5(String step5) {
		this.step5 = step5;
	}
	public String getRecommend_people() {
		return recommend_people;
	}
	public void setRecommend_people(String recommend_people) {
		this.recommend_people = recommend_people;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getOpening_area() {
		return opening_area;
	}
	public void setOpening_area(String opening_area) {
		this.opening_area = opening_area;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getRoad_address() {
		return road_address;
	}
	public void setRoad_address(String road_address) {
		this.road_address = road_address;
	}
	public String getJibun_address() {
		return jibun_address;
	}
	public void setJibun_address(String jibun_address) {
		this.jibun_address = jibun_address;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public String getConvenience_option() {
		return convenience_option;
	}
	public void setConvenience_option(String convenience_option) {
		this.convenience_option = convenience_option;
	}
	public String getHealth_safety_option() {
		return health_safety_option;
	}
	public void setHealth_safety_option(String health_safety_option) {
		this.health_safety_option = health_safety_option;
	}
	public int getApproval_condition() {
		return approval_condition;
	}
	public void setApproval_condition(int approval_condition) {
		this.approval_condition = approval_condition;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "ClassDTO [class_cd=" + class_cd + ", tutor_cd=" + tutor_cd + ", title=" + title + ", introduce="
				+ introduce + ", category=" + category + ", sub_category=" + sub_category + ", tutor_introduce="
				+ tutor_introduce + ", content=" + content + ", free_curriculum=" + free_curriculum + ", duration_time="
				+ duration_time + ", step1=" + step1 + ", step2=" + step2 + ", step3=" + step3 + ", step4=" + step4
				+ ", step5=" + step5 + ", recommend_people=" + recommend_people + ", tag=" + tag + ", opening_area="
				+ opening_area + ", postcode=" + postcode + ", road_address=" + road_address + ", jibun_address="
				+ jibun_address + ", detail_address=" + detail_address + ", sido=" + sido + ", sigungu=" + sigungu
				+ ", bname=" + bname + ", directions=" + directions + ", convenience_option=" + convenience_option
				+ ", health_safety_option=" + health_safety_option + ", approval_condition=" + approval_condition
				+ ", reg_date=" + reg_date + "]";
	}
	
	
	
	
	
	
		

}
