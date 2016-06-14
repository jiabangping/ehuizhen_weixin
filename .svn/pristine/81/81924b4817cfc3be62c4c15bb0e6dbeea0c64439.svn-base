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

import com.ehuizhen.weixin.model.DoctorAuditModel;
import com.ehuizhen.weixin.model.WeiXinAccountModel;

@Repository
public class WxAccountDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * @param doctorAuditId  auto_increment ID
	 * @return
	 */
	public WeiXinAccountModel getById(int id) throws Exception {
		try {
			return hibernateTemplate.get(WeiXinAccountModel.class, id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public int save(WeiXinAccountModel model) throws Exception {
		try {
			model.setStatus(1);
			return (int)hibernateTemplate.save(model);
		}catch(Exception e) {
			throw e;
		}
	} 
	
	
	
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public boolean update(WeiXinAccountModel model) throws Exception {
		try{
			hibernateTemplate.update(model);
			return true;
		}catch(Exception e) {
			throw e;
		}
	}

	/**
	 * 根据weixinOpenId查询
	 * @param doctorName
	 * @return
	 */
	public WeiXinAccountModel getByWxOpenId(final String wxOpenId) throws Exception {
		try {
			WeiXinAccountModel model = hibernateTemplate.execute(new HibernateCallback<WeiXinAccountModel>() {

				@Override
				public WeiXinAccountModel doInHibernate(Session session) throws HibernateException {
					Query query = session
							.createQuery("FROM WeiXinAccountModel WHERE wxOpenId =:wxOpenId ");
					query.setParameter("wxOpenId", wxOpenId);
					return (WeiXinAccountModel) query.uniqueResult();
				}
			});
			return model;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
