package com.latte.db;

import java.sql.Timestamp;

public class QnaDTO {
	private int qno;
	private int class_cd;
	private int member_cd;
	private String content;
	private int q_ref;
	private int q_lev;
	private int q_seq;
	private Timestamp reg_date;
	
	
	public int getClass_cd() {
		return class_cd;
	}
	public void setClass_cd(int class_cd) {
		this.class_cd = class_cd;
	}
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public int getMember_cd() {
		return member_cd;
	}
	public void setMember_cd(int member_cd) {
		this.member_cd = member_cd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getQ_ref() {
		return q_ref;
	}
	public void setQ_ref(int q_ref) {
		this.q_ref = q_ref;
	}
	public int getQ_lev() {
		return q_lev;
	}
	public void setQ_lev(int q_lev) {
		this.q_lev = q_lev;
	}
	public int getQ_seq() {
		return q_seq;
	}
	public void setQ_seq(int q_seq) {
		this.q_seq = q_seq;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "QnaDTO [qno=" + qno + ", class_cd=" + class_cd + ", member_cd=" + member_cd + ", content=" + content
				+ ", q_ref=" + q_ref + ", q_lev=" + q_lev + ", q_seq=" + q_seq + ", reg_date=" + reg_date + "]";
	}
	
}
