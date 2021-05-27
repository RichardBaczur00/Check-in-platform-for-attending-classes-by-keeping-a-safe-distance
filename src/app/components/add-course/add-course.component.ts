import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Classroom } from '../course/classroomModel';
import { Course } from '../course/courseModel';
import { User } from '../course/userModel';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {
  private usersList: User[];
  public teachersList: User[];
  private courseList: Course[];
  public classroomList: Classroom[];

  constructor(private httpClient: HttpClient, private router: Router) { }

  ngOnInit(): void {
    const form = document.getElementById('postForm')!;
    form.addEventListener('submit', (event) => { event.preventDefault(); this.onSubmit() });

    this.httpClient.get<User[]>("http://localhost:8080/user/all").subscribe(val => {
      this.usersList = val;
      this.teachersList = [];
      this.usersList.forEach(element => {
        if (element.role == "teacher") {
          this.teachersList.push(element);
        }
      });
    });

    this.httpClient.get<Course[]>("http://localhost:8080/class/all").subscribe(val => {
      this.courseList = val;
      this.classroomList = [];
      this.courseList.forEach(element => {
        var includes = false;
        this.classroomList.forEach(classroom => {
          if (classroom.id == element.classroom.id) {
            includes = true;
          }
        });
        if (!includes) {
          this.classroomList.push(element.classroom);
        }
      });
    });
  }

  onSubmit() {
    const postItem = {
      classroom: { id: Number((<HTMLInputElement>document.getElementById("classroom")).value) },
      name: (<HTMLInputElement>document.getElementById("name")).value,
      year: Number((<HTMLInputElement>document.getElementById("year")).value),
      students: [],
      section: (<HTMLInputElement>document.getElementById("section")).value,
      teacher: { id: Number((<HTMLInputElement>document.getElementById("teacher")).value) }
    }

    this.httpClient.post<any>("http://localhost:8080/class", postItem).subscribe(
      (res) => {
        this.router.navigate(['']);
      },
      (err) => {
        this.router.navigate(['']);
      },
    );
  }
}
