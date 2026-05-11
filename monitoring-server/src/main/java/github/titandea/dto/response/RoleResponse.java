package github.titandea.dto.response;

import lombok.*;

/**
 * DTO для получения списка всех ролей.
 * Используется в контроллере.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    private String role;

    private String displayName;
}
