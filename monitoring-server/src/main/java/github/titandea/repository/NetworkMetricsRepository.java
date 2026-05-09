package github.titandea.repository;

import github.titandea.entity.NetworkMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Репозиторий метрик соединений.
 */
public interface NetworkMetricsRepository extends JpaRepository<NetworkMetricsEntity, UUID> {
}
