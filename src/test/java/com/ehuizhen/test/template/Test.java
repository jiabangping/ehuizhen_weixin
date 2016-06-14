package com.ehuizhen.test.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehuizhen.test.template.OrderResponse.Order;
import com.ehuizhen.weixin.util.MyX509TrustManager;

import net.sf.json.JSONObject;

public class Test {
	/**
	 商品名称：{{product.DATA}}
	 商品价格：{{price.DATA}}
	 购买时间：{{time.DATA}}
	 {{remark.DATA}}
	 */
	private static final Logger log = LoggerFactory.getLogger(Test.class);

	public static String send_templatemsg_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	
	public static OrderResponse getOrderByOrderId(String oid) {
		return new OrderResponse();
	}
	
	
	/**
	 *	商品购买成功
	 * templateId 模板ID
	 * orderId 订单id
	 */
	public static String toTemplateMsgText(String orderId,String templateId){
		OrderResponse response=getOrderByOrderId(orderId);
		//查询订单信息
		Order order=response.getOrder();
		String first="您好，欢迎在新礼特购物！";
		String remark="您的收货信息："+order.getReceiver_name()+"电话："+order.getReceiver_mobile()+"地址:"+order.getReceiver_city()+order.getReceiver_zone()+order.getReceiver_address()+"我们将尽快发货，祝您购物愉快！";
		String jsonText="{\"touser\":\"OPENID\",\"template_id\":\"templateId\",\"url\":\"\",\"topcolor\":\"#FF0000\",\"data\":{\"first\": {\"value\":\"firstData\",\"color\":\"#173177\"},\"product\": {\"value\":\"productData\",\"color\":\"#173177\"},\"price\": {\"value\":\"priceData\",\"color\":\"#173177\"},\"time\": {\"value\":\"timeData\",\"color\":\"#173177\"},\"remark\": {\"value\":\"remarkData\",\"color\":\"#173177\"}}}";
		jsonText= jsonText.replace("firstData", first).replace("templateId", templateId).replace("OPENID", order.getBuyer_openid()).replace("productData", order.getProduct_name()).replace("priceData",order.getOrder_total_price()+"元").replace("timeData", order.getOrder_create_time()).replace("remarkData", remark);
	    return jsonText;
	}
	/*
	{{first.DATA}}
	发起人：{{keyword1.DATA}}
	患者信息：{{keyword2.DATA}}
	申请时间：{{keyword3.DATA}}
	会诊时间：{{keyword4.DATA}}
	{{remark.DATA}}
	*/
	public static String toTemplateMsgText2(String openId,String templateId){
		
		String first="您好，欢迎来到e会诊！";
		//KEYWORD4
		
		String remark="您的订单信息：xxx,电话：xxx,金额xxx,待付款！";
		String jsonText="{\"touser\":\"OPENID\",\"template_id\":\"templateId\",\"url\":\"\",\"topcolor\":\"#FF0000\",\"data\":{\"first\": {\"value\":\"firstData\",\"color\":\"#173177\"},\"keyword1\": {\"value\":\"KEY1\",\"color\":\"#173177\"},\"keyword2\": {\"value\":\"KEY2\",\"color\":\"#173177\"},\"keyword3\": {\"value\":\"KEY3\",\"color\":\"#173177\"},\"keyword4\": {\"value\":\"KEY4\",\"color\":\"#173177\"},\"remark\": {\"value\":\"remarkData\",\"color\":\"#173177\"}}}";
		jsonText= jsonText.replace("firstData", first).replace("templateId", templateId).replace("OPENID", openId).replace("KEY1", "贾邦平").replace("KEY2","郑琪").replace("KEY3", "2016-06-02").replace("KEY4", "2016-06-02").replace("remarkData", remark);
	    return jsonText;
	}
	
	
	
	
	public static void main(String[] args) {
		String accessToken = "Muxo3K2nVdDbrYgfkfQLkOtooWfcJ_b3P5QHSw4Xnv04oORXmd4R4mbbAh3qee4XYAoW6duzrdCvm3U-8zAjG0mDWEBP37TvkUN7Q1VTZeVPgO-wQ9ADCnpBtEcQJHEwOLGhADAWTO";
		String orderId = "";
		String templateId = "x1UNAOQn0gCZuzlOxa9B1Gami3N9IGqMKwfHAN1DUOs";
		String openId = "oHp7Ys0pZZeroqJ9vS89EEmnHkc8";
		sendTemplateMsg(accessToken,toTemplateMsgText2(openId,templateId));
	}
	
	/**
	 * @param accessToken
	 * @param jsonData
	 */
	public static void sendTemplateMsg(String accessToken, String jsonData) {

		String requestUrl = send_templatemsg_url.replace("ACCESS_TOKEN", accessToken);

		JSONObject jsonObject = httpRequest(requestUrl, "GET", jsonData);
		if (jsonObject != null) {
			if ("0".equals(jsonObject.getString("errcode"))) {
				System.out.println("发送模板消息成功！");
			} else {
				System.out.println(jsonObject.getString("errcode"));
			}
		}
	}

	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader = null;
		OutputStream outputStream = null;
		HttpsURLConnection httpUrlConn = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.flush();
			}
			// 将返回的输入流转换成字符串
			bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), "utf-8"));
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			jsonObject = JSONObject.fromObject(buffer.toString());
			return jsonObject;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}finally{
			try {
				bufferedReader.close();
				outputStream.close();
				httpUrlConn.disconnect();
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
		return null;
	}
}
