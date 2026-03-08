package net.openathens.journal.types.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreateJournalRequestTest {

    @Test
    void gettersAndSettersShouldWorkAsExpected() {
        CreateJournalRequest request = new CreateJournalRequest();
        request.setText("Some text");
        request.setAuthor("Some author");

        assertEquals("Some text", request.getText());
        assertEquals("Some author", request.getAuthor());
    }
}

