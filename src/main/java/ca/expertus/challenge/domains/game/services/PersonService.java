package ca.expertus.challenge.domains.game.services;

import ca.expertus.challenge.domains.game.models.Person;
import ca.expertus.challenge.domains.game.utils.JsonFileManipulator;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class PersonService extends AbstractListService<Person> {

    public PersonService() {
        datasFileName = "persons.json";
    }

    @PostConstruct
    public void postConstruct() {
        try {
            elems = JsonFileManipulator.fromFileToObject(ResourceUtils.getURL("classpath:" + datasFileName),
                    new TypeReference<List<Person>>() {
                    });
            if (elems == null) {
                elems = new ArrayList<>();
            } else {
                initMap();
            }
        }
        catch(Exception e) {
            log.error(e);
        }
    }
}
