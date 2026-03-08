package net.openathens.journal.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ApiKeyConstantsTest {

    @Test
    void journalApiKeyShouldMatchExpectedValue() {
        assertEquals("abc123", ApiKeyConstants.JOURNAL_API_KEY);
    }
}

