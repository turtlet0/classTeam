package file.db;

public class FileDTO {
	private int class_cd;
	private int member_cd;
	private String img_idx;
	private String img_name;
	private String img_orig_name;
	private String img_upload_path;
	
	public int getClass_cd() {
		return class_cd;
	}
	public void setClass_cd(int class_cd) {
		this.class_cd = class_cd;
	}
	public int getMember_cd() {
		return member_cd;
	}
	public void setMember_cd(int member_cd) {
		this.member_cd = member_cd;
	}
	public String getImg_idx() {
		return img_idx;
	}
	public void setImg_idx(String img_idx) {
		this.img_idx = img_idx;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public String getImg_orig_name() {
		return img_orig_name;
	}
	public void setImg_orig_name(String img_orig_name) {
		this.img_orig_name = img_orig_name;
	}
	public String getImg_upload_path() {
		return img_upload_path;
	}
	public void setImg_upload_path(String img_upload_path) {
		this.img_upload_path = img_upload_path;
	}
	
	@Override
	public String toString() {
		return "FileDTO [class_cd=" + class_cd + ", member_cd=" + member_cd + ", img_idx=" + img_idx + ", img_name="
				+ img_name + ", img_orig_name=" + img_orig_name + ", img_upload_path=" + img_upload_path + "]";
	}
	
	
	
	
	
	
	
}
