import {HttpClient} from '@angular/common/http';
import {TestBed} from '@angular/core/testing';
import {of} from 'rxjs';

import {JournalsService} from './journals.service';
import {CreateJournalPayload} from '../types/journal';
import {vi} from 'vitest';

describe('JournalsService', () => {
  let service: JournalsService;
  let postMock: ReturnType<typeof vi.fn>;

  beforeEach(() => {
    postMock = vi.fn();

    TestBed.configureTestingModule({
      providers: [
        JournalsService,
        {provide: HttpClient, useValue: {post: postMock}},
      ],
    });

    service = TestBed.inject(JournalsService);
  });

  it('should create the service', () => {
    expect(service).toBeTruthy();
  });

  it('should call POST /api/v1/articles-add with API key header when adding a journal', async () => {
    const payload: CreateJournalPayload = {author: 'Author', text: 'Some text'};
    const expectedResponse = {author: 'Author', text: 'Some text', publicationDate: '2024-01-01T00:00:00'};

    postMock.mockReturnValue(of(expectedResponse));

    const result = await new Promise<unknown>((resolve, reject) => {
      service.addJournal(payload).subscribe({next: resolve, error: reject});
    });

    expect(result).toEqual(expectedResponse);
    expect(postMock).toHaveBeenCalledWith(
      'http://localhost:8080/api/v1/articles-add',
      payload,
      expect.objectContaining({
        headers: expect.any(Object),
      }),
    );
    const callArgs = postMock.mock.calls[0];
    const headers = callArgs[2]?.headers;
    expect(headers?.get?.('X-API-KEY')).toBe('abc123');
  });
});
