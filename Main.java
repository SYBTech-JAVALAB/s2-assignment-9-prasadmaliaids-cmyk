import java.io.*;
import java.util.*;

class Student {
    String name;
    int id;
    double grade;

    Student(String name, int id, double grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file = new File("students.txt");

        try {
            // Create file if not exists
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created.");
            }

            // Choose mode
            System.out.print("Enter mode (append/overwrite): ");
            String mode = sc.nextLine();

            FileWriter fw;
            if (mode.equalsIgnoreCase("append")) {
                fw = new FileWriter(file, true);
            } else {
                fw = new FileWriter(file, false);
            }

            BufferedWriter bw = new BufferedWriter(fw);

            // Input student details
            System.out.print("Enter number of students: ");
            int n = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < n; i++) {
                System.out.print("Enter name: ");
                String name = sc.nextLine();

                System.out.print("Enter ID: ");
                int id = sc.nextInt();

                System.out.print("Enter Grade: ");
                double grade = sc.nextDouble();
                sc.nextLine();

                bw.write(name + "," + id + "," + grade);
                bw.newLine();
            }

            bw.close();

            // Reading file
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<Student> list = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                list.add(new Student(data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2])));
            }

            br.close();

            // Sorting by name
            Collections.sort(list, (a, b) -> a.name.compareToIgnoreCase(b.name));

            // Display
            System.out.println("\n--- Sorted Student Records ---");
            for (Student s : list) {
                System.out.println("Name: " + s.name + ", ID: " + s.id + ", Grade: " + s.grade);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}