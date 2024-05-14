package pbo.f01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pbo.f01.model.Dorm;
import pbo.f01.model.Student;
/**
 * 12S22030 - Bryan Evans Simamora
 * 12S22036 - Jonathan Fransilo Hutabarat
 */
public class App {
        private static EntityManagerFactory factory;
        private static EntityManager entityManager;
        static final String DELIMINATOR = "#";
        static final String STOP = "---";
    
        public static void main(String[] _args) {
            factory = Persistence.createEntityManagerFactory("students_pu");
            entityManager = factory.createEntityManager();
            
            Scanner scan = new Scanner(System.in);
            String stdin;
            String[] buffer;
    
            while (true) {
                stdin = scan.nextLine();
                if (stdin.equals(STOP))
                    break;
                buffer = stdin.split(DELIMINATOR);
                String order = buffer[0];
                buffer = Arrays.copyOfRange(buffer,1,buffer.length);
    
                switch (order) {
                    case "student-add":
                        // seedTableStudent();
                        break;
    
                    case "dorm-add":
                        // seedTableDorm();
                        break;
                    
                    case "assign":
                        break;
    
                    case "display-all":
                        displayAllStudents();
                        break;
                }
            }
    
            // display
            // insert
            // delete
            // select
    
    
            entityManager.close();
            scan.close();
        }
    
        private static void displayAllStudents() {
            String query = "SELECT s FROM student s ORDER BY s.name";
            List<Student> students = entityManager
                .createQuery(query, Student.class)
                .getResultList();
    
            System.out.println("displayAllDorms--");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    
    }
