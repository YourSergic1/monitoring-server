package github.titandea.mapper;

import github.titandea.dto.response.*;
import github.titandea.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * Маппер для преобразования JPA-сущностей в Response DTO.
 */
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EntityToResponseDtoMapper {
    // === Существующие мапперы ===
    OrganizationResponse toOrganizationResponse(OrganizationEntity entity);

    OrganizationSummaryResponse toOrganizationSummaryResponse(OrganizationEntity entity);

    AgentSummaryResponse toAgentSummaryResponse(AgentEntity entity);

    // === Мапперы для метрик (элементы) ===
    CpuMetricsResponse toCpuMetricsResponse(CpuMetricsEntity entity);

    MemoryMetricsResponse toMemoryMetricsResponse(MemoryMetricsEntity entity);

    DiskMetricsResponse toDiskMetricsResponse(DiskMetricsEntity entity);

    NetworkMetricsResponse toNetworkMetricsResponse(NetworkMetricsEntity entity);

    // === Основной маппер системных метрик ===
    // MapStruct автоматически использует методы выше для преобразования Set<Entity> -> Set<DTO>
    SystemMetricsResponse toSystemMetricsResponse(SystemMetricsEntity entity);

    CalendarResponse toCalendarResponse(CalendarEntity entity);
}