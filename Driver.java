public class Driver {

    public static void main(String[] args) {
        CustomerFeedback testObj = new CustomerFeedback();
        char choice = testObj.continueToFeedback();

        if (choice != 'Y') {
            return;
        } else {
            testObj.promptQuestionFeedback();
            testObj.updateQuestionDatabase();
            
        }

    }
}
