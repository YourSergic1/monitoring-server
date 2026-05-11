package github.titandea.service;

import github.titandea.dto.create.User;
import github.titandea.entity.UserEntity;
import github.titandea.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UUID createUser(User userCreateDto) {
        String rawPassword = generateSecurePassword(12);

        String hashedPassword = passwordEncoder.encode(rawPassword);

        UserEntity user = UserEntity.builder()
                .name(userCreateDto.getName())
                .surname(userCreateDto.getSurname())
                .patronymic(userCreateDto.getPatronymic())
                .phone(userCreateDto.getPhone())
                .email(userCreateDto.getEmail())
                .role(userCreateDto.getRole())
                .password(hashedPassword)
                .build();

        userRepository.save(user);

        try {
            String userEmail = userCreateDto.getEmail();
            String userFullName = String.format("%s %s %s",
                    userCreateDto.getSurname(),
                    userCreateDto.getName(),
                    userCreateDto.getPatronymic()).trim();

            emailService.sendCredentials(userEmail, userFullName, rawPassword);
            log.info("Учетные данные отправлены на {}", userEmail);
        } catch (Exception e) {
            log.error("Ошибка отправки пароля на почту: {}", e.getMessage(), e);
        }

        return user.getId();
    }

    private String generateSecurePassword(int length) {
        SecureRandom random = new SecureRandom();
        String alphabet = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789!@#$%&";
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return password.toString();
    }
}