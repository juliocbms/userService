package com.microservice.User.Controller;

import com.microservice.User.Models.Entities.User;
import com.microservice.User.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Tag(name = "Salvar usuario", description = "salva usuario")
    public ResponseEntity<User> save(@RequestBody User user){
        user = userService.saveUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    @Tag(name = "Listar usuarios", description = "lista usuarios")
    public ResponseEntity<List<User>> listaUsuarios(){
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @Tag(name = "Buscar usuario por Id", description = "busca usuario por id")
    public ResponseEntity<User> getById(@PathVariable Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    @Tag(name = "Atualizar usuario", description = "atualiza usuario")
    public ResponseEntity<User> atualizar(@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        userService.update(id,user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    @Tag(name = "Deletar usuario", description = "deleta usuario")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
