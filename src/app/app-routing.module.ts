import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { CourseComponent } from './components/course/course.component';
import { EnrollComponent } from './components/enroll/enroll.component';

const routes: Routes = [
  { path: '', component: CourseComponent },
  { path: 'add', component: AddCourseComponent },
  { path: 'enroll', component: EnrollComponent },
  { path: 'user', component: AddUserComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
