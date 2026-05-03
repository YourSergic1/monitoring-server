package github.titandea.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Data
@Table(name = "cpu_metrics",
        indexes = @Index(name = "idx_cpu_system", columnList = "system_id"))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CpuMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "system_id", nullable = false)
    private SystemMetricsEntity system;

    @Column(name = "usage_percent")
    private Double usagePercent;

    @Column(name = "user_percent")
    private Double userPercent;

    @Column(name = "system_percent")
    private Double systemPercent;

    @Column(name = "iowait_percent")
    private Double iowaitPercent;

    @Column(name = "load_average_1")
    private Double loadAverage1;

    @Column(name = "load_average_5")
    private Double loadAverage5;

    @Column(name = "load_average_15")
    private Double loadAverage15;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "physical_cores")
    private Integer physicalCores;

    @Column(name = "logical_processors")
    private Integer logicalProcessors;
}