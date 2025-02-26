import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

class TrainingSession {
    String date;

    public TrainingSession(String date) {
        this.date = date;
    }
}

public class AikidoTracker {
    public static ArrayList<TrainingSession> sessions = new ArrayList<>();
    private static final int ELIGIBILITY_SESSIONS = 100;
    private static final int ELIGIBILITY_MONTHS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Aikido Practice Tracker =====");
            System.out.println("1. Add Practice Session");
            System.out.println("2. View Total Sessions");
            System.out.println("3. Check Graduation Eligibility");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPracticeSession(scanner);
                    break;
                case 2:
                    viewTotalSessions();
                    break;
                case 3:
                    checkGraduationEligibility();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        } while (choice != 4);
    }

    public static void addPracticeSession(Scanner scanner) {
        System.out.print("Enter session date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        sessions.add(new TrainingSession(date));
        System.out.println("Session added successfully!\n");
    }

    public static void viewTotalSessions() {
        System.out.println("Total practice sessions: " + sessions.size() + "\n");
    }

    public static void checkGraduationEligibility() {
        if (sessions.isEmpty()) {
            System.out.println("No sessions recorded yet.\n");
            return;
        }

        String firstSessionDate = sessions.get(0).date;
        LocalDate startDate;
        try {
            startDate = LocalDate.parse(firstSessionDate);
        } catch (DateTimeParseException e) {
            System.out.println("Not eligible yet\n");
            return;
        }

        LocalDate currentDate = LocalDate.now();
        long monthsPassed = ChronoUnit.MONTHS.between(startDate, currentDate);

        if (sessions.size() >= ELIGIBILITY_SESSIONS || monthsPassed >= ELIGIBILITY_MONTHS) {
            System.out.println("You are eligible for Kyu graduation!\n");
        } else {
            System.out.println("Not eligible yet. You need " + (ELIGIBILITY_SESSIONS - sessions.size()) + " more sessions or must wait " + (ELIGIBILITY_MONTHS - monthsPassed) + " more months.\n");
        }
    }
}
