package ca.expertus.challenge.domains.game.models;


import ca.expertus.challenge.domains.game.models.interfaces.SimpleModelInterface;
import lombok.Data;
import lombok.NonNull;

@Data
public class SegmentationQuestion implements SimpleModelInterface {

    @NonNull
    protected int id;

    @NonNull
    protected String key;

    protected String label;

}
