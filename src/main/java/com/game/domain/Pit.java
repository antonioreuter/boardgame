package com.game.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "index", "pieces" ,"type", "player", "game"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Pit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int index;

    @JsonIgnore
    @ManyToOne
    private Player player;

    @JsonIgnore
    @ManyToOne
    private Game game;

    @Version
    private int version;

    @Setter(AccessLevel.PRIVATE)
    private int pieces;

    @Enumerated(value = EnumType.STRING)
    private PitType type;

    public int removeAllPieces() {
        int totalPieces = this.pieces;
        this.pieces = 0;
        return totalPieces;
    }

    public void addPieces(int newPieces) {
        this.pieces += newPieces;
    }

    public void addPiece() {
        this.pieces += 1;
    }

    public boolean isEmpty() {
        return this.pieces == 0;
    }

    public boolean isBigPit() {
        return this.type.equals(PitType.BIG);
    }
}
