import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AikidoTrackerTest {

    @BeforeEach
    void setUp() {
        // Reset the sessions list before each test
        AikidoTracker.sessions.clear();
    }

    @Test
    void addPracticeSession() {
        String input = "2023-10-01\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        AikidoTracker.addPracticeSession(scanner);

        assertEquals(1, AikidoTracker.sessions.size());
        assertEquals("2023-10-01", AikidoTracker.sessions.get(0).date);
    }

    @Test
    void viewTotalSessions() {
        AikidoTracker.sessions.add(new TrainingSession("2023-10-01"));
        AikidoTracker.sessions.add(new TrainingSession("2023-10-02"));

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        AikidoTracker.viewTotalSessions();

        assertTrue(outContent.toString().contains("Total practice sessions: 2"));
    }

    @Test
    void checkGraduationEligibility() {
        AikidoTracker.sessions.add(new TrainingSession("2023-04-01"));
        AikidoTracker.sessions.add(new TrainingSession("2024-04-01"));
        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        AikidoTracker.checkGraduationEligibility();
        assertTrue(outContent.toString().contains("You are eligible for Kyu graduation!\n"));
    }
}