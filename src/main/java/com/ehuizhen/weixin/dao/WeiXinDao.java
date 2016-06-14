package com.ehuizhen.weixin.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ehuizhen.weixin.config.ConstantClass;
import com.ehuizhen.weixin.config.EnumClass.OrderStatus;
import com.ehuizhen.weixin.config.EnumClass.UserLevel;
import com.ehuizhen.weixin.model.BrowseHistoryModel;
import com.ehuizhen.weixin.model.ConfigModel;
import com.ehuizhen.weixin.model.CourseAssistModel;
import com.ehuizhen.weixin.model.CourseModel;
import com.ehuizhen.weixin.model.FeedbackModel;
import com.ehuizhen.weixin.model.OrderModel;
import com.ehuizhen.weixin.model.SmsModel;
import com.ehuizhen.weixin.model.UserModel;

@Repository
public class WeiXinDao {
	@Autowired
	HibernateTemplate hibernateTemplate;

	public UserModel isVIPFailure(final int currentPage, final int userId) {
		try {
			List<UserModel> user = hibernateTemplate.execute(new HibernateCallback<List<UserModel>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<UserModel> doInHibernate(Session session) throws HibernateException {
					Query query = session
							.createQuery("FROM UserModel WHERE id =:userId AND status = 1 AND vIPExpireTime > NOW()");
					query.setParameter("userId", userId);
					if (currentPage > 0) {
						query.setFirstResult((currentPage - 1) * ConstantClass.PageSize);
						query.setMaxResults(ConstantClass.PageSize);
					}
					return query.list();
				}
			});
			if (user == null || user.size() == 0) {
				return null;
			}
			return user.get(0);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 用户是否购买
	 * 
	 * @param userId
	 * @param courseId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public OrderModel getIsOrder(final int userId, final int courseId) {
		try {
			List<OrderModel> order = hibernateTemplate.execute(new HibernateCallback<List<OrderModel>>() {
				@Override
				public List<OrderModel> doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(
							"from OrderModel where status=:status and userId=:userId and courseId=:courseId");
					query.setParameter("status", OrderStatus.ORDER_PAYED.getValue());
					query.setParameter("userId", userId);
					query.setParameter("courseId", courseId);
					return query.list();
				}
			});
			if (order == null || order.size() == 0) {
				return null;
			}
			return order.get(0);
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 获取课程详细信息
	 * 
	 * @param id
	 * @return
	 */
	public CourseModel getCourseDetail(int id) {
		return hibernateTemplate.get(CourseModel.class, id);
	}

	/**
	 * 课程列表读取
	 * 
	 * @return
	 */
	public List<CourseModel> getCourseList(final int currentPage, final int menuid) {
		return hibernateTemplate.execute(new HibernateCallback<List<CourseModel>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<CourseModel> doInHibernate(Session session) throws HibernateException {
				StringBuffer hqlSb = new StringBuffer("FROM CourseModel WHERE 1=1");
				hqlSb.append(" AND type =" + menuid);
				hqlSb.append(" AND status = '1'");
				Query query = session.createQuery(hqlSb.toString());
				if (currentPage > 0) {
					query.setFirstResult((currentPage - 1) * ConstantClass.PageSize);
					query.setMaxResults(ConstantClass.PageSize);
				}
				return query.list();
			}
		});
	}

	/**
	 * 课辅列表读取
	 * 
	 * @return
	 */
	public List<CourseAssistModel> getAssist(final int currentPage, final int courseId) {
		return hibernateTemplate.execute(new HibernateCallback<List<CourseAssistModel>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<CourseAssistModel> doInHibernate(Session session) throws HibernateException {
				StringBuffer hqlSb = new StringBuffer("FROM CourseAssistModel WHERE 1=1 ");
				hqlSb.append(" AND courseId =" + courseId);
				// hqlSb.append(" ORDER BY editDate DESC");
				Query query = session.createQuery(hqlSb.toString());
				if (currentPage > 0) {
					query.setFirstResult((currentPage - 1) * ConstantClass.PageSize);
					query.setMaxResults(ConstantClass.PageSize);
				}
				return query.list();
			}
		});
	}

