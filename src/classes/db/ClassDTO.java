package classes.db;

public class ClassDTO {
	private String content;
	private String address;
	private String className;
	private String roadAddress;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	
	@Override
	public String toString() {
		return "ClassDTO [content=" + content + ", address=" + address + ", className=" + className + ", roadAddress="
				+ roadAddress + "]";
	}
	
	
		

}
