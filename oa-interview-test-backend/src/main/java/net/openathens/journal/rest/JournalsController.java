package net.openathens.journal.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.openathens.journal.domain.ArticlesDO;
import net.openathens.journal.security.ApiKeyConstants;
import net.openathens.journal.types.rest.CreateJournalRequest;
import net.openathens.journal.types.rest.JournalResponse;
import net.openathens.journal.types.rest.JournalResponseList;

/**
 * REST controller for journal articles: list, search, and create.
 */
@RestController
public class JournalsController {
    private final ArticlesDO articlesDO;
    private final ApiKeyConstants apiKeyConstants;

    public JournalsController(ArticlesDO articlesDO, ApiKeyConstants apiKeyConstants) {
        this.articlesDO = articlesDO;
        this.apiKeyConstants = apiKeyConstants;
    }

    /**
     * Returns all journal articles.
     *
     * @return 200 OK with a list of all journals
     */
    @GetMapping("/api/v1/articles-fetch/all")
    public ResponseEntity<JournalResponseList> getAll() {
        return ResponseEntity.ok(articlesDO.getAllArticles());
    }

    /**
     * Searches journal articles by body text.
     *
     * @param search optional query string to filter by body content (default empty = no filter)
     * @return 200 OK with journals whose body contains the search string
     */
    @GetMapping("/api/v1/articles-fetch")
    public ResponseEntity<JournalResponseList> search(@RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(articlesDO.searchArticleByBody(search));
    }

    /**
     * Creates a new journal article. Requires a valid API key in the X-API-KEY header.
     *
     * @param apiKey  value of the X-API-KEY header
     * @param request JSON body with author and text
     * @return 201 Created with the created article (including publicationDate), or 401 if API key is invalid
     */
    @PostMapping("/api/v1/articles-add")
    public ResponseEntity<JournalResponse> addArticle(
            @RequestHeader("X-API-KEY") String apiKey,
            @RequestBody CreateJournalRequest request) {

        if (!apiKeyConstants.getJournalApiKey().equals(apiKey)) {
            return ResponseEntity.status(401).build();
        }

        JournalResponse created = articlesDO.addArticle(request.getText(), request.getAuthor());
        return ResponseEntity.status(201).body(created);
    }
}
