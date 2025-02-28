import { Component } from '@angular/core';
import { Task, TaskService } from 'src/app/services/task.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
})
export class TaskListComponent {
  tasks: Task[] = [];
  loading = false;

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.taskService.tasks$.subscribe(tasks => {
      this.tasks = tasks;
    });

    this.taskService.loading$.subscribe(loading => {
      this.loading = loading;
    });

    this.taskService.loadTasks();
  }

  toggleTaskStatus(task: Task): void {
    this.taskService.updateTaskStatus(task.id, !task.completed).subscribe();
  }

  deleteTask = (task: Task): void => {
    Swal.fire({
      title: "¿Estás seguro?",
      text: "No podrás revertir esta acción",
      icon: "warning",
      showCancelButton: true,
      confirmButtonText: "Sí, eliminar",
      confirmButtonColor: "#d33",
      cancelButtonText: "Cancelar",
    }).then(result => {
      if (result.isConfirmed) {
        this.taskService.deleteTask(task.id).subscribe();
      }
    })

  }

}
