// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.util.Scanner;
// import java.util.List;
// public class Student {

//     void SignUp() throws Exception {

//         System.out.println("\n\n                                    ::::::::::::: Student Sign Up Interface :::::::::::::\n");
  
//         Scanner sc = new Scanner(System.in);

//         Class.forName("com.mysql.cj.jdbc.Driver");
//         String dbur1 = "jdbc:mysql://localhost:3306/onlineexaminationsystem";
//         String duser = "root";
//         String dbpass = "";
//         Connection con = DriverManager.getConnection(dbur1, duser, dbpass);

//         if (con != null) {

//             String sql = "insert into student(UserName,Email,Password,DateOfBirth,Gender) values (?,?,?,?,?)";
//             PreparedStatement pst = con.prepareStatement(sql);
//             System.out.print("  Enter User Name : ");
//             String as=sc.next();
//             pst.setString(1, as);
//             System.out.print("  Enter Email Id : ");
//             pst.setString(2, sc.next());
//             System.out.print("  Enter Password : ");
//             pst.setString(3, sc.next());
//             System.out.println(" Records : " + pst.executeUpdate());
//             String sql1 = "Select AdminId from admin where UserName=? ";
//             PreparedStatement pst1 = con.prepareStatement(sql1);
//             pst1.setString(1, as);

//             ResultSet rs=pst1.executeQuery();

//             while(rs.next()){
//                 int id=rs.getInt("AdminId");
//                 System.out.println("\n  Your AdminId Is : "+id);
//             }
//         }
//     }

//     void LogIn(int attempt) throws Exception {

//         Admin admin=new Admin();

//         Scanner sc = new Scanner(System.in);

//         Class.forName("com.mysql.cj.jdbc.Driver");
//         String dbur1 = "jdbc:mysql://localhost:3306/onlineexaminationsystem";
//         String duser = "root";
//         String dbpass = "";
    
//         Connection con = DriverManager.getConnection(dbur1, duser, dbpass);

//         if (con != null) {

//             String sql = "Select * from Admin where UserName=? ";
//             PreparedStatement pst = con.prepareStatement(sql);
//             System.out.print("  Enter Admin Id : ");
//             pst.setString(1, sc.next());
//             System.out.print("  Enter Password : ");
//             String Password=sc.next();

//             ResultSet rs=pst.executeQuery();
//             boolean Found=false;
            
//             while(rs.next()){
//             Found=true;
//                 String pass=rs.getString("Password");

//                 if (Password.equalsIgnoreCase(pass)) {
//                     System.out.println("\n  [ Log In Successful ]\n");







                    
//                 } 
//             }
            
            
//             if(attempt<1){
//                 if(Found!=true) {
//                   System.out.println("\n  [ Please Check Your UserName OR Password Is Invalid ]\n");
//                  admin.LogIn(attempt+1);
//                  }
//              }
//             if(attempt==1){
//                 System.out.println("\n  [ Please Check Your UserName OR Password Is Invalid ]\n"); 
//                 System.out.println("  [ This Is Your Last Try To Log In ]\n");
//                 admin.LogIn(attempt+1);

//             }
//             if(attempt==2){
//                 System.out.println("\n  [ Please Try Again After Some Time ]\n");
//             }
           
//     }

    
// }
// void DeleteAccount(int attempt) throws Exception {

//     Admin admin=new Admin();

//         Scanner sc = new Scanner(System.in);

//         Class.forName("com.mysql.cj.jdbc.Driver");
//         String dbur1 = "jdbc:mysql://localhost:3306/onlineexaminationsystem";
//         String duser = "root";
//         String dbpass = "";
    
//         Connection con = DriverManager.getConnection(dbur1, duser, dbpass);

//         if (con != null) {

//             String sql = "Delete from Admin Where UserName=? And Password=?  ";
//             PreparedStatement pst = con.prepareStatement(sql);
//             System.out.print("  Enter User Name : ");
//             pst.setString(1, sc.next());
//             System.out.print("  Enter Password : ");
//             pst.setString(2, sc.next());

//             int done=pst.executeUpdate();
//             boolean Found=false;
            
//             if(done==1){
//             Found=true;
                
