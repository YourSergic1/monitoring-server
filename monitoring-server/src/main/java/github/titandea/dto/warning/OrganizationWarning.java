package github.titandea.dto.warning;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * DTO для предупреждения о неисправностях.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationWarning {
    private UUID id;

    private String name;

    List<AgentWarning> agentWarningList = new ArrayList<>();
}
