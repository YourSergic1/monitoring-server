package github.titandea.enums;

import github.titandea.dto.response.RoleResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Роли пользователя.
 */
public enum UserRole {
    ENGINEER("Инженер по обслуживанию"),
    MANAGER("Менеджер по работе с клиентами"),
    ADMIN("Администратор");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<RoleResponse> getAllRoles() {
        return Arrays.stream(UserRole.values()).map(
                UserRole -> new RoleResponse(UserRole.name(), UserRole.displayName)).toList();

    }
}
