package pbo.f01;
/**
 * 12S22038 Ade Yohana Azeka Siahaan
 * 12S22044 - Jufourlisa Sirait
 */

 import java.util.*;

import pbo.f01.model.Dorm;
import pbo.f01.model.Student;

import javax.persistence.*;

public class App {
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Dorm> dorms = new ArrayList<>();

    public static void main(String[] _args) {

            factory = Persistence.createEntityManagerFactory("dormy_pu");
            entityManager = factory.createEntityManager();

            displayAllContacts();
            clearTables();
            seedTables();
            displayAllContacts();


        
        entityManager.close();
    }


    private static void displayAllContacts(){
        String jpql = "SELECT c FROM Student c ORDER BY c.nim";
        List<Dorm> dormss = entityManager.createQuery(jpql, Dorm.class).getResultList();

        System.out.println("amannnnnn");
        for(Dorm dorm : dormss){
            System.out.println(dorm);
        }
    }

    private static void seedTables(){
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("---")) {
                break;
            }

        String[] inputSegments = input.split("#");
        if (inputSegments[0].equals("student-add")) {
            // student-add#12S21010#Bobby Siagian#2021#male
            String nim = inputSegments[1];
            String nama = inputSegments[2];
            String year = inputSegments[3];
            String jenisK = inputSegments[4];

            boolean isDuplicate = false;
            for (Student s : students) {
                if (s.getNim().equals(nim)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                Student s = new Student(nim, nama, year, jenisK);
                students.add(s);
                // System.out.println(s);
            }
        } else if (inputSegments[0].equals("dorm-add")) {
            // dorm-add#Pniel#5#female
            String asrama = inputSegments[1];
            int maxkapasitas = Integer.parseInt(inputSegments[2]);
            String jenis = inputSegments[3];

            Dorm a = new Dorm(asrama, maxkapasitas, jenis);
            dorms.add(a);
            Dorm cDorm = new Dorm (asrama, maxkapasitas, jenis);
            // System.out.println(a);
        } else if (inputSegments[0].equals("assign")) {
            String nim = inputSegments[1];
            String asrama = inputSegments[2];

            Student assignedStudent = null;
            Dorm targetDorm = null;

            for (Student student : students) {
                if (student.getNim().equals(nim)) {
                    assignedStudent = student;
                    break;
                }
            }

            for (Dorm dorm : dorms) {
                if (dorm.getAsrama().equals(asrama)) {
                    targetDorm = dorm;
                    break;
                }
            }

            if (assignedStudent != null && targetDorm != null) {
                targetDorm.addPenghuni(assignedStudent);
            }
        } else if (inputSegments[0].equals("display-all")) {
            // Sort dorms by name
            Collections.sort(dorms, Comparator.comparing(Dorm::getAsrama));

            for (Dorm dorm : dorms) {
                // Display dorm information
                System.out.println(dorm.getAsrama() + "|" + dorm.getJenis() + "|" + dorm.getMaxkapasitas() + "|" + dorm.getNumberOfStudents());

                // Sort students by name within each dorm
                Collections.sort(dorm.getPenghuni(), Comparator.comparing(Student::getNama));

                // Display students in the dorm
                for (Student student : dorm.getPenghuni()) {
                    System.out.println(student.getNim() + "|" + student.getNama() + "|" + student.getYear());
                }
            }
        }

        //entityManager.persist(cDorm);

        entityManager.flush();
        entityManager.getTransaction().commit();
    }
    }
    
    public static void clearTables() {
        String jpql = "DELETE FROM Dorm.c";

        entityManager.getTransaction().begin();

        int effected = entityManager.createQuery(jpql).executeUpdate();
        entityManager.flush();
        entityManager.getTransaction().commit();
        System.out.println("brsih");
    }
    
}