package com.ehuizhen.weixin.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehuizhen.weixin.init.ServerConfigConst;

public class EmailUtil {
	static final Logger log = LoggerFactory.getLogger(EmailUtil.class);
	static  String host = ServerConfigConst.emailHost;
	static String account = ServerConfigConst.emailAccount;
	static String password = ServerConfigConst.emailPassword;
	
	public static void sendRegisteEmail(String subject,String sendTo,String content) throws MessagingException, UnsupportedEncodingException {
		sendEmail(subject,sendTo,content);
	}
	
	public static void sendResetPasswdEmail(String subject,String sendTo,String content) throws MessagingException, UnsupportedEncodingException {
		sendEmail(subject,sendTo,content);
	}
	
	public static void sendEmail(String subject,String sendTo,String content) throws MessagingException, UnsupportedEncodingException {
    final Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.user",account);
    props.put("mail.password", password);

    // 构建授权信息，用于进行SMTP进行身份验证
    Authenticator authenticator = new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            // 用户名、密码
            String userName = props.getProperty("mail.user");
            String password = props.getProperty("mail.password");
            return new PasswordAuthentication(userName, password);
        }
    };
    // 使用环境属性和授权信息，创建邮件会话
    Session mailSession = Session.getInstance(props, authenticator);
    // 创建邮件消息
    MimeMessage message = new MimeMessage(mailSession);
    // 设置发件人
    InternetAddress form = new InternetAddress(props.getProperty("mail.user"),ServerConfigConst.emailPersonal,"UTF-8");
    message.setFrom(form);

    // 设置收件人
    InternetAddress to = new InternetAddress(sendTo);
    message.setRecipient(RecipientType.TO, to);

    // 设置邮件标题
   // message.setSubject("e会诊医生注册邮箱验证");
    message.setSubject(subject);

    // 设置邮件的内容体
    //message.setContent("<a href='http://www.fkjava.org'>测试的HTML邮件</a>", "text/html;charset=UTF-8");
    message.setContent(content, "text/html;charset=UTF-8");
    log.error("to:【"+sendTo+"】【subject="+subject+"】【content="+content+"】");
    
    // 发送邮件
    Transport.send(message);
	}
}