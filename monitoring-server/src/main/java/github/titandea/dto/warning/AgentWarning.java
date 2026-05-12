package github.titandea.dto.warning;

import github.titandea.enums.AgentState;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO для предупреждения о неисправностях.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentWarning {
    private UUID id;

    private String localIp;

    private AgentState state;

    private LocalDateTime lastMetricReceived;
}
