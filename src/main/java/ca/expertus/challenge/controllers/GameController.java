package ca.expertus.challenge.controllers;


import ca.expertus.challenge.components.SessionInfo;
import ca.expertus.challenge.domains.game.components.GameInfo;
import ca.expertus.challenge.domains.game.models.Person;
import ca.expertus.challenge.domains.game.services.GameService;
import ca.expertus.challenge.domains.game.services.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/game")
@Log4j2
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    SessionInfo sessionInfo;

    @Value("${expertus.game.maxPersons}")
    int nbMaxPersonsForTheGame;

    @GetMapping("/")
    public String index() {
        return "game/index";
    }

    /**
     * Load a new game, set all datas
     * @return
     */
    @GetMapping("/start")
    public ModelAndView start() {
        //Init all infos for a new Game
        GameInfo gameInfo = gameService.createNewGame(nbMaxPersonsForTheGame);
        sessionInfo.setGameInfo(gameInfo);

        ModelAndView mv = new ModelAndView("game/start");
        mv.addObject("gameId", gameInfo.getId());
        mv.addObject("bodyOnLoad", "expertus.game.start");
        return mv;
    }

    @GetMapping("/end")
    public ModelAndView end() {
        GameInfo gameInfo = sessionInfo.getGameInfo();
        ModelAndView mv = new ModelAndView("game/end");
        mv.addObject("gameInfo", gameInfo);
        if(gameInfo.isSucceeded()) {
            Person person = gameInfo.getPersonsLeft().get(0);
            mv.addObject("personName", person.getFirstname()+" "+person.getLastname());
        }
        return mv;
    }

}
