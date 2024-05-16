package pbo.f01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "entrance_year", nullable = false)
    private String entranceYear;

    @Column(name = "gender", nullable = false)
    private String gender;

    @ManyToMany
    private Set<Dorm> dorms = new HashSet<>();

    public Student() {
    }

    public Student(String studentId, String studentName, String entranceYear, String gender) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.entranceYear = entranceYear;
        this.gender = gender;
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(String entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Dorm> getDorms() {
        return dorms;
    }

    public void setDorms(Set<Dorm> dorms) {
        this.dorms = dorms;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s", studentId, studentName, entranceYear);
    }
}
