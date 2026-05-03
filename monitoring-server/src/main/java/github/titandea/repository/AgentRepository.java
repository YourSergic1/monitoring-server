package github.titandea.repository;

import github.titandea.entity.AgentEntity;
import github.titandea.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AgentRepository extends JpaRepository<AgentEntity, UUID> {

    Optional<AgentEntity> findByLocalIp(String localIp);
}
