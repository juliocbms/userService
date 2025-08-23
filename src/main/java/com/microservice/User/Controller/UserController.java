package com.microservice.User.Controller;

import com.microservice.User.Config.Security.TokenService;
import com.microservice.User.Models.DTO.AuthenticationDTO;
import com.microservice.User.Models.DTO.LoginResponseDTO;
import com.microservice.User.Models.DTO.RegisterDTO;
import com.microservice.User.Models.DTO.UpdateDTO;
import com.microservice.User.Models.Entities.User;
import com.microservice.User.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Tag(name = "Autenticar usuario", description = "autentica usuario")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(),dto.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return  ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping
    @Tag(name = "Salvar usuario", description = "salva usuario")
    public ResponseEntity<User> save(@RequestBody @Valid RegisterDTO registerDTO){
        User user  = userService.fromDTO(registerDTO);
        user = userService.saveUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @Tag(name = "Listar usuarios", description = "lista usuarios")
    public ResponseEntity<List<User>> listaUsuarios(){
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @Tag(name = "Buscar usuario por Id", description = "busca usuario por id")
    public ResponseEntity<User> getById(@PathVariable UUID id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    @Tag(name = "Atualizar usuario", description = "atualiza usuario")
    public ResponseEntity<User> atualizar(@PathVariable UUID id, @RequestBody UpdateDTO updateDTO){
        User user = userService.update(id, updateDTO);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    @Tag(name = "Deletar usuario", description = "deleta usuario")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
