package com.ehuizhen.test.patient.service;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ehuizhen.weixin.model.PatientModel;
import com.ehuizhen.weixin.service.PatientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:servlet-context.xml"})
public class PatientServiceTest {
	static Logger log = LoggerFactory.getLogger(PatientServiceTest.class);
	@Autowired
	private ApplicationContext ctx;
	    
	@Autowired
	private PatientService service;
	
    @Test
    public void getPatientByPhoneNum(){
       assertEquals("13991326043",service.getPatientByPhoneNum("13991326043").getPhoneNum());
    }
    
    @Test
    public void save() {
    	 PatientModel entity = new PatientModel("222", "222", "222", new Timestamp(System.currentTimeMillis()));
   		 service.save(entity);
    }
   

	@Test
    public void getPatientByWxOpenId(String openId) {
		
	}
    
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:servlet-context.xml");
		PatientService service2 = context.getBean(PatientService.class);
    	PatientModel model;
		try {
			model = service2.getPatientByWxOpenId("ox39CwaThU6vFJA6zwtrFfzirGWA");
			log.error(model.toString());
			service2.delete(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

