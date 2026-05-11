package github.titandea.dto.response;

import github.titandea.enums.AgentState;
import lombok.*;

import java.util.UUID;

/**
 * DTO для получения списка всех организаций.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationSummaryResponse {
    private UUID id;

    private String name;

    private AgentState state;
}
