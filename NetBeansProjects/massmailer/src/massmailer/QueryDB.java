package massmailer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author spidy
 * <p>This class queries from database for a specified number of emails.
 * The number of emails fetched per query is equal to the value of fetch_mails_volume
 * </p>
 */
public class QueryDB {
    /**
     *
     */
    public static final String defaultStringValue="abcdefghijklmnopqrstuvwxyz";
    int fetch_mails_volume=100;
    QueryDB()
    {
        fetch_mails_volume=UserInputAndDefaulValues.no_of_mails_per_query;
    }
    /**
     *<p>
     * This method contains sql statements and all queries are done within this method
     * The number of emails fetched per query is equal to the value of fetch_mails_volume
     * </p>
     * @return Queue<String> return a queue of string, here email is contains as string
     * 
     */
    Queue<String> queryTable(Connection conn, Statement stmt) throws SQLException
    {
        //System.out.println("trialfetch :"+ UserInputAndDefaulValues.no_of_mails_per_query+" "+fetch_mails_volume);
        conn.setAutoCommit(false); 
          Queue<String> queue=new LinkedList<String>(); 
          int id=1;
          String sql;
       /*   
          sql="UPDATE emailID_List SET lastFetchedID=1 WHERE ID=1";
          stmt.executeUpdate(sql);
       */ 
          sql = "SELECT lastFetchedID FROM emailID_List WHERE ID=1 FOR UPDATE";
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next())
          {
             id  = rs.getInt("lastFetchedID");
             System.out.print(id+" testing");
          }
         
          sql="UPDATE emailID_List SET lastFetchedID=lastFetchedID+"+fetch_mails_volume+" WHERE ID=1";
          stmt.executeUpdate(sql);
        conn.commit();
        conn.setAutoCommit(true);
          sql="SELECT email_ID FROM emailID_List WHERE ID>="+id+" AND ID<"+(id+fetch_mails_volume);
        //  System.out.println(sql);
          rs = stmt.executeQuery(sql);
         
          if(!rs.isBeforeFirst())queue.add(defaultStringValue);
          while(rs.next())
          {
             String email_id  = rs.getString("EMAIL_ID");
             queue.add(email_id);
            
          }
          
          rs.close();
          return queue;
    }
    
}
