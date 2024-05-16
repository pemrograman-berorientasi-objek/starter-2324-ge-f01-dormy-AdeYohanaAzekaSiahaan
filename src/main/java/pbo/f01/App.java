package pbo.f01;

/**
 * 12S22038 Ade Yohana Azeka Siahaan
 * 12S22044 - Jufourlisa Sirait
 */

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pbo.f01.model.Dorm;
import pbo.f01.model.Student;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dormy_pu");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        // Clean tables at the start of the program
        cleanTables(em);

        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split("#");
            String command = parts[0];

            switch (command) {
                case "dorm-add":
                    if (parts.length == 4) {
                        String dormName = parts[1];
                        int capacity = Integer.parseInt(parts[2]);
                        String gender = parts[3];
                        Dorm dorm = new Dorm(dormName, capacity, gender, 0);
                        em.getTransaction().begin();
                        em.persist(dorm);
                        em.getTransaction().commit();
                    }
                    break;
                case "student-add":
                    if (parts.length == 5) {
                        String studentId = parts[1];
                        // Check if student with same ID already exists
                        if (em.find(Student.class, studentId) == null) {
                            String studentName = parts[2];
                            String entranceYear = parts[3];
                            String gender = parts[4];
                            Student student = new Student(studentId, studentName, entranceYear, gender);
                            em.getTransaction().begin();
                            em.persist(student);
                            em.getTransaction().commit();
                        } else {
                            // System.out.println("Student with ID " + studentId + " already exists.");
                        }
                    }
                    break;
                case "assign":
                    if (parts.length == 3) {
                        String studentId = parts[1];
                        String dormName = parts[2];
                        Student student = em.find(Student.class, studentId);
                        Dorm dorm = em.createQuery("SELECT d FROM Dorm d WHERE d.name = :name", Dorm.class)
                                .setParameter("name", dormName)
                                .getSingleResult();
                        if (student != null && dorm != null && student.getGender().equals(dorm.getGender()) && student.getDorms().isEmpty()) {
                            if (dorm.getResident() < dorm.getCapacity()) {
                                student.getDorms().add(dorm);
                                dorm.getStudents().add(student);
                                dorm.setResident(dorm.getResident() + 1);
                                em.getTransaction().begin();
                                em.merge(student);
                                em.merge(dorm);
                                em.getTransaction().commit();
                            } else {
                                // System.out.println("Dorm " + dormName + " is full.");
                            }
                        } else {
                            // System.out.println("Student " + studentId + " cannot be assigned to dorm " + dormName + ".");
                        }
                    }
                    break;
                case "display-all":
                    List<Dorm> dorms = em.createQuery("SELECT d FROM Dorm d ORDER BY d.name", Dorm.class).getResultList();
                    for (Dorm dorm : dorms) {
                        System.out.println(dorm.toString());
                        for (Student student : dorm.getStudents()) {
                            System.out.println(student.toString());
                        }
                    }
                    break;
                case "---":
                    em.close();
                    emf.close();
                    sc.close();
                    return;
                default:
                    break;
            }
        }
    }

    private static void cleanTables(EntityManager em) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Student").executeUpdate();
        em.createQuery("DELETE FROM Dorm").executeUpdate();
        em.getTransaction().commit();
    }
}
