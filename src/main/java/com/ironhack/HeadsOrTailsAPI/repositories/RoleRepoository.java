package com.ironhack.HeadsOrTailsAPI.repositories;

import com.ironhack.HeadsOrTailsAPI.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepoository extends JpaRepository<Role,Long> {
}
