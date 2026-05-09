package github.titandea.dto.response;

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
public class OrganizationSummaryResponse {
    private UUID id;

    private String name;
}
