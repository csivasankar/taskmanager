
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';

import { TaskService } from '../task.service';
import { Task } from '../task.model';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  taskForm: FormGroup;

  tasks: Task[] = [];

  currentPage = 0;
  pageSize = 5;
  totalPages = 0;
  totalElements = 0;

  loading = false;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private taskService: TaskService
  ) {
    this.taskForm = this.fb.group({
      title: ['', Validators.required],
      description: ['']
    });
  }

  ngOnInit(): void {
    this.loadTasks();
  }

  // Generate page numbers
  get pages(): number[] {
    return Array.from(
      { length: this.totalPages },
      (_, index) => index
    );
  }

  // Get paginated tasks
  loadTasks(page: number = this.currentPage): void {

    this.loading = true;

    this.taskService.getTasks(
      page,
      this.pageSize,
      'id',
      'desc'
    ).subscribe({

      next: (response) => {

        this.tasks = response.content;

        this.currentPage = response.page.number;
        this.totalPages = response.page.totalPages;
        this.totalElements = response.page.totalElements;

        this.loading = false;
      },

      error: (error) => {

        console.error('Error loading tasks:', error);

        this.errorMessage = 'Unable to load tasks';
        this.tasks = [];
        this.loading = false;
      }
    });
  }

  // Navigate between pages
  changePage(page: number): void {

    if (page >= 0 && page < this.totalPages) {
      this.loadTasks(page);
    }
  }

  // Change page size
  changePageSize(size: number): void {

    this.pageSize = size;
    this.currentPage = 0;

    this.loadTasks(0);
  }

  // Submit new task
  addTask(): void {

    if (this.taskForm.invalid) {
      this.taskForm.markAllAsTouched();
      return;
    }

    const task: Task = this.taskForm.value;

    this.taskService.createTask(task).subscribe({

      next: (response) => {

        console.log('Task created:', response);

        this.taskForm.reset();

        // Reload first page to display newly created task
        this.currentPage = 0;
        this.loadTasks(0);
      },

      error: (error) => {

        this.errorMessage = 'Unable to create task';

        console.error(error);
      }
    });
  }
}
