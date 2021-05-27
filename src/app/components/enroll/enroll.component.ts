import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Course } from '../course/courseModel';
import { User } from '../course/userModel';

@Component({
  selector: 'app-enroll',
  templateUrl: './enroll.component.html',
  styleUrls: ['./enroll.component.css']
})
export class EnrollComponent implements OnInit {
  public courseList: Course[];
  private usersList: User[];
  public studentsList: User[];
  constructor(private router: Router, private modalService: NgbModal, private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.httpClient.get<Course[]>("http://localhost:8080/class/all").subscribe(val => {
      this.courseList = val;
    });

    this.httpClient.get<User[]>("http://localhost:8080/user/all").subscribe(val => {
      this.usersList = val;
      this.studentsList = [];
      this.usersList.forEach(element => {
        if (element.role == "student") {
          this.studentsList.push(element);
        }
      });
    })
  }

  public getFreeStudents(enrolledStudents: User[]): User[] {
    var freeStudents: User[] = [];
    var enrolledStudentsIds: number[] = [];
    enrolledStudents.forEach(e => {
      enrolledStudentsIds.push(e.id);
    });
    this.studentsList.forEach(student => {
      if(!enrolledStudentsIds.includes(student.id)){
        freeStudents.push(student);
      }
    });
    return freeStudents;
  }

  public getStudents(c: Course) {
    return c.students;
  }

  openVerticallyCentered(content) {
    this.modalService.open(content, { centered: true });
  }
  openClassmateDetails(id) {
    var student = document.getElementById(id);
    if(student.classList.contains("hide")) {
      student.classList.remove("hide");
    }
    else {
      student.classList.add("hide");
    }
  }
  countStudents(students) {
    return students.length;
  }

  public onSubmit(course: Course) {
    const postItem = {
      userId: Number((<HTMLInputElement>document.getElementById("student")).value),
      courseId: course.id
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
