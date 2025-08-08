import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Admin {

    void SignUp() throws Exception {

        System.out.println("\n\n                                    ::::::::::::: Admin Sign Up Interface :::::::::::::\n");
  
        Scanner sc = new Scanner(System.in);

        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbur1 = "jdbc:mysql://localhost:3306/onlineexaminationsystem";
        String duser = "root";
        String dbpass = "";
        Connection con = DriverManager.getConnection(dbur1, duser, dbpass);

        if (con != null) {

            String sql = "INSERT INTO admin (UserName, Email, Password) VALUES (?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            System.out.print("  Enter User Name : ");
            String as=sc.nextLine();
            pst.setString(1, as);
            System.out.print("  Enter Email Id : ");
            pst.setString(2, sc.nextLine());
            System.out.print("  Enter Password : ");
            pst.setString(3, sc.nextLine());
            int as1=pst.executeUpdate();
            
            String sql1 = "Select AdminId from Admin where UserName=? ";
            PreparedStatement pst1 = con.prepareStatement(sql1);
            pst1.setString(1, as);
            ResultSet rs=pst1.executeQuery();

            while(rs.next()){
                int id=rs.getInt("AdminId");
                System.out.println("\n  Your Admin Id Is : "+id+"\n\n");

                System.out.println("  PLease Enter [1] For Log In : ");
                System.out.println("  PLease Enter [2] For Return To Last Page : ");
                System.out.print("  PLease Enter [3] For Exit The System :\n\n  Input--> ");
                int choice =sc.nextInt();

                switch (choice) {
                    case 1:
                Admin admin=new Admin();
                    try {
                        admin.LogIn(0);
                       } catch (Exception e) {
                System.out.println("  [Somthing Went Wrong Please Try Again Later]");
                       }
                        break; 
                        case 2:
                        OnlineExaminationSystem obj=new OnlineExaminationSystem();
                        obj.Run(0);
                        break; 
                        case 3: 
                    System.out.println("  \n[ Thank you For Visisting Us ]\n");
                        break;
                
                    default:
                    System.out.println("\n [ Please Enter Default ]\n");
                        break;
                }
            }
        }
    }

    void LogIn(int attempt) throws Exception {

        Admin admin=new Admin();

        Scanner sc = new Scanner(System.in);

        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbur1 = "jdbc:mysql://localhost:3306/onlineexaminationsystem";
        String duser = "root";
        String dbpass = "";
    
        Connection con = DriverManager.getConnection(dbur1, duser, dbpass);

        if (con != null) {

            String sql = "Select * from admin where AdminID=? and Password=? ";
            PreparedStatement pst = con.prepareStatement(sql);
            System.out.print("  Enter Admin Id : ");
            int adminId=sc.nextInt();
            pst.setInt(1,adminId );
            System.out.print("  Enter Password : ");
            String Password=sc.next();
            pst.setString(2, Password);


            ResultSet rs=pst.executeQuery();
            boolean Found=false;
            
            while(rs.next()){
            Found=true;
                    System.out.println("\n  [ Log In Successful ]\n\n");

                    System.out.println("  PLease Enter [1] For Add Exam : ");
                    System.out.println("  PLease Enter [2] For Delete Exam : ");
                    System.out.println("  PLease Enter [3] For Show Student Details : ");
                    System.out.println("  PLease Enter [4] For Show Result Of Students : ");
                    System.out.println("  PLease Enter [5] For Return To Last Page : ");
                    System.out.print("  PLease Enter [6] For Exit The System :\n\n  Input--> ");
                    int choise =sc.nextInt();

                    switch (choise) {
                        case 1:
                            Exam ex=new Exam();
                            ex.AddExam(adminId,0);
                            break;
                    case 2:
                    Exam ex1=new Exam();
                            ex1.DeleteExam(0,adminId);
                        default:
                            break;
                    }



            }
            
            
            if(attempt<1){
                if(Found!=true) {
                  System.out.println("\n  [ Please Check Your UserName OR Password Is Invalid ]\n");
                 admin.LogIn(attempt+1);
                 }
             }
            if(attempt==1){
                System.out.println("\n  [ Please Check Your UserName OR Password Is Invalid ]\n"); 
                System.out.println("  [ This Is Your Last Try To Log In ]\n");
                admin.LogIn(attempt+1);

            }
            if(attempt==2){
                System.out.println("\n  [ User Not Found Please Try Again After Some Time ]\n");
            }
           
    }

    
}
void DeleteAccount(int attempt) throws Exception {

    Admin admin=new Admin();

        Scanner sc = new Scanner(System.in);

        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbur1 = "jdbc:mysql://localhost:3306/onlineexaminationsystem";
        String duser = "root";
        String dbpass = "";
    
        Connection con = DriverManager.getConnection(dbur1, duser, dbpass);

        if (con != null) {

            String sql = "Delete from Admin Where AdminId=? and Password=? ";
            PreparedStatement pst = con.prepareStatement(sql);
            System.out.print("  Enter Admin Id : ");
            int adminID=sc.nextInt();
            pst.setInt(1, adminID);            
            System.out.print("  Enter Password : ");
            pst.setString(2, sc.next());

            int done=pst.executeUpdate();
            boolean Found=false;
            
            if(done==1){
            Found=true;
                Exam exam=new Exam();
                exam.DeleteAllExam(adminID);
                    System.out.println("\n  [ Account Deleted Successfully ]\n");
                return;
            }
            
            
            if(attempt<1){
                if(Found!=true) {
                  System.out.println("\n  [ Please Check Your UserName OR Password Is Invalid ]\n");
                 admin.DeleteAccount(attempt+1);
                 }
             }
            if(attempt==1){
                System.out.println("\n  [ Please Check Your UserName OR Password Is Invalid ]\n"); 
                System.out.println("  [ This Is Your Last Try To Delete Account  ]\n");
                admin.DeleteAccount(attempt+1);

            }
            if(attempt==2){
                System.out.println("\n  [  User Not Found Please Try Again After Some Time ]\n");
            }
}

}
}
