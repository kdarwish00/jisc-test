# Journal API

This project loads a set of journals from disk and presents them via an API 

## To run

Requires java 18

#### Build:

To fully build the project, follow this command:
```
mvn clean install
```

on the first run, the OWASP database can take some time to download on your local machine (around 30 minutes), so you
can skip it temporarily during your development by running:

```
mvn clean install -Dskip.owasp=true
```
#### Run:

Run `net.openathens.journal.JournalApplication` in your IDE

OR

```
mvn spring-boot:run
```

Under `localhost:8080/api/v1/articles-fetch/all` you should see a list of all journals.

Under `localhost:8080/api/v1/articles-fetch?search=` you can search for a journal by body.

To add a new journal article, send a `POST` request to:

`localhost:8080/api/v1/articles-add`

The request must include the header `X-API-KEY: abc123` and a JSON body of the form:

```json
{
  "author": "Author name",
  "text": "Article body"
}
```

On success the API responds with HTTP `201 Created` and a JSON representation of the created article, including a server-generated `publicationDate`.