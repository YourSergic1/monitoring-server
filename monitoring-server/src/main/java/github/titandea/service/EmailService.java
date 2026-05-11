package github.titandea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username")
    private String fromEmail;

    public void sendCredentials(String toEmail, String fullName, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Ваши учетные данные для Сервиса мониторинга");
        message.setText(String.format("""
            Здравствуйте, %s!
            
            Ваша учетная запись создана.
            Логин: %s
            Пароль: %s
            
            С уважением,
            Команда мониторинга
            """, fullName, toEmail, password));

        mailSender.send(message);
    }
}