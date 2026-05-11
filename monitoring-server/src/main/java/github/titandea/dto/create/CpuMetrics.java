package github.titandea.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для получения данных метрик ЦПУ.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CpuMetrics {

    private double usagePercent;

    private double userPercent;

    private double systemPercent;

    private double iowaitPercent;

    private double loadAverage1;

    private double loadAverage5;

    private double loadAverage15;

    private double temperature;

    private int physicalCores;

    private int logicalProcessors;
}
