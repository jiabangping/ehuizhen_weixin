package com.ehuizhen.test.doctor.service;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ehuizhen.weixin.model.DoctorAuditModel;
import com.ehuizhen.weixin.model.DoctorModel;
import com.ehuizhen.weixin.model.DoctorPasswdModifyLinkToken;
import com.ehuizhen.weixin.service.DoctorAuditService;
import com.ehuizhen.weixin.service.DoctorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml"})
public class DoctorServiceTest {
	/**
	 * ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:servlet-context.xml");
		DoctorAuditDao dao = context.getBean(DoctorAuditDao.class);
		HibernateTemplate teplate = context.getBean(HibernateTemplate.class);
	 */
	
	static final Logger log = LoggerFactory.getLogger(DoctorServiceTest.class);
	
	@Autowired
	ApplicationContext ctx;
	
	@Autowired
	DoctorAuditService doctorAuditService;
	
	@Autowired
	HibernateTemplate t;
	
	DoctorAuditModel model = new DoctorAuditModel("testDoctorName","testPhoneNum",1,"testIdCard","testHospital","testDept","testPostion","testSkill","testSelfInfo","testSelftPhotoUrl",1,1,"testWexinOpenId",new Timestamp(System.currentTimeMillis()));
	
	@Autowired
	DoctorService doctorService;
	@Test
	public void save(){
		try {
			int id = doctorAuditService.save(model);
			DoctorAuditModel actual = doctorAuditService.getDoctorAuditById(id);
			assertEquals(id, actual.getId());
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	@Test
	public void getDoctorAuditByDoctorName(){
		try {
			assertEquals(model.getDoctorName(),doctorAuditService.getDoctorAuditByDoctorName("testDoctorName").getDoctorName());
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	
	@Test
	public void getDoctorByPhoneNum(){
		try {
			DoctorModel model = doctorService.getDoctorByPhoneNum("13892771917"); 
			log.error(model.getPhoneNum());
			if(model.getStatus()  == '1') {
				log.error("ok");
			} 
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	
	@Test
	public void getDoctorByWxOpenId(){
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:servlet-context.xml");
			DoctorService doctorService2 = context.getBean(DoctorService.class);
			
			DoctorModel model = doctorService2.getDoctorByWxOpenId("1");
			log.error(model.toString());
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	

	public static void main(String[] args) {
		
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:servlet-context.xml");
			/*DoctorAuditService doctorService2 = context.getBean(DoctorAuditService.class);
			
			DoctorAuditModel model = doctorService2.getDoctorAuditByWxId("ox39CwaThU6vFJA6zwtrFfzirGWA");
			log.error(model.toString());
			
			doctorService2.delete(model);*/
			
			DoctorService s = context.getBean(DoctorService.class);
			//s.savePasswdLinkToken(new DoctorPasswdModifyLinkToken("11", 1));
			
			List<DoctorPasswdModifyLinkToken> list = s.getPasswdLinkTokenList();
			
			//s.deletePasswdModifyLinkToken("11");
			System.out.println(1);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	
}
