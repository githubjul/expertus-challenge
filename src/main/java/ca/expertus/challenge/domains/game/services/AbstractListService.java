package ca.expertus.challenge.domains.game.services;

import ca.expertus.challenge.domains.game.models.interfaces.SimpleModelInterface;
import ca.expertus.challenge.domains.game.utils.JsonFileManipulator;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractListService<T extends SimpleModelInterface> {

    List<T> elems = new ArrayList<>();

    protected String datasFileName;

    Map<Integer, T> elemsMap = new HashMap<>();

    public void initMap() {
        elemsMap.clear();
        for (T elem : elems) {
            elemsMap.put(elem.getId(), elem);
        }
    }

    public T findOne(int id) {
        return elemsMap.get(id);
    }

    public List<T> list() {
        return elems;
    }
}
