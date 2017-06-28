package com.services;

import com.domain.request.BookTicketsInternalRequest;
import com.exceptions.MailSendingFailedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * Created by burbulet on 4/24/17.
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOG = Logger.getLogger(MailService.class);

    @Value("${spring.mail.username}")
    private String emailFromAddress;

    @Value("${mail.toAddress}")
    private String emailToAddress;

    private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setMailContentBuilder(MailContentBuilder mailContentBuilder) {
        this.mailContentBuilder = mailContentBuilder;
    }

    @Override
    public void send(BookTicketsInternalRequest request) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(emailFromAddress);
            messageHelper.setReplyTo(request.getEmail());
            messageHelper.setTo(emailToAddress);
            messageHelper.setSubject("bus2eu | Бронь | " + request.getId());
            String content = mailContentBuilder.build(request);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            LOG.info("failed to send booking email " + e.getMessage());
            throw new MailSendingFailedException("failed to send booking email");
        }
    }
}
