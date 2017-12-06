package ca.expertus.challenge.components;

import ca.expertus.challenge.domains.game.components.GameInfo;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@Data
@SessionScope
public class SessionInfo {

    private GameInfo gameInfo;
}
