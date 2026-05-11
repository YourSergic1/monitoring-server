package github.titandea.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO для получения календаря.
 * Используется в контроллере.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarResponse {
    private UUID id;
    private LocalDate date;
    private String dayOfWeek;
    private boolean workingDay;
}
