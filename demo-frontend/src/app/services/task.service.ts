import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, finalize, Observable, of, switchMap } from 'rxjs';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

export interface Task {
  id: number;
  title: string;
  description: string;
  completed: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private tasksSubject = new BehaviorSubject<Task[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  tasks$ = this.tasksSubject.asObservable();
  loading$ = this.loadingSubject.asObservable();

  private apiUrl: string = environment.backendUrl + "/tasks";

  constructor(private http: HttpClient) { }

  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.apiUrl);
  }

  createTask(title: string, description: string): Observable<void> {
    this.loadingSubject.next(true);
    return this.http.post<Task>(this.apiUrl, { title, description }).pipe(
      switchMap(() => this.getTasks()),
      finalize(() => this.loadingSubject.next(false))
    ).pipe(switchMap(tasks => {
      this.tasksSubject.next(tasks);
      return of(void 0);
    }));
  }

  updateTaskStatus(id: number, completed: boolean): Observable<void> {
    this.loadingSubject.next(true);
    return this.http.put<Task>(`${this.apiUrl}/${id}`, { completed }).pipe(
      switchMap(() => this.getTasks()),
      finalize(() => this.loadingSubject.next(false))
    ).pipe(switchMap(tasks => {
      this.tasksSubject.next(tasks);
      return new Observable<void>();
    }));
  }

  deleteTask(id: number): Observable<void> {
    this.loadingSubject.next(true);
    return this.http.delete<Task>(`${this.apiUrl}/${id}`).pipe(
      switchMap(() => this.getTasks()),
      finalize(() => this.loadingSubject.next(false))
    ).pipe(switchMap(tasks => {
      this.tasksSubject.next(tasks);
      return new Observable<void>();
    }));
  }

  loadTasks(): void {
    this.loadingSubject.next(true);
    this.getTasks().pipe(
      finalize(() => this.loadingSubject.next(false))
    ).subscribe({
      next: (tasks) => this.tasksSubject.next(tasks),
      error: (_err) => {
        Swal.fire({
          title: 'Error',
          text: 'No se pudieron obtener las tareas',
          icon: 'error',
        });
      }
    });
  }
}
