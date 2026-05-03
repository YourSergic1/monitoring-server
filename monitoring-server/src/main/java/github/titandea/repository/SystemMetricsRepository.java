package github.titandea.repository;

import github.titandea.entity.SystemMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface SystemMetricsRepository extends JpaRepository<SystemMetricsEntity, UUID> {

    @Query("SELECT DISTINCT s FROM SystemMetricsEntity s " +
            "LEFT JOIN FETCH s.cpuMetricsEntities " +
            "LEFT JOIN FETCH s.memoryMetricsEntities " +
            "LEFT JOIN FETCH s.diskMetricsEntities " +
            "LEFT JOIN FETCH s.networkMetricsEntities " +
            "WHERE s.id = :id")
    Optional<SystemMetricsEntity> findByIdWithAllMetrics(@Param("id") UUID id);

    boolean existsByLocalIpAndDateTime(String localIp, LocalDateTime dateTime);
}
