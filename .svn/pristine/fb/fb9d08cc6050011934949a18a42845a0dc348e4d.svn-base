package com.ehuizhen.weixin.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "WeiXinAccount")
public class WeiXinAccountModel implements java.io.Serializable {

	
	private static final long serialVersionUID = 8151130661206418277L;
	private int id;
	private int status;//0:未关注，1:关注,
	private String wxOpenId;
	
	//private int patientId;//患者账号Id default 0:未绑定
	private PatientModel patient;
	
	
	//private int doctorId;//医生账号 Id default 0:未绑定
	private DoctorModel doctor;
	
	
	private Date subscribeDate;//关注日期
	
	private Date unSubscribeDate;//取消关注日期
	
	
	private Date patientAccountBindDate;//患者账号绑定时间
	
	private Date doctorAccountBindDate;//医生账号绑定时间
	
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@Column(name = "Status", nullable = false,length=11)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "WxOpenId", nullable = false)
	public String getWxOpenId() {
		return this.wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}
	
	
	@OneToOne
	@JoinColumn(name="PatientId")
	public PatientModel getPatient() {
		return patient;
	}

	public void setPatient(PatientModel patient) {
		this.patient = patient;
	}
	
	
	@OneToOne
	@JoinColumn(name="DoctorId")
	public DoctorModel getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorModel doctor) {
		this.doctor = doctor;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SubscribeDate", nullable = true)
	public Date getSubscribeDate() {
		return this.subscribeDate;
	}

	public void setSubscribeDate(Date subscribeDate) {
		this.subscribeDate = subscribeDate;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UnSubscribeDate", nullable = true)
	public Date getUnSubscribeDate() {
		return this.unSubscribeDate;
	}

	public void setUnSubscribeDate(Date unSubscribeDate) {
		this.unSubscribeDate = unSubscribeDate;
	}

	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PatientAccountBindDate", nullable = true)
	public Date getPatientAccountBindDate() {
		return this.patientAccountBindDate;
	}

	public void setPatientAccountBindDate(Date patientAccountBindDate) {
		this.patientAccountBindDate = patientAccountBindDate;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DoctorAccountBindDate", nullable = true)
	public Date getDoctorAccountBindDate() {
		return this.doctorAccountBindDate;
	}

	public void setDoctorAccountBindDate(Date doctorAccountBindDate) {
		this.unSubscribeDate = doctorAccountBindDate;
	}
	
	
	public WeiXinAccountModel() {
	}
	
	public WeiXinAccountModel(String wxOpenId) {
		super();
		this.wxOpenId = wxOpenId;
	}

	public WeiXinAccountModel(int status, String wxOpenId, PatientModel patient) {
		super();
		this.status = status;
		this.wxOpenId = wxOpenId;
		this.patient = patient;
	}

	public WeiXinAccountModel(int id, int status, String wxOpenId, PatientModel patient, Date subscribeDate,
			Date unSubscribeDate, Date patientAccountBindDate, Date doctorAccountBindDate) {
		super();
		this.id = id;
		this.status = status;
		this.wxOpenId = wxOpenId;
		this.patient = patient;
		this.subscribeDate = subscribeDate;
		this.unSubscribeDate = unSubscribeDate;
		this.patientAccountBindDate = patientAccountBindDate;
		this.doctorAccountBindDate = doctorAccountBindDate;
	}

	public WeiXinAccountModel(int id, int status, String wxOpenId, PatientModel patient) {
		super();
		this.id = id;
		this.status = status;
		this.wxOpenId = wxOpenId;
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "WeiXinAccountModel [id=" + id + ", status=" + status + ", wxOpenId=" + wxOpenId + ", patient=" + patient
				+ ", doctor=" + doctor + ", subscribeDate=" + subscribeDate + ", unSubscribeDate=" + unSubscribeDate
				+ ", patientAccountBindDate=" + patientAccountBindDate + ", doctorAccountBindDate="
				+ doctorAccountBindDate + "]";
	}

	
	
	
}
