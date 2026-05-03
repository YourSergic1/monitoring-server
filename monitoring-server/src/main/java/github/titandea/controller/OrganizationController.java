package github.titandea.controller;

import github.titandea.dto.Organization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organzations")
public class OrganizationController {

    @GetMapping
    public List<Organization> getAllOrganizations() {

    }
}
