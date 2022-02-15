package com.latteclass.member.db;

import java.sql.Timestamp;

public class MemberDTO {
	private int member_cd;
	private String email;
	private String nick_name;
	private String password;
	private String sns_id;
	private int phone_num;
	private Timestamp join_date;

	public int getMember_cd() {
		return member_cd;
	}
	public void setMember_cd(int member_cd) {
		this.member_cd = member_cd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSns_id() {
		return sns_id;
	}
	public void setSns_id(String sns_id) {
		this.sns_id = sns_id;
	}
	public int getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(int phone_num) {
		this.phone_num = phone_num;
	}
	public Timestamp getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Timestamp join_date) {
		this.join_date = join_date;
	}
	@Override
	public String toString() {
		return "MemberDTO [member_cd=" + member_cd + ", email=" + email + ", nick_name=" + nick_name + ", password="
				+ password + ", sns_id=" + sns_id + ", phone_num=" + phone_num + ", join_date=" + join_date + "]";
	}
	
	

	
	
	
}
