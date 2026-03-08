import {ChangeDetectionStrategy, Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField, MatLabel, MatError} from '@angular/material/form-field';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatInput} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {CreateJournalPayload} from '../../types/journal';
import {AbstractControl, FormGroupDirective, NgForm} from '@angular/forms';

const touchedErrorMatcher: ErrorStateMatcher = {
  isErrorState(control: AbstractControl | null, _form: FormGroupDirective | NgForm | null): boolean {
    return !!(control?.invalid && control?.touched);
  },
};

@Component({
  selector: 'iw-add-journal-form',
  standalone: true,
  imports: [ReactiveFormsModule, MatFormField, MatLabel, MatError, MatInput, MatButton],
  templateUrl: './add-journal-form.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AddJournalFormComponent {
  @Output() readonly formSubmitted = new EventEmitter<CreateJournalPayload>();

  readonly form: FormGroup;
  readonly touchedErrorMatcher = touchedErrorMatcher;

  constructor(private readonly formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      author: ['', [Validators.required]],
      text: ['', [Validators.required, Validators.minLength(5)]],
    });
  }

  handleSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.formSubmitted.emit(this.form.value as CreateJournalPayload);
    this.form.reset();
  }
}

