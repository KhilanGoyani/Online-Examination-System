import java.util.Scanner;

public class OnlineExaminationSystem  {
    public static void main(String[] args) throws Exception {
        
                System.out.println("*********************************************************************************************************************\n\n");
                System.out.println("                                           WELCOME TO ONLINE EXAMINATION SYSTEM");
                System.out.println("\n\n*********************************************************************************************************************\n\n\n");

                OnlineExaminationSystem obj=new OnlineExaminationSystem();
        obj.Run(0);
    }  // psvm method closed

        void Run(int attempt){
            Scanner sc=new Scanner(System.in);
boolean Found=false;
        System.out.println("                                       ::::::::::::: Main Interface :::::::::::::\n");
        System.out.println("  PLease Enter [1] For Admin Interface : ");
        System.out.println("  PLease Enter [2] For Student Interface : ");
        System.out.print("  PLease Enter [3] For Exit System :\n\n  Input--> ");


        if(sc.hasNextInt()){

        int role =sc.nextInt();
        Found=true;
        
        switch(role){

            case 1:

                System.out.println("\n\n                                       ::::::::::::: Admin Interface :::::::::::::\n");
                Admin admin=new Admin();

                System.out.println("  PLease Enter [1] For Sing Up : ");
                System.out.println("  PLease Enter [2] For Log In : ");
                System.out.println("  PLease Enter [3] For Delete Account : ");
                System.out.println("  PLease Enter [4] For Return To Last Page : ");
                System.out.print("  PLease Enter [5] For Exit The System :\n\n  Input--> ");
                if (sc.hasNextInt()) {
                    int choise =sc.nextInt();

                    switch (choise) {
     
                        case 1:
                        try {
                         admin.SignUp();
                         
                        } catch (Exception e) {
                         System.out.println("  [ Somthing Went Wrong Please Try Again Later ]");
                        }
                         break;
     
                         case 2:
                        System.out.println("\n\n                                     ::::::::::::: Admin Log In Interface :::::::::::::\n");
     
                         try {
                             admin.LogIn(0);
                            } catch (Exception e) {
                     
                     System.out.println("Error :"+e);           //System.out.println("  [Somthing Went Wrong Please Try Again Later]");
                            }
                         break;
    
                         case 3:
                         try {
                            System.out.println("\n\n                                     ::::::::::::: Admin Account Delete Interface :::::::::::::\n");
     
                             admin.DeleteAccount(0);
                            } catch (Exception e) {
                                System.out.println("Error :"+e);
                     System.out.println("  [Somthing Went Wrong Please Try Again Later]");
                            }
                     
                         break;
     
                         case 4:
                         OnlineExaminationSystem obj=new OnlineExaminationSystem();
                         obj.Run(0);
                         break;
     
                         case 5:
                         System.out.println("  \n[ Thank you For Visisting Us ]\n");
                         break;
     
                         default:
                         System.out.println("\n [ Please Enter Default ]\n");
                         break;
                      } 
                }
                else{
                    OnlineExaminationSystem obj=new OnlineExaminationSystem();
                            
                    if(attempt<1){
                        if(Found!=true) {
                            System.out.println( "\n  [ Please Enter Default Value ]");
                          obj.Run(attempt+1);
                         }
                     }
                    if(attempt==1){
                        System.out.println( "\n  [ Please Enter Default Value ]");
                        System.out.println("  [ This Is Your Last Try ]\n");
                        obj.Run(attempt+1);
        
                    }
                    if(attempt==2){
                        System.out.println("\n  [ Please Try Again After Some Time ]\n");
                    }
                 
                    
                } 
                

            break;

            case 2:
            System.out.println("\n\n                                       ::::::::::::: Student Interface :::::::::::::\n");
           // Student student=new Student();

            System.out.println("  PLease Enter [1] For Sing Up : ");
            System.out.println("  PLease Enter [2] For Log In : ");
            System.out.println("  PLease Enter [3] For Delete Account : ");
            System.out.println("  PLease Enter [4] For Return To Last Page : ");
            System.out.print("  PLease Enter [5] For Exit The System :\n\n  Input--> ");
            
            if(sc.hasNextInt()){
            int choise2 =sc.nextInt();

           switch (choise2) {

               case 1:
               try {
              //  student.SignUp();
               } catch (Exception e) {
                System.out.println("  [Somthing Went Wrong Please Try Again Later]");
               }
               
                break;

                case 2:
                try {
                System.out.println("\n\n                                     ::::::::::::: Student Log In Interface :::::::::::::\n");
               //     student.LogIn(0);
                   } catch (Exception e) {
                System.out.println("  [Somthing Went Wrong Please Try Again Later]");
                   }
                break;

                case 3:
                OnlineExaminationSystem obj=new OnlineExaminationSystem();
                obj.Run(0);
                break;
                case 4:
                     System.out.println("  \n[ Thank you For Visisting Us ]\n");
                break;

                default:
                System.out.println("\n [ Please Enter Default ]\n");
                break;
             }
            }
             else{
                OnlineExaminationSystem obj=new OnlineExaminationSystem();
                        
                if(attempt<1){
                    if(Found!=true) {
                        System.out.println( "\n  [ Please Enter Default Value ]");
                      obj.Run(attempt+1);
                     }
                 }
                if(attempt==1){
                    System.out.println( "\n  [ Please Enter Default Value ]");
                    System.out.println("  [ This Is Your Last Try ]\n");
                    obj.Run(attempt+1);
    
                }
                if(attempt==2){
                    System.out.println("\n  [ Please Try Again After Some Time ]\n");
                }
             
                
            } 
            break;

            case 3:
                System.out.println("  \n[ Thank you For Visisting Us ]\n");
            break;

            default:
            System.out.println("\n [ Please Enter Default  ]\n");
            break;
        }
        }
        else{
            OnlineExaminationSystem obj=new OnlineExaminationSystem();
                    
            if(attempt<1){
                if(Found!=true) {
                    System.out.println( "\n  [ Please Enter Default Value ]");
                  obj.Run(attempt+1);
                 }
             }
            if(attempt==1){
                System.out.println( "\n  [ Please Enter Default Value ]");
                System.out.println("  [ This Is Your Last Try ]\n");
                obj.Run(attempt+1);

            }
            if(attempt==2){
                System.out.println("\n  [ Please Try Again After Some Time ]\n");
                           
            }
         
            
        }

        
    
    }

}