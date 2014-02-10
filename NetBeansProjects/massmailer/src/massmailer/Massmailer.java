package massmailer;

import java.io.IOException;

/**
 *
 * @author spidy
 * 
 * This is the class where the execution starts
 */
public class Massmailer {

    /**
     * @param args the command line arguments
     * @throws IOException  
     */
    public static void main(String[] args) throws IOException {
       
        
        new UserInputAndDefaulValues().validateUserInputAndDefaultValues();
  
        while(!UserInputAndDefaulValues.start_mailing_flag && !UserInputInterfaceGui.userInputWindow_closed)
        {
            // System.out.println(UserInputAndDefaulValues.start_mailing_flag);
        }
      
        if(UserInputAndDefaulValues.start_mailing_flag)
        //MainExecutor.main_executor();
           
            new MailInProgressGui();
            
        
    }
}
