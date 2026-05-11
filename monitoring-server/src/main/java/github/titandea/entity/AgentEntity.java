package github.titandea.entity;

import github.titandea.enums.AgentState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сущность агента для БД.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agent")
public class AgentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "local_ip", length = 255)
    private String localIp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private OrganizationEntity organization;

    @Column(name = "continuous", nullable = false)
    private Boolean continuous;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "last_metric_received")
    private LocalDateTime lastMetricReceived;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SystemMetricsEntity> systemMetrics = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 20)
    private AgentState state;
}
