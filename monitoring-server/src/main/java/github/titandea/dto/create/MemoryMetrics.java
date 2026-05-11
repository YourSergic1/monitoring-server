package github.titandea.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для получения данных по оперативной памяти.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemoryMetrics {

    private long totalBytes;

    private long availableBytes;

    private long usedBytes;

    private long swapTotalBytes;

    private long swapUsedBytes;

    private double swapUsagePercent;
}