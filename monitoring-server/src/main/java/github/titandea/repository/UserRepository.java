package github.titandea.repository;

import github.titandea.entity.AgentEntity;
import github.titandea.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий юзера.
 */
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
