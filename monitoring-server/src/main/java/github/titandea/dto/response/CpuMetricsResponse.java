package github.titandea.dto.response;

import lombok.*;

/**
 * DTO для отправки данных о метриках ЦПУ.
 * Используется в контроллере.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CpuMetricsResponse {

    private Double usagePercent;

    private Double userPercent;

    private Double systemPercent;

    private Double iowaitPercent;

    private Double loadAverage1;

    private Double loadAverage5;

    private Double loadAverage15;

    private Double temperature;

    private Integer physicalCores;

    private Integer logicalProcessors;
}
