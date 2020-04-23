package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.mail.MailDTO;
import com.ebs.platform.business.service.IMailService;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 11:02
 */
@Api(value = "电子邮件", description = "邮件相关操作")
@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    private IMailService mailService;

    @ApiOperation(value = "发送一封简易邮件")
    @PostMapping("sendMail")
    public WebResult sendMail(@RequestBody MailDTO mailDTO) throws MessagingException {
        mailService.sendMail(mailDTO);
        return WebUtils.success("ok");
    }
}
