package org.example.havayolu.controller;

import org.example.havayolu.dto.CreateUserDto;
import org.example.havayolu.dto.LoginRequestDto;
import org.example.havayolu.entity.Users;
import org.example.havayolu.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<Users> createUsers(@RequestBody CreateUserDto UserDto){
        return new ResponseEntity<>(usersService.createUser(UserDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequest) {
        String tc = loginRequest.getTc();
        String password = loginRequest.getPassword();

        try {
            return ResponseEntity.ok(usersService.loginUser(tc, password));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    
    @PutMapping("/updateRole/{userId}")
    public ResponseEntity<Void> updateUserRole(@PathVariable("userId") int userId) {
        usersService.updateUserRole(userId);
        return ResponseEntity.noContent().build();
    }
}