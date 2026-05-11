package github.titandea.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO для получения конкретного пользователя.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UUID id;

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    private String phone;

    private String role;

    private LocalDateTime createdAt;
}
