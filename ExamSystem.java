import java.sql.*;
import java.util.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.List;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ExamSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Timer timer = new Timer(); // Timer for exam duration

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String jdbcUrl = "jdbc:mysql://localhost:3306/onlineexaminationsystem";
        String username = "root";
        String password = "";

        try {
            // Establish connection to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();

            // Retrieve exam details including duration from database
            resultSet = statement.executeQuery("SELECT * FROM exam LIMIT 1"); // Assuming only one row for exam details
            int examDuration = 0;

            if (resultSet.next()) {
                examDuration = resultSet.getInt("duration");
            }

            // Retrieve questions from database
            resultSet = statement.executeQuery("SELECT QuestionId, Question, OptionA, OptionB, OptionC, OptionD, CurrectAnswer FROM examdata");
            List<Question> questions = new ArrayList<>();

            // Populate questions list
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String questionText = resultSet.getString("question_text");
                String option1 = resultSet.getString("option1");
                String option2 = resultSet.getString("option2");
                String option3 = resultSet.getString("option3");
                String option4 = resultSet.getString("option4");
                int correctOption = resultSet.getInt("correct_option");

                List<String> options = Arrays.asList(option1, option2, option3, option4);
                Question question = new Question(id, questionText, options, correctOption);
                questions.add(question);
            }

            // Create Exam object
            Exam exam = new Exam(questions);

            // Start exam with timer
            startExamWithTimer(exam, examDuration);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void startExamWithTimer(Exam exam, int examDuration) {
        // Schedule a reminder for every hour
        int oneHour = 60; // 60 minutes
        int twoHours = 120; // 120 minutes
        int threeHours = 180; // 180 minutes

        // Schedule tasks for reminders
        timer.schedule(new ExamReminderTask("One hour remaining!", oneHour), (examDuration - oneHour) * 60 * 1000);
        timer.schedule(new ExamReminderTask("Two hours remaining!", twoHours), (examDuration - twoHours) * 60 * 1000);
        timer.schedule(new ExamReminderTask("Three hours remaining!", threeHours), (examDuration - threeHours) * 60 * 1000);

        // Start exam
        simulateExam(exam, examDuration);
    }

    public static void simulateExam(Exam exam, int examDuration) {
        List<Question> questions = exam.getQuestions();
        Set<Integer> attemptedQuestions = new HashSet<>();
        int score = 0;
        int remainingTime = examDuration;

        // Display each question
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + q.getQuestionText());

            // Check if question has been attempted
            if (attemptedQuestions.contains(q.getId())) {
                System.out.println("Already attempted.");
                continue;
            }

            List<String> options = q.getOptions();

            // Display options
            for (int j = 0; j < options.size(); j++) {
                System.out.println((char) ('A' + j) + ". " + options.get(j));
            }

            // Get user's answer (ensuring valid input)
            int userAnswer = getUserAnswer(options.size());

            if (userAnswer == -1) {
                System.out.println("Invalid input. Please enter A, B, C, or D.");
                i--; // Re-attempt the same question
                continue;
            }

            if (userAnswer == q.getCorrectOption()) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was option " + (char) ('A' + q.getCorrectOption()) + ".\n");
            }

            // Mark question as attempted
            attemptedQuestions.add(q.getId());

            // Calculate remaining time
            remainingTime = calculateRemainingTime(examDuration);

            // Check remaining time
            if (remainingTime <= 0) {
                System.out.println("Time's up! Exam will be submitted.");
                break;
            }
        }

        // Display final score
        System.out.println("Exam completed! Your score is: " + score + "/" + questions.size());

        // Option to attempt non-attempted questions or submit exam
        if (remainingTime > 0) {
            System.out.println("Do you want to attempt non-attempted questions?");
            System.out.println("1. Yes");
            System.out.println("2. Submit Exam");

            int choice = getUserInput("Enter your choice (1-2): ");

            if (choice == 1) {
                attemptNonAttemptedQuestions(exam, attemptedQuestions, remainingTime);
            } else {
                System.out.println("Submitting exam...");
            }
        } else {
            System.out.println("Submitting exam...");
        }

        // Cancel timer
        timer.cancel();

        // Close scanner
        scanner.close();
    }

    public static void attemptNonAttemptedQuestions(Exam exam, Set<Integer> attemptedQuestions, int remainingTime) {
        List<Question> questions = exam.getQuestions();

        // Display non-attempted questions
        System.out.println("Non-attempted questions:");

        for (Question q : questions) {
            if (!attemptedQuestions.contains(q.getId())) {
                System.out.println("Question " + q.getId() + ": " + q.getQuestionText());
                List<String> options = q.getOptions();
                for (int j = 0; j < options.size(); j++) {
                    System.out.println((char) ('A' + j) + ". " + options.get(j));
                }
                System.out.println();
            }
        }

        // Option to submit exam after attempting non-attempted questions
        System.out.println("Do you want to submit exam?");
        System.out.println("1. Yes");
        System.out.println("2. No (continue attempting)");

        int choice = getUserInput("Enter your choice (1-2): ");

        if (choice == 1) {
            System.out.println("Submitting exam...");
        } else {
            System.out.println("Continuing exam...");
            simulateExam(exam, remainingTime); // Restart exam with remaining time for attempting remaining questions
        }
    }

    public static int getUserInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer value.");
            scanner.next(); // Clear the invalid input
        }
        return scanner.nextInt();
    }

    public static int getUserAnswer(int numOptions) {
        String input = scanner.next().trim().toUpperCase();
        char answerChar = input.charAt(0);

        if (answerChar < 'A' || answerChar > 'A' + numOptions - 1) {
            return -1; // Invalid input
        }

        return answerChar - 'A';
    }

    public static int calculateRemainingTime(int examDuration) {
        long examStartTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - examStartTime) / 1000; // Convert milliseconds to seconds

        int remainingTime = examDuration - (int) elapsedTime / 60; // Convert seconds to minutes

        return remainingTime;
    }

    // TimerTask class for exam reminders
    static class ExamReminderTask extends TimerTask {
        private String message;
        private int remainingTime;

        public ExamReminderTask(String message, int remainingTime) {
            this.message = message;
            this.remainingTime = remainingTime;
        }

        @Override
        public void run() {
            System.out.println(message);
            System.out.println("Remaining time: " + remainingTime + " minutes");
        }
    }
}

// Class to represent a question
class Question {
    private int id;
    private String questionText;
    private List<String> options;
    private int correctOption;

    public Question(int id, String questionText, List<String> options, int correctOption) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

// Class to represent an exam
class Exam {
    private List<Question> questions;

    public Exam(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

