package com.latteclass.db;

public class ClassDTO {
	private int class_cd;				//클래스 코드
	private String title;				//클래스명
	private String class_rep_img;		//클래스 이미지
	private String category;			//카테고리
	private String subcategory;			//서브 카테고리
	private String sido;				//시·도
	private String price;				//가격
	
	
	public int getClass_cd() {
		return class_cd;
	}
	public void setClass_cd(int class_cd) {
		this.class_cd = class_cd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClass_rep_img() {
		return class_rep_img;
	}
	public void setClass_rep_img(String class_rep_img) {
		this.class_rep_img = class_rep_img;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ClassDTO [class_cd=" + class_cd + ", title=" + title + ", class_rep_img=" + class_rep_img
				+ ", category=" + category + ", subcategory=" + subcategory + ", sido=" + sido + ", price=" + price
				+ "]";
	}
	
	
	
	

}
