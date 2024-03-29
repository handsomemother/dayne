package cn.dayne.gz.platform.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class SimpleMailSender {
	
	private final Logger log = Logger.getLogger(SimpleMailSender.class);
	
	/**
	 * 服务器地址的key
	 */
	private final String hostKey = "mail.smtp.host";
	
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		EmailAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new EmailAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			if(mailInfo.getToAddress()!=null && !"".equals(mailInfo.getToAddress())){
				String []as = mailInfo.getToAddress().split(";");
				Address []toa = new InternetAddress[as.length];
				for(int i = 0 ;i<as.length ;i++){
					toa[i] = new InternetAddress(as[i]);
				}
				mailMessage.setRecipients(Message.RecipientType.TO, toa);
			}
			
			//抄送
			if(mailInfo.getCcAddress()!=null && !"".equals(mailInfo.getCcAddress())){
				String []as = mailInfo.getCcAddress().split(";");
				Address []ccs = new InternetAddress[as.length];
				for(int i = 0 ;i<as.length ;i++){
					ccs[i] = new InternetAddress(as[i]);
				}
				mailMessage.setRecipients(Message.RecipientType.CC, ccs);
			}
			
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			log.error("==================邮件发送错误======================"+ex);
		} finally{
			log.info("邮件服务器为："+sendMailSession.getProperties().get(hostKey));
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		EmailAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new EmailAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			if(mailInfo.getToAddress()!=null && !"".equals(mailInfo.getToAddress())){
				String []as = mailInfo.getToAddress().split(";");
				Address []toa = new InternetAddress[as.length];
				for(int i = 0 ;i<as.length ;i++){
					toa[i] = new InternetAddress(as[i]);
				}
				mailMessage.setRecipients(Message.RecipientType.TO, toa);
			}
			
			//抄送
			if(mailInfo.getCcAddress()!=null && !"".equals(mailInfo.getCcAddress())){
				String []as = mailInfo.getCcAddress().split(";");
				Address []ccs = new InternetAddress[as.length];
				for(int i = 0 ;i<as.length ;i++){
					ccs[i] = new InternetAddress(as[i]);
				}
				mailMessage.setRecipients(Message.RecipientType.CC, ccs);
			}
			
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			log.error("==================邮件发送错误======================"+ex);
		}
		return false;
	}
}
