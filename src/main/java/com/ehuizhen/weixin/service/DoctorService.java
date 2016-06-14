package com.ehuizhen.weixin.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuizhen.weixin.config.JsonConst;
import com.ehuizhen.weixin.config.WeixinBasicKit;
import com.ehuizhen.weixin.dao.DoctorDao;
import com.ehuizhen.weixin.init.ServerConfigConst;
import com.ehuizhen.weixin.model.DoctorModel;
import com.ehuizhen.weixin.model.DoctorPasswdModifyLinkToken;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class DoctorService { 
	
	@Autowired
	private DoctorDao doctorDao;
	
	private static final Logger log = LoggerFactory.getLogger(DoctorService.class);
	
	public String getInitData() throws Exception {
		
		String strResult = "";
//		String url = ServerConfigConst.baseApiUrl+"api/v1/init_v2";
		String url = ServerConfigConst.baseApiUrl+ServerConfigConst.doctorInitDataUrl;
		
		strResult = WeixinBasicKit.sendGet(url);
	
		if(null != strResult && !strResult.isEmpty()) {
			strResult = strResult.replaceAll("null", "\"\"");
			JSONObject jsonResult = JSONObject.fromObject(strResult);
			if(jsonResult != null) {
				jsonResult.put(JsonConst.result, JsonConst.success);
				return jsonResult.toString();
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
	
	
	public String getHospitalsByCity(int cityId) throws Exception {
		
		String strResult = "";
//		String url = ServerConfigConst.baseApiUrl+"api/v1/init_v2";
		String url = ServerConfigConst.baseApiUrl+ServerConfigConst.getHospitalsByCityUrl;
		url = url.replace(ServerConfigConst.CITYID, String.valueOf(cityId));
		
		strResult = WeixinBasicKit.sendGet(url);
	
		if(null != strResult && !strResult.isEmpty()) {
			strResult = strResult.replaceAll("null", "\"\"");
			JSONArray arr = JSONArray.fromObject(strResult);
			if(arr != null) {
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
	
	
	
	
	
	
	public String getDoctors(int regionId,int hospitalId,int departmentId,int currentPage){ 
		String strResult = "";
		String url = "";
		if(hospitalId != -1 ) {//选了医院,根据医院Id+科室Id查
			url = ServerConfigConst.baseApiUrl+ServerConfigConst.queryByHospitalIdAndDepartmentIdUrl;
			url = url.replace(ServerConfigConst.HOSPITALID, String.valueOf(hospitalId));
			url = url.replace(ServerConfigConst.DEPARTMENTID, String.valueOf(departmentId));
			url = url.replace(ServerConfigConst.CURRENTPAGE, String.valueOf(currentPage));
		}else {//根据区域Id+科室Id查询
			url = ServerConfigConst.baseApiUrl+ServerConfigConst.queryByRegionIDAndDepartmentIdUrl;
			url = url.replace(ServerConfigConst.REGIONID, String.valueOf(regionId));
			url = url.replace(ServerConfigConst.DEPARTMENTID, String.valueOf(departmentId));
			url = url.replace(ServerConfigConst.CURRENTPAGE, String.valueOf(currentPage));
		}
		try {
			strResult = WeixinBasicKit.sendGet(url);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	
		if(null != strResult && !strResult.isEmpty()) {
			strResult = strResult.replaceAll("null", "\"\"");
			JSONArray arr = JSONArray.fromObject(strResult);
			if(arr != null) {
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
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	//精彩病历列表
	public String casehistoryList(int departmentId,int currentPage) {
		try {
			String url = ServerConfigConst.baseApiUrl+ServerConfigConst.defaultCasehistorylistURL+"?currentPage="+currentPage;
			url = url.replace("DEPARTMENTID", String.valueOf(departmentId));
			String strResult = WeixinBasicKit.sendGet(url);
			
			if(null != strResult && !strResult.isEmpty()) {
				strResult = strResult.replaceAll("null", "\"\"");
				JSONArray arr = JSONArray.fromObject(strResult);
				if(arr != null) {
					for(int i=0;i<arr.size();i++) {
						long arrangeTime = arr.getJSONObject(i).getLong("arrangeTime");
						arr.getJSONObject(i).put("arrangeTimeStr", format.format(new Date(arrangeTime)));
					}
					String pre = "{\"result\": \"success\",\"data\":";
					String result =  pre+arr.toString()+"}";
					return result;
				}
			}
			JSONObject result = new JSONObject();
			result.put(JsonConst.result, JsonConst.fail);
			return result.toString();
		}catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		
		JSONObject result = new JSONObject();
		result.put(JsonConst.result, JsonConst.fail);
		return result.toString();
	}
	
	
	public DoctorModel getDoctorByPhoneNum(String phoneNum) {
		try {
			return doctorDao.getDoctorInfo(phoneNum);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
			return null;
		}
	}
	
	/**
	 * @return
	 */
	public boolean update(DoctorModel model) throws Exception {
		try {
			return doctorDao.update(model);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public DoctorModel getDoctorByWxOpenId(String wxOpenId) throws Exception {
		try {
			if(null == wxOpenId || "".equals(wxOpenId)) {
				return null;
			}
			return doctorDao.getDoctorByWxOpenId(wxOpenId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
			return null;
		}
	}
	
	
	public JSONObject getDoctorDetail(int doctorId) throws Exception {
		
		String strResult = "";
		String url = ServerConfigConst.baseApiUrl+ServerConfigConst.getDoctorDetailByIdUrl;
		url = url.replace(ServerConfigConst.DOCTORID, String.valueOf(doctorId));
		
		strResult = WeixinBasicKit.sendGet(url);
	
		if(null != strResult && !strResult.isEmpty()) {
			strResult = strResult.replaceAll("null", "\"\"");
			JSONObject jsonResult = JSONObject.fromObject(strResult);
			if(jsonResult != null) {
				jsonResult.put(JsonConst.result, JsonConst.success);
				//return jsonResult.toString();
				return jsonResult;
			}
		}
		/*JSONObject result = new JSONObject();
		result.put(JsonConst.result, JsonConst.fail);
		return result.toString();*/
		return null;
	}
	
	public DoctorModel getDoctorByFieldValue(String field,String value) throws Exception {
		try {
			return doctorDao.getDoctorByFieldValue(field,value);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public DoctorModel getDoctorById(int id) throws Exception {
		try {
			return doctorDao.getDoctorById(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void savePasswdLinkToken(DoctorPasswdModifyLinkToken token) throws Exception {
		try{
			doctorDao.savePasswdLinkToken(token);
		}catch(Exception e){
			throw e;
		}
	}
	
	public List<DoctorPasswdModifyLinkToken> getPasswdLinkTokenList() throws Exception {
		try{
			return doctorDao.getPasswdLinkTokenList();
		}catch(Exception e){
			throw e;
		}
	}
	
	public void updatePasswdModifyLinkToken(DoctorPasswdModifyLinkToken token) throws Exception {
		try{
			doctorDao.updatePasswdModifyLinkToken(token);
		}catch(Exception e){
			throw e;
		}
	}
	
	
	public void deletePasswdModifyLinkToken(String key) throws Exception {
		try{
			doctorDao.deletePasswdModifyLinkToken(key);
		}catch(Exception e){
			throw e;
		}
	}
	
}
