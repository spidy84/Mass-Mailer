package massmailer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author spidy
 * 
 * This class contains all the default variables and values needed during the execution of the project
 * 
 */
public class UserInputAndDefaulValues {
    static int port=587;
    static String host_smtp="not set";
    static String mailerUsername="not set";
    static String mailerPassword="not set";
    static String subject="not set, default subject";
    static String message_body="not set. This is a default Message";
    static String databaseUsername="not set";
    static String databasePassword="not set";
    static String database_url= "jdbc:mysql://127.0.0.1/addressbook";
    static String database_table_name= "not set";
    static int no_of_mails_per_query=100;
    static volatile boolean start_mailing_flag=false;
   
    
    /**
     *
     * @throws IOException
     * 
     * This method creates a default file if it is absent, for storing default Input Values
     * This method also reads the default values from the default file to the userInputInterface
     * 
     */
    public void validateUserInputAndDefaultValues() throws IOException{
   
        String currentPath=System.getProperty("user.dir")+File.separator+"defaultInputs.txt";
        File propertyFile = new File(currentPath);
        if(!propertyFile.exists())
        {
                 //System.out.println("testing " + propertyFile);
                 propertyFile.createNewFile();
        }
        
       // InputStream is = getClass().getResourceAsStream(currentPath);//In case of jar, ClassLoader and getResourceAsStream is used else file reading fails.
       //BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
              BufferedReader br = new BufferedReader(new FileReader(currentPath));
              String inputValue=null;
              if((inputValue=br.readLine())==null)
              {            
              }
              else
              {
                    port=Integer.parseInt(inputValue);
                    inputValue=br.readLine();
                    host_smtp=inputValue;
                    inputValue=br.readLine();
                    mailerUsername=inputValue;
                    inputValue=br.readLine();
                    mailerPassword=inputValue;
                    inputValue=br.readLine();
                    databaseUsername=inputValue;
                    inputValue=br.readLine();
                    databasePassword=inputValue;
                    inputValue=br.readLine();
                    database_url= inputValue;
                    inputValue=br.readLine();
                    database_table_name= inputValue;
                   
              }
              
               UserInputInterfaceGui userInterfaceGui_object = new UserInputInterfaceGui();
              
             
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserInputAndDefaulValues.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserInputAndDefaulValues.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
