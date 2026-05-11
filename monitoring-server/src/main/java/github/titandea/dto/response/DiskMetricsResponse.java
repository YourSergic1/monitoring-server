package github.titandea.dto.response;

import lombok.*;

/**
 * DTO для отправки данных о метриках дискового пространства.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiskMetricsResponse {

    private String mountPoint;

    private String type;

    private Long totalBytes;

    private Long usedBytes;

    private Long freeBytes;

    private Double usagePercent;
}
