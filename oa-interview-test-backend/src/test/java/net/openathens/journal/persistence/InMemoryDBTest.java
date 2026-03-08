package net.openathens.journal.persistence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.openathens.journal.types.transfer.JournalTO;

class InMemoryDBTest {

    InMemoryDB target;

    @Test
    void happyPath() {
        target = new InMemoryDB();

        List<JournalTO> journalTOS = target.fetchAll();

        assertThat(journalTOS.isEmpty(), is(false));
        assertThat(journalTOS.get(0).getAuthor(), is(notNullValue()));
    }

    //TODO: exceptional cases
}