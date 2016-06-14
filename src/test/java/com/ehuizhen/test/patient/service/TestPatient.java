package com.ehuizhen.test.patient.service;

import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.ehuizhen.weixin.dao.PatientDao;
import com.ehuizhen.weixin.model.DoctorModel;
import com.ehuizhen.weixin.service.DoctorService;
import com.ehuizhen.weixin.service.PatientService;

public class TestPatient {
	static org.slf4j.Logger log = LoggerFactory.getLogger(TestPatient.class);
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:servlet-context.xml");
		PatientDao dao = context.getBean(PatientDao.class);
		HibernateTemplate t = (HibernateTemplate)context.getBean("hibernateTemplate");
		
		PatientService service = context.getBean(PatientService.class);
		DoctorService doctorService = context.getBean(DoctorService.class);
		try {
			//PatientModel p = service.getPatientByWxOpenId("2");
			//System.out.println(p);
			
			DoctorModel model = doctorService.getDoctorByPhoneNum("13892771917");
			System.out.println(model);
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
