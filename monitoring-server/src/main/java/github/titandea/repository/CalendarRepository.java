package github.titandea.repository;

import github.titandea.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Репозиторий календаря.
 */
@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, UUID> {
    boolean existsByDate(LocalDate date);

    List<CalendarEntity> getAllByDateBetween(LocalDate dateAfter, LocalDate dateBefore);
}
