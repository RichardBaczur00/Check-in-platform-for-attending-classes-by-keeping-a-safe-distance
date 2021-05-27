import { Classroom } from "./classroomModel";
import { User } from "./userModel";

export interface Course{
    id:number;
    classroom:Classroom;
    name:string;
    year:number;
    students:User[];
    teacher:User;
    section:string;
}
