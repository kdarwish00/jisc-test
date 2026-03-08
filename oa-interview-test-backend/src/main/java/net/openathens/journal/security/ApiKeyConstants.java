package net.openathens.journal.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Central place for API key configuration used to authorise journal operations.
 * Key is loaded from application config (e.g. journal.api.key).
 */
@Component
public class ApiKeyConstants {

    private final String journalApiKey;

    public ApiKeyConstants(@Value("${journal.api.key}") String journalApiKey) {
        this.journalApiKey = journalApiKey;
    }

    public String getJournalApiKey() {
        return journalApiKey;
    }
}
