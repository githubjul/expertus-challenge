package ca.expertus.challenge.domains.game.services;

import ca.expertus.challenge.domains.game.models.Person;
import ca.expertus.challenge.domains.game.utils.JsonFileManipulator;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService extends AbstractListService<Person> {

    public PersonService() {
        datasFileName = "persons.json";
    }

    @PostConstruct
    public void postConstruct() {
        elems = JsonFileManipulator.fromFileToObject(new File(getClass().getClassLoader().getResource(datasFileName).getFile()),
                new TypeReference<List<Person>>() {
                });
        if (elems == null) {
            elems = new ArrayList<>();
        } else {
            initMap();
        }
    }
}
