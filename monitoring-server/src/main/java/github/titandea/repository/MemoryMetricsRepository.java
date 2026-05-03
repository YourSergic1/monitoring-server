package github.titandea.repository;

import github.titandea.entity.MemoryMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemoryMetricsRepository extends JpaRepository<MemoryMetricsEntity, UUID> {
}
