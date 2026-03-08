package net.openathens.journal.types.rest;

import java.util.List;

public class JournalResponseList {

    private List<JournalResponse> journals;

    public JournalResponseList( List<JournalResponse> journals) {
        this.journals = journals;
    }

    public void setJournals(List<JournalResponse> journals) {
        this.journals = journals;
    }

    public List<JournalResponse> getJournals() {
        return journals;
    }
}
