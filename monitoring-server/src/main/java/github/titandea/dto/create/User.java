package github.titandea.dto.create;

import github.titandea.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO для создания юзера.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    private String phone;

    private UserRole role;

}
