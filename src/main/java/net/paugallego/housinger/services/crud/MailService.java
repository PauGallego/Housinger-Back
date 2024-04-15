package net.paugallego.housinger.services.crud;

import com.resend.Resend;

import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${spring.mailing.api-key}")
    private String apiKey;


    public void sendMail(String to, String subject, String html) {

        try {

            Resend resend = new Resend(apiKey);

            SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                    .from("mailing@paugallego.com")
                    .to(to)
                    .subject(subject)
                    .html(html)
                    .build();

            SendEmailResponse data = resend.emails().send(sendEmailRequest);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}
