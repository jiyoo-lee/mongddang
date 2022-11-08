package com.jeeyulee.mongddang.common.mail;

import javax.mail.MessagingException;

public interface MailService {
    public void send(MailDTO mailDTO) throws MailSendException;
}
