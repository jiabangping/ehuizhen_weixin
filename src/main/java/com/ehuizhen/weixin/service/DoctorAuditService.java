package com.ehuizhen.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuizhen.weixin.dao.DoctorAuditDao;
import com.ehuizhen.weixin.model.DoctorAuditModel;

@Service
public class DoctorAuditService {
	
	@Autowired
	private DoctorAuditDao doctorAuditDao;
//	private static final Logger log = LoggerFactory.getLogger(DoctorAuditService.class);
	
	/**
	 * @param phoneNum
	 * @return
	 */
	public DoctorAuditModel getDoctorAuditByPhoneNum(String phoneNum) throws Exception {
		try {
			return doctorAuditDao.getDoctorAuditByPhoneNum(phoneNum);
		}catch (Exception e) {
			throw e;
		}
	}
	
	public DoctorAuditModel getDoctorAuditByKey(String field,Object v) throws Exception {
		try {
			return doctorAuditDao.getDoctorAuditByKey(field,v);
		}catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * @param doctorName
	 * @return
	 */
	public DoctorAuditModel getDoctorAuditByDoctorName(String doctorName) throws Exception {
		try {
			return doctorAuditDao.getDoctorAuditByDoctorName(doctorName);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @param doctorName
	 * @return
	 */
	public DoctorAuditModel getDoctorAuditById(int id) throws Exception {
		try {
			return doctorAuditDao.getDoctorAuditById(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @return
	 */
	public int save(DoctorAuditModel model) throws Exception {
		try {
			return doctorAuditDao.save(model);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * @return
	 */
	public boolean update(DoctorAuditModel model) throws Exception {
		try {
			return doctorAuditDao.update(model);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * @param wxId
	 * @return
	 */
	public DoctorAuditModel getDoctorAuditByWxId(String wxId) throws Exception {
		try {
			return doctorAuditDao.getDoctorAuditByWxId(wxId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @param wxId
	 * @return
	 */
	public void delete(DoctorAuditModel model) throws Exception {
		try {
			doctorAuditDao.delete(model);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
