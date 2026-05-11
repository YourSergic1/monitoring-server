package github.titandea.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Сущность метрик дискового пространства.
 */
@Entity
@Getter
@Setter
@Table(name = "disk_metrics",
        indexes = @Index(name = "idx_disk_system", columnList = "system_id"))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DiskMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", nullable = false)
    private SystemMetricsEntity system;

    @Column(name = "mount_point", length = 255)
    private String mountPoint;

    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "total_bytes")
    private Long totalBytes;

    @Column(name = "used_bytes")
    private Long usedBytes;

    @Column(name = "free_bytes")
    private Long freeBytes;

    @Column(name = "usage_percent")
    private Double usagePercent;
}