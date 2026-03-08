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

@RestController
public class JournalsController {
    private final ArticlesDO articlesDO = new ArticlesDO();

    @GetMapping("/api/v1/articles-fetch/all")
    public ResponseEntity<JournalResponseList> getAll() {
        return ResponseEntity.ok(articlesDO.getAllArticles());
    }

    @GetMapping("/api/v1/articles-fetch")
    public ResponseEntity<JournalResponseList> search(@RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(articlesDO.searchArticleByBody(search));
    }

    @PostMapping("/api/v1/articles-add")
    public ResponseEntity<JournalResponse> addArticle(
            @RequestHeader("X-API-KEY") String apiKey,
            @RequestBody CreateJournalRequest request) {

        if (!ApiKeyConstants.JOURNAL_API_KEY.equals(apiKey)) {
            return ResponseEntity.status(401).build();
        }

        JournalResponse created = articlesDO.addArticle(request.getText(), request.getAuthor());
        return ResponseEntity.status(201).body(created);
    }
}
