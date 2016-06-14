package com.ehuizhen.test.patient.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ehuizhen.weixin.dao.PatientDao;
import com.ehuizhen.weixin.model.PatientModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:servlet-context.xml"})
public class PatientDaoTest {
	
	 @Autowired
	 ApplicationContext ctx;
	 
	 @Autowired
	 private PatientDao dao;
	 
    @Test
    public void getPatientByPhoneNum(){
    	PatientDao service  = ctx.getBean(PatientDao.class);
        assertEquals("13186059398",service.getPatientByPhoneNum("13186059398").getPhoneNum());
    }
    
    @Test
    public void save() {
    	 PatientModel entity = new PatientModel("222", "222", "222", new Timestamp(System.currentTimeMillis()));
    	 dao.save(entity);
    }
    
}
