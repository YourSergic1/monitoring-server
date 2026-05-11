package github.titandea.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для получения данных по дискам.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiskMetrics {

    private String mountPoint;

    private String type;

    private long totalBytes;

    private long usedBytes;

    private long freeBytes;

    private double usagePercent;
}