//                     System.out.println("\n  [ Account Deleted Successfully ]\n");
//                 return;
//             }
            
            
//             if(attempt<1){
//                 if(Found!=true) {
//                   System.out.println("\n  [ Please Check Your UserName OR Password Is Invalid ]\n");
//                  admin.DeleteAccount(attempt+1);
//                  }
//              }
//             if(attempt==1){
//                 System.out.println("\n  [ Please Check Your UserName OR Password Is Invalid ]\n"); 
//                 System.out.println("  [ This Is Your Last Try To Delete Account  ]\n");
//                 admin.DeleteAccount(attempt+1);

//             }
//             if(attempt==2){
//                 System.out.println("\n  [ Please Try Again After Some Time ]\n");
//             }
// }

// }
// }


























// import java.util.*;
// import java.util.concurrent.*;

// // Class to represent a question
// class Question {
//     private String questionText;
//     private List<String> options;
//     private int correctOption;

//     public Question(String questionText, List<String> options, int correctOption) {
//         this.questionText = questionText;
//         this.options = options;
//         this.correctOption = correctOption;
//     }

//     public String getQuestionText() {
//         return questionText;
//     }

//     public List<String> getOptions() {
//         return options;
//     }

//     public int getCorrectOption() {
//         return correctOption;
//     }
// }

// // Class to represent an exam
// class Exam {
//     private List<Question> questions;

//     public Exam(List<Question> questions) {
//         this.questions = questions;
//     }

//     public List<Question> getQuestions() {
//         return questions;
//     }
// }

// // Main class for the online exam system with time limit
// public class OnlineExamSystemWithTimeLimit {
//     private static final int EXAM_DURATION_MINUTES = 180; // 3 hours
//     private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

//     public static void main(String[] args) {
//         // Sample questions
//         List<Question> questions = new ArrayList<>();
//         questions.add(new Question("What is the capital of France?",
//                 Arrays.asList("London", "Berlin", "Paris", "Madrid"), 2));
//         questions.add(new Question("What is the largest planet in our solar system?",
//                 Arrays.asList("Mars", "Jupiter", "Saturn", "Neptune"), 1));
//         questions.add(new Question("Who developed the theory of relativity?",
//                 Arrays.asList("Isaac Newton", "Albert Einstein", "Galileo Galilei", "Stephen Hawking"), 1));

//         // Creating an exam
//         Exam exam = new Exam(questions);

//         // Start the exam with time limit
//         conductExamWithTimeLimit(exam);
//     }

//     public static void conductExamWithTimeLimit(Exam exam) {
//         Scanner scanner = new Scanner(System.in);
//         List<Question> questions = exam.getQuestions();
//         int score = 0;

//         System.out.println("Welcome to the Online Exam!");
//         System.out.println("You have " + EXAM_DURATION_MINUTES + " minutes (3 hours) to complete the exam.");

//         // Shuffle questions to randomize order
//         Collections.shuffle(questions);

//         // Record start time
//         long startTime = System.currentTimeMillis();

//         // Schedule task to end exam after time limit
//         scheduler.schedule(() -> {
//             System.out.println("\nTime's up! Exam has ended.");
//             submitExam(score, questions.size());
//             System.exit(0);
//         }, EXAM_DURATION_MINUTES, TimeUnit.MINUTES);

//         // Display each question
//         for (int i = 0; i < questions.size(); i++) {
//             Question q = questions.get(i);
//             System.out.println("\nQuestion " + (i + 1) + ": " + q.getQuestionText());
//             List<String> options = q.getOptions();

//             // Shuffle options to randomize order
//             Collections.shuffle(options);

//             // Display options
//             for (int j = 0; j < options.size(); j++) {
//                 System.out.println((j + 1) + ". " + options.get(j));
//             }

//             // Get user's answer
//             System.out.print("Enter your answer (1-" + options.size() + "): ");
//             int userAnswer = scanner.nextInt();

//             // Validate answer
//             if (userAnswer == q.getCorrectOption()) {
//                 System.out.println("Correct!");
//                 score++;
//             } else {
//                 System.out.println("Incorrect. The correct answer was option " + q.getCorrectOption() + ".");
//             }

