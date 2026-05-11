package github.titandea.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "calendar",
        uniqueConstraints = @UniqueConstraint(columnNames = "date"))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "day_of_week", nullable = false, length = 20)
    private String dayOfWeek;

    @Column(name = "is_working_day", nullable = false)
    private boolean workingDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}