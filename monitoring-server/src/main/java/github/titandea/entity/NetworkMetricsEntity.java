package github.titandea.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Сущность метрик соединений.
 */
@Entity
@Getter
@Setter
@Table(name = "network_metrics",
        indexes = @Index(name = "idx_network_system", columnList = "system_id"))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NetworkMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", nullable = false)
    private SystemMetricsEntity system;

    @Column(name = "interface_name", length = 255)
    private String interfaceName;

    @Column(name = "bytes_sent")
    private Long bytesSent;

    @Column(name = "bytes_recv")
    private Long bytesRecv;

    @Column(name = "packets_sent")
    private Long packetsSent;

    @Column(name = "packets_recv")
    private Long packetsRecv;

    @Column(name = "in_errors")
    private Long inErrors;

    @Column(name = "out_errors")
    private Long outErrors;

    @Column(name = "speed")
    private Long speed;
}