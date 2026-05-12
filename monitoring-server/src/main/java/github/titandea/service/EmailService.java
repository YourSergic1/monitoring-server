package github.titandea.service;

import github.titandea.dto.warning.AgentWarning;
import github.titandea.dto.warning.OrganizationWarning;
import github.titandea.entity.CalendarEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final CalendarService calendarService;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private volatile String lastSentWarningContent = null;
    private final Object warningCacheLock = new Object();

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

    public void sendWarnings(List<OrganizationWarning> warnings) {
        if (warnings == null || warnings.isEmpty()) {
            return;
        }

        CalendarEntity calendarEntity = calendarService.getCalendarEntityByDay(LocalDate.now());
        if (calendarEntity == null || calendarEntity.getUser() == null || calendarEntity.getUser().getEmail() == null) {
            log.warn("️Не найден дежурный на сегодня или у него нет email. Отмена отправки.");
            return;
        }

        String toEmail = calendarEntity.getUser().getEmail();

        String newContent = buildWarningMessage(warnings);

        synchronized (warningCacheLock) {
            if (newContent.equals(lastSentWarningContent)) {
                log.info("Предупреждение пропущено: список проблем не изменился с последней отправки.");
                return;
            }
            lastSentWarningContent = newContent;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Предупреждение: проблемы с агентами мониторинга");
        message.setText(newContent);

        mailSender.send(message);
        log.info("Предупреждение успешно отправлено на {}", toEmail);
    }

    /**
     * Собирает детализированное тело письма из списка предупреждений.
     * Вынесено отдельно для удобства тестирования и поддержки.
     */
    private String buildWarningMessage(List<OrganizationWarning> warnings) {
        StringBuilder sb = new StringBuilder();
        sb.append("Здравствуйте!\n\n")
                .append("Обнаружены проблемы с агентами мониторинга:\n\n");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        for (OrganizationWarning org : warnings) {
            sb.append("Организация: ").append(org.getName())
                    .append(" (ID: ").append(org.getId()).append(")\n");

            List<AgentWarning> agents = org.getAgentWarningList();
            if (agents != null && !agents.isEmpty()) {
                for (AgentWarning agent : agents) {
                    sb.append("Агент: ").append(agent.getLocalIp()).append("\n")
                            .append("Статус: ").append(agent.getState().name()).append("\n")
                            .append("Последняя метрика: ")
                            .append(agent.getLastMetricReceived() != null ? agent.getLastMetricReceived().format(dtf) : "Нет данных")
                            .append("\n\n");
                }
            } else {
                sb.append("Активные агенты не найдены или список пуст\n\n");
            }
        }

        sb.append("Пожалуйста, проверьте состояние систем.\n\n")
                .append("С уважением,\nКоманда мониторинга");

        return sb.toString();
    }
}