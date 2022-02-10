package classes.db;

public class TutorDTO {
	private int tutor_cd;
	
	private String company_name;
	private String instagram;
	private String blog;
	private String youtube;
	
	private int member_cd;

	public int getTutor_cd() {
		return tutor_cd;
	}

	public void setTutor_cd(int tutor_cd) {
		this.tutor_cd = tutor_cd;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public int getMember_cd() {
		return member_cd;
	}

	public void setMember_cd(int member_cd) {
		this.member_cd = member_cd;
	}

	@Override
	public String toString() {
		return "TutorDTO [tutor_cd=" + tutor_cd + ", company_name=" + company_name + ", instagram=" + instagram
				+ ", blog=" + blog + ", youtube=" + youtube + ", member_cd=" + member_cd + "]";
	}
	
	
}
