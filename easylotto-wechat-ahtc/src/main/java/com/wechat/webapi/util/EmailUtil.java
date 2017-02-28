package com.wechat.webapi.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;

import com.sun.mail.util.MailSSLSocketFactory;
import com.wechat.config.EmailConfig;

public class EmailUtil {

	public static void sendToGroup(String title, String content, EmailConfig config) {
		try {
			if (config.getState())
				send(config.getSendUser(), config.getKey(), title, config.getSign() +" \n\t "+ content, config.getGroups());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendToGroupGZ(String title, String content, EmailConfig config) {
		try {
			if (config.getState())
				send(config.getSendUser(), config.getKey(), title, config.getSign() +" \n\t "+ content, config.getGroupgzs());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendToGroupAH(String title, String content, EmailConfig config) {
		try {
			if (config.getState())
				send(config.getSendUser(), config.getKey(), title, config.getSign() +" \n\t "+ content, config.getGroupahs());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendToGroupGZ(String title, String content, EmailConfig config, String[] emails) {
		try {
			if (config.getState())
				send(config.getSendUser(), config.getKey(), title, config.getSign() +" \n\t "+ content, emails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void send(String sendUser, String key, String title, String content, String[] emails) {
		try {
			Properties props = new Properties();

			// 开启debug调试
			props.setProperty("mail.debug", "true");
			// 发送服务器需要身份验证
			props.setProperty("mail.smtp.auth", "true");
			// 设置邮件服务器主机名
			props.setProperty("mail.host", "smtp.qq.com");
			// 发送邮件协议名称
			props.setProperty("mail.transport.protocol", "smtp");

			props.setProperty("mail.smtp.port", "465");
			props.setProperty("smtp.qq.com", "465");

			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sf);

			Session session = Session.getInstance(props);

			Message msg = new MimeMessage(session);
			msg.setSubject(title);

			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(content, "text/html;charset=UTF-8");
			mp.addBodyPart(mbp);
			msg.setContent(mp); // Multipart加入到信件
			msg.setSentDate(new Date()); // 设置信件头的发送日期
			msg.setFrom(new InternetAddress(sendUser));
			Transport transport = session.getTransport();
			transport.connect("smtp.qq.com", sendUser, key);

			for (String email : emails) {
				if (StringUtils.isNotBlank(email))
					transport.sendMessage(msg, new Address[] { new InternetAddress(email) });//
			}
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
