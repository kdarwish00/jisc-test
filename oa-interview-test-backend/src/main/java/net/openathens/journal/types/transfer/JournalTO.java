package net.openathens.journal.types.transfer;

import java.time.LocalDateTime;
import java.util.UUID;

public class JournalTO {

    private UUID id;
    private String text;
    private LocalDateTime publicationDate;
    private String author;

    public JournalTO(UUID id, String text, LocalDateTime publicationDate, String author) {
        this.id = id;
        this.text = text;
        this.publicationDate = publicationDate;
        this.author = author;
    }

    public JournalTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
