package github.titandea.controller;

import github.titandea.dto.response.CalendarResponse;
import github.titandea.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    /**
     * Ручная генерация календаря на 1 год вперёд.
     */
    @PostMapping("/init")
    public void initializeCalendar() {
        calendarService.generateCalendarOneYearAhead();
    }

    /**
     * Получение расписания на месяц
     */
    @GetMapping("/month")
    public List<CalendarResponse> getCalendarByMonthAndYear(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return calendarService.getCalendarByMonthAndYear(year, month);
    }
}
