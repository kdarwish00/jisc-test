# Jisc / OpenAthens Take-Home Test

This repository contains the backend and frontend for a journal-articles application, plus the work done to implement **authorised add-journal** end-to-end (API, UI, validation, tests, and docs).

---

## Repository structure

| Folder                               | Description                                                                                       |
| ------------------------------------ | ------------------------------------------------------------------------------------------------- |
| **`oa-interview-test-backend`**      | Spring Boot API (Java). Loads journals from disk, exposes fetch/search and **add** endpoints.     |
| **`oa-interview-test-frontend-e2e`** | Angular app + Cucumber/Playwright E2E. Lists/searches journals and allows admins to add new ones. |

---

## What was implemented

### Feature: Authorised users can add a new journal article

- **Backend:** New `POST /api/v1/articles-add` endpoint, protected by API key header `X-API-KEY: abc123`. Request body: `{ "author": "...", "text": "..." }`. Response: 201 with created article (including server-generated `publicationDate`). Invalid/missing API key returns 401.
- **Frontend:** Admin mode toggle; add-journal form (author + text) with validation (required, min length 5); success/error feedback; list refresh after add. CORS configured so the Angular app on port 4200 can call the API on 8080.
- **Tests:** Backend unit/controller tests (API key, add-article behaviour, publication date); frontend unit tests (Vitest) for service, add-journal form, and app; E2E scenario (Cucumber) for “admin adds a journal and it appears in the list”.
- **Docs:** Backend and frontend READMEs updated; this root README added.

---

## Prerequisites

- **Backend:** Java 18+ (e.g. OpenJDK 21).
- **Frontend:** Node.js 20+ (e.g. 22), npm. Run `npx playwright install` in the frontend folder for E2E.

---

## How to run

### 1. Backend

```bash
cd oa-interview-test-backend
./mvnw spring-boot:run
```

Or run `net.openathens.journal.JournalApplication` from your IDE. API runs at **http://localhost:8080**.

- List all: `GET http://localhost:8080/api/v1/articles-fetch/all`
- Search: `GET http://localhost:8080/api/v1/articles-fetch?search=...`
- Add (authorised): `POST http://localhost:8080/api/v1/articles-add` with header `X-API-KEY: abc123` and JSON body `{"author":"...","text":"..."}`

### 2. Frontend

```bash
cd oa-interview-test-frontend-e2e
npm install
npm run start
```

App runs at **http://localhost:4200**. Use “Enable admin mode”, then the Add journal form (author + text). Success/error messages appear below the form.

### 3. E2E tests

Start both backend and frontend, then:

```bash
cd oa-interview-test-frontend-e2e
npm run e2e
```

Runs the Cucumber scenarios (including `add_journal.feature`).

---

## Running tests only

### Backend (JUnit)

```bash
cd oa-interview-test-backend
./mvnw test
```

To skip OWASP (faster): `./mvnw test -Dskip.owasp=true`. If JaCoCo causes issues: `./mvnw test -Djacoco.skip=true`.

### Frontend (Vitest)

```bash
cd oa-interview-test-frontend-e2e
ng test
```

### Frontend build

```bash
cd oa-interview-test-frontend-e2e
ng build
```

---

## Summary of changes (by area)

### Backend (`oa-interview-test-backend`)

- **New:** `ApiKeyConstants` (API key `abc123`), `CreateJournalRequest` DTO, `WebConfig` (CORS for `http://localhost:4200`), `ArticlesDO.addArticle`, `InMemoryDB.add`, `JournalsController.addArticle` (POST, API key check, 201/401).
- **Tests:** `ApiKeyConstantsTest`, `CreateJournalRequestTest`, `ArticlesDOTest` (add + publication date), `JournalsControllerTest` (add with valid/invalid API key), `InMemoryDBTest` made data-agnostic.
- **Docs:** README updated with add-endpoint and API key usage.

### Frontend (`oa-interview-test-frontend-e2e`)

- **New:** `CreateJournalPayload` type, `JournalsService.addJournal`, `AddJournalFormComponent` (reactive form, validation, error messages, `ErrorStateMatcher` so red state only when touched + invalid), admin toggle and add-form wiring in `App`, success/error message signal and UI.
- **E2E:** `add_journal.feature`, `add_journal.steps.ts`, `ExamplePage` (enableAdminMode, addJournal, journalIsVisible).
- **Tests:** `journals.service.spec.ts` (addJournal call), `add-journal-form.component.spec.ts` (emit when valid, no emit when invalid), `app.component.spec.ts` (handleCreateJournal calls service).
- **Docs:** README updated with add-journal feature and E2E notes.

---

## API key

The add-article endpoint expects header **`X-API-KEY: abc123`**. Wrong or missing key returns **401 Unauthorized**. The frontend sends this header when adding a journal.

---

## Security / authorisation

The current approach (single shared API key in a header) is intentionally minimal and suitable for this exercise. For a production system we would use proper authentication, user identity and role-based authorisation (e.g. only users with an “admin” role can add articles), and never ship secrets in the frontend; the backend would validate tokens and enforce permissions.

---

For more detail on build, run, and test commands, see the README in each subproject (`oa-interview-test-backend/README.md` and `oa-interview-test-frontend-e2e/README.md`).
