public class CustomerFeedback {
    static Scanner sc = new Scanner(System.in);
    private ArrayList<String> responses = new ArrayList<String>();
    
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/Uniqlo_Kiosk_System";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "manager";
    
    public char continueToFeedback(){
        char choice;
        
        System.out.print("Would you like to answer a short survey? [Y/N]: ");
        String temp = sc.nextLine();
        temp = temp.toUpperCase();
        choice = temp.charAt(0);
        
        if(choice!='Y' && choice!='N'){
            System.out.println("Invalid choice. Terminating program");
            return 'N';
        }
        return choice;
    }
    
    public String getResponse(){
        String response;
        int choice;
        
        while (true) {            
            try {
                System.out.print("[1] Satisfied\n"
                        + "[2] Neutral\n"
                        + "[3] Dissatisfied\n"
                        + "Choice: ");

                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: "+e.getMessage());
                continue;
            }break;
        }
        
        switch (choice) {
            case 1:
                response = "Satisfied";
                break;
            case 2:
                response = "Neutral";
                break;
            case 3:
                response = "Dissatisfied";
                break;
            default:
                throw new AssertionError();
        }
        return response;
    }
    
    public void promptQuestionFeedback(){
        //Sample questions
        String tempResponse;
        ArrayList<String> questions = new ArrayList<String>();
        
        questions.add("How satisfied were you with the overall shopping experience?");
        questions.add("How satisfied were you with the helpfulness of our kiosk?");
        questions.add("How would you rate the quality of the products you purchased?");
        
        for(int i = 0; i<questions.size(); ++i){
            System.out.println(questions.get(i));
            tempResponse = getResponse();
            responses.add(tempResponse);
        }
        
        System.out.println("\nResponse list");
        for(int i = 0; i<responses.size(); ++i){
            System.out.println(responses.get(i));
        }
    }
    
    
    
    public void executeResponseQuery(Connection conn, String statement){
        try {
            PreparedStatement pstmt = conn.prepareStatement(statement);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateQuestionDatabase(){
        try {   
            Class.forName("org.mariadb.jdbc.Driver");   //I use mariadb since I use XAMPP. Change this if you use mysql
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String temp;
            
            if(responses.get(0) == "Satisfied"){
                temp = "INSERT INTO question1 (satisfied) values (1);";
                executeResponseQuery(conn, temp);
            }
            else if(responses.get(0) == "Neutral"){
                temp = "INSERT INTO question1 (neutral) values (1);";
                executeResponseQuery(conn, temp);
            }
            else if(responses.get(0) == "Dissatisfied"){
                temp = "INSERT INTO question1 (unsatisfied) values (1);";
                executeResponseQuery(conn, temp);
            }
            
            if(responses.get(0) == "Satisfied"){
                temp = "INSERT INTO question2 (satisfied) values (1);";
                executeResponseQuery(conn, temp);
            }
            else if(responses.get(0) == "Neutral"){
                temp = "INSERT INTO question2 (neutral) values (1);";
                executeResponseQuery(conn, temp);
            }
            else if(responses.get(0) == "Dissatisfied"){
                temp = "INSERT INTO question2 (unsatisfied) values (1);";
                executeResponseQuery(conn, temp);
            }
            
            if(responses.get(0) == "Satisfied"){
                temp = "INSERT INTO question3 (satisfied) values (1);";
                executeResponseQuery(conn, temp);
            }
            else if(responses.get(0) == "Neutral"){
                temp = "INSERT INTO question3 (neutral) values (1);";
                executeResponseQuery(conn, temp);
            }
            else if(responses.get(0) == "Dissatisfied"){
                temp = "INSERT INTO question3 (unsatisfied) values (1);";
                executeResponseQuery(conn, temp);
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}
