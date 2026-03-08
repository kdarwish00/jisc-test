package net.openathens.journal.types.rest;

/**
 * Request body used to create a new journal article via the REST API.
 */
public class CreateJournalRequest {

    private String text;
    private String author;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

