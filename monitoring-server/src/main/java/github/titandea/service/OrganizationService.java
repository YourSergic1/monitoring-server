package github.titandea.service;

import github.titandea.dto.create.Organization;
import github.titandea.dto.response.OrganizationResponse;
import github.titandea.dto.response.OrganizationSummaryResponse;
import github.titandea.entity.OrganizationEntity;
import github.titandea.enums.AgentState;
import github.titandea.mapper.CreateDtoToEntityMapper;
import github.titandea.mapper.EntityToResponseDtoMapper;
import github.titandea.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис для работы с организациями.
 */
@RequiredArgsConstructor
@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final CreateDtoToEntityMapper createDtoToEntityMapper;

    private final EntityToResponseDtoMapper entityToResponseDtoMapper;


    public List<OrganizationSummaryResponse> getAllOrganizationsSummary() {
        return organizationRepository.findAll().stream()
                .map(organizationEntity ->
                        entityToResponseDtoMapper.toOrganizationSummaryResponse(organizationEntity))
                .collect(Collectors.toList());
    }

    public ResponseEntity<UUID> createOrganization(Organization organization) {
        OrganizationEntity organizationEntity = createDtoToEntityMapper.toOrganizationEntity(organization);
        organizationEntity.setState(AgentState.OK);
        organizationRepository.save(organizationEntity);
        return ResponseEntity.ok(organizationEntity.getId());
    }


    public OrganizationResponse getOrganizationById(UUID uuid) {
        return entityToResponseDtoMapper.toOrganizationResponse(
                organizationRepository.findById(uuid).orElse(null));
    }

    @Transactional
    public void deleteOrganizationById(UUID uuid) {
        organizationRepository.deleteById(uuid);
    }

    @Transactional
    public void changeOrganizationById(UUID uuid, Organization organization) {
        OrganizationEntity organizationEntity = organizationRepository.findById(uuid).orElse(null);
        if (StringUtils.isNoneEmpty(organization.getAddress())) {
            organizationEntity.setAddress(organization.getAddress());
        }
        if (StringUtils.isNoneEmpty(organization.getName())) {
            organizationEntity.setName(organization.getName());
        }
        if (StringUtils.isNoneEmpty(organization.getPhoneNumber())) {
            organizationEntity.setPhoneNumber(organization.getPhoneNumber());
        }
        if (StringUtils.isNoneEmpty(organization.getContactPerson())) {
            organizationEntity.setContactPerson(organization.getPhoneNumber());
        }
        organizationRepository.save(organizationEntity);
    }
}
