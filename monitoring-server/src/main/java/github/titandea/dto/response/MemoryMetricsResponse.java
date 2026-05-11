package github.titandea.dto.response;

import lombok.*;

/**
 * DTO для отправки данных о метриках оперативной памяти.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemoryMetricsResponse {

    private Long totalBytes;

    private Long availableBytes;

    private Long usedBytes;

    private Long swapTotalBytes;

    private Long swapUsedBytes;

    private Double swapUsagePercent;
}
