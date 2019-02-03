import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-users-panel',
  templateUrl: './users-panel.component.html',
  styleUrls: ['./users-panel.component.css']
})

export class UsersPanelComponent implements OnInit {
  user = {};
  users = [];

  constructor(
    private _userService: UserService,
    private _messageService: MessageService
  ) { }

  ngOnInit() {
    this.getAll();
  }

  getAll(): any {
    this._userService
      .getAll()
      .subscribe(res => this.users = <any>res);
  }

  add() {
    this._userService
      .add(this.user)
      .subscribe(
        () => {
          this.user = {};
          this.getAll();

          this._messageService
            .add({
              severity: 'success',
              summary: 'User successfuly added.'
            });
        },
        res => {
          let msg = 'Unexpected error, try again.';

          if (res.error.message) {
            msg = res.error.message;
          }

          this._messageService
            .add({
              severity: 'error',
              summary: msg
            });
        });
  }
}
