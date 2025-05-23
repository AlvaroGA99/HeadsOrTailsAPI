package com.ironhack.HeadsOrTailsAPI.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
//Same as match
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "username"
)
public class User {


    @Id
    @Column(name = "username")
    private String username;
    private String password;
    private int elo;
    private int coins;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_roles")
    private Collection<Role> roles;


    @OneToMany(mappedBy = "headsUser")
    private Set<Match> headsMatches;

    @OneToMany(mappedBy = "tailsUser")
    private Set<Match> tailsMatches;



}
