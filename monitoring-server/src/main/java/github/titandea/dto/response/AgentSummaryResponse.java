package github.titandea.dto.response;

import github.titandea.enums.AgentState;
import lombok.*;

import java.util.UUID;

/**
 * DTO для получения списка всех организаций.
 * Используется в контроллере.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentSummaryResponse {
    private UUID id;

    private String localIp;

    private AgentState state;
}
