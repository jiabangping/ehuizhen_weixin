package com.ehuizhen.weixin.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ehuizhen.weixin.model.DoctorModel;
import com.ehuizhen.weixin.model.PatientModel;

@Repository
public class PatientDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public int save(PatientModel model) {
		return (int)hibernateTemplate.save(model);
	} 
	
	public PatientModel getPatientDetail(int patientId) {
		return hibernateTemplate.get(PatientModel.class, patientId);
	}
	
	public PatientModel getPatientByPatientName(final String patientName) {
		PatientModel model = hibernateTemplate.execute(new HibernateCallback<PatientModel>() {

			@Override
			public PatientModel doInHibernate(Session session) throws HibernateException {
				Query query = session
						.createQuery("FROM PatientModel WHERE 1=1 and status = 1 and PatientName =:PatientName ");
				query.setParameter("PatientName", patientName);
				return (PatientModel) query.uniqueResult();
			}
			
		});
		return model;
	}
	
	public PatientModel getPatientByPhoneNum(final String phoneNum) {
		PatientModel model = hibernateTemplate.execute(new HibernateCallback<PatientModel>() {

			@Override
			public PatientModel doInHibernate(Session session) throws HibernateException {
				Query query = session
						.createQuery("FROM PatientModel WHERE 1=1 and status = 1 and phoneNum =:phoneNum ");
				query.setParameter("phoneNum", phoneNum);
				return (PatientModel) query.uniqueResult();
			}
			
		});
		return model;
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public void update(PatientModel model) throws Exception {
		try{
			hibernateTemplate.update(model);
		}catch(Exception e) {
			throw e;
		}
	}
	
	
	public PatientModel getPatientByWxOpenId(final String openId) throws Exception {
		try{
			PatientModel model = hibernateTemplate.execute(new HibernateCallback<PatientModel>() {
				@Override
				public PatientModel doInHibernate(Session session) throws HibernateException {
					Query query = session
							.createQuery("FROM PatientModel WHERE 1=1 and status = 1 and bindStatus = 1 and openId =:openId ");
					query.setParameter("openId", openId);
					return (PatientModel) query.uniqueResult();
				}
			});
			return model;
		}catch(Exception e) {
			throw e;
		}
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public void delete(PatientModel model) throws Exception {
		try{
			hibernateTemplate.delete(model);
		}catch(Exception e) {
			throw e;
		}
	}

}
