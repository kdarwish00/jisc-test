import {ChangeDetectionStrategy, Component} from '@angular/core';
import {MatToolbar} from '@angular/material/toolbar';

@Component({
  selector: 'iw-app-layout',
  imports: [
    MatToolbar
  ],
  templateUrl: './app-layout.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppLayout {

}
