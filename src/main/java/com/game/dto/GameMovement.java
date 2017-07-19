package com.game.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"login", "gameId", "index"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameMovement implements Serializable {
    @NotEmpty
    private String login;
    @Range(min=0)
    private Long gameId;
    @Range(min=0, max=100)
    private int index;
}
