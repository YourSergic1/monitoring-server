package github.titandea.service;

import github.titandea.dto.create.Organization;
import github.titandea.dto.response.OrganizationResponse;
import github.titandea.dto.response.OrganizationSummaryResponse;
import github.titandea.entity.OrganizationEntity;
import github.titandea.mapper.CreateDtoToEntityMapper;
import github.titandea.mapper.EntityToResponseDtoMapper;
import github.titandea.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    public List<OrganizationSummaryResponse> getAllOrganizationsSummary() {
        return organizationRepository.findAll().stream()
                .map(organizationEntity ->
                        entityToResponseDtoMapper.toOrganizationSummaryResponse(organizationEntity))
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

    /**
     * Поиск организации по UUID.
     */
    public OrganizationResponse getOrganizationByUUID(@RequestParam UUID uuid) {
        return entityToResponseDtoMapper.toOrganizationResponse(
                organizationRepository.findById(uuid).orElse(null));
    }

    /**
     * Удаление организации по UUID.
     */
    @Transactional
    public void deleteOrganizationByUUID(@RequestParam UUID uuid) {
        organizationRepository.deleteById(uuid);
    }
}
