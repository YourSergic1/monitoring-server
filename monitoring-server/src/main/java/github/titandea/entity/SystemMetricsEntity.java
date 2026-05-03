package github.titandea.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "system_metrics",
        uniqueConstraints = @UniqueConstraint(name = "uq_system_ip_time", columnNames = {"local_ip", "date_time"}))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SystemMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
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

    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CpuMetricsEntity> cpuMetricsEntities = new HashSet<>();

    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemoryMetricsEntity> memoryMetricsEntities = new HashSet<>();

    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DiskMetricsEntity> diskMetricsEntities = new HashSet<>();

    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NetworkMetricsEntity> networkMetricsEntities = new HashSet<>();
}