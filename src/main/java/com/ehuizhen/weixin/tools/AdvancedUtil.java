package com.ehuizhen.weixin.tools;

//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ehuizhen.weixin.pojo.SNSUserInfo;
import com.ehuizhen.weixin.pojo.WeixinOauth2Token;

//import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONArray;
//import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Component
public class AdvancedUtil {
	
	@Autowired
	CommonUtil util;
	
//	private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);

//	public String makeTextCustomMessage(String openId, String content) {
//		content = content.replace("\"", "\\\"");
//		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
//		return String.format(jsonMsg, openId, content);
//	}
//
//	public String makeImageCustomMessage(String openId, String mediaId) {
//		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
//		return String.format(jsonMsg, openId, mediaId);
//	}
//
//	public String makeVoiceCustomMessage(String openId, String mediaId) {
//		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
//		return String.format(jsonMsg, openId, mediaId);
//	}
//
//	public  String makeVideoCustomMessage(String openId, String mediaId, String thumbMediaId) {
//		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
//		return String.format(jsonMsg, openId, mediaId, thumbMediaId);
//	}
//
//	public  String makeMusicCustomMessage(String openId, Music music) {
//		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
//		jsonMsg = String.format(jsonMsg, openId, JSONObject.fromObject(music).toString());
//		jsonMsg = jsonMsg.replace("thumbmediaid", "thumb_media_id");
//		return jsonMsg;
//	}
//	
//	public  String makeNewsCustomMessage(String openId, List<Article> articleList) {
//		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
//		jsonMsg = String.format(jsonMsg, openId, JSONArray.fromObject(articleList).toString().replaceAll("\"", "\\\""));
//		jsonMsg = jsonMsg.replace("picUrl", "picurl");
//		return jsonMsg;
//	}
//
//	public  boolean sendCustomMessage(String accessToken, String jsonMsg) {
//		log.info("ﾏ鋧｢ﾄﾚﾈﾝ｣ｺ{}", jsonMsg);
//		boolean result = false;
//		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
//		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
//
//		if (null != jsonObject) {
//			int errorCode = jsonObject.getInt("errcode");
//			String errorMsg = jsonObject.getString("errmsg");
//			if (0 == errorCode) {
//				result = true;
//				log.info("ｿﾍｷ�ﾏ鋧｢ｷ｢ﾋﾍｳﾉｹｦ errcode:{} errmsg:{}", errorCode, errorMsg);
//			} else {
//				log.error("ｿﾍｷ�ﾏ鋧｢ｷ｢ﾋﾍﾊｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
//			}
//		}
//
//		return result;
//	}

	public WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// ｻ｡ﾍｳﾊﾚﾈｨﾆｾﾖ､
		JSONObject jsonObject = util.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
//				log.error("ｻ｡ﾍｳﾊﾚﾈｨﾆｾﾖ､ﾊｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return wat;
	}

	public WeixinOauth2Token refreshOauth2AccessToken(String appId, String refreshToken) {
		WeixinOauth2Token wat = null;
		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		// ﾋ｢ﾐﾂﾍｳﾊﾚﾈｨﾆｾﾖ､
		JSONObject jsonObject = util.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
//				log.error("ﾋ｢ﾐﾂﾍｳﾊﾚﾈｨﾆｾﾖ､ﾊｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return wat;
	}

	@SuppressWarnings( { "deprecation", "unchecked" })
	public SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// ﾍｨｹ�ﾍｳﾊﾚﾈｨｻ｡ﾓﾃｻｧﾐﾅﾏ｢
		JSONObject jsonObject = util.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				// ﾓﾃｻｧｵﾄｱ�ﾊｶ
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// �ﾇｳﾆ
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				// ﾐﾔｱｨ1ﾊﾇﾄﾐﾐﾔ｣ｬ2ﾊﾇﾅｮﾐﾔ｣ｬ0ﾊﾇﾎｴﾖｪ｣ｩ
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				// ﾓﾃｻｧﾋﾚｹ恝ﾒ
				snsUserInfo.setCountry(jsonObject.getString("country"));
				// ﾓﾃｻｧﾋﾚﾊ｡ｷﾝ
				snsUserInfo.setProvince(jsonObject.getString("province"));
				// ﾓﾃｻｧﾋﾚｳﾇﾊﾐ
				snsUserInfo.setCity(jsonObject.getString("city"));
				// ﾓﾃｻｧﾍｷﾏ�
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// ﾓﾃｻｧﾌﾘﾈｨﾐﾅﾏ｢
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
//				log.error("ｻ｡ﾓﾃｻｧﾐﾅﾏ｢ﾊｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return snsUserInfo;
	}

