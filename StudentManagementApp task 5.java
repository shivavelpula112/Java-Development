import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        loadStudents();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
        saveStudents();
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private void saveStudents() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }

    private void loadStudents() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            students = new ArrayList<>();
        }
    }
}

public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    sms.addStudent(new Student(name, rollNumber, grade));
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.print("Enter roll number to remove: ");
                    int rollToRemove = scanner.nextInt();
                    sms.removeStudent(rollToRemove);
                    System.out.println("Student removed if exists.");
                    break;
                case 3:
                    System.out.print("Enter roll number to search: ");
                    int rollToSearch = scanner.nextInt();
                    Student student = sms.searchStudent(rollToSearch);
                    System.out.println((student != null) ? student : "Student not found.");
                    break;
                case 4:
                    sms.displayStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}
