package com.cesul.barber.controller;

import com.cesul.barber.dto.UserCreationDTO;
import com.cesul.barber.dto.UserDTO;
import com.cesul.barber.dto.UserUpdateDTO;
import com.cesul.barber.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Listar todos", description = "Retorna a lista de todos os usuários cadastrados.")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Usuário", description = "Atualiza todos os dados do usuário apartir do ID informado")
    public ResponseEntity<UserDTO> update(@PathVariable UUID id, @Valid @RequestBody UserUpdateDTO updatedUser) {

        Optional<UserDTO> userUpdated = userService.update(id, updatedUser);
        if (userUpdated.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userUpdated.get());
    }

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Cadastra um novo usuário no servidor")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserCreationDTO user) {

        UserDTO newUser = userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PatchMapping("/{id}/avatar")
    @Operation(summary = "Atualizar Avatar", description = "Atualizar o avatar do usuário apartir do ID informado")
    public ResponseEntity<UserDTO> patchUser(@PathVariable UUID id, @RequestBody Map<String, Object> avatar) {

        Optional<UserDTO> userUpdated = userService.updateAvatar(id, avatar);

        if (userUpdated.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userUpdated.get());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Usuário", description = "Deleta um usuário do servidor apartir do ID informado")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        if (userService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/clear")
    @Operation(summary = "Limpar todos os dados", description = "Limpa todos os dados do servidor")
    public ResponseEntity<Void> clearUsers() {
        userService.clear();
        return ResponseEntity.noContent().build();
    }


}
