import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Exam {


    void AddExam(int AdminId,int attempt) throws Exception{
        System.out.println("\n\n                                    ::::::::::::: Add Exam Interface :::::::::::::\n");
          
                Scanner sc = new Scanner(System.in);
        
                Class.forName("com.mysql.cj.jdbc.Driver");
                String dbur1 = "jdbc:mysql://localhost:3306/onlineexaminationsystem";
                String duser = "root";
                String dbpass = "";
                Connection con = DriverManager.getConnection(dbur1, duser, dbpass);
        
                if (con != null) {
        
                    String sql = "insert into exam(SubjectName,AdminId,TimeDuration) values (?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    System.out.print("\n  Enter Subject Name : ");
                    String as=sc.next();
                    pst.setString(1, as);
                    pst.setInt(2, AdminId);
                    System.out.println("Please Enter Time Duration Of Exam in Minits :");
                    if (sc.hasNextInt()) {
                    int Time=sc.nextInt();
                    pst.setInt(3, Time);
                    }
                    else{System.out.println("\n  [ Please Enter Integer ]");}
        
                    pst.executeUpdate();
        
                     
                    System.out.print("\n  Enter Number Of  Questions You Want To Insert : "); 
                    if(sc.hasNextInt()){
                    int no=sc.nextInt();
                    sc.nextLine();
                    int temp=0;
                    while(temp<no){
                    String sql11 = "INSERT INTO examdata (Question, OptionA,OptionB,OptionC,OptionD, CurrectAnswer,ExamId) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement pst11 = con.prepareStatement(sql11);
                    
                   
                    System.out.print("\n  [1] Enter Question : ");
                    pst11.setString(1, sc.nextLine());
                    System.out.print("\n\n  [1] Enter Option A : ");
                    pst11.setString(2, sc.nextLine());
                    System.out.print("\n  [2] Enter Option B : ");
                    pst11.setString(3, sc.nextLine());
                    System.out.print("\n  [3] Enter Option C : ");
                    pst11.setString(4, sc.nextLine());
                    System.out.print("\n  [4] Enter Option D : ");
                    pst11.setString(5, sc.nextLine());
                    System.out.print("\n\n  [5] Enter Currect Answer : ");
                    pst11.setString(6, sc.nextLine());
                    String sql1 = "Select ExamId from exam where SubjectName=? ";
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    pst1.setString(1, as);
                    ResultSet rs=pst1.executeQuery();
        
                    while(rs.next()){
                        int id=rs.getInt("ExamId");
                        pst11.setInt(7, id );
                        System.out.println("\n  Exam Id Is : "+id+"\n\n");
                        pst11.executeUpdate();
                    }
                    temp++;            
                }
                    }
                    else {
                    if (attempt==2) {System.out.println("\n  [ Try again later ]");
                            }
                    System.out.println("\n  [ Please enter Integer ]");
                    Exam ex=new Exam();
                    ex.AddExam(AdminId,attempt+1);
                  }
                }
            }
    void DeleteExam(int attempt,int adminID) throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.println("\n\n                                    ::::::::::::: Delete Exam Interface :::::::::::::\n");
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbur1 = "jdbc:mysql://localhost:3306/onlineexaminationsystem";
        String duser = "root";
        String dbpass = "";

        
            

        Connection con = DriverManager.getConnection(dbur1, duser, dbpass);
        
        if (con != null) {

            String sql = "delete from exam where ExamId=? and AdminId=?";
        PreparedStatement pst = con.prepareStatement(sql);
            System.out.print("\n  Please Enter Exam Id :");
            if(sc.hasNextInt()){

            int as=sc.nextInt();
            pst.setInt(1, as);
            pst.setInt(2, adminID);
            int done=pst.executeUpdate();
            if(done==1){
            System.out.println("\n  [ Exam Deleted Successfully ]");

            String sql1 = "delete from examdata where ExamId=? ";
        PreparedStatement pst1 = con.prepareStatement(sql1);
        pst1.setInt(1, as);
       pst1.executeUpdate();
            }
            
            }
            else{
                if (attempt==2) {System.out.println("\n  [ Try again later ]");}
                Exam exam=new Exam();
                exam.DeleteExam(attempt+1,adminID);
                
            }
        }
        
    }

    void DeleteAllExam(int adminID) throws Exception{
        Scanner sc=new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbur1 = "jdbc:mysql://localhost:3306/onlineexaminationsystem";
        String duser = "root";
        String dbpass = "";

        Connection con = DriverManager.getConnection(dbur1, duser, dbpass);
        
        if (con != null) {
            String sql1 = "Select ExamId from exam where AdminId=? ";
            PreparedStatement pst1 = con.prepareStatement(sql1);
            pst1.setInt(1, adminID);
            ResultSet rs=pst1.executeQuery();
                while(rs.next()){
            String sql11 = "delete from examdata where ExamId=? ";
        PreparedStatement pst11 = con.prepareStatement(sql11);
        int id=rs.getInt("ExamId");
        pst11.setInt(1, id);
       pst11.executeUpdate();
            }

            String sql = "delete from exam where AdminId=?";
        PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, adminID);
            pst.executeUpdate();
            
            
        }
    }


    void ConductExam(){

    }
}















// Class to represent a question
class Question {
    private String questionText;
     private List<String> options;
     private int correctOption;

     public Question(String questionText, List<String> options, int correctOption) {
         this.questionText = questionText;
         this.options = options;
         this.correctOption = correctOption;
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
 class CreateExam {
     private List<Question> questions;

     public CreateExam(List<Question> questions) {
         this.questions = questions;
     }

     public List<Question> getQuestions() {
         return questions;
     }
     
 }


