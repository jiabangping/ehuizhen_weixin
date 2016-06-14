package com.ehuizhen.weixin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ehuizhen.weixin.model.MenuTest;

@Repository
public class MenuDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public void save(MenuTest m) {
		hibernateTemplate.save(m);
	}
	
	public MenuTest get(int id) {
		return hibernateTemplate.get(MenuTest.class, id);
	}
}
