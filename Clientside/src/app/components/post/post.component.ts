import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { PostFormComponent } from '../post-form/post-form.component';
@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss'],
})
export class PostComponent {
  constructor(public dialog: MatDialog) {}
  openDialog(): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<PostFormComponent> = this.dialog.open(
      PostFormComponent,
      {
        width: '40%', // Set the width of the dialog

        data: { name: 'John' }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
