package com.bryanlopes.ficonAPI.controller;

import com.bryanlopes.ficonAPI.user.DataUserRegister;
import com.bryanlopes.ficonAPI.user.User;
import com.bryanlopes.ficonAPI.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataUserRegister data) {
        repository.save(new User(data));
    }

    public void searchById() {

    }
}
