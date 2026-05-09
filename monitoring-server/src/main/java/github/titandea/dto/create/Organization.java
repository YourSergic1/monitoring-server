package github.titandea.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для получения данных об организации.
 * Используется в контроллере.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    private String name;

    private String address;

    private String phoneNumber;

    private String contactPerson;
}
