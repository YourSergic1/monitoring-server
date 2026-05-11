package github.titandea.mapper;

import github.titandea.dto.response.*;
import github.titandea.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

/**
 * Маппер для преобразования JPA-сущностей в Response DTO.
 */
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EntityToResponseDtoMapper {
    OrganizationResponse toOrganizationResponse(OrganizationEntity entity);

    OrganizationSummaryResponse toOrganizationSummaryResponse(OrganizationEntity entity);

    AgentSummaryResponse toAgentSummaryResponse(AgentEntity entity);

    CpuMetricsResponse toCpuMetricsResponse(CpuMetricsEntity entity);

    MemoryMetricsResponse toMemoryMetricsResponse(MemoryMetricsEntity entity);

    DiskMetricsResponse toDiskMetricsResponse(DiskMetricsEntity entity);

    NetworkMetricsResponse toNetworkMetricsResponse(NetworkMetricsEntity entity);

    SystemMetricsResponse toSystemMetricsResponse(SystemMetricsEntity entity);

    @Mapping(target = "fullName", source = "user")
    @Mapping(target = "employeeId", source = "user.id")
    CalendarResponse toCalendarResponse(CalendarEntity entity);

    default String userToFullName(UserEntity user) {
        if (user == null) return null;

        StringBuilder sb = new StringBuilder();
        if (user.getSurname() != null) sb.append(user.getSurname()).append(" ");
        if (user.getName() != null) sb.append(user.getName()).append(" ");
        if (user.getPatronymic() != null) sb.append(user.getPatronymic());

        return sb.toString().trim();
    }

    UserSummaryResponse toUserSummaryResponse(UserEntity entity);

    UserResponse toUserResponse(UserEntity entity);
}