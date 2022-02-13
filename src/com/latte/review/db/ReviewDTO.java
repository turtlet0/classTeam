package com.latte.review.db;

import java.sql.Timestamp;

public class ReviewDTO {
	
	private int cno;
	private int class_cd;
	private int member_cd;
	private String rating;
	private String content;
	private int c_ref;
	private int c_lev;
	private int c_seq;
	private Timestamp reg_date;
	
	@Override
	public String toString() {
		return "ReviewDTO [cno=" + cno + ", class_cd=" + class_cd + ", member_cd=" + member_cd + ", rating=" + rating
				+ ", content=" + content + ", c_ref=" + c_ref + ", c_lev=" + c_lev + ", c_seq=" + c_seq + ", reg_date="
				+ reg_date + "]";
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
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
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getC_ref() {
		return c_ref;
	}
	public void setC_ref(int c_ref) {
		this.c_ref = c_ref;
	}
	public int getC_lev() {
		return c_lev;
	}
	public void setC_lev(int c_lev) {
		this.c_lev = c_lev;
	}
	public int getC_seq() {
		return c_seq;
	}
	public void setC_seq(int c_seq) {
		this.c_seq = c_seq;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
}
