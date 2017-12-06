package ca.expertus.challenge.domains.game.components;

import ca.expertus.challenge.domains.game.models.Person;
import ca.expertus.challenge.domains.game.models.SegmentationAnswer;
import ca.expertus.challenge.domains.game.models.SegmentationQuestion;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameInfo {

    private String id;
    private List<Person> personsAtTheBeginning;
    private List<Person> personsLeft;
    private List<SegmentationQuestion> questionsAvailable;

    private List<SegmentationAnswer> answers = new ArrayList<>();

    private int nbQuestionsAsked = 0;

    private boolean finished = false;
    private boolean succeeded = false;
}
