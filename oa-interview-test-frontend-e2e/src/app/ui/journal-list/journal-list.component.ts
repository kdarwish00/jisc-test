import {Component, input} from '@angular/core';
import {MatList, MatListItem, MatListItemLine, MatListItemTitle} from '@angular/material/list';
import {DatePipe} from '@angular/common';
import {Journal} from '../../types/journal';

@Component({
  selector: 'iw-journal-list',
  imports: [
    MatList,
    MatListItem,
    MatListItemTitle,
    MatListItemLine,
    DatePipe
  ],
  templateUrl: './journal-list.component.html',
})
export class JournalList {
  readonly journals = input<Journal[]>();
}
