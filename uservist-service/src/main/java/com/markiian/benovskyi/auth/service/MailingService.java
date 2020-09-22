package com.markiian.benovskyi.auth.service;

import com.google.common.collect.Lists;
import com.markiian.benovskyi.auth.persistance.model.User;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MailingService {

    private final EmailService emailService;

    @Autowired
    public MailingService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendRegistrationEmail(User user)
            throws UnsupportedEncodingException, CannotSendEmailException {
        final InternetAddress fromAddress = new InternetAddress(
                "no-reply@markiian-benovskyi.com", "[Uservist] No-Reply");
        InternetAddress address = new InternetAddress(
                user.getEmail(),
                user.getFirstName().concat(" ").concat(user.getLastName()));

        List<InternetAddress> toAddress = Lists.newArrayList(address);

        final String subject = String.format("Your account for service %s has been created",
                user.getServiceRoles().iterator().next().getService().getName());
        final String encoding = "UTF-8";

        final Email email = DefaultEmail.builder()
                .from(fromAddress)
                .to(toAddress)
                .subject(subject)
                .body("")
                .encoding(encoding).build();

        //Defining the model object for the given Freemarker template
        final Map<String, Object> modelObject = new HashMap<>();
        modelObject.put("title", "Welcome to Uservist services");
        modelObject.put("email", user.getEmail());
        modelObject.put("username", user.getUsername());

        emailService.send(email, "registration-email.ftl", modelObject);
    }
}
