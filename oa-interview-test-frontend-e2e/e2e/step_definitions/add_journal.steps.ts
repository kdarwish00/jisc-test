import {Given, When, Then} from '@cucumber/cucumber';
import {CustomWorld} from '../support/world';

Given('the user enables admin mode', async function (this: CustomWorld) {
  await this.examplePage.enableAdminMode();
});

When(
  'the user adds a new journal with author {string} and text {string}',
  async function (this: CustomWorld, author: string, text: string) {
    await this.examplePage.addJournal(author, text);
  },
);

Then(
  'the new journal with text {string} is visible in the list',
  async function (this: CustomWorld, text: string) {
    await this.examplePage.journalIsVisible(text);
  },
);

