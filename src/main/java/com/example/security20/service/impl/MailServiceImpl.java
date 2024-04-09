package com.example.security20.service.impl;

import com.example.security20.entity.MailType;
import com.example.security20.entity.User;
import com.example.security20.service.MailService;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.prefs.Preferences;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final Configuration configuration;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(User user, MailType type, Properties properties) {
        switch (type) {
            case REGISTRATION -> sendRegistrationEmail(user, properties);
            default -> {}
        }
    }

    @SneakyThrows
    private void sendRegistrationEmail(User user, Properties properties){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("Аккаунт зарегистрирован! " + user.getFirstName());
        helper.setTo(user.getUserName());
        String emailContent = getRegistrationEmailContent(user);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    @SneakyThrows
    private String getRegistrationEmailContent(User user){
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getFirstName());
        model.put("email", user.getUserName());
        model.put("password", user.getPassword());
        configuration.getTemplate("registration.html")
                .process(model,stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
