package github.titandea.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * DTO для получения данных агента.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agent {

    private String organization;

    private String localIp;

    private Boolean continuous;

    private LocalTime startTime;

    private LocalTime endTime;
}
