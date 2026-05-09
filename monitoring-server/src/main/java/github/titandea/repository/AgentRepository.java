package github.titandea.repository;

import github.titandea.entity.AgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий агента.
 */
public interface AgentRepository extends JpaRepository<AgentEntity, UUID> {
    List<AgentEntity> findByOrganizationId(UUID organizationId);

    Optional<AgentEntity> findByLocalIp(String localIp);
}
