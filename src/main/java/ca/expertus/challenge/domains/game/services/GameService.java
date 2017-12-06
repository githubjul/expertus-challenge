package ca.expertus.challenge.domains.game.services;

import ca.expertus.challenge.components.MessageSourceExternalizer;
import ca.expertus.challenge.domains.game.components.GameInfo;
import ca.expertus.challenge.domains.game.models.Person;
import ca.expertus.challenge.domains.game.models.SegmentationAnswer;
import ca.expertus.challenge.domains.game.models.SegmentationQuestion;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GameService {

    @Autowired
    PersonService personService;

    @Autowired
    SegmentationQuestionService segmentationQuestionService;


    /**
     * Return a random list of persons for a new game, and construct this list
     * safely, ie without having 2 persons with all informations identical
     * @param maxPersons to set in the game
     * @return
     */
    private List<Person> getUnifiedPersonListForNewGame(int maxPersons) {
        List<Person> allPersons = personService.list();
        List<Person> persons = new ArrayList<>();

        Set<String> verificationKeys = new HashSet<>();
        Collections.shuffle(allPersons);

        for(Person person : allPersons) {
            if(!verificationKeys.contains(person.getVerificationKey())) {
                verificationKeys.add(person.getVerificationKey());
                persons.add(person);
                if(persons.size() == maxPersons) {
                    break;
                }
            }
        }
        return persons;
    }

    /**
     * This method is used to verify if a question should remove persons on the submitted persons list
     * @param persons
     * @param question
     * @return
     */
    private boolean isSignificantQuestion(List<Person> persons, SegmentationQuestion question) {
        try {
            Class cls = Class.forName("ca.expertus.challenge.domains.game.models.Person");
            //Construct MethodName
            String methodName = "is"
                    + question.getKey().substring(0, 1).toUpperCase()
                    + question.getKey().substring(1);

            Method method = cls.getDeclaredMethod(methodName);
            boolean first = true;
            boolean lastAnswer = true;
            for (Person person : persons) {
                if (!first && lastAnswer != (boolean)method.invoke(person)) {
                    log.debug("{} est significatif", methodName);
                    return true;
                }
                first = false;
                lastAnswer = (boolean)method.invoke(person);
            }
            log.debug("{} n'est pas significatif", methodName);
        }
        catch(Exception e) {
            log.error(e);
        }

        return false;
    }

    public GameInfo createNewGame(int maxPersons) {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setId(UUID.randomUUID().toString());
        List<Person> personsForThisGame = getUnifiedPersonListForNewGame(maxPersons);
        List<SegmentationQuestion> questions = segmentationQuestionService.list();
        questions = questions.stream()
                .filter(localQuestion -> isSignificantQuestion(personsForThisGame, localQuestion))
                .collect(Collectors.toList());
        Collections.shuffle(questions);
        gameInfo.setPersonsAtTheBeginning(personsForThisGame);
        gameInfo.setPersonsLeft(personsForThisGame);
        gameInfo.setNbQuestionsAsked(0);
        gameInfo.setQuestionsAvailable(questions);
        return gameInfo;

    }

    public SegmentationQuestion nextQuestion(GameInfo gameInfo) {
        if(gameInfo.getQuestionsAvailable().size() > 0) {
            return gameInfo.getQuestionsAvailable().get(0);
        }
        else {
            return null;
        }
    }

    public GameInfo manageAnswer(GameInfo gameInfo, int segmentationQuestionId, boolean correct) {

        SegmentationQuestion question = segmentationQuestionService.findOne(segmentationQuestionId);

        //1st step : update persons left
        List<Person> personsBefore = gameInfo.getPersonsLeft();
        List<Person> personsAfter = new ArrayList<>();

        try {
            Class cls = Class.forName("ca.expertus.challenge.domains.game.models.Person");

            //Construct MethodName
            String methodName = "is"
                     + question.getKey().substring(0, 1).toUpperCase()
                     + question.getKey().substring(1);

            Method method = cls.getDeclaredMethod(methodName);
            for (Person person : personsBefore) {
                if ((boolean)method.invoke(person) == correct) {
                    personsAfter.add(person);
                }
            }
        }
        catch(Exception e) {
            log.error(e);
        }

        gameInfo.setPersonsLeft(personsAfter);

        if(personsAfter.size() == 1) { //Yes, we found it !
            gameInfo.setFinished(true);
            gameInfo.setSucceeded(true);
        } else if(personsAfter.size() == 0) { //Oh no, no person left... I've lost !
            gameInfo.setFinished(true);
            gameInfo.setSucceeded(false);
        }

        //2nd step : save answer
        List<SegmentationAnswer> answers = gameInfo.getAnswers();
        answers.add(new SegmentationAnswer(question, correct, personsBefore.size() - personsAfter.size()));
        gameInfo.setAnswers(answers);

        //3rd step : update questions relative informations
        List<SegmentationQuestion> questions = gameInfo.getQuestionsAvailable();
        questions = questions.stream()
                .filter(localQuestion -> localQuestion.getId() != question.getId() && isSignificantQuestion(personsAfter, localQuestion))
                .collect(Collectors.toList());

        gameInfo.setQuestionsAvailable(questions);
        //No more questions, but game not finished.... too bad !
        if(!gameInfo.isFinished() && questions.size() == 0) {
            gameInfo.setFinished(true);
            gameInfo.setSucceeded(false);
        }

        gameInfo.setNbQuestionsAsked(gameInfo.getNbQuestionsAsked()+1);

        return gameInfo;
    }
}
