package ca.expertus.challenge.domains.game.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;

@Log4j2
public class JsonFileManipulator {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static boolean saveToFile(String file, Object elements) {

        try {
            MAPPER.writeValue(new File(file), elements);
            return true;
        } catch (IOException e) {
            log.error(e);
            return false;
        }
    }

    public static <T> T fromFileToObject(File file, TypeReference<T> typeRef) {
        T t = null;
        try {
            t = MAPPER.readValue(file, typeRef);
        } catch (IOException e) {
            log.error(e);
        }
        return t;
    }
}
