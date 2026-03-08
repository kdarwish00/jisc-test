export interface Journal {
  author: string;
  publicationDate: string;
  text: string;
}

export interface GetJournalsResponse {
  journals: Journal[];
}

export interface CreateJournalPayload {
  author: string;
  text: string;
}
