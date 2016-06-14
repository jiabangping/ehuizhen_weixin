package com.ehuizhen.weixin.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ehuizhen.weixin.model.SmsModel;

@Repository
public class SmsDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	public List<SmsModel>  queryByMobile(final String mobile,final int pageSize,final int pageIndex) {
		List<SmsModel> list = hibernateTemplate.execute(new HibernateCallback<List<SmsModel>>() {

			@Override
			public List<SmsModel>  doInHibernate(Session session) throws HibernateException {
				Query query = session
						.createQuery("FROM SmsModel WHERE mobile =:mobile AND type=3 and status =1 ");
				query.setParameter("mobile", mobile);
				if (pageIndex > 0) {
					query.setFirstResult((pageIndex - 1) * pageSize);
					query.setMaxResults(pageSize);
				}
				return query.list();
			}
		});
		return list;
	}

}
