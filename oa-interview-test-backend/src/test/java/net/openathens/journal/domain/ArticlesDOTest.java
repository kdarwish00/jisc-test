package net.openathens.journal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import net.openathens.journal.types.rest.JournalResponse;

class ArticlesDOTest {

    @Test
    void addArticleShouldCreateArticleWithCurrentPublicationDate() {
        ArticlesDO articlesDO = new ArticlesDO();
        LocalDateTime beforeCreation = LocalDateTime.now();

        JournalResponse created = articlesDO.addArticle("New article text", "Author Name");

        LocalDateTime afterCreation = LocalDateTime.now();

        assertThat(created.getText()).isEqualTo("New article text");
        assertThat(created.getAuthor()).isEqualTo("Author Name");
        assertThat(created.getPublicationDate()).isNotNull();
        assertThat(created.getPublicationDate()).isAfterOrEqualTo(beforeCreation);
        assertThat(created.getPublicationDate()).isBeforeOrEqualTo(afterCreation.plusSeconds(5));
    }
}
