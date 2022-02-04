package com.test.tclass.model;

public class ClassDTO {
	private String classCd;
	private String className;
	private String classImgFile;
	private String classCategory;
	private String roadAddress;
	private String price;
	
	public String getClassCd() {
		return classCd;
	}
	public void setClassCd(String classCd) {
		this.classCd = classCd;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassImgFile() {
		return classImgFile;
	}
	public void setClassImgFile(String classImgFile) {
		this.classImgFile = classImgFile;
	}
	public String getClassCategory() {
		return classCategory;
	}
	public void setClassCategory(String classCategory) {
		this.classCategory = classCategory;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ClassDTO [classCd=" + classCd + ", className=" + className + ", classImgFile=" + classImgFile
				+ ", classCategory=" + classCategory + ", roadAddress=" + roadAddress + ", price=" + price + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
