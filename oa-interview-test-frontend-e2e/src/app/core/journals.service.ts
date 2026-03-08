import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {CreateJournalPayload, GetJournalsResponse, Journal} from '../types/journal';
import {map, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class JournalsService {
  private readonly http = inject(HttpClient);

  private readonly apiBase = 'http://localhost:8080';

  getJournals(): Observable<Journal[]> {
    return this.http.get<GetJournalsResponse>(`${this.apiBase}/api/v1/articles-fetch/all`).pipe(
      map(response => response.journals)
    );
  }

  searchJournals(searchTerm: string): Observable<Journal[]> {
    return this.http.get<GetJournalsResponse>(`${this.apiBase}/api/v1/articles-fetch`, {
      params: new HttpParams().set('search', searchTerm)
    }).pipe(
      map(response => response.journals),
    );
  }

  addJournal(payload: CreateJournalPayload): Observable<Journal> {
    const headers = new HttpHeaders({ 'X-API-KEY': 'abc123' });
    return this.http.post<Journal>(`${this.apiBase}/api/v1/articles-add`, payload, { headers });
  }
}
