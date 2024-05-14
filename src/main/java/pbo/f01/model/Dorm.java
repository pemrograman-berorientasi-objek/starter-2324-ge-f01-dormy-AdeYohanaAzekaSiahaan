package pbo.f01.model;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name = "Dorm")
public class Dorm {
    @Column(name = "asrama", nullable = false, length = 255)
    private String asrama;
    @Column(name = "maxkapasitas", nullable = false, length = 255)
    private int maxkapasitas;
    @Column(name = "jenis", nullable = false, length = 255)
    private String jenis;
    @Column(name = "penghuni", nullable = false, length = 1000)
    private ArrayList<Student> penghuni;

    public Dorm(String asrama, int maxkapasitas, String jenis) {
        this.asrama = asrama;
        this.maxkapasitas = maxkapasitas;
        this.jenis = jenis;
        this.penghuni = new ArrayList<>();
    }

    public String getAsrama() {
        return asrama;
    }

    public int getMaxkapasitas() {
        return maxkapasitas;
    }

    public String getJenis() {
        return jenis;
    }

    public ArrayList<Student> getPenghuni() {
        return penghuni;
    }

    public void addPenghuni(Student student) {
        if (penghuni.size() < maxkapasitas) {
            penghuni.add(student);
        }
    }

    public int getNumberOfStudents() {
        return penghuni.size();
    }
}
