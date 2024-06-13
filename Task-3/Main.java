import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeUsers();

        while (true) {
            System.out.println("Online Examination System - Choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Update Profile");
            System.out.println("3. Change Password");
            System.out.println("4. Start Exam");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    updateProfile(scanner);
                    break;
                case 3:
                    changePassword(scanner);
                    break;
                case 4:
                    startExam();
                    break;
                case 5:
                    logout();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void initializeUsers() {
        users.add(new User("user1", "password1", "John Doe", "john.doe@example.com"));
        users.add(new User("user2", "password2", "Jane Smith", "jane.smith@example.com"));
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                currentUser = user;
                System.out.println("Login successful. Welcome, " + currentUser.getFullName() + "!");
                return;
            }
        }

        System.out.println("Invalid username or password.");
    }

    private static void updateProfile(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter new full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        currentUser.setFullName(fullName);
        currentUser.setEmail(email);

        System.out.println("Profile updated successfully.");
    }

    private static void changePassword(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();
        if (!currentUser.checkPassword(currentPassword)) {
            System.out.println("Incorrect current password.");
            return;
        }

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        currentUser.setPassword(newPassword);

        System.out.println("Password changed successfully.");
    }

    private static void startExam() {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new Question("Who wrote 'To Kill a Mockingbird'?", new String[]{"Harper Lee", "Mark Twain", "Ernest Hemingway", "Jane Austen"}, 0));

        Exam exam = new Exam(questions, 60); // 60 seconds for the exam
        exam.start();
    }

    private static void logout() {
        if (currentUser == null) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("Goodbye, " + currentUser.getFullName() + "!");
        currentUser = null;
    }
}
