package pbo.f01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "dorms")
public class Dorm {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "resident", nullable = false)
    private int resident;

    @ManyToMany(mappedBy = "dorms")
    private Set<Student> students;

    public Dorm() {
    }

    public Dorm(String name, int capacity, String gender, int resident) {
        this.name = name;
        this.capacity = capacity;
        this.gender = gender;
        this.resident = resident;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getResident() {
        return resident;
    }

    public void setResident(int resident) {
        this.resident = resident;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%d|%d", name, gender, capacity, resident);
    }
}
