import { Before, After } from '@cucumber/cucumber';
import { chromium } from 'playwright';
import { CustomWorld } from './world';
import { ExamplePage } from '../pages/ExamplePage';

Before(async function (this: CustomWorld) {
  this.browser = await chromium.launch({ headless: false });
  this.page = await this.browser.newPage();
  this.examplePage = new ExamplePage(this.page);
});

After(async function (this: CustomWorld) {
  if (this.page) await this.page.close();
  if (this.browser) await this.browser.close();
});


