package ca.expertus.challenge.domains.game.models;


import ca.expertus.challenge.domains.game.models.interfaces.SimpleModelInterface;
import lombok.Data;
import lombok.NonNull;

@Data
public class Person implements SimpleModelInterface {

    @NonNull
    protected int id;

    @NonNull
    protected String picto;

    @NonNull
    protected String lastname;

    @NonNull
    protected String firstname;

    @NonNull
    protected boolean man;

    @NonNull
    protected boolean woman;

    @NonNull
    protected boolean teeth;

    @NonNull
    protected boolean noEar;

    @NonNull
    protected boolean oneEar;

    @NonNull
    protected boolean twoEars;

    @NonNull
    protected boolean beard;

    @NonNull
    protected boolean shirt;

    @NonNull
    protected boolean checkShirt;

    @NonNull
    protected String verificationKey;

}
