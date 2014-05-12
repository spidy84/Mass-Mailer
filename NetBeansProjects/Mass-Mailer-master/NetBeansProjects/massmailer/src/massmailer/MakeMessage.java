package massmailer;

import java.util.LinkedList;
import java.util.Queue;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author spidy
 * 
 * This class contains methods that creates a message for each instance of thread
 * 
 */
public class MakeMessage {
    
    FetchEmailID fetchEmailIDobj= new FetchEmailID();
    Queue<String> queue=new LinkedList<String>();
    boolean finishedJobsFlag=false;
    
    /**
     * 
     * @param session
     * @param from
     * @return returns composed message to be sent
     * @throws MessagingException 
     */
    MimeMessage make_message(Session session,String from) throws MessagingException
    {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.setSubject(UserInputAndDefaulValues.subject);
         message.setText(UserInputAndDefaulValues.message_body);
         return message;
    }
    
    /**
     * This method sets the recipient each time for individual user
     * and it also checks if the email_queue contains any more email or not, if the message queue contains number of email_id less than teh threshold value
     * it calls for fetching of email_id's
     * 
     * @param message
     * @return returns the message with recipient set
     * @throws MessagingException 
     */
   
    MimeMessage change_recipient(MimeMessage message) throws MessagingException
    {
         if(queue.size()<1)
            queue = new FetchEmailID().fetchIDs();
         
         String mailTo=(String)queue.poll();
         if(mailTo.equals(QueryDB.defaultStringValue))
         {
             finishedJobsFlag=true;
             return message;
         }
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
         return message;
    }
  
    
}
