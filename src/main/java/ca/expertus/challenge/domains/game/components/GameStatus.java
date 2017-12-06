package ca.expertus.challenge.domains.game.components;

import lombok.Data;
import lombok.NonNull;

@Data
public class GameStatus {

    @NonNull
    private int nbPersonsLeft;

    @NonNull
    private boolean finished;

    @NonNull
    private boolean succeeded;
}
