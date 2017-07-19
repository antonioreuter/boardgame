package com.game.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.game.exceptions.IllegalMovementException;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of =  {"id", "currentRound", "pieces", "game", "user"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="current_round")
    private int currentRound;

    @Setter(AccessLevel.PRIVATE)
    @Column(name="pieces")
    private int pieces;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Pit> pits;

    public Player(User user, Game game) {
        this.user = user;
        this.game = game;
        this.currentRound = 1;
        this.addPieces(game.getRounds());
    }

    public Player(User user, Game game, List<Pit> pits) {
        this(user, game);
        this.pits = pits;
        this.pits.stream().forEach(p -> p.setPlayer(this));
    }

    public boolean lastPiece() {
        return pieces == 1;
    }

    public void move(Integer index) {
        if (index < 0 || index > game.getRounds()) {
            throw new IllegalArgumentException("Invalid index");
        }

        if (!game.isActive()) {
            throw new IllegalMovementException("The game is not active.");
        }

        if (this.pieces == 0 && this.isLastRound()) {
            throw new IllegalMovementException("You don't have pieces enough to execute this movement.");
        }

        this.pieces -= 1;

        if (this.pieces == 0 && !this.isLastRound()) {
            this.addPieces(this.game.getRounds());
            this.currentRound += 1;
        }

        Pit pit = getPitByIndex(index);
        pit.addPiece();
    }

    public boolean isLastRound() {
        return (this.currentRound == this.game.getRounds());
    }

    public boolean finished() {
        return this.isLastRound() && (this.pieces == 0);
    }

    public Pit getPitByIndex(final Integer index) {
        if (index  > this.pits.size()) {
            throw new IllegalArgumentException(String.format("The index must be between %d and %d", 0, this.pits.size()));
        }
        if (this.pits.isEmpty()) {
            throw new RuntimeException("There is no pits loaded");
        } else {
            return pits.stream().filter(p -> p.getIndex() == index).findFirst().get();
        }
    }

    public List<Pit> getPits() {
        return this.pits;
    }

    private void addPieces(int pieces) {
        this.pieces += pieces;
    }
}
