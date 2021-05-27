import { Classroom } from "./classroomModel";
import { User } from "./userModel";

export interface CourseCreate{
    classroom:Classroom;
    name:string;
    year:number;
    students:User[];
    teacher:User;
    section:string;
}
