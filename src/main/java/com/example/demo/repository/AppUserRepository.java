package com.example.demo.repository;

import com.example.demo.models.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByuserName(String userName);
}

