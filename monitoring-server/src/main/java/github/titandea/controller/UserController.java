package github.titandea.controller;

import github.titandea.dto.create.User;
import github.titandea.dto.response.RoleResponse;
import github.titandea.dto.response.UserResponse;
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
 * Контроллер для работы с пользователями.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    /**
     * Получение списка пользователей кратко.
     */
    @GetMapping
    public List<UserSummaryResponse> getAllUsers() {
        return userService.getAllUsersSummary();
    }

    /**
     * Получение пользователя по UUID.
     */
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }


    /**
     * Создание нового пользователя.
     */
    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    /**
     * Удаление пользователя по UUID.
     */
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }

    /**
     * Редактирование пользователя по UUID.
     */
    @PatchMapping("/{id}")
    public void changeUserById(@PathVariable UUID id, @RequestBody User user) {
        userService.changeUserById(id, user);
    }

    /**
     * Получение доступных ролей для установки пользователю.
     */
    @GetMapping("/roles")
    public List<RoleResponse> getRoles() {
        return UserRole.getAllRoles();
    }
}
