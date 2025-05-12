package com.ironhack.HeadsOrTailsAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @Column(name = "username")
    private String username;
    private String password;
    private int elo;
    private int coins;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "headsUser",cascade = CascadeType.ALL)
    private Set<Match> headsMatches;

    @OneToMany(mappedBy = "tailsUser",cascade = CascadeType.ALL)
    private Set<Match> tailsMatches;



}
