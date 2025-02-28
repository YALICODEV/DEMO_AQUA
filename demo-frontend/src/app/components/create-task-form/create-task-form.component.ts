import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TaskService } from 'src/app/services/task.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-task-form',
  templateUrl: './create-task-form.component.html',
  standalone: true,
  imports: [FormsModule],
})
export class CreateTaskFormComponent {
  task = { title: '', description: '' };
  loading = false;

  constructor(private taskService: TaskService) {}

   onSubmit(form: any) {
    if (Object.values(this.task).some((value) => value.trim() === '')) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Todos los campos son requeridos',
      });
      return;
    }

    Swal.fire({
      title: 'Creando tarea...',
      text: 'Por favor, espera.',
      allowOutsideClick: false,
      didOpen: () => Swal.showLoading(),
    });

    this.loading = true;

    const { title, description } = this.task;

    this.taskService.createTask(title, description).subscribe({
      next: () => {
        this.task = { title: '', description: '' };
        form.reset();

        Swal.fire({
          title: 'Tarea creada',
          icon: 'success',
        });
      },
      error: (err) => {
        Swal.fire({
          title: 'Error',
          text: 'No se pudo crear la tarea',
          icon: 'error',
        });
      }
    });
  }
  
}
