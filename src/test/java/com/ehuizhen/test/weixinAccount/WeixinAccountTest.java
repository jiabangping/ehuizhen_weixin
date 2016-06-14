package com.ehuizhen.test.weixinAccount;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.ehuizhen.weixin.dao.WxAccountDao;
import com.ehuizhen.weixin.model.WeiXinAccountModel;

public class WeixinAccountTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:servlet-context.xml");
		HibernateTemplate t = (HibernateTemplate)context.getBean("hibernateTemplate");
		
		try {
			WxAccountDao dao = context.getBean(WxAccountDao.class);
			WeiXinAccountModel model = new WeiXinAccountModel("123456");
			model.setSubscribeDate(new Date());
			
			dao.save(model);
			
			WeiXinAccountModel model2 = dao.getByWxOpenId("123456");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
