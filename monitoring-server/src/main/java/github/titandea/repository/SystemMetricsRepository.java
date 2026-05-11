package github.titandea.repository;

import github.titandea.entity.SystemMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий метрик системных метрик.
 */
public interface SystemMetricsRepository extends JpaRepository<SystemMetricsEntity, UUID> {

    @Query("SELECT DISTINCT s FROM SystemMetricsEntity s " +
            "LEFT JOIN FETCH s.cpuMetricsEntities " +
            "LEFT JOIN FETCH s.memoryMetricsEntities " +
            "LEFT JOIN FETCH s.diskMetricsEntities " +
            "LEFT JOIN FETCH s.networkMetricsEntities " +
            "WHERE s.agent.id = :agentId " +
            "AND s.dateTime >= :startTime " +
            "AND s.dateTime <= :endTime " +
            "ORDER BY s.dateTime DESC")
    List<SystemMetricsEntity> findByAgentIdAndDateTimeRange(
            @Param("agentId") UUID agentId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

    boolean existsByLocalIpAndDateTime(String localIp, LocalDateTime dateTime);
}
