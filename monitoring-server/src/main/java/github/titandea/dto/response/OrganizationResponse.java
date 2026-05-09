package github.titandea.dto.response;

import lombok.*;

import java.util.UUID;

/**
 * DTO для отправки данных об организации на фронт.
 * Используется в контроллере.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponse {
    private UUID id;

    private String name;

    private String address;

    private String phoneNumber;

    private String contactPerson;
}