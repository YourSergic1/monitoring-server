package github.titandea.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO для отправки системных метрик.
 * Используется в контроллере.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemMetricsResponse {

    private String hostname;

    private String localIp;

    private String publicIp;

    private LocalDateTime dateTime;

    private Long uptimeMinutes;

    private Set<CpuMetricsResponse> cpuMetricsEntities = new HashSet<>();

    private Set<MemoryMetricsResponse> memoryMetricsEntities = new HashSet<>();

    private Set<DiskMetricsResponse> diskMetricsEntities = new HashSet<>();

    private Set<NetworkMetricsResponse> networkMetricsEntities = new HashSet<>();
}
