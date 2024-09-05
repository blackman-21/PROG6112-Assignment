import java.util.ArrayList;
import java.util.Scanner;

// Student Class
class Student {
    // ArrayLists to store student information
    private static ArrayList<String> studentIDs = new ArrayList<>();
    private static ArrayList<String> studentNames = new ArrayList<>();
    private static ArrayList<String> studentAges = new ArrayList<>();
    private static ArrayList<String> studentEmails = new ArrayList<>();
    private static ArrayList<String> studentCourses = new ArrayList<>();

    // Getters for the test cases to access student data
    public static ArrayList<String> getStudentIDs() {
        return studentIDs;
    }

    public static ArrayList<String> getStudentNames() {
        return studentNames;
    }

    public static ArrayList<String> getStudentAges() {
        return studentAges;
    }

    public static ArrayList<String> getStudentEmails() {
        return studentEmails;
    }

    public static ArrayList<String> getStudentCourses() {
        return studentCourses;
    }

    // Save a new student
    public void SaveStudent(Scanner scanner) {
        System.out.println("\nCAPTURE A NEW STUDENT");
        System.out.println("********************************************");

        System.out.print("Enter the student ID: ");
        studentIDs.add(scanner.nextLine());

        System.out.print("Enter the student name: ");
        studentNames.add(scanner.nextLine());

        // Validate the student age
        String ageInput;
        int age = 0;
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Enter the student age (must be 16 or older): ");
            ageInput = scanner.nextLine();
            try {
                age = Integer.parseInt(ageInput);
                if (age >= 16) {
                    validAge = true;
                } else {
                    System.out.println("You have entered an incorrect student age.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        studentAges.add(String.valueOf(age));

        System.out.print("Enter the student email: ");
        studentEmails.add(scanner.nextLine());

        System.out.print("Enter the student course: ");
        studentCourses.add(scanner.nextLine());

        System.out.println("Student captured successfully!");
    }

    // Search for a student by ID
    public void SearchStudent(Scanner scanner) {
        System.out.println("\nSEARCH FOR A STUDENT");
        System.out.println("********************************************");

        System.out.print("Enter the student ID: ");
        String studentID = scanner.nextLine();

        // Search for the student by ID
        int index = studentIDs.indexOf(studentID);

        if (index != -1) {
            // Student found, display the details
            System.out.println("Student found:");
            System.out.println("Student ID: " + studentIDs.get(index));
            System.out.println("Name: " + studentNames.get(index));
            System.out.println("Age: " + studentAges.get(index));
            System.out.println("Email: " + studentEmails.get(index));
            System.out.println("Course: " + studentCourses.get(index));
        } else {
            System.out.println("Error: Student with ID " + studentID + " cannot be located.");
        }
    }

    // Delete a student by ID
    public void DeleteStudent(Scanner scanner) {
        System.out.println("\nDELETE A STUDENT");
        System.out.println("********************************************");

        System.out.print("Enter the student ID to delete: ");
        String studentID = scanner.nextLine();

        // Search for the student by ID
        int index = studentIDs.indexOf(studentID);

        if (index != -1) {
            System.out.println("Are you sure you want to delete student with ID: " + studentID + " from the system? Yes (y) to delete.");
            String confirmation = scanner.nextLine();

            if ("y".equalsIgnoreCase(confirmation)) {
                // Delete the student details from all lists
                studentIDs.remove(index);
                studentNames.remove(index);
                studentAges.remove(index);
                studentEmails.remove(index);
                studentCourses.remove(index);
                System.out.println("Student with Student ID: " + studentID + " was deleted!");
            } else {
                System.out.println("Deletion canceled.");
            }
        } else {
            System.out.println("Error: Student with ID " + studentID + " cannot be located.");
        }
    }

    // Print student report
    public void StudentReport() {
        System.out.println("\nSTUDENT REPORT");
        System.out.println("********************************************");

        if (studentIDs.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (int i = 0; i < studentIDs.size(); i++) {
                System.out.println("STUDENT " + (i + 1));
                System.out.println("______________________________________________");
                System.out.println("STUDENT ID: " + studentIDs.get(i));
                System.out.println("STUDENT NAME: " + studentNames.get(i));
                System.out.println("STUDENT AGE: " + studentAges.get(i));
                System.out.println("STUDENT EMAIL: " + studentEmails.get(i));
                System.out.println("STUDENT COURSE: " + studentCourses.get(i));
                System.out.println("_______________________________________________");
            }
        }
    }

    // Method to validate student age input
    public boolean isValidAge(Scanner scanner) {
        try {
            int age = Integer.parseInt(scanner.nextLine());
            return age >= 16; // Valid if age is 16 or above
        } catch (NumberFormatException e) {
            return false; // Invalid if not a number
        }
    }

    // Exit the application
    public void ExitStudentApplication() {
        System.out.println("Exiting Application...");
        System.exit(0);
    }
}


// Main Class
public class PROGAssignment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student studentManager = new Student();

        System.out.println("STUDENT MANAGEMENT APPLICATION");
        System.out.println("******************************************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");

        String input = scanner.nextLine();

        if ("1".equals(input)) {
            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        studentManager.SaveStudent(scanner);
                        break;
                    case 2:
                        studentManager.SearchStudent(scanner);
                        break;
                    case 3:
                        studentManager.DeleteStudent(scanner);
                        break;
                    case 4:
                        studentManager.StudentReport();
                        break;
                    case 5:
                        studentManager.ExitStudentApplication();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Exiting Application...");
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nPlease select one of the following menu items:");
        System.out.println("(1) Capture a new student.");
        System.out.println("(2) Search for a student.");
        System.out.println("(3) Delete a student.");
        System.out.println("(4) Print student report.");
        System.out.println("(5) Exit Application.");
        System.out.print("Enter your choice: ");
    }
}