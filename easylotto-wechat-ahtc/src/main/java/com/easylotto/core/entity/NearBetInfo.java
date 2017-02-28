package com.easylotto.core.entity;

import java.util.Date;

public class NearBetInfo {
	
private Long  int_rec_id;
private String vc_customer_name;
private Integer  int_customer_type; 
private String vc_address;
private String  vc_contact_name;
private String  vc_phone;
private Date  dt_create_time; 
private String vc_longitude;
private String  vc_latitude;
private String  vc_worker_name;
private String  vc_worker_phone;
private String  vc_enterprise_name;
private int distance;
public Long getInt_rec_id() {
	return int_rec_id;
}
public void setInt_rec_id(Long int_rec_id) {
	this.int_rec_id = int_rec_id;
}
public String getVc_customer_name() {
	return vc_customer_name;
}
public void setVc_customer_name(String vc_customer_name) {
	this.vc_customer_name = vc_customer_name;
}

public Integer getInt_customer_type() {
	return int_customer_type;
}
public void setInt_customer_type(Integer int_customer_type) {
	this.int_customer_type = int_customer_type;
}
public String getVc_address() {
	return vc_address;
}
public void setVc_address(String vc_address) {
	this.vc_address = vc_address;
}
public String getVc_contact_name() {
	return vc_contact_name;
}
public void setVc_contact_name(String vc_contact_name) {
	this.vc_contact_name = vc_contact_name;
}
public String getVc_phone() {
	return vc_phone;
}
public void setVc_phone(String vc_phone) {
	this.vc_phone = vc_phone;
}
public Date getDt_create_time() {
	return dt_create_time;
}
public void setDt_create_time(Date dt_create_time) {
	this.dt_create_time = dt_create_time;
}
public String getVc_longitude() {
	return vc_longitude;
}
public void setVc_longitude(String vc_longitude) {
	this.vc_longitude = vc_longitude;
}
public String getVc_latitude() {
	return vc_latitude;
}
public void setVc_latitude(String vc_latitude) {
	this.vc_latitude = vc_latitude;
}
public String getVc_worker_name() {
	return vc_worker_name;
}
public void setVc_worker_name(String vc_worker_name) {
	this.vc_worker_name = vc_worker_name;
}
public String getVc_worker_phone() {
	return vc_worker_phone;
}
public void setVc_worker_phone(String vc_worker_phone) {
	this.vc_worker_phone = vc_worker_phone;
}
public String getVc_enterprise_name() {
	return vc_enterprise_name;
}
public void setVc_enterprise_name(String vc_enterprise_name) {
	this.vc_enterprise_name = vc_enterprise_name;
}
public int getDistance() {
	return distance;
}
public void setDistance(int distance) {
	this.distance = distance;
}


}
