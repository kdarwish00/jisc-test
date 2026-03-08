import {ComponentFixture, TestBed} from '@angular/core/testing';
import {AddJournalFormComponent} from './add-journal-form.component';
import {CreateJournalPayload} from '../../types/journal';

describe('AddJournalFormComponent', () => {
  let component: AddJournalFormComponent;
  let fixture: ComponentFixture<AddJournalFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddJournalFormComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AddJournalFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit payload when form is valid and submitted', () => {
    const payload: CreateJournalPayload = {author: 'Author', text: 'Valid text'};
    const emitted: CreateJournalPayload[] = [];
    component.formSubmitted.subscribe(value => emitted.push(value));

    component.form.setValue(payload);
    component.handleSubmit();

    expect(emitted.length).toBe(1);
    expect(emitted[0]).toEqual(payload);
  });

  it('should not emit when form is invalid', () => {
    const emitted: CreateJournalPayload[] = [];
    component.formSubmitted.subscribe(value => emitted.push(value));

    component.form.setValue({author: '', text: 'abc'});
    component.handleSubmit();

    expect(emitted.length).toBe(0);
  });
});

