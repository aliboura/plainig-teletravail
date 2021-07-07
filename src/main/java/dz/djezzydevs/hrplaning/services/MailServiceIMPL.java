package dz.djezzydevs.hrplaning.services;

import com.sun.mail.util.MailConnectException;
import dz.djezzydevs.hrplaning.dto.MailRequest;
import dz.djezzydevs.hrplaning.dto.MailResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;

@AllArgsConstructor
@Service
@Transactional
public class MailServiceIMPL implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public MailResponse<String> sendNotifications(MailRequest mailRequest) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = this.getMessageHelpers(message, mailRequest);
            helper.setText("<h1>" + mailRequest.getMailDescription() + "</h1>", true);
            javaMailSender.send(message);
            return new MailResponse<>(true, "OK");
        } catch (MailConnectException e) {
            return new MailResponse<>(e.getMessage());
        } catch (MessagingException e) {
            return new MailResponse<>(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            return new MailResponse<>(e.getMessage());
        }
    }

    @Override
    public MailResponse<String> sendNotifications(MailRequest mailRequest, DataSource joint) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = this.getMessageHelpers(message, mailRequest);
            helper.setText("<h1>" + mailRequest.getMailDescription() + "</h1>", true);
            helper.addAttachment("planing.xlsx", joint);
          //  helper.addAttachment();
//            ClassPathResource resource = new ClassPathResource("images/logo.png");
//            helper.addInline("logoImage", resource);

            javaMailSender.send(message);
            return new MailResponse<>(true, "OK");
        } catch (MailConnectException e) {
            return new MailResponse<>(e.getMessage());
        } catch (MessagingException e) {
            return new MailResponse<>(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            return new MailResponse<>(e.getMessage());
        }
    }

    private MimeMessageHelper getMessageHelpers(MimeMessage message, MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("rh-planing@djezzy.dz", mailRequest.getMailSubject());
        helper.setSubject(mailRequest.getMailSubject());
        helper.setTo(mailRequest.getMails());
        if (mailRequest.getCc() != null) {
            helper.setCc(mailRequest.getCc());
        }
        //ClassPathResource resource = new ClassPathResource("images/mail-logo.png");
        //helper.addInline("logoImage", resource);
        return helper;
    }
}
