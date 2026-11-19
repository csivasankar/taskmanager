export interface Task {
  id?: number;
  title: string;
  description?: string;
  createdAt?: string;
}

export interface PageResponse<T> {
  content: T[];
  page: {
    size: number;
    number: number;
    totalElements: number;
    totalPages: number;
  };
}
