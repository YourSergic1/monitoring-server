package github.titandea.service;


import github.titandea.dto.response.CalendarResponse;
import github.titandea.entity.CalendarEntity;
import github.titandea.entity.UserEntity;
import github.titandea.mapper.EntityToResponseDtoMapper;
import github.titandea.repository.CalendarRepository;
import github.titandea.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarService {

    private final CalendarRepository calendarRepository;

    private final UserRepository userRepository;

    private final EntityToResponseDtoMapper mapper;

    @Transactional
    public int generateCalendar(LocalDate startDate, LocalDate endDate) {
        log.info("Генерация календаря: {} → {}", startDate, endDate);

        AtomicInteger created = new AtomicInteger(0);

        startDate.datesUntil(endDate.plusDays(1))
                .forEach(date -> {
                    if (calendarRepository.existsByDate(date)) return;

                    CalendarEntity entry = CalendarEntity.builder()
                            .date(date)
                            .dayOfWeek(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru")))
                            .workingDay(isWorkingDay(date))
                            .build();

                    calendarRepository.save(entry);
                    created.incrementAndGet();
                });

        int result = created.get();
        log.info("Создано записей календаря: {}", result);
        return result;
    }

    @Transactional
    public int generateCalendarOneYearAhead() {
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusYears(1);
        return generateCalendar(start, end);
    }

    private boolean isWorkingDay(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY;
    }


    @Transactional
    @Scheduled(cron = "0 0 0 1 1,7 ?", zone = "Europe/Moscow")
    public void changeAgentState() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusYears(1);
        generateCalendar(startDate, endDate);
    }

    public List<CalendarResponse> getCalendarByMonthAndYear(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.with(java.time.temporal.TemporalAdjusters.lastDayOfMonth());
        return calendarRepository.findAllByDateBetweenOrderByDateAsc(startDate, endDate).stream().map(
                calendarEntity -> mapper.toCalendarResponse(calendarEntity)
        ).toList();
    }

    public void addManager(UUID dayId, UUID employeeId) {
        CalendarEntity calendarEntity = calendarRepository.findById(dayId).orElse(null);
        if (calendarEntity == null) return;
        if (employeeId == null) {
            calendarEntity.setUser(null);
        } else {
            UserEntity userEntity = userRepository.findById(employeeId).orElse(null);
            calendarEntity.setUser(userEntity);
        }
        calendarRepository.save(calendarEntity);
    }

    public CalendarEntity getCalendarEntityByDay(LocalDate date) {
        return calendarRepository.findByDate(date);
    }
}