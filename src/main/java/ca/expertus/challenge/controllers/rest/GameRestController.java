package ca.expertus.challenge.controllers.rest;

import ca.expertus.challenge.components.MessageSourceExternalizer;
import ca.expertus.challenge.components.SessionInfo;
import ca.expertus.challenge.domains.game.components.GameInfo;
import ca.expertus.challenge.domains.game.components.GameStatus;
import ca.expertus.challenge.domains.game.models.Person;
import ca.expertus.challenge.domains.game.models.SegmentationQuestion;
import ca.expertus.challenge.domains.game.services.GameService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class GameRestController {

    @Autowired
    MessageSourceExternalizer messageSourceExternalizer;


    @Autowired
    GameService gameService;

    @Autowired
    SessionInfo sessionInfo;

    /**
     * This method retrieve the gameInfo of the session only if it matches with the gameId provided
     * @param gameId
     * @return
     */
    private GameInfo retrieveGameInfo(String gameId) {
        GameInfo gameInfo = sessionInfo.getGameInfo();
        if(gameInfo != null && gameId.equals(gameInfo.getId())) {
            return gameInfo;
        }
        return null;
    }

    @GetMapping("/listPersons/{gameId:.*}")
    public ResponseEntity<List<Person>> listPersons(@PathVariable String gameId) {
        GameInfo gameInfo = retrieveGameInfo(gameId);

        if(gameInfo != null) {
            return new ResponseEntity<>(gameInfo.getPersonsLeft(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @GetMapping("/loadQuestion/{gameId:.*}")
    public ResponseEntity<SegmentationQuestion> loadQuestion(@PathVariable String gameId) {
        GameInfo gameInfo = retrieveGameInfo(gameId);

        if(gameInfo != null) {
            SegmentationQuestion question = gameService.nextQuestion(gameInfo);
            if (question != null) {
                question.setLabel(messageSourceExternalizer.getMessage("game.question." + question.getKey()));
                return new ResponseEntity<>(question, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/answer/{gameId:[A-Za-z0-9\\-]+}/{segmentationQuestionId:[0-9]+}")
    public ResponseEntity<GameStatus> answer(@PathVariable String gameId, @PathVariable int segmentationQuestionId, @RequestParam boolean correct) {
        GameInfo gameInfo = retrieveGameInfo(gameId);
        if(gameInfo != null) {
            gameInfo = gameService.manageAnswer(gameInfo, segmentationQuestionId, correct);
            sessionInfo.setGameInfo(gameInfo);
            return new ResponseEntity<>(new GameStatus(gameInfo.getPersonsLeft().size(),gameInfo.isFinished(), gameInfo.isSucceeded()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
