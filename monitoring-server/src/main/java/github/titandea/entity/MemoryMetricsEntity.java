package github.titandea.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Сущность метрик об оперативной памяти для БД.
 */
@Entity
@Getter
@Setter
@Table(name = "memory_metrics",
        indexes = @Index(name = "idx_memory_system", columnList = "system_id"))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemoryMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", nullable = false)
    private SystemMetricsEntity system;

    @Column(name = "total_bytes")
    private Long totalBytes;

    @Column(name = "available_bytes")
    private Long availableBytes;

    @Column(name = "used_bytes")
    private Long usedBytes;

    @Column(name = "swap_total_bytes")
    private Long swapTotalBytes;

    @Column(name = "swap_used_bytes")
    private Long swapUsedBytes;

    @Column(name = "swap_usage_percent")
    private Double swapUsagePercent;
}