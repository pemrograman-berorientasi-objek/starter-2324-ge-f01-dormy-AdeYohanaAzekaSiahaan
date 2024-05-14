package pbo.f01.model;

import javax.persistence.*;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @Column(name = "nim", nullable = false, length = 255)
    private String nim;
    @Column(name = "nama", nullable = false, length = 255)
    private String nama;
    @Column(name = "year", nullable = false, length = 255)
    private String year;
    @Column(name = "jenisK", nullable = false, length = 255)
    private String jenisK;
    
    public Student() {
        //
    }

    public Student(String nim, String nama, String year, String jenisK) {
        this.nim = nim;
        this.nama = nama;
        this.year = year;
        this.jenisK = jenisK;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setName(String nama) {
        this.nama = nama;
    }

    public String getJenisK() {
        return jenisK;
    }

    public void setJenisK(String jenisK) {
        this.jenisK = jenisK;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
