package pbo.f01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import pbo.f01.model.Dorm;
import pbo.f01.model.Student;

/**
 * 12S22030 - Bryan Evans Simamora
 * 12S22036 - Jonathan Fransilo Hutabarat
 */
public class App {

    static final String DATA_SEPARATOR = "#";

    public static void main(String[] _args) {
        Simulator simulator = new Simulator("dormy_pu");
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;
        String line = null;

        while (scanner.hasNext() && keepGoing) {
            line = scanner.nextLine();

            if (line.equals("---")) {
                break;
            }

            String[] data = line.split(DATA_SEPARATOR);
            String command = data[0];
            data = Arrays.copyOfRange(data, 1, data.length);

            switch (command) {
                case "student-add":
                    addStudent(simulator, data);
                    break;

                case "dorm-add":
                    addDorm(simulator, data);
                    break;

                case "assign":
                    assign(simulator, data);
                    break;

                case "display-all":
                    displayAll(simulator);
                    break;
            }
        }

        scanner.close();
        simulator.shutdown();
    }

    private static void addStudent(Simulator simulator, String[] data) {
        if (simulator.findStudent(data[0]) != null) {
            return;
        }

        simulator.save(new Student(data[0], data[1], data[2], data[3]));
    }

    private static void addDorm(Simulator simulator, String[] data) {
        if (simulator.findDorm(data[0]) != null) {
            return;
        }

        simulator.save(new Dorm(data[0], Integer.parseInt(data[1]), data[2]));
    }

    private static void assign(Simulator simulator, String[] data) {
        Student student = simulator.findStudent(data[0]);
        if (student == null) {
            return;
        }

        Dorm dorm = simulator.findDorm(data[1]);
        if (dorm == null) {
            return;
        }

        if (dorm.addResident(student)) {
            simulator.update(dorm);
        }
    }

    private static void displayAll(Simulator simulator) {
        List<Dorm> dorms = simulator.getAllDorms();

        for (Dorm dorm : dorms) {
            System.out.println(dorm);
            for (Student student : dorm.getResidents()) {
                System.out.println(student);
            }
        }
    }

}