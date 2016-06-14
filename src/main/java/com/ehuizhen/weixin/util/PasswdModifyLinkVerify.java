package com.ehuizhen.weixin.util;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehuizhen.weixin.init.ServerConfigConst;

public class PasswdModifyLinkVerify extends TimerTask {

	private static final Logger log = LoggerFactory.getLogger(PasswdModifyLinkVerify.class);
	private String key;//分+randomNum
	private int doctorId;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	public PasswdModifyLinkVerify(String key, int doctorId) {
		this.key = key;
		this.doctorId = doctorId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + doctorId;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswdModifyLinkVerify other = (PasswdModifyLinkVerify) obj;
		if (doctorId != other.doctorId)
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PasswdModifyLinkVerify [key=" + key + ", doctorId=" + doctorId + "]";
	}
	
	@Override
	public void run() {
		log.error("定时删除【"+this.toString()+"】");
		PasswdModifyLinkValidPeriodUtil.getInstance().remove(this.getKey());
		try {
			ServerConfigConst.doctorService.deletePasswdModifyLinkToken(this.getKey());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
