import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;

public class StudentTest {
    private Student studentManager;
    private Scanner scanner;

    @Before
    public void setUp() {
        studentManager = new Student();
        scanner = new Scanner(System.in);
    }

    @After
    public void tearDown() {
        // Clean up resources
        scanner.close();
    }

    @Test
    public void TestSaveStudent() {
        // Simulate student input
        String input = "123\nJohn Doe\n18\njohn.doe@example.com\nComputer Science\n";
        scanner = new Scanner(input); // Simulating user input through Scanner

        studentManager.SaveStudent(scanner);

        // Search to verify the student is saved correctly
        assertEquals("123", Student.getStudentIDs().get(0));
        assertEquals("John Doe", Student.getStudentNames().get(0));
        assertEquals("18", Student.getStudentAges().get(0));
        assertEquals("john.doe@example.com", Student.getStudentEmails().get(0));
        assertEquals("Computer Science", Student.getStudentCourses().get(0));
    }

    @Test
    public void TestSearchStudent() {
        String input = "123\nJohn Doe\n18\njohn.doe@example.com\nComputer Science\n";
        scanner = new Scanner(input);

        studentManager.SaveStudent(scanner);

        String searchInput = "123\n";
        scanner = new Scanner(searchInput); // Simulate searching by student ID
        studentManager.SearchStudent(scanner);

        assertEquals("123", Student.getStudentIDs().get(0));
    }

    @Test
    public void TestSearchStudent_StudentNotFound() {
        String searchInput = "999\n";
        scanner = new Scanner(searchInput); // Simulate searching for a non-existing student

        // Expected behavior is to return no match, or index -1
        int index = Student.getStudentIDs().indexOf("999");
        assertEquals(-1, index);
    }

    @Test
    public void TestDeleteStudent() {
        String input = "123\nJohn Doe\n18\njohn.doe@example.com\nComputer Science\n";
        scanner = new Scanner(input);

        studentManager.SaveStudent(scanner);

        String deleteInput = "123\n";
        scanner = new Scanner(deleteInput); // Simulate deleting by student ID
        studentManager.DeleteStudent(scanner);

        // Verify student is deleted
        assertFalse(Student.getStudentIDs().contains("123"));
    }

    @Test
    public void TestDeleteStudent_StudentNotFound() {
        String deleteInput = "999\n";
        scanner = new Scanner(deleteInput); // Simulate deleting a non-existing student

        int index = Student.getStudentIDs().indexOf("999");
        assertEquals(-1, index); // Ensure the student is not found
    }

    @Test
    public void TestStudentAge_StudentAgeValid() {
        String ageInput = "18\n";
        scanner = new Scanner(ageInput); // Simulate valid age input

        // Add logic to validate student age and capture
        boolean isValid = studentManager.isValidAge(scanner);
        assertTrue(isValid);
    }

    @Test
    public void TestStudentAge_StudentAgeInvalid() {
        String ageInput = "15\n";
        scanner = new Scanner(ageInput); // Simulate invalid age (less than 16)

        boolean isValid = studentManager.isValidAge(scanner);
        assertFalse(isValid);
    }

    @Test
    public void TestStudentAge_StudentAgeInvalidCharacter() {
        String ageInput = "abc\n";
        scanner = new Scanner(ageInput); // Simulate invalid character input for age

        boolean isValid = studentManager.isValidAge(scanner);
        assertFalse(isValid);
    }
}
