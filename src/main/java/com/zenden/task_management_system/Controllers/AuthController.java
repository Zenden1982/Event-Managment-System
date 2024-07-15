package com.zenden.task_management_system.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zenden.task_management_system.Autorization.JwtTokenUtils;
import com.zenden.task_management_system.Classes.DTO.JwtRequest;
import com.zenden.task_management_system.Classes.DTO.JwtResponse;
import com.zenden.task_management_system.Classes.DTO.UserDTO;
import com.zenden.task_management_system.Repositories.UserRepository;
import com.zenden.task_management_system.Services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "AuthController", description = "Контроллер для аутентификации и регистрации пользователей")
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Operation(summary = "Аутентификация пользователя и выдача JWT токена")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешная аутентификация", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtResponse.class))),
        @ApiResponse(responseCode = "401", description = "Неправильное имя пользователя или пароль", 
                     content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/auth")
    @Transactional
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        log.info(userRepository.findByUsername(request.getUsername()).get().toString());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неправильное имя пользователя или пароль");
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешная регистрация", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)))
    })
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody UserDTO request) {
        log.info("Новый пользователь зарегистрирован: " + request);
        userService.createUser(request);
        return ResponseEntity.ok(request);
    }

    @Operation(summary = "Эндпоинт для администраторов")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Контент для администраторов", 
                     content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/admin")
    @Transactional
    public String getAdminContent() {
        return "Администрация";
    }

    @Operation(summary = "Эндпоинт для пользователей")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Контент для пользователей", 
                     content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/user")
    @Transactional
    public String getUserContent() {
        return "Пользователь";
    }
}

