package com.game.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "rounds", "status", "createdAt"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter(AccessLevel.PRIVATE)
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GameStatus status;

    @Range(min=3, max=100)
    @Column(name = "rounds", nullable = false)
    private int rounds = 6;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JoinColumn(name = "created_at", nullable = false)
    private Date createdAt;

    @ManyToOne
    @CreatedBy
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne
    @CreatedBy
    @JoinColumn(name = "winner")
    private Player winner;

    @Setter(AccessLevel.PRIVATE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_player_id", nullable = true)
    private Player firstPlayer;

    @Setter(AccessLevel.PRIVATE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_player_id", nullable = true)
    private Player secondPlayer;

    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Pit> pits;

    public Game(User user, int rounds) {
        this.rounds = rounds;
        this.createdBy = user;
        this.status = GameStatus.WAITING_PLAYER;
        this.createdAt =  new Date();
        this.pits = new ArrayList<>();
    }

    public boolean isActive() {
        return this.status == GameStatus.IN_PROGRESS;
    }

    public void joinPlayer(Player player) {
        Assert.notNull(player, "You need to inform a player who will join the game.");

        if (this.firstPlayer != null && this.secondPlayer != null) {
            throw new IllegalStateException("We already have two players in this match.");
        }

        if (this.firstPlayer == null) {
            this.firstPlayer = player;
        } else  {
            this.secondPlayer = player;
            this.status = GameStatus.IN_PROGRESS;
        }

        buildPits(player);
    }

    public void finishGame() {
        this.status = GameStatus.FINISHED;
        this.winner = calculateWinner();
    }

    private Player calculateWinner() {
        int totalPiecesPlayer1 = this.firstPlayer.getPits().stream().mapToInt(pit -> pit.getPieces()).reduce(0, Integer::sum);
        int totalPiecesPlayer2 = this.secondPlayer.getPits().stream().mapToInt(pit -> pit.getPieces()).reduce(0, Integer::sum);

        if (totalPiecesPlayer1 >= totalPiecesPlayer2) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }

    private void buildPits(Player player) {
        int numberOfPits = this.rounds + 1;

        this.pits.add(Pit.builder().index(0).game(this).type(PitType.BIG).player(player).build());

        for (int index = 1; index < numberOfPits; index++) {
            this.pits.add(Pit.builder().index(index).game(this).type(PitType.REGULAR).player(player).build());
        }
    }
}
