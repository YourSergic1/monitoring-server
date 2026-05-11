package github.titandea.dto.response;

import github.titandea.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO для получения конкретного пользователя.
 * Используется в контроллере.
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

    private UserRole role;

    private LocalDateTime createdAt;
}
