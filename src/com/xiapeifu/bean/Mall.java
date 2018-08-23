package com.xiapeifu.bean;

public class Mall {
	String id;
	String name;
	String discount;
	String localtion;
	String price;
	String number;
	public Mall() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Mall(String id, String name, String discount, String localtion,
			String price, String number) {
		super();
		this.id = id;
		this.name = name;
		this.discount = discount;
		this.localtion = localtion;
		this.price = price;
		this.number = number;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getLocaltion() {
		return localtion;
	}
	public void setLocaltion(String localtion) {
		this.localtion = localtion;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getnumber() {
		return number;
	}
	public void setnumber(String number) {
		this.number = number;
	}
	public String toString() {
		return "Mall [id=" + id + ", name=" + name + ", discount=" + discount
				+ ", localtion=" + localtion + ", price=" + price + ", number="
				+ number + "]";
	}
	
}
