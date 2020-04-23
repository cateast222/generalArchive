package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.mail.MailDTO;

/**
 * 邮件服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 10:56
 */
public interface IMailService {
    /**
     * 发送邮件
     * @param mailDTO
     * @throws javax.mail.MessagingException
     */
    void sendMail(MailDTO mailDTO) throws javax.mail.MessagingException ;
}
