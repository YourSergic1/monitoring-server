package github.titandea.mapper;

import github.titandea.dto.response.AgentResponse;
import github.titandea.dto.response.OrganizationResponse;
import github.titandea.dto.response.OrganizationSummaryResponse;
import github.titandea.entity.AgentEntity;
import github.titandea.entity.OrganizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * Маппер для преобразования JPA-сущностей в Response DTO.
 */
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EntityToResponseDtoMapper {

    /**
     * Преобразует OrganizationEntity → OrganizationResponse
     */
    OrganizationResponse toOrganizationResponse(OrganizationEntity entity);

    /**
     * Преобразует OrganizationEntity → OrganizationSummaryResponse
     */
    OrganizationSummaryResponse toOrganizationSummaryResponse(OrganizationEntity entity);

    /**
     * Преобразует AgentEntity → AgentResponse
     */
    AgentResponse toAgentResponse(AgentEntity entity);
}