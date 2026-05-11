package github.titandea.controller;

import github.titandea.dto.create.User;
import github.titandea.dto.response.OrganizationResponse;
import github.titandea.dto.response.RoleResponse;
import github.titandea.dto.response.UserSummaryResponse;
import github.titandea.enums.UserRole;
import github.titandea.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с юзерами.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    /**
     * Получение списка организаций кратко.
     */
    @GetMapping
    public List<UserSummaryResponse> getAllUsers() {
        return userService.getAllUsersSummary();
    }




    /**
     * Создание нового юзера.
     */
    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    /**
     * Получение доступных ролей.
     */
    @GetMapping("/roles")
    public List<RoleResponse> getRoles() {
        return UserRole.getAllRoles();
    }
}
