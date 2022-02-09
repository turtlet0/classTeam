package dto;

import java.sql.Timestamp;

public class PaymentDTO {
	
	
	private String payment_cd;
	private String payment_member_cd;
	private String payment_class_cd;
	private int price;
	private String pay;
	private String class_name;
	private Timestamp payment_date;
	

	public Timestamp getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Timestamp payment_date) {
		this.payment_date = payment_date;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPayment_cd() {
		return payment_cd;
	}
	public void setPayment_cd(String payment_cd) {
		this.payment_cd = payment_cd;
	}
	public String getPayment_member_cd() {
		return payment_member_cd;
	}
	public void setPayment_member_cd(String payment_member_cd) {
		this.payment_member_cd = payment_member_cd;
	}
	public String getPayment_class_cd() {
		return payment_class_cd;
	}
	public void setPayment_class_cd(String payment_class_cd) {
		this.payment_class_cd = payment_class_cd;
	}
	
	
	
}
