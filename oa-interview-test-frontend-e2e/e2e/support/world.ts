import { setWorldConstructor, World } from '@cucumber/cucumber';
import { Browser, Page } from 'playwright';
import { ExamplePage } from '../pages/ExamplePage';

export class CustomWorld extends World {
  browser!: Browser;
  page!: Page;
  examplePage!: ExamplePage;
}

setWorldConstructor(CustomWorld);

