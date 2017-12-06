package ca.expertus.challenge.domains.game.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class SegmentationAnswer {

    @NonNull
    protected SegmentationQuestion question;

    @NonNull
    protected boolean correct;

    @NonNull
    protected int nbPersonsRejected;
}
