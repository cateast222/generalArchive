package com.ebs.platform.core.dto;

import java.util.Arrays;

/**
 * 简单邮件对象
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/6 15:44
 */
public class MailDTO {

    @Override
    public String toString() {
        return "MailDTO{" +
                "to=" + Arrays.toString(to) +
                ", cc=" + Arrays.toString(cc) +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String[] to;
    public String[] cc;
    public String subject;
    public String text;

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
