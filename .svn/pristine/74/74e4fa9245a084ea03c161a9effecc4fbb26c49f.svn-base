package com.ehuizhen.test.doctor.photoDeal;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ehuizhen.weixin.dao.DoctorDao;
import com.ehuizhen.weixin.model.DoctorModel;

public class DefaultPhoto {
	static Logger log = LoggerFactory.getLogger(DefaultPhoto.class);
	static String basePhotoUrl = "http://testimage.zhongyinginfo.com:81/DoctorHeader/"; 
	//http://testimage.zhongyinginfo.com:81/DoctorHeader/default.jpg?t=1463470096000
	public static void main(String[] args) {
		//14848.jpg
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:servlet-context.xml");
		DoctorDao dao = context.getBean(DoctorDao.class);
		
		//HibernateTemplate t = (HibernateTemplate)context.getBean("hibernateTemplate");
		
		try {
			List<DoctorModel> list = dao.getDoctors();
			log.error("list.size="+list.size());
			int index = 0;
			for(DoctorModel model : list) {
				log.error("index="+(index++));
				String photoUrl =  basePhotoUrl+model.getPhoto();
				if(photoUrl != null && !"".equals(photoUrl)) {
					if(sendGet(photoUrl)) {
						model.setPhoto("default.jpg");
						dao.update(model);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static boolean sendGet(String url) throws Exception {
		HttpGet get = null;
		CloseableHttpResponse resp = null;
		CloseableHttpClient client = null;
		try {
			client = HttpClients.createDefault();
			get = new HttpGet(url);
			resp = client.execute(get);
			int statusCode = resp.getStatusLine().getStatusCode();
		/*	if(statusCode>=200&&statusCode<300) {
				HttpEntity entity = resp.getEntity();
				String content = EntityUtils.toString(entity,"utf-8");
				return content;
			}*/
			if(statusCode == 404) {
				return true;
			}
			
		} catch (Exception e) {
//			log.error(e.getMessage(),e);
			throw e;
		} finally {
			try {
				if(resp!=null) resp.close();
				if(client!=null) client.close();
			} catch (IOException e) {
				throw e;
			}
		}
		return false;
	}
}
