package massmailer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author spidy
 * This class contains methods for database connection and call to QueryDB class method for querying the database
 * 
 * 
 */
public class FetchEmailID {

   QueryDB queryDBobj= new QueryDB();
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   boolean flashed_flag=false;
   /**
    *
    * This method does the connection work to the server/database and class a method from class QueryDB that returns a queue of email_id's(string)
    * @return Queue<String> queue of strings(email_id)
    */
   Queue<String> fetchIDs()
   {
      
         Queue<String> queue = null;
         Connection conn = null;
         Statement stmt = null;
         try{
          
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(UserInputAndDefaulValues.database_url,UserInputAndDefaulValues.databaseUsername,UserInputAndDefaulValues.databasePassword);
                stmt = conn.createStatement();
                queue=queryDBobj.queryTable(conn,stmt);        
                stmt.close();
                conn.close();
            
         } catch (ClassNotFoundException ex) {
           Logger.getLogger(FetchEmailID.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
           Logger.getLogger(FetchEmailID.class.getName()).log(Level.SEVERE, null, ex);
          if(!flashed_flag)
           JOptionPane.showMessageDialog(null, "stmt/conn error");
          flashed_flag=true;
         }finally{
             try{
                    if(stmt!=null)
                       stmt.close();
                }catch(SQLException se2){
                
                }
             try{
                if(conn!=null)
                   conn.close();
                }catch(SQLException se){
                
                }
         }
       
       return queue;
       
    }
}

