import {Page} from 'playwright';

export class ExamplePage {
  constructor(private page: Page) {}

  async goto() {
    await this.page.goto('http://localhost:4200');
  }

  async enableAdminMode() {
    await this.page.getByRole('button', {name: /enable admin mode/i}).click();
  }

  async addJournal(author: string, text: string) {
    await this.page.getByLabel(/author/i).fill(author);
    await this.page.getByLabel(/text/i).fill(text);
    await this.page.getByRole('button', {name: /add journal/i}).click();
  }

  async journalIsVisible(text: string) {
    await this.page.getByText(text).waitFor();
  }
}

