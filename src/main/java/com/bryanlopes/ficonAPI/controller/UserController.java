package com.bryanlopes.ficonAPI.controller;

import com.bryanlopes.ficonAPI.user.DataUserList;
import com.bryanlopes.ficonAPI.user.DataUserRegister;
import com.bryanlopes.ficonAPI.user.User;
import com.bryanlopes.ficonAPI.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<DataUserList> search(@PageableDefault(size=10, sort={"name"}) Pageable pagination) {
        return repository.findAll(pagination).map(DataUserList::new);
    }

    @GetMapping("/{userId}")
    public DataUserList searchById(@PathVariable Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        return new DataUserList(user);
    }
}
