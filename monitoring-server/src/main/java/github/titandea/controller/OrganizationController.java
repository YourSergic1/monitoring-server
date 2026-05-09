package github.titandea.controller;

import github.titandea.dto.create.Organization;
import github.titandea.dto.response.OrganizationResponse;
import github.titandea.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с организациями.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    /**
     * Получение списка организаций.
     */
    @GetMapping
    public List<OrganizationResponse> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    /**
     * Создание новой организации.
     */
    @PostMapping
    public ResponseEntity<UUID> createOrganization(@RequestBody Organization organization) {
        return organizationService.createOrganization(organization);
    }
}
