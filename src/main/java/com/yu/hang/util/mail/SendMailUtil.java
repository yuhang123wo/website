package com.yu.hang.util.mail;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

public class SendMailUtil {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private SimpleMailMessage simpleMailMessage;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	/**
	 * 
	 * @param nickName
	 * @param content
	 * @param email
	 *            void
	 */
	public void send(Map<String, Object> map, String toUser, String subject, String mb) {
		try {
			// 根据模板内容，动态把map中的数据填充进去，生成HTML
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(mb);
			// map中的key，对应模板中的${key}表达式
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			sendMail(toUser, subject, text);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 *            void
	 */
	private void sendMail(String to, String subject, String content) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(simpleMailMessage.getFrom());
			if (subject != null) {
				messageHelper.setSubject(subject);
			} else {
				messageHelper.setSubject(simpleMailMessage.getSubject());
			}
			messageHelper.setTo(to);
			messageHelper.setText(content, true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
