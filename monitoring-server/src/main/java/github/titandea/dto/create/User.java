package github.titandea.dto.create;

import github.titandea.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для создания пользователя.
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
