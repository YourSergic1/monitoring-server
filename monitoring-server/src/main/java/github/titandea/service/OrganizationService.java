package github.titandea.service;

import github.titandea.dto.create.Organization;
import github.titandea.dto.response.OrganizationResponse;
import github.titandea.entity.OrganizationEntity;
import github.titandea.mapper.CreateDtoToEntityMapper;
import github.titandea.mapper.EntityToResponseDtoMapper;
import github.titandea.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    /**
     * Получение списка организаций.
     */
    public List<OrganizationResponse> getAllOrganizations() {
        return organizationRepository.findAll().stream()
                .map(organizationEntity ->
                        entityToResponseDtoMapper.toOrganizationResponse(organizationEntity))
                .collect(Collectors.toList());
    }

    /**
     * Создание новой организации.
     */
    public ResponseEntity<UUID> createOrganization(@RequestBody Organization organization) {
        OrganizationEntity organizationEntity = createDtoToEntityMapper.toOrganizationEntity(organization);
        organizationRepository.save(organizationEntity);
        return ResponseEntity.ok(organizationEntity.getId());
    }
}
