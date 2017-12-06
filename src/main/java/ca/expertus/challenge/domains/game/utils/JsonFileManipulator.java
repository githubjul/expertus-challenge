package ca.expertus.challenge.domains.game.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Log4j2
public class JsonFileManipulator {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T fromFileToObject(URL url, TypeReference<T> typeRef) {
        T t = null;
        try {
            t = MAPPER.readValue(url, typeRef);
        } catch (IOException e) {
            log.error(e);
        }
        return t;
    }
}
