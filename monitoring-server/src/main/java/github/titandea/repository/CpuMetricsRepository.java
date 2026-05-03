package github.titandea.repository;

import github.titandea.entity.CpuMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CpuMetricsRepository extends JpaRepository<CpuMetricsEntity, UUID> {
}
