package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dto.mail.MailDTO;
import com.ebs.platform.business.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * 邮件服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 10:58
 */
@Service
public class MailServiceImpl implements IMailService {
//    @Autowired
//    private JavaMailSender mailSender;

//    @Value("${spring.mail.username}")
//    private String from;

    @Override
    public void sendMail(MailDTO mailDTO) throws javax.mail.MessagingException {
//        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
//        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
//        message.setFrom(from);
//        message.setTo(mailDTO.getTo());
//        message.setCc(mailDTO.getCc());
//        message.setSubject(mailDTO.getSubject());
//        message.setText(mailDTO.getText());
//        this.mailSender.send(mimeMessage);
    }
}
