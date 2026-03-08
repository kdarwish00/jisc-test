package net.openathens.journal.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.openathens.journal.persistence.InMemoryDB;
import net.openathens.journal.security.ApiKeyConstants;
import net.openathens.journal.types.rest.JournalResponse;
import net.openathens.journal.types.rest.JournalResponseList;
import net.openathens.journal.types.transfer.JournalTO;

@Component
public class ArticlesDO {

    private static final Logger logger = LoggerFactory.getLogger(ArticlesDO.class);

    private final InMemoryDB db = new InMemoryDB();
    private final ApiKeyConstants apiKeyConstants;

    public ArticlesDO(ApiKeyConstants apiKeyConstants) {
        this.apiKeyConstants = apiKeyConstants;
    }

    public JournalResponseList getAllArticles() {

        List<JournalResponse> transformed = new ArrayList<>();
        logger.info("Using '{}' to get data from database...", apiKeyConstants.getJournalApiKey());
        for (JournalTO each : db.fetchAll()) {

            JournalResponse temp = new JournalResponse();
            temp.text = each.getText();
            temp.setAuthor(each.getAuthor());
            temp.setPublicationDate(each.getPublicationDate());

            transformed.add(temp);
        }

        return new JournalResponseList(transformed);
    }

    public JournalResponseList searchArticleByBody(String stringToSearch) {
        List<JournalResponse> transformed = new ArrayList<>();

        logger.info("Using '{}' to get data from database...", apiKeyConstants.getJournalApiKey());

        logger.info("Search for '{}' in the database...", stringToSearch);
        for (JournalTO each : db.fetchAll()) {

            if (each.getText().contains(stringToSearch)) {
                logger.info("Found '{}' in the database...", stringToSearch);
                JournalResponse temp = new JournalResponse();
                temp.text = each.getText();
                temp.setAuthor(each.getAuthor());
                temp.setPublicationDate(each.getPublicationDate());

                transformed.add(temp);
            }
        }

        return new JournalResponseList(transformed);
    }

    public JournalResponse addArticle(String text, String author) {
        UUID id = UUID.randomUUID();
        LocalDateTime publicationDate = LocalDateTime.now();
        JournalTO journal = new JournalTO(id, text, publicationDate, author);
        db.add(journal);

        JournalResponse response = new JournalResponse();
        response.text = journal.getText();
        response.setAuthor(journal.getAuthor());
        response.setPublicationDate(journal.getPublicationDate());

        return response;
    }
}

