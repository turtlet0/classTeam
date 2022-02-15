package dto;

public class LikesDTO {
	
	
	private int likes_cd;
	private String likes_class_cd;
	private String likes_member_cd;
	private String likes_class_name;
	private String likes_class_date;
	
	public String getLikes_class_date() {
		return likes_class_date;
	}
	public void setLikes_class_date(String likes_class_date) {
		this.likes_class_date = likes_class_date;
	}
	public String getLikes_class_name() {
		return likes_class_name;
	}
	public void setLikes_class_name(String likes_class_name) {
		this.likes_class_name = likes_class_name;
	}
	public int getLikes_cd() {
		return likes_cd;
	}
	public void setLikes_cd(int likes_cd) {
		this.likes_cd = likes_cd;
	}
	public String getLikes_class_cd() {
		return likes_class_cd;
	}
	public void setLikes_class_cd(String likes_class_cd) {
		this.likes_class_cd = likes_class_cd;
	}
	public String getLikes_member_cd() {
		return likes_member_cd;
	}
	public void setLikes_member_cd(String likes_member_cd) {
		this.likes_member_cd = likes_member_cd;
	}
	
	
	
	
}
