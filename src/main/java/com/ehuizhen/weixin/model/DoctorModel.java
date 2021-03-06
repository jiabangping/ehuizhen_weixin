package com.ehuizhen.weixin.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
* User generated by hbm2java
*/
@Entity
@Table(name = "Doctor")
public class DoctorModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7092894980120893689L;
	private Integer id;
	
	private String phoneNum;
	
	private String password;

	private char status;//'0':无效，'1':有效

	private String weixinAppId;
	
	private String doctorName;
	
	private int overSea;
	
	private String email;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "PhoneNum", nullable = false)
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	
	@Column(name = "Password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Status")
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Column(name = "weixinAppId", nullable = true)
	public String getWeixinAppId() {
		return weixinAppId;
	}

	public void setWeixinAppId(String weixinAppId) {
		this.weixinAppId = weixinAppId;
	}

	@Column(name = "DoctorName", nullable = true)
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	@Column(name = "OverSea", nullable = true)
	public int getOverSea() {
		return overSea;
	}

	public void setOverSea(int overSea) {
		this.overSea = overSea;
	}

	@Column(name = "Email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	private String Photo;
	
	@Column(name = "Photo", nullable = false)
	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}
	
	
	
	
}