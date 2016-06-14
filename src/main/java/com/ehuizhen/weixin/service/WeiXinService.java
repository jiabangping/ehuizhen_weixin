package com.ehuizhen.weixin.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuizhen.weixin.config.ConstantClass;
import com.ehuizhen.weixin.dao.WeiXinDao;
import com.ehuizhen.weixin.model.BrowseHistoryModel;
import com.ehuizhen.weixin.model.ConfigModel;
import com.ehuizhen.weixin.model.CourseAssistModel;
import com.ehuizhen.weixin.model.CourseModel;
import com.ehuizhen.weixin.model.FeedbackModel;
import com.ehuizhen.weixin.model.OrderModel;
import com.ehuizhen.weixin.model.SmsModel;
import com.ehuizhen.weixin.model.UserModel;

@Service
public class WeiXinService {
	@Autowired
	WeiXinDao weixinDao;

	public UserModel isVIPFailure(int currentPage, int userId) {
		return weixinDao.isVIPFailure(currentPage, userId);
	}

	public OrderModel getIsOrder(int userId, int courseId) {
		return weixinDao.getIsOrder(userId, courseId);
	}

	public CourseModel getCourseDetail(int id) {
		return weixinDao.getCourseDetail(id);
	}

	public UserModel isWeiXinRegister(String wxId) {
		return weixinDao.isWeiXinRegister(wxId);
	}

	public List<CourseModel> getCourseList(int currentPage, int menuid) {
		return weixinDao.getCourseList(currentPage, menuid);
	}

	public List<CourseAssistModel> getAssist(int currentPage, int courseId) {
		return weixinDao.getAssist(currentPage, courseId);
	}

	// public List<CourseModel> getEducationList(int currentPage, int menuid) {
	// return weixinDao.getEducationList(currentPage, menuid);
	// }

	public List<BrowseHistoryModel> getHistoryList() {
		return weixinDao.getHistoryList();
	}

	public BrowseHistoryModel isHistoryList(int userId, int courseId) {
		return weixinDao.isHistoryList(userId, courseId);
	}

	public boolean feedbackInsert(FeedbackModel feedback) {
		return weixinDao.feedbackInsert(feedback);
	}

	public int registerInsert(UserModel user) {
		return weixinDao.registerInsert(user);
	}

	public boolean registerUpdate(UserModel user) {
		return weixinDao.registerUpdate(user);
	}

	public boolean isRegistered(String mobile) {
		return weixinDao.isRegistered(mobile);
	}

	public UserModel getRegister(int userId) {
		return weixinDao.getRegister(userId);
	}

	public ConfigModel getConfigModel(int configId) {
		return weixinDao.getConfigModel(configId);
	}

	/**
	 * 验证码有效性
	 * 
	 * @param mobile
	 * @throws Exception
	 */
	public boolean isVerifyCodeValidity(SmsModel sms) {
		return weixinDao.isVerifyCodeValidity(sms);
	}

	/**
	 * 获取验证码
	 * 
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public boolean getVerifyCode(String mobile) {
		try {
			// 生成验证码
			String verifyCode = ConstantClass.getVerifyCode(true, 6);

			// 过期时间
			Calendar afterTime = Calendar.getInstance();
			afterTime.add(Calendar.MINUTE, ConstantClass.VERIFYCODE_EXPIRED_TIME);
			Date afterDate = (Date) afterTime.getTime();

			String smsText = "【百万销售成长营】亲爱的用户，您的手机验证码：" + verifyCode + "。请在10分钟内将此验证码输入到系统。";

			// 数据入库
			SmsModel sms = new SmsModel();
			sms.setType(0);
			sms.setMobile(mobile);
			sms.setVerifyCode(verifyCode);
			sms.setExpTime(afterDate);
			sms.setNoteContent(smsText);
			int smsId = weixinDao.addHasVerifyCodeSMS(sms);
			if (smsId == -1) {
				return false;
			}

			// 验证码短信发送
			if (!ConstantClass.sendSms(ConstantClass.API_KEY, smsText, mobile).equals("0")) {
				return false;
			}

			// 更新数据库
			if (!weixinDao.smsSended(smsId)) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public UserModel getUserById(int userId) {
		return weixinDao.getUserById(userId);
	}

	// 下单
	public void CreateOrder(OrderModel order) throws Exception {
		weixinDao.CreateOrder(order);
	}

	// 修改订单状态
	public void payOrder(String transactionId, String orderId) {
		weixinDao.payOrder(orderId);
	}

	// 根据订单ID 查询订单
	public OrderModel getOrderDetail(String orderId) {
		return weixinDao.getOrderDetail(orderId);
	}

	// 购买成功后，向历史添加记录
	public void historyInsert(String orderId) throws Exception {
		OrderModel orderModel = weixinDao.getOrderDetail(orderId);
		BrowseHistoryModel historyModel = new BrowseHistoryModel();
		historyModel.setCourseId(orderModel.getCourseId());
		historyModel.setUserId(orderModel.getUserId());
		historyModel.setInUser(orderModel.getUserId());
		historyModel.setInDate(new Date());
		UserModel user = weixinDao.getRegister(orderModel.getUserId());
		if (user != null) {
			if (user.getLevel() == '0') {
				Calendar curr = Calendar.getInstance();
				curr.setTime(new Date());
				curr.add(Calendar.YEAR, 1);
				Date date = curr.getTime();
				historyModel.setExpireTime(date);
			} else {
				historyModel.setExpireTime(user.getvIPExpireTime());
			}
		} else {
			Calendar curr = Calendar.getInstance();
			curr.setTime(new Date());
			curr.add(Calendar.YEAR, 1);
			Date date = curr.getTime();
			historyModel.setExpireTime(date);
		}
		weixinDao.historyInsert(historyModel);
	}

	// 已学习课程的向历史添加记录
	public void historyStudyInsert(int userId, int courseId) {
		BrowseHistoryModel historyModel = new BrowseHistoryModel();
		historyModel.setCourseId(courseId);
		historyModel.setUserId(userId);
		historyModel.setInUser(userId);
		historyModel.setInDate(new Date());
		UserModel user = weixinDao.getRegister(userId);
		if (user != null) {
			if (user.getLevel() == '0') {
				Calendar curr = Calendar.getInstance();
				curr.setTime(new Date());
				curr.add(Calendar.YEAR, 1);
				Date date = curr.getTime();
				historyModel.setExpireTime(date);
			} else {
				historyModel.setExpireTime(user.getvIPExpireTime());
			}
		} else {
			Calendar curr = Calendar.getInstance();
			curr.setTime(new Date());
			curr.add(Calendar.YEAR, 1);
			Date date = curr.getTime();
			historyModel.setExpireTime(date);
		}
		weixinDao.historyInsert(historyModel);
	}
}
