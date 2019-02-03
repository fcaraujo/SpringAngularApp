import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl = 'http://localhost:8080/api/users';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get(this.apiUrl);
  }

  add(user: any) {
    return this.httpClient.post(this.apiUrl, user);
  }
}
