package net.openathens.journal.persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.openathens.journal.types.transfer.JournalTO;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class InMemoryDB {

    private final String FILE_NAME = "src/main/resources/db.json";
    private final List<JournalTO> CACHE = new ArrayList<>();

    private final ObjectMapper mapper = new ObjectMapper();

    public InMemoryDB() {

        String contents = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contents += line;
            }

            CACHE.addAll(mapper.readValue(contents, new TypeReference<>() {}));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<JournalTO> fetchAll() {
        return CACHE;
    }

    public void add(JournalTO newJournal) {
        CACHE.add(newJournal);
    }
}
