package com.ironhack.HeadsOrTailsAPI.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ironhack.HeadsOrTailsAPI.HeadsOrTailsApiApplication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "match_type", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
// Instead of using @JsonBackReference and @JsonManagedReference, i use @JsonIdentityInfo as it doesnt require an owner of the relationship
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

public abstract class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    private boolean headsWinner;

    private int headsBet;

    private int tailsBet;

    private Date date;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "heads_user_id")
    private User headsUser;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "tails_user_id")
    private User tailsUser;

}
