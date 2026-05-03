package github.titandea.repository;

import github.titandea.entity.DiskMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiskMetricsRepository extends JpaRepository<DiskMetricsEntity, UUID> {
}
