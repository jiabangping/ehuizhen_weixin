package com.ehuizhen.weixin.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuizhen.weixin.config.JsonConst;
import com.ehuizhen.weixin.config.WeixinBasicKit;
import com.ehuizhen.weixin.dao.PatientDao;
import com.ehuizhen.weixin.init.ServerConfigConst;
import com.ehuizhen.weixin.model.PatientModel;
import com.ehuizhen.weixin.tools.MD5;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class PatientService {
	private static Logger log = LoggerFactory.getLogger(PatientService.class);
	@Autowired
	private PatientDao patientDao;
	
	public int save(PatientModel model) {
		model.setBindStatus(0);
		model.setStatus(1);;
		String md5Ps = MD5.encodeByMD5(model.getPassWord());
		log.info("Patient设置密码  MD5 password : " + md5Ps+","+model);
		model.setPassWord(md5Ps);
		return patientDao.save(model);
	}
	
	public PatientModel getPatientDetail(int patientId) {
		return patientDao.getPatientDetail(patientId);
	}
	
	public PatientModel getPatientByPatientName(String patientName) {
		return patientDao.getPatientByPatientName(patientName);
	}
	
	public PatientModel getPatientByPhoneNum(String phoneNum) {
		return patientDao.getPatientByPhoneNum(phoneNum);
	}
	
	public void update(PatientModel model) throws Exception {
		try {
			patientDao.update(model);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public PatientModel getPatientByWxOpenId(String openId) throws Exception {
		try {
			if(null == openId || "".equals(openId)) {
				return null;
			}
			return patientDao.getPatientByWxOpenId(openId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public void delete(PatientModel model) throws Exception {
		try {
			patientDao.delete(model);
		} catch (Exception e) {
			throw e;
		}
	}
static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public String getConsultationDataByPhoneNum(String phoneNum,int currentPage) throws Exception {
		String strResult = "";
//		String url = ServerConfigConst.baseApiUrl+"api/v1/init_v2";
		String url = ServerConfigConst.baseApiUrl+ServerConfigConst.patientConsultationUrl+"?currentPage="+currentPage;
		url = url.replace(ServerConfigConst.PHONENUM, phoneNum);
		
		strResult = WeixinBasicKit.sendGet(url);
	
		if(null != strResult && !strResult.isEmpty()) {
			strResult = strResult.replaceAll("null", "\"\"");
			JSONArray arr = JSONArray.fromObject(strResult);
			if(arr != null) {
				for(int i=0;i<arr.size();i++) {
					JSONObject obj = arr.getJSONObject(i);
					String inDate = obj.getString("inDate");
					String inDateStr = format.format(new Date(Long.parseLong(inDate)));
					obj.put("inDateStr", inDateStr);
				}
				String pre = "{\"result\": \"success\",\"data\":";
				String result =  pre+arr.toString()+"}";
				return result;
			}else {
				JSONObject result = new JSONObject();
				result.put(JsonConst.result, JsonConst.fail);
				return result.toString();
			}
		}
		JSONObject result = new JSONObject();
		result.put(JsonConst.result, JsonConst.fail);
		return result.toString();
	}
}
