import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ToDoItem} from "./to-do-item";
import {environment} from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ToDoItemService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getToDoItem(): Observable<ToDoItem[]> {
    return this.http.get<ToDoItem[]>(`${this.apiServerUrl}/todo`);
  }

  public addToDoItem(toDoItem: ToDoItem): Observable<ToDoItem> {
    return this.http.post<ToDoItem>(`${this.apiServerUrl}/todo`, toDoItem);
  }

  public updateToDoItem(toDoItem: ToDoItem): Observable<ToDoItem> {
    return this.http.put<ToDoItem>(`${this.apiServerUrl}/todo/update`, toDoItem);
  }

  public deleteToDoItem(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/todo/${id}`);
  }

}
