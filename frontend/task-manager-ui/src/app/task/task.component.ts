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

  // Get all tasks

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
        this.tasks = [];
        this.loading = false;
      }
    });
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

        this.loadTasks();

      },

      error: (error) => {

        this.errorMessage = 'Unable to create task';

        console.error(error);

      }

    });

  }

}
