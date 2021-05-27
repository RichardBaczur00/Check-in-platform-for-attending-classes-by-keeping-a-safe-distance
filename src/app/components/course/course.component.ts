import { Component, ComponentFactoryResolver, OnInit } from '@angular/core';
import { Course } from './courseModel';
import { HttpClient } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  public courseList: Course[];
  private colorsList: string[] = ["LightGreen","LightPink","LightSalmon","LemonChiffon","LightSkyBlue","Linen","MistyRose","Pink","PowderBlue"];
  private index: number = 0;
  constructor(private modalService: NgbModal, private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.httpClient.get<Course[]>("http://localhost:8080/class/all").subscribe(val => {
      this.courseList = val;
    });
  }

  public getItemName(item: Course) {
    document.getElementById(item.id.toString()).style.backgroundColor = this.colorsList[this.index % this.colorsList.length];
    this.index += 1;
    return item.name;
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
}
