package massmailer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author spidy
 */
public class SendMessageExperiment {
    
    void send_message()
    {   
        MakeMessage makeMessageObject=new MakeMessage();
        
            try {
                Queue<String> queue=new LinkedList<String>();
                Transport transport = null;   
                Session session = new MailerAuthenticator().authenticate();
                MimeMessage message = null;
                message = makeMessageObject.make_message(session, UserInputAndDefaulValues.mailerUsername);
              //System.out.println("passed message ready");
                long startTime=System.currentTimeMillis();
               System.out.println("mailer start "+ startTime/1000);
                message=makeMessageObject.change_recipient(message);
                transport = session.getTransport("smtp");
                transport.connect();
                while(true)
                {
                    message=makeMessageObject.change_recipient(message);
                    if(makeMessageObject.finishedJobsFlag)break;
                    transport.sendMessage(message, message.getAllRecipients());
                    System.out.println("sentsuccessfully");
                    // transport.sendMessage(message, new Address[] {new InternetAddress(to)});
                }
                
            //  System.out.println("mailer start "+ (System.currentTimeMillis()-startTime)/1000);
                transport.close();
                
            } catch (MessagingException ex) {
                Logger.getLogger(SendMessageExperiment.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
}
