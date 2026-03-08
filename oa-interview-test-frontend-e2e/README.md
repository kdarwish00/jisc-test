# Interview

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 21.0.1.

When you first clone this repository, run `npm install` to install the dependencies and `npx playwright install` to install the e2e testing framework.

## Development server

To start a local development server, run:

```bash
npm run start
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Vitest](https://vitest.dev/) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

(front and back end will need to be running before tests can be run)

```bash
npm run e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Add journal feature

When the application is running, you can:

- Enable admin mode using the **Enable admin mode** button on the main page.
- Use the **Add journal** form (author + text, with required and minimum-length validation) to submit a new article.
- The frontend sends the request to the backend `POST /api/v1/articles-add` endpoint with the `X-API-KEY: abc123` header.

The e2e suite includes a scenario (`add_journal.feature`) that covers enabling admin mode, creating a journal, and verifying that it appears in the list.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