//	public static WeixinQRCode createTemporaryQRCode(String accessToken, int expireSeconds, int sceneId) {
//		WeixinQRCode weixinQRCode = null;
//		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
//		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
//		// ﾐ靨ｪﾌ眇ｻｵﾄjsonﾊ�ｾﾝ
//		String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
//		// ｴｴｽｨﾁﾙﾊｱｴﾎｶ�ﾎｬﾂ�
//		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, expireSeconds, sceneId));
//
//		if (null != jsonObject) {
//			try {
//				weixinQRCode = new WeixinQRCode();
//				weixinQRCode.setTicket(jsonObject.getString("ticket"));
//				weixinQRCode.setExpireSeconds(jsonObject.getInt("expire_seconds"));
//				log.info("ｴｴｽｨﾁﾙﾊｱｴﾎｶ�ﾎｬﾂ�ｳﾉｹｦ ticket:{} expire_seconds:{}", weixinQRCode.getTicket(), weixinQRCode.getExpireSeconds());
//			} catch (Exception e) {
//				weixinQRCode = null;
//				int errorCode = jsonObject.getInt("errcode");
//				String errorMsg = jsonObject.getString("errmsg");
//				log.error("ｴｴｽｨﾁﾙﾊｱｴﾎｶ�ﾎｬﾂ�ﾊｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
//			}
//		}
//		return weixinQRCode;
//	}
//
//	public static String createPermanentQRCode(String accessToken, int sceneId) {
//		String ticket = null;
//		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
//		String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
//		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, sceneId));
//
//		if (null != jsonObject) {
//			try {
//				ticket = jsonObject.getString("ticket");
//				log.info("ｴｴｽｨﾓﾀｾﾃｴﾎｶ�ﾎｬﾂ�ｳﾉｹｦ ticket:{}", ticket);
//			} catch (Exception e) {
//				int errorCode = jsonObject.getInt("errcode");
//				String errorMsg = jsonObject.getString("errmsg");
//				log.error("ｴｴｽｨﾓﾀｾﾃｴﾎｶ�ﾎｬﾂ�ﾊｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
//			}
//		}
//		return ticket;
//	}
//
//	public static String getQRCode(String ticket, String savePath) {
//		String filePath = null;
//		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
//		requestUrl = requestUrl.replace("TICKET", CommonUtil.urlEncodeUTF8(ticket));
//		try {
//			URL url = new URL(requestUrl);
//			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//			conn.setDoInput(true);
//			conn.setRequestMethod("GET");
//
//			if (!savePath.endsWith("/")) {
//				savePath += "/";
//			}
//			filePath = savePath + ticket + ".jpg";
//
//			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
//			FileOutputStream fos = new FileOutputStream(new File(filePath));
//			byte[] buf = new byte[8096];
//			int size = 0;
//			while ((size = bis.read(buf)) != -1)
//				fos.write(buf, 0, size);
//			fos.close();
//			bis.close();
//
//			conn.disconnect();
//			log.info("ｸﾝticketｻｻﾈ｡ｶ�ﾎｬﾂ�ｳﾉｹｦ｣ｬfilePath=" + filePath);
//		} catch (Exception e) {
//			filePath = null;
//			log.error("ｸﾝticketｻｻﾈ｡ｶ�ﾎｬﾂ�ﾊｧｰﾜ｣ｺ{}", e);
//		}
//		return filePath;
//	}
//
//	/**
//	 * ｻ｡ﾓﾃｻｧﾐﾅﾏ｢
//	 * 
//	 * @param accessToken ｽﾓｿﾚｷﾃﾎﾊﾆｾﾖ､
//	 * @param openId ﾓﾃｻｧｱ�ﾊｶ
//	 * @return WeixinUserInfo
//	 */
//	public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
//		WeixinUserInfo weixinUserInfo = null;
//		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
//		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
//		// ｻ｡ﾓﾃｻｧﾐﾅﾏ｢
//		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
//
//		if (null != jsonObject) {
//			try {
//				weixinUserInfo = new WeixinUserInfo();
//				// ﾓﾃｻｧｵﾄｱ�ﾊｶ
//				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
//				// ｹﾘﾗ｢ﾗｴﾌｬ｣ｨ1ﾊﾇｹﾘﾗ｢｣ｬ0ﾊﾇﾎｴｹﾘﾗ｢｣ｩ｣ｬﾎｴｹﾘﾗ｢ﾊｱｻ｡ｲｻｵｽﾆ萼獎ﾅﾏ｢
//				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
//				// ﾓﾃｻｧｹﾘﾗ｢ﾊｱｼ�
//				weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
//				// �ﾇｳﾆ
//				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
//				// ﾓﾃｻｧｵﾄﾐﾔｱｨ1ﾊﾇﾄﾐﾐﾔ｣ｬ2ﾊﾇﾅｮﾐﾔ｣ｬ0ﾊﾇﾎｴﾖｪ｣ｩ
//				weixinUserInfo.setSex(jsonObject.getInt("sex"));
//				// ﾓﾃｻｧﾋﾚｹ恝ﾒ
//				weixinUserInfo.setCountry(jsonObject.getString("country"));
//				// ﾓﾃｻｧﾋﾚﾊ｡ｷﾝ
//				weixinUserInfo.setProvince(jsonObject.getString("province"));
//				// ﾓﾃｻｧﾋﾚｳﾇﾊﾐ
//				weixinUserInfo.setCity(jsonObject.getString("city"));
//				// ﾓﾃｻｧｵﾄﾓ�ﾑﾔ｣ｬｼ袒ﾐﾎﾄﾎｪzh_CN
//				weixinUserInfo.setLanguage(jsonObject.getString("language"));
//				// ﾓﾃｻｧﾍｷﾏ�
//				weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
//			} catch (Exception e) {
//				if (0 == weixinUserInfo.getSubscribe()) {
//					log.error("ﾓﾃｻｧ{}ﾒﾑﾈ｡ﾏ鄧ﾘﾗ｢", weixinUserInfo.getOpenId());
//				} else {
//					int errorCode = jsonObject.getInt("errcode");
//					String errorMsg = jsonObject.getString("errmsg");
//					log.error("ｻ｡ﾓﾃｻｧﾐﾅﾏ｢ﾊｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
//				}
//			}
//		}
//		return weixinUserInfo;
//	}
//
//	/**
//	 * ｻ｡ｹﾘﾗ｢ﾕﾟﾁﾐｱ�
//	 * 
//	 * @param accessToken ｵﾃｽﾓｿﾚﾆｾﾖ､
//	 * @param nextOpenId ｵﾚﾒｻｸｭﾈ｡ｵﾄopenId｣ｬｲｻﾌ鍗ｬﾈﾏｴﾓﾍｷｿｪﾊｼﾀｭﾈ｡
//	 * @return WeixinUserList
//	 */
//	@SuppressWarnings( { "unchecked", "deprecation" })
//	public static WeixinUserList getUserList(String accessToken, String nextOpenId) {
//		WeixinUserList weixinUserList = null;
//
//		if (null == nextOpenId)
//			nextOpenId = "";
//
//		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
//		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", nextOpenId);
//		// ｻ｡ｹﾘﾗ｢ﾕﾟﾁﾐｱ�
//		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
//		// ﾈ郢鉎�ﾇﾉｹｦ
//		if (null != jsonObject) {
//			try {
//				weixinUserList = new WeixinUserList();
//				weixinUserList.setTotal(jsonObject.getInt("total"));
//				weixinUserList.setCount(jsonObject.getInt("count"));
//				weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
//				JSONObject dataObject = (JSONObject) jsonObject.get("data");
//				weixinUserList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"), List.class));
//			} catch (JSONException e) {
//				weixinUserList = null;
//				int errorCode = jsonObject.getInt("errcode");
//				String errorMsg = jsonObject.getString("errmsg");
//				log.error("ｻ｡ｹﾘﾗ｢ﾕﾟﾁﾐｱ桄ｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
//			}
//		}
//		return weixinUserList;
//	}
//
//	/**
//	 * ｲ鰉ｯｷﾖﾗ�
//	 * 
//	 * @param accessToken ｵﾃｽﾓｿﾚﾆｾﾖ､
//	 */
//	@SuppressWarnings( { "unchecked", "deprecation" })
//	public static List<WeixinGroup> getGroups(String accessToken) {
//		List<WeixinGroup> weixinGroupList = null;
//		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
//		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
//		// ｲ鰉ｯｷﾖﾗ�
//		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
//
//		if (null != jsonObject) {
//			try {
//				weixinGroupList = JSONArray.toList(jsonObject.getJSONArray("groups"), WeixinGroup.class);
//			} catch (JSONException e) {
//				weixinGroupList = null;
//				int errorCode = jsonObject.getInt("errcode");
//				String errorMsg = jsonObject.getString("errmsg");
//				log.error("ｲ鰉ｯｷﾖﾗ鯡ｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
//			}
//		}
//		return weixinGroupList;
//	}
//
//	/**
//	 * ｴｴｽｨｷﾖﾗ�
//	 * 
//	 * @param accessToken ｽﾓｿﾚｷﾃﾎﾊﾆｾﾖ､
//	 * @param groupName ｷﾖﾗ鯏﨤ﾆ
//	 * @return
//	 */
//	public static WeixinGroup createGroup(String accessToken, String groupName) {
//		WeixinGroup weixinGroup = null;
//		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
//		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
//		// ﾐ靨ｪﾌ眇ｻｵﾄjsonﾊ�ｾﾝ
//		String jsonData = "{\"group\" : {\"name\" : \"%s\"}}";
//		// ｴｴｽｨｷﾖﾗ�
//		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, groupName));
//
//		if (null != jsonObject) {
//			try {
//				weixinGroup = new WeixinGroup();
//				weixinGroup.setId(jsonObject.getJSONObject("group").getInt("id"));
//				weixinGroup.setName(jsonObject.getJSONObject("group").getString("name"));
//			} catch (JSONException e) {
//				weixinGroup = null;
//				int errorCode = jsonObject.getInt("errcode");
//				String errorMsg = jsonObject.getString("errmsg");
//				log.error("ｴｴｽｨｷﾖﾗ鯡ｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
//			}
//		}
//		return weixinGroup;
//	}
//
//	/**
//	 * ﾐﾞｸﾄｷﾖﾗ鯏�
//	 * 
//	 * @param accessToken ｽﾓｿﾚｷﾃﾎﾊﾆｾﾖ､
//	 * @param groupId ｷﾖﾗ駟d
//	 * @param groupName ﾐﾞｸﾄｺﾄｷﾖﾗ鯏�
//	 * @return true | false
//	 */
//	public static boolean updateGroup(String accessToken, int groupId, String groupName) {
//		boolean result = false;
//		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
//		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
//		// ﾐ靨ｪﾌ眇ｻｵﾄjsonﾊ�ｾﾝ
//		String jsonData = "{\"group\": {\"id\": %d, \"name\": \"%s\"}}";
//		// ﾐﾞｸﾄｷﾖﾗ鯏�
//		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, groupId, groupName));
//
//		if (null != jsonObject) {
//			int errorCode = jsonObject.getInt("errcode");
//			String errorMsg = jsonObject.getString("errmsg");
//			if (0 == errorCode) {
//				result = true;
//				log.info("ﾐﾞｸﾄｷﾖﾗ鯏﨤ﾉｹｦ errcode:{} errmsg:{}", errorCode, errorMsg);
//			} else {
//				log.error("ﾐﾞｸﾄｷﾖﾗ鯏鈹ｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
//			}
//		}
//		return result;
//	}
//
//	/**
//	 * ﾒﾆｶｯﾓﾃｻｧｷﾖﾗ�
//	 * 
//	 * @param accessToken ｽﾓｿﾚｷﾃﾎﾊﾆｾﾖ､
//	 * @param openId ﾓﾃｻｧｱ�ﾊｶ
//	 * @param groupId ｷﾖﾗ駟d
//	 * @return true | false
//	 */
//	public static boolean updateMemberGroup(String accessToken, String openId, int groupId) {
//		boolean result = false;
//		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
//		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
//		// ﾐ靨ｪﾌ眇ｻｵﾄjsonﾊ�ｾﾝ
//		String jsonData = "{\"openid\":\"%s\",\"to_groupid\":%d}";
//		// ﾒﾆｶｯﾓﾃｻｧｷﾖﾗ�
//		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, openId, groupId));
//
//		if (null != jsonObject) {
//			int errorCode = jsonObject.getInt("errcode");
//			String errorMsg = jsonObject.getString("errmsg");
//			if (0 == errorCode) {
//				result = true;
//				log.info("ﾒﾆｶｯﾓﾃｻｧｷﾖﾗ魑ﾉｹｦ errcode:{} errmsg:{}", errorCode, errorMsg);
//			} else {
//				log.error("ﾒﾆｶｯﾓﾃｻｧｷﾖﾗ鯡ｧｰﾜ errcode:{} errmsg:{}", errorCode, errorMsg);
//			}
//		}
//		return result;
//	}
//
//	/**
//	 * ﾉﾏｴｫﾃｽﾌ衾ﾄｼ�
//	 * 
//	 * @param accessToken ｽﾓｿﾚｷﾃﾎﾊﾆｾﾖ､
//	 * @param type ﾃｽﾌ衾ﾄｼ�ﾀ獎ﾍ｣ｨimage｡｢voice｡｢videoｺﾍthumb｣ｩ
//	 * @param mediaFileUrl ﾃｽﾌ衾ﾄｼ�ｵﾄurl
//	 */
//	public static WeixinMedia uploadMedia(String accessToken, String type, String mediaFileUrl) {
//		WeixinMedia weixinMedia = null;
//		// ﾆｴﾗｰﾇ�ﾇﾘﾖｷ
//		String uploadMediaUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
//		uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
//
//		// ｶｨﾒ衞�ｾﾝｷﾖｸ�
//		String boundary = "------------7da2e536604c8";
//		try {
//			URL uploadUrl = new URL(uploadMediaUrl);
//			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
//			uploadConn.setDoOutput(true);
//			uploadConn.setDoInput(true);
//			uploadConn.setRequestMethod("POST");
//			// ﾉ靹ﾃﾇ�ﾇｷContent-Type
//			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//			// ｻ｡ﾃｽﾌ衾ﾄｼ�ﾉﾏｴｫｵﾄﾊ莎ｨﾍ｢ﾐﾅｷ�ﾎｴﾊ�ｾﾝ｣ｩ
//			OutputStream outputStream = uploadConn.getOutputStream();
//
//			URL mediaUrl = new URL(mediaFileUrl);
//			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
//			meidaConn.setDoOutput(true);
//			meidaConn.setRequestMethod("GET");
//
//			// ｴﾓﾇ�ﾇｷﾖﾐｻ｡ﾄﾚﾈﾝﾀ獎ﾍ
//			String contentType = meidaConn.getHeaderField("Content-Type");
//			// ｸﾝﾄﾚﾈﾝﾀ獎ﾍﾅﾐｶﾏﾎﾄｼ�ﾀｩﾕｹﾃ�
//			String fileExt = CommonUtil.getFileExt(contentType);
//			// ﾇ�ﾇ蠢ｪﾊｼ
//			outputStream.write(("--" + boundary + "\r\n").getBytes());
//			outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt).getBytes());
//			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());
//
//			// ｻ｡ﾃｽﾌ衾ﾄｼ�ｵﾄﾊ菠�ﾁｨｶﾁﾈ｡ﾎﾄｼ�｣ｩ
//			BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
//			byte[] buf = new byte[8096];
//			int size = 0;
//			while ((size = bis.read(buf)) != -1) {
//				// ｽｫﾃｽﾌ衾ﾄｼ�ﾐｴｵｽﾊ莎ｨﾍ｢ﾐﾅｷ�ﾎｴﾊ�ｾﾝ｣ｩ
//				outputStream.write(buf, 0, size);
//			}
//			// ﾇ�ﾇ蠖睫�
//			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
//			outputStream.close();
//			bis.close();
//			meidaConn.disconnect();
//
//			// ｻ｡ﾃｽﾌ衾ﾄｼ�ﾉﾏｴｫｵﾄﾊ菠�ﾁｨｴﾓﾎ｢ﾐﾅｷ�ﾎﾁﾊ�ｾﾝ｣ｩ
//			InputStream inputStream = uploadConn.getInputStream();
//			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
//			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//			StringBuffer buffer = new StringBuffer();
//			String str = null;
//			while ((str = bufferedReader.readLine()) != null) {
//				buffer.append(str);
//			}
//			bufferedReader.close();
//			inputStreamReader.close();
//			// ﾊﾍｷﾅﾗﾊﾔｴ
//			inputStream.close();
//			inputStream = null;
//			uploadConn.disconnect();
//
//			// ﾊｹﾓﾃJSON-libｽ簧ｵｻﾘｽ盪�
//			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
//			weixinMedia = new WeixinMedia();
//			weixinMedia.setType(jsonObject.getString("type"));
//			// typeｵﾈﾓﾚthumbﾊｱｵﾄｷｵｻﾘｽ盪釚ﾍﾆ萢�ﾀ獎ﾍｲｻﾒｻﾑ�
//			if ("thumb".equals(type))
//				weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
//			else
//				weixinMedia.setMediaId(jsonObject.getString("media_id"));
//			weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));
//		} catch (Exception e) {
//			weixinMedia = null;
//			log.error("ﾉﾏｴｫﾃｽﾌ衾ﾄｼ�ﾊｧｰﾜ｣ｺ{}", e);
//		}
//		return weixinMedia;
//	}
//
//	/**
//	 * ﾏﾂﾔﾘﾃｽﾌ衾ﾄｼ�
//	 * 
//	 * @param accessToken ｽﾓｿﾚｷﾃﾎﾊﾆｾﾖ､
//	 * @param mediaId ﾃｽﾌ衾ﾄｼ�ｱ�ﾊｶ
//	 * @param savePath ﾎﾄｼ�ﾔﾚｷ�ﾎﾏｵﾄｴ豢｢ﾂｷｾｶ
//	 * @return
//	 */
//	public static String getMedia(String accessToken, String mediaId, String savePath) {
//		String filePath = null;
//		// ﾆｴｽﾓﾇ�ﾇﾘﾖｷ
//		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
//		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
//		System.out.println(requestUrl);
//		try {
//			URL url = new URL(requestUrl);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setDoInput(true);
//			conn.setRequestMethod("GET");
//
//			if (!savePath.endsWith("/")) {
//				savePath += "/";
//			}
//			// ｸﾝﾄﾚﾈﾝﾀ獎ﾍｻ｡ﾀｩﾕｹﾃ�
//			String fileExt = CommonUtil.getFileExt(conn.getHeaderField("Content-Type"));
//			// ｽｫmediaIdﾗｪﾎﾄｼ�ﾃ�
//			filePath = savePath + mediaId + fileExt;
//
//			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
//			FileOutputStream fos = new FileOutputStream(new File(filePath));
//			byte[] buf = new byte[8096];
//			int size = 0;
//			while ((size = bis.read(buf)) != -1)
//				fos.write(buf, 0, size);
//			fos.close();
//			bis.close();
//
//			conn.disconnect();
//			log.info("ﾏﾂﾔﾘﾃｽﾌ衾ﾄｼ�ｳﾉｹｦ｣ｬfilePath=" + filePath);
//		} catch (Exception e) {
//			filePath = null;
//			log.error("ﾏﾂﾔﾘﾃｽﾌ衾ﾄｼ�ﾊｧｰﾜ｣ｺ{}", e);
//		}
//		return filePath;
//	}
//
//	public static void main(String args[]) {
//		// ｻ｡ｽﾓｿﾚｷﾃﾎﾊﾆｾﾖ､
//		String accessToken = CommonUtil.getToken("APPID", "APPSECRET").getAccessToken();
//
//		/**
//		 * ｷ｢ﾋﾍｿﾍｷ�ﾏ鋧｢｣ｨﾎﾄｱｾﾏ鋧｢｣ｩ
//		 */
//		// ﾗ鰊ｰﾎﾄｱｾｿﾍｷ�ﾏ鋧｢
//		String jsonTextMsg = makeTextCustomMessage("oEdzejiHCDqafJbz4WNJtWTMbDcE", "ｵ羹鯀ｴ<a href=\"http://blog.csdn.net/lyq8479\">ﾁ蠏ﾄｲｩｿﾍ</a>");
//		// ｷ｢ﾋﾍｿﾍｷ�ﾏ鋧｢
//		sendCustomMessage(accessToken, jsonTextMsg);
//
//		/**
//		 * ｷ｢ﾋﾍｿﾍｷ�ﾏ鋧｢｣ｨﾍｼﾎﾄﾏ鋧｢｣ｩ
//		 */
//		Article article1 = new Article();
//		article1.setTitle("ﾎ｢ﾐﾅﾉﾏﾒｲﾄﾜｶｷｵﾘﾖ�");
//		article1.setDescription("");
//		article1.setPicUrl("http://www.egouji.com/xiaoq/game/doudizhu_big.png");
//		article1.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/Doudizhu/doudizhu.htm");
//		Article article2 = new Article();
//		article2.setTitle("ｰﾁﾆﾛﾓ･\n80ｺｻｵﾃｲｻﾍ豬ﾄｾｭｵ萼ﾎﾏｷ");
//		article2.setDescription("");
//		article2.setPicUrl("http://www.egouji.com/xiaoq/game/aoqixiongying.png");
//		article2.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/Plane/aoqixiongying.html");
//		List<Article> list = new ArrayList<Article>();
//		list.add(article1);
//		list.add(article2);
//		// ﾗ鰊ｰﾍｼﾎﾄｿﾍｷ�ﾏ鋧｢
//		String jsonNewsMsg = makeNewsCustomMessage("oEdzejiHCDqafJbz4WNJtWTMbDcE", list);
//		// ｷ｢ﾋﾍｿﾍｷ�ﾏ鋧｢
//		sendCustomMessage(accessToken, jsonNewsMsg);
//
//		/**
//		 * ｴｴｽｨﾁﾙﾊｱｶ�ﾎｬﾂ�
//		 */
//		WeixinQRCode weixinQRCode = createTemporaryQRCode(accessToken, 900, 111111);
//		// ﾁﾙﾊｱｶ�ﾎｬﾂ�ｵﾄticket
//		System.out.println(weixinQRCode.getTicket());
//		// ﾁﾙﾊｱｶ�ﾎｬﾂ�ｵﾄﾓﾐﾐｧﾊｱｼ�
//		System.out.println(weixinQRCode.getExpireSeconds());
//
//		/**
//		 * ｸﾝticketｻｻﾈ｡ｶ�ﾎｬﾂ�
//		 */
//		String ticket = "gQEg7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2lIVVJ3VmJsTzFsQ0ZuQ0Y1bG5WAAIEW35+UgMEAAAAAA==";
//		String savePath = "G:/download";
//		// ｸﾝticketｻｻﾈ｡ｶ�ﾎｬﾂ�
//		getQRCode(ticket, savePath);
//
//		/**
//		 * ｻ｡ﾓﾃｻｧﾐﾅﾏ｢
//		 */
//		WeixinUserInfo user = getUserInfo(accessToken, "oEdzejiHCDqafJbz4WNJtWTMbDcE");
//		System.out.println("OpenID｣ｺ" + user.getOpenId());
//		System.out.println("ｹﾘﾗ｢ﾗｴﾌｬ｣ｺ" + user.getSubscribe());
//		System.out.println("ｹﾘﾗ｢ﾊｱｼ茱ｺ" + user.getSubscribeTime());
//		System.out.println("�ﾇｳﾆ｣ｺ" + user.getNickname());
//		System.out.println("ﾐﾔｱｺ" + user.getSex());
//		System.out.println("ｹ恝ﾒ｣ｺ" + user.getCountry());
//		System.out.println("ﾊ｡ｷﾝ｣ｺ" + user.getProvince());
//		System.out.println("ｳﾇﾊﾐ｣ｺ" + user.getCity());
//		System.out.println("ﾓ�ﾑﾔ｣ｺ" + user.getLanguage());
//		System.out.println("ﾍｷﾏｺ" + user.getHeadImgUrl());
//
//		/**
//		 * ｻ｡ｹﾘﾗ｢ﾕﾟﾁﾐｱ�
//		 */
//		WeixinUserList weixinUserList = getUserList(accessToken, "");
//		System.out.println("ﾗﾜｹﾘﾗ｢ﾓﾃｻｧﾊ�｣ｺ" + weixinUserList.getTotal());
//		System.out.println("ｱｾｴﾎｻ｡ﾓﾃｻｧﾊ�｣ｺ" + weixinUserList.getCount());
//		System.out.println("OpenIDﾁﾐｱ惞ｺ" + weixinUserList.getOpenIdList().toString());
//		System.out.println("next_openid｣ｺ" + weixinUserList.getNextOpenId());
//
//		/**
//		 * ｲ鰉ｯｷﾖﾗ�
//		 */
//		List<WeixinGroup> groupList = getGroups(accessToken);
//		// ﾑｭｻｷﾊ莎ﾖﾗ鰔ﾅﾏ｢
//		for (WeixinGroup group : groupList) {
//			System.out.println(String.format("ID｣ｺ%d ﾃ﨤ﾆ｣ｺ%s ﾓﾃｻｧﾊ�｣ｺ%d", group.getId(), group.getName(), group.getCount()));
//		}
//
//		/**
//		 * ｴｴｽｨｷﾖﾗ�
//		 */
//		WeixinGroup group = createGroup(accessToken, "ｹｫﾋｾﾔｱｹ､");
//		System.out.println(String.format("ｳﾉｹｦｴｴｽｨｷﾖﾗ鬟ｺ%s id｣ｺ%d", group.getName(), group.getId()));
//
//		/**
//		 * ﾐﾞｸﾄｷﾖﾗ鯏�
//		 */
//		updateGroup(accessToken, 100, "ﾍｬﾊﾂ");
//
//		/**
//		 * ﾒﾆｶｯﾓﾃｻｧｷﾖﾗ�
//		 */
//		updateMemberGroup(accessToken, "oEdzejiHCDqafJbz4WNJtWTMbDcE", 100);
//
//		/**
//		 * ﾉﾏｴｫｶ狹ｽﾌ衾ﾄｼ�
//		 */
//		WeixinMedia weixinMedia = uploadMedia(accessToken, "voice", "http://localhost:8080/weixinmpapi/test.mp3");
//		System.out.println(weixinMedia.getMediaId());
//		System.out.println(weixinMedia.getType());
//		System.out.println(weixinMedia.getCreatedAt());
//
//		/**
//		 * ﾏﾂﾔﾘｶ狹ｽﾌ衾ﾄｼ�
//		 */
//		getMedia(accessToken, "N7xWhOGYSLWUMPzVcGnxKFbhXeD_lLT5sXxyxDGEsCzWIB2CcUijSeQOYjWLMpcn", "G:/download");
//	}
}
