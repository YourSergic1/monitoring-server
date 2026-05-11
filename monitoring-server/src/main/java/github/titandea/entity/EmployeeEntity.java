package github.titandea.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Сущность юзера для БД.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String email;

    private String phone;

    private String address;

    private String role;
}
