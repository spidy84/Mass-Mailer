package massmailer;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 *
 * @author spidy
 */
public class MailerAuthenticator {
   
     Session authenticate()
     {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", UserInputAndDefaulValues.host_smtp);
            properties.put("mail.smtp.port", UserInputAndDefaulValues.port);
            Session session = Session.getInstance(properties,  
            new javax.mail.Authenticator() {  
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(UserInputAndDefaulValues.mailerUsername,UserInputAndDefaulValues.mailerPassword);  
            }  
        }); 
            return session;
     }
    
    
}
