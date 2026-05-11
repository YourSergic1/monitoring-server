package github.titandea.dto.response;

import lombok.*;

import java.util.UUID;

/**
 * DTO для получения списка всех пользователей.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryResponse {
    private UUID id;

    private String name;

    private String surname;

    private String patronymic;
}