//             // Check remaining time after each question
//             long elapsedTime = (System.currentTimeMillis() - startTime) / 1000 / 60; // in minutes
//             if (elapsedTime >= EXAM_DURATION_MINUTES) {
//                 System.out.println("\nTime's up! Exam has ended.");
//                 submitExam(score, questions.size());
//                 System.exit(0);
//             } else {
//                 System.out.println("Time remaining: " + (EXAM_DURATION_MINUTES - elapsedTime) + " minutes");
//             }
//         }

//         // If all questions are answered before time limit
//         submitExam(score, questions.size());

//         scanner.close();
//     }

//     public static void submitExam(int score, int totalQuestions) {
//         System.out.println("\nExam completed!");
//         System.out.println("Your score is: " + score + "/" + totalQuestions);
//     }
// }




// import java.sql.*;
// import java.util.*;

// // Class to represent a question
// class Question {
//     private int id;
//     private String questionText;
//     private List<String> options;
//     private int correctOption;

//     public Question(int id, String questionText, List<String> options, int correctOption) {
//         this.id = id;
//         this.questionText = questionText;
//         this.options = options;
//         this.correctOption = correctOption;
//     }

//     public int getId() {
//         return id;
//     }

//     public String getQuestionText() {
//         return questionText;
//     }

//     public List<String> getOptions() {
//         return options;
//     }

//     public int getCorrectOption() {
//         return correctOption;
//     }
// }

// // Main class to simulate an online examination system with JDBC
// public class OnlineExamSystemJDBC {
//     // JDBC database URL, username and password
//     static final String jdbcUrl = "jdbc:mysql://localhost:3306/online_exam";
//     static final String username = "your_username";
//     static final String password = "your_password";

//    public static void main(String[] args) {
//         Connection connection = null;
//         Statement statement = null;
//         ResultSet resultSet = null;

//         try {
//             // Establish connection to database
//             connection = DriverManager.getConnection(jdbcUrl, username, password);
//             statement = connection.createStatement();

//             // Retrieve questions from database
//             resultSet = statement.executeQuery("SELECT * FROM questions");
//             List<Question> questions = new ArrayList<>();

//             // Populate questions list
//             while (resultSet.next()) {
//                 int id = resultSet.getInt("id");
//                 String questionText = resultSet.getString("question_text");
//                 String option1 = resultSet.getString("option1");
//                 String option2 = resultSet.getString("option2");
//                 String option3 = resultSet.getString("option3");
//                 String option4 = resultSet.getString("option4");
//                 int correctOption = resultSet.getInt("correct_option");

//                 List<String> options = Arrays.asList(option1, option2, option3, option4);
//                 Question question = new Question(id, questionText, options, correctOption);
//                 questions.add(question);
//             }

//             // Creating an exam
//             Exam exam = new Exam(questions);

//             // Simulating exam process
//             simulateExam(exam);

//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             // Close all resources
//             try {
//                 if (resultSet != null) resultSet.close();
//                 if (statement != null) statement.close();
//                 if (connection != null) connection.close();
//             } catch (SQLException e) {
//                 e.printStackTrace();
//             }
//         }
//     // }

//     public static void simulateExam(Exam exam) {
//         Scanner scanner = new Scanner(System.in);
//         List<Question> questions = exam.getQuestions();
//         int score = 0;

//         // Shuffle questions to randomize order
//         Collections.shuffle(questions);

//         // Display each question
//         for (int i = 0; i < questions.size(); i++) {
//             Question q = questions.get(i);
//             System.out.println("Question " + (i + 1) + ": " + q.getQuestionText());
//             List<String> options = q.getOptions();

//             // Shuffle options to randomize order
//             Collections.shuffle(options);

//             // Display options
//             for (int j = 0; j < options.size(); j++) {
//                 System.out.println((j + 1) + ". " + options.get(j));
//             }

//             // Get user's answer
//             System.out.print("Enter your answer (1-" + options.size() + "): ");
//             int userAnswer = scanner.nextInt();

//             // Validate answer
//             if (userAnswer == q.getCorrectOption()) {
//                 System.out.println("Correct!\n");
//                 score++;
//             } else {
//                 System.out.println("Incorrect. The correct answer was option " + q.getCorrectOption() + ".\n");
//             }
//         }

//         // Display final score
//         System.out.println("Exam completed! Your score is: " + score + "/" + questions.size());

//         scanner.close();
    
// }
// }