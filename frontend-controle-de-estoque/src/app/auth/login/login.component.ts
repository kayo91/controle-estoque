import {ChangeDetectionStrategy, Component, signal} from '@angular/core';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginComponent  {
   hide = signal(true);
   clickEvent(event: MouseEvent) {
   this.hide.set(!this.hide());
   event.stopPropagation();
  }

}
