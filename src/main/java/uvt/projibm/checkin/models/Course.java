package uvt.projibm.checkin.models;

import javax.persistence.*;
import java.util.HashSet;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String Name;

    private Integer Year;

    @ManyToOne
    @JoinColumn(name="TEACHER_ID", referencedColumnName = "id")
    private User Teacher;

    private String Section;

    @ManyToMany
    @JoinTable(
            name="STUDENTS_ID",
            joinColumns = @JoinColumn(name="Course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="Student_id", referencedColumnName="id")
    )
    private HashSet<User> Students;

    @ManyToOne
    @JoinColumn(name="CLASSROOM_ID", referencedColumnName = "id")
    private Classroom classroom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public User getTeacher() {
        return Teacher;
    }

    public void setTeacher(User teacher) {
        Teacher = teacher;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public HashSet<User> getStudents() {
        return Students;
    }

    public void setStudents(HashSet<User> students) {
        Students = students;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