	/**
	 * 历史数据
	 * 
	 * @return
	 */
	public List<BrowseHistoryModel> getHistoryList() {
		return hibernateTemplate.execute(new HibernateCallback<List<BrowseHistoryModel>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<BrowseHistoryModel> doInHibernate(Session session) throws HibernateException {
				StringBuffer hqlSb = new StringBuffer("FROM BrowseHistoryModel WHERE 1=1 ");
				hqlSb.append(" AND userId =" + 1);
				// hqlSb.append(" ORDER BY editDate DESC");
				Query query = session.createQuery(hqlSb.toString());
				return query.list();
			}
		});
	}
	
	/**
	 * 是否有历史数据
	 * 
	 * @return
	 */
	public BrowseHistoryModel isHistoryList(final int userId, final int courseId) {
		try {
			List<BrowseHistoryModel> history = hibernateTemplate.execute(new HibernateCallback<List<BrowseHistoryModel>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<BrowseHistoryModel> doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery("FROM BrowseHistoryModel where userId=:userId and courseId=:courseId");
					query.setParameter("userId", userId);
					query.setParameter("courseId", courseId);
					return query.list();
				}
			});
			if (history == null || history.size() == 0) {
				return null;
			}
			return history.get(0);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 写入历史数据
	 * @param feedback
	 * @return
	 */
	@Transactional
	public boolean historyInsert(BrowseHistoryModel historyModel) {
		BrowseHistoryModel insertObj = new BrowseHistoryModel();
		try {
			insertObj.setUserId(historyModel.getUserId());
			insertObj.setCourseId(historyModel.getCourseId());
			insertObj.setWatchTime(0.0);
			insertObj.setExpireTime(historyModel.getExpireTime());
			insertObj.setInUser(historyModel.getInUser());
			insertObj.setInDate(historyModel.getInDate());
			hibernateTemplate.save(insertObj);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean feedbackInsert(FeedbackModel feedback) {
		FeedbackModel insertObj = new FeedbackModel();
		try {
			insertObj.setUserName(feedback.getUserName());
			insertObj.setUserMobile(feedback.getUserMobile());
			insertObj.setDescription(feedback.getDescription());
			insertObj.setAdvice(feedback.getAdvice());
			insertObj.setInDate(new Date());
			hibernateTemplate.save(insertObj);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	public int registerInsert(UserModel user) {
		UserModel insertObj = new UserModel();
		try {
			insertObj.setName(user.getName());
			insertObj.setMobile(user.getMobile());
			insertObj.setCompanyName(user.getCompanyName());
			insertObj.setAddress(user.getAddress());
			insertObj.setBusiness(user.getBusiness());
			insertObj.setEmail(user.getEmail());
			insertObj.setHobby(user.getHobby());
			insertObj.setPosition(user.getPosition());
			insertObj.setSex(user.getSex());
			insertObj.setSignature(user.getSignature());
			insertObj.setSigns(user.getSigns());
			insertObj.setWxid(user.getWxid());

			// vip到期时间
			Calendar curr = Calendar.getInstance();
			curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 1);
			Date date = curr.getTime();
			insertObj.setvIPExpireTime(date);

			insertObj.setStatus('1');
			insertObj.setLevel('0');
			insertObj.setInDate(new Date());
			hibernateTemplate.save(insertObj);
			hibernateTemplate.flush();
			return insertObj.getId();
		} catch (Exception e) {
			return  -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public boolean registerUpdate(final UserModel user) {
		List<UserModel> userList = hibernateTemplate.execute(new HibernateCallback<List<UserModel>>() {
			@Override
			public List<UserModel> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("from UserModel where wxid=:wxid");
				query.setParameter("wxid", user.getWxid());
				return query.list();
			}
		});
		if (userList == null || userList.size() == 0) {
			return false;
		}
		UserModel updateObj = userList.get(0);
		try {
			updateObj.setName(user.getName());
			updateObj.setMobile(user.getMobile());
			updateObj.setCompanyName(user.getCompanyName());
			updateObj.setAddress(user.getAddress());
			updateObj.setBusiness(user.getBusiness());
			updateObj.setEmail(user.getEmail());
			updateObj.setHobby(user.getHobby());
			updateObj.setPosition(user.getPosition());
			updateObj.setSex(user.getSex());
			updateObj.setSignature(user.getSignature());
			updateObj.setSigns(user.getSigns());
			updateObj.setWxid(user.getWxid());
			// updateObj.setInDate(new Date());
			hibernateTemplate.update(updateObj);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 用户ID查找用户
	 * 
	 * @param userId
	 * @return
	 */
	public UserModel getRegister(int userId) {
		return hibernateTemplate.get(UserModel.class, userId);
	}

	/**
	 * 通过配置ID查找配置信息
	 * 
	 * @param configId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ConfigModel getConfigModel(final int configId) {
		List<ConfigModel> list = hibernateTemplate.execute(new HibernateCallback<List<ConfigModel>>() {
			@Override
			public List<ConfigModel> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("FROM ConfigModel WHERE id=:id");
				query.setParameter("id", configId);
				return query.list();
			}
		});
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 查询手机号是否注册
	 * 
	 * @param mobile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isRegistered(final String mobile) {
		try {
			List<UserModel> user = hibernateTemplate.execute(new HibernateCallback<List<UserModel>>() {
				@Override
				public List<UserModel> doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery("from UserModel where mobile = :mobile");
					query.setParameter("mobile", mobile);
					return query.list();
				}
			});

			if (user == null || user.size() == 0) {
				return false;
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 查询微信号是否注册用户
	 * 
	 * @param mobile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public UserModel isWeiXinRegister(final String wxId) {
		try {
			List<UserModel> user = hibernateTemplate.execute(new HibernateCallback<List<UserModel>>() {
				@Override
				public List<UserModel> doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery("from UserModel where wxid=:wxid");
					query.setParameter("wxid", wxId);
					return query.list();
				}
			});
			if (user == null || user.size() == 0) {
				return null;
			}
			return user.get(0);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 验证码有效性
	 * 
	 * @param mobile
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean isVerifyCodeValidity(final SmsModel sms) {
		try {
			List<SmsModel> smsList = hibernateTemplate.execute(new HibernateCallback<List<SmsModel>>() {
				@Override
				public List<SmsModel> doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(
							"FROM SmsModel WHERE mobile =:mobile AND verifyCode =:verifyCode AND ExpTime > NOW()");
					query.setParameter("mobile", sms.getMobile());
					query.setParameter("verifyCode", sms.getVerifyCode());
					return query.list();
				}
			});

			if (smsList == null || smsList.size() == 0) {
				return false;
			}

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 添加验证码短信
	 * 
	 * @param sms
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int addHasVerifyCodeSMS(final SmsModel sms) {
		try {
			List<SmsModel> smsList = hibernateTemplate.execute(new HibernateCallback<List<SmsModel>>() {
				@Override
				public List<SmsModel> doInHibernate(Session session) throws HibernateException {
					Query query = session
							.createQuery("FROM SmsModel WHERE mobile =:mobile AND status=0 AND expTime > NOW()");
					query.setParameter("mobile", sms.getMobile());
					return query.list();
				}
			});

			if (smsList != null && smsList.size() != 0) {
				for (SmsModel smsModel : smsList) {
					if (smsModel == null)
						continue;
					smsModel.setExpTime(new Date());
					hibernateTemplate.update(smsModel);
				}
			}
			// 插入最新的验证码
			SmsModel smsInsertObj = new SmsModel();
			smsInsertObj.setType(sms.getType());
			smsInsertObj.setMobile(sms.getMobile());
			smsInsertObj.setNoteContent(sms.getNoteContent());
			// 验证码
			smsInsertObj.setVerifyCode(sms.getVerifyCode());
			// 过期时间
			smsInsertObj.setExpTime(sms.getExpTime());
			smsInsertObj.setStatus(0);
			smsInsertObj.setInDate(new Date());
			smsInsertObj.setInUser(sms.getInUser());
			smsInsertObj.setEditDate(new Date());
			smsInsertObj.setEditUser(sms.getInUser());
			hibernateTemplate.save(smsInsertObj);
			return smsInsertObj.getId();
		} catch (Exception ex) {
			return -1;
		}
	}

	/**
	 * 更新短信发送状态
	 * 
	 * @param sms
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean smsSended(int smsId) {
		SmsModel smsUpdateObj = hibernateTemplate.get(SmsModel.class, smsId);
		if (smsUpdateObj == null) {
			return false;
		}
		try {
			smsUpdateObj.setRetryCount(1);
			smsUpdateObj.setSendTime(new Date());
			smsUpdateObj.setStatus(1);
			hibernateTemplate.update(smsUpdateObj);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public UserModel getUserById(int userId) {
		return hibernateTemplate.get(UserModel.class, userId);
	}

	/**
	 * 下单
	 * 
	 * @param order
	 */
	@Transactional
	public void CreateOrder(OrderModel order) throws Exception {
		hibernateTemplate.save(order);
	}

	/**
	 * 根据OrderID 查询订单
	 * 
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public OrderModel getOrderDetail(final String orderId) {
		return hibernateTemplate.execute(new HibernateCallback<OrderModel>() {
			@Override
			public OrderModel doInHibernate(Session session) throws HibernateException {
				String hql = "FROM OrderModel WHERE orderId = :orderId";
				Query query = session.createQuery(hql);
				query.setParameter("orderId", orderId);
				List<OrderModel> list = query.list();
				if (list == null || list.size() == 0) {
					return null;
				}
				return list.get(0);
			}
		});
	}

	/**
	 * 修改订单状态为已支付
	 * 
	 * @param orderId
	 */
	@Transactional
	public void payOrder(String orderId) {
		// 查询本地订单
		OrderModel updateObj = getOrderDetail(orderId);
		if (updateObj != null) {
			try {
				// 修改订单为已支付
				updateObj.setStatus(OrderStatus.ORDER_PAYED.getValue());
				updateObj.setInDate(new Date());
				hibernateTemplate.update(updateObj);
				// 购买VIP的订单
				if (updateObj.getOrderId().startsWith("VIP")) {
					UserModel vipUpdateObj = hibernateTemplate.get(UserModel.class, updateObj.getUserId());
					if (vipUpdateObj != null) {
						if (UserLevel.VIP.getCode() == 1) {
							vipUpdateObj.setLevel('1');
						} else {
							vipUpdateObj.setLevel('0');
						}
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(new Date());
						// 设置过期时间为一年
						calendar.add(Calendar.YEAR, 1);
						Date expireTime = calendar.getTime();
						vipUpdateObj.setvIPExpireTime(expireTime);
						hibernateTemplate.update(vipUpdateObj);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
