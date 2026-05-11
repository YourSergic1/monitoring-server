package github.titandea.repository;

import github.titandea.entity.UserEntity;
import github.titandea.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий юзера.
 */
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findAllByRole(UserRole role);
}
