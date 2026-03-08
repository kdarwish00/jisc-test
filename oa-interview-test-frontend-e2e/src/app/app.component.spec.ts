import {TestBed} from '@angular/core/testing';
import {App} from './app.component';
import {JournalsService} from './core/journals.service';
import {of} from 'rxjs';
import {CreateJournalPayload} from './types/journal';
import {vi} from 'vitest';

describe('App', () => {
  const addJournalMock = vi.fn();

  beforeEach(async () => {
    addJournalMock.mockReturnValue(of({author: 'Author', text: 'Text', publicationDate: '2024-01-01T00:00:00'}));

    await TestBed.configureTestingModule({
      imports: [App],
      providers: [
        {
          provide: JournalsService,
          useValue: {
            getJournals: () => of([]),
            searchJournals: () => of([]),
            addJournal: addJournalMock,
          },
        },
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(App);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should call addJournal when handleCreateJournal is called', () => {
    const fixture = TestBed.createComponent(App);
    const app = fixture.componentInstance;
    const payload: CreateJournalPayload = {author: 'Author', text: 'Some text'};

    app.handleCreateJournal(payload);

    expect(addJournalMock).toHaveBeenCalledWith(payload);
  });
});
