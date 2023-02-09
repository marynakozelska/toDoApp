import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {ToDoItem} from "../to-do-item/to-do-item";
import {ToDoItemService} from "../to-do-item/to-do-item.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  public toDoItems: ToDoItem[] | undefined;
  public editToDoItem: ToDoItem | undefined;
  public deleteToDoItem!: ToDoItem;

  constructor(private toDoItemService: ToDoItemService) {
  }

  ngOnInit(): void {
    this.getToDoItems();
  }

  public getToDoItems(): void {
    this.toDoItemService.getToDoItem().subscribe(
      (response: ToDoItem[]) => {
        this.toDoItems = response;
        console.log(this.toDoItems);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddToDoItem(addForm: NgForm): void {
    document.getElementById('add-todo-form');
    this.toDoItemService.addToDoItem(addForm.value).subscribe(
      (response: ToDoItem) => {
        console.log(response);
        this.getToDoItems();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateToDoItem(toDoItem: ToDoItem): void {
    this.toDoItemService.updateToDoItem(toDoItem).subscribe(
      (response: ToDoItem) => {
        console.log(response);
        this.getToDoItems();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteToDoItem(id: number): void {
    this.toDoItemService.deleteToDoItem(id).subscribe(
      (response: void) => {
        console.log(response);
        this.getToDoItems();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(toDoItem: ToDoItem, mode: string): void {
    console.log("on open modal opened...");

    const container = document.getElementById('main-container')!;
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if (mode === 'add') {
      button.setAttribute('data-target', '#addToDoItemModal');
    }
    if (mode === 'edit') {
      this.editToDoItem = toDoItem;
      button.setAttribute('data-target', '#updateToDoItemModal');
      console.log("inside of edit...");
    }
    if (mode === 'delete') {
      this.deleteToDoItem = toDoItem;
      button.setAttribute('data-target', '#deleteToDoItemModal');
      console.log("inside of delete...");
    }

    container.appendChild(button);
    button.click();
  }

}
