package github.titandea.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

/**
 * DTO для отправки данных об агенте на фронт.
 * Используется в контроллере.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentResponse {
    private UUID id;

    private String localIp;

    private Boolean continuous;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDateTime lastMetricReceived;
}
