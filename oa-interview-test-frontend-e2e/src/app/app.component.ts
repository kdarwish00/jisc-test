import {ChangeDetectionStrategy, Component, inject, signal} from '@angular/core';
import {AppLayout} from './layout/app-layout/app-layout.component';
import {JournalList} from './ui/journal-list/journal-list.component';
import {JournalsService} from './core/journals.service';
import {AsyncPipe} from '@angular/common';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {MatButton} from '@angular/material/button';
import {iif, startWith, switchMap} from 'rxjs';
import {AddJournalFormComponent} from './ui/add-journal-form/add-journal-form.component';
import {CreateJournalPayload} from './types/journal';

@Component({
  selector: 'iw-root',
  imports: [AppLayout, JournalList, AsyncPipe, MatFormField, MatLabel, MatInput, MatButton, ReactiveFormsModule, AddJournalFormComponent],
  templateUrl: './app.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class App {
  private readonly journalService = inject(JournalsService);

  protected readonly searchControl = new FormControl('', { nonNullable: true })

  protected readonly journals$ = this.searchControl.valueChanges.pipe(
    startWith(this.searchControl.value),
    switchMap((searchTerm) =>
      iif(
        () => searchTerm === '',
        this.journalService.getJournals(),
        this.journalService.searchJournals(searchTerm),
      ),
    ),
  );

  protected isAdmin = false;

  protected readonly addMessage = signal<{ type: 'success' | 'error'; text: string } | null>(null);

  handleToggleAdmin(): void {
    this.isAdmin = !this.isAdmin;
  }

  handleCreateJournal(payload: CreateJournalPayload): void {
    this.addMessage.set(null);
    this.journalService.addJournal(payload).subscribe({
      next: () => {
        this.handleJournalCreated();
        this.addMessage.set({ type: 'success', text: 'Journal added successfully.' });
        setTimeout(() => this.addMessage.set(null), 3000);
      },
      error: () => {
        this.addMessage.set({ type: 'error', text: 'Failed to add journal. Please try again.' });
      },
    });
  }

  handleJournalCreated(): void {
    this.searchControl.setValue(this.searchControl.value);
  }
}
