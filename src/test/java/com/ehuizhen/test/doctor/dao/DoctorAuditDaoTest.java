package com.ehuizhen.test.doctor.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ehuizhen.weixin.dao.DoctorAuditDao;
import com.ehuizhen.weixin.model.DoctorAuditModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml"})
public class DoctorAuditDaoTest {
	/**
	 * ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:servlet-context.xml");
		DoctorAuditDao dao = context.getBean(DoctorAuditDao.class);
		HibernateTemplate teplate = context.getBean(HibernateTemplate.class);
	 */
	
	
	static final Logger log = LoggerFactory.getLogger(DoctorAuditDaoTest.class);
	
	@Autowired
	ApplicationContext ctx;
	
	@Autowired
	DoctorAuditDao dao;
	
	@Autowired
	HibernateTemplate t;
	
	DoctorAuditModel save = new DoctorAuditModel("testDoctorName","testPhoneNum",1,"testIdCard","testHospital","testDept","testPostion","testSkill","testSelfInfo","testSelftPhotoUrl",1,1,"testWexinOpenId",new Timestamp(System.currentTimeMillis()));
	
	
	@Test
	public void getDoctorAuditByDoctorName(){
		try {
			assertEquals("testDoctorName",dao.getDoctorAuditByDoctorName("testDoctorName").getDoctorName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
