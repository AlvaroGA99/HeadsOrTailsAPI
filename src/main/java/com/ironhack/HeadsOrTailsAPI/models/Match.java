package com.ironhack.HeadsOrTailsAPI.models;

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
public abstract class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    private boolean headsWinner;

    private int headsBet;

    private int tailsBet;

    private Date date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "heads_user_id")
    private User headsUser;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tails_user_id")
    private User tailsUser;

}
