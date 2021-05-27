import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Classroom } from '../course/classroomModel';
import { Course } from '../course/courseModel';
import { User } from '../course/userModel';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  constructor(private httpClient: HttpClient, private router: Router) { }

  ngOnInit(): void {
    const form = document.getElementById('postForm')!;
    form.addEventListener('submit', (event) => { event.preventDefault(); this.onSubmit() });
  }

  onSubmit() {
    const postItem = {
      firstName: (<HTMLInputElement>document.getElementById("firstname")).value,
      lastName: (<HTMLInputElement>document.getElementById("lastname")).value,
      year: Number((<HTMLInputElement>document.getElementById("year")).value),
      section: (<HTMLInputElement>document.getElementById("section")).value,
      department: (<HTMLInputElement>document.getElementById("department")).value,
      groupName: (<HTMLInputElement>document.getElementById("groupname")).value,
      role: (<HTMLInputElement>document.getElementById("role")).value,
    }

    this.httpClient.post<any>("http://localhost:8080/users", postItem).subscribe(
      (res) => {
        this.router.navigate(['']);
      },
      (err) => {
        this.router.navigate(['']);
      },
    );
  }
}
