package net.openathens.journal.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class JournalsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllShouldReturnSeedData() throws Exception {
        MvcResult mvcResult =
            mockMvc.perform(get("/api/v1/articles-fetch/all")).andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertThat(content, containsString("author"));
        assertThat(content, containsString("journals"));
    }

    @Test
    void addArticleWithValidApiKeyShouldReturnCreated() throws Exception {
        String requestBody = """
            {
              "text": "New article body",
              "author": "New Author"
            }
            """;

        MvcResult mvcResult =
            mockMvc.perform(
                    post("/api/v1/articles-add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-KEY", "abc123")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content, containsString("New article body"));
        assertThat(content, containsString("New Author"));
        assertThat(content, containsString("publicationDate"));
    }

    @Test
    void addArticleWithInvalidApiKeyShouldReturnUnauthorized() throws Exception {
        String requestBody = """
            {
              "text": "Body",
              "author": "Author"
            }
            """;

        mockMvc.perform(
                post("/api/v1/articles-add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("X-API-KEY", "wrong-key")
                    .content(requestBody))
            .andExpect(status().isUnauthorized());
    }
}
