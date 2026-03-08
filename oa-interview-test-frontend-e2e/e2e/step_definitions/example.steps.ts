import { Given, When, Then } from '@cucumber/cucumber';
import { expect } from '@playwright/test';
import { CustomWorld } from '../support/world';

Given('the user navigates to the page', async function (this: CustomWorld) {
  await this.examplePage.goto();
});

When('the user views the page', async function (this: CustomWorld) {
//
});

Then('the page contains Interview', async function (this: CustomWorld) {
 const pageTitle = await this.page.getByText('Interview').innerText();
 expect(pageTitle).toContain('Interview');
});

