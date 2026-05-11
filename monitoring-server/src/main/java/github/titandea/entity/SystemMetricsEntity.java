package github.titandea.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сущность системных метрик.
 */
@Entity
@Getter
@Setter
@Table(name = "system_metrics",
        uniqueConstraints = @UniqueConstraint(name = "uq_system_ip_time", columnNames = {"local_ip", "date_time"}))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SystemMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", nullable = false)
    private AgentEntity agent;

    @Column(name = "hostname", length = 255)
    private String hostname;

    @Column(name = "local_ip", nullable = false)
    private String localIp;

    @Column(name = "public_ip")
    private String publicIp;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "uptime_minutes")
    private Long uptimeMinutes;

    @OneToMany(mappedBy = "system",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<CpuMetricsEntity> cpuMetricsEntities = new ArrayList<>();

    @OneToMany(mappedBy = "system",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<MemoryMetricsEntity> memoryMetricsEntities = new ArrayList<>();

    @OneToMany(mappedBy = "system",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<DiskMetricsEntity> diskMetricsEntities = new ArrayList<>();

    @OneToMany(mappedBy = "system",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<NetworkMetricsEntity> networkMetricsEntities = new ArrayList<>();
}