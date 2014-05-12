package massmailer;

/**
 *
 * @author spidy
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author spidy
 * 
 * This class uses executor service to create multiple sessions
 * 
 */
public class MainExecutor {

    /**
     *
     */
    public MainExecutor()         
    {
        //main_executor();
    }
    
    /**
     * This method uses executor service to achieve multithreading and multi-processing as the multiprocessing in java is dependent on the os
     * The maximum number of threads created is equal to the number of available processors
     * It is programmed as such the thread once started never returns to the pool unless all the task is finished, 
     * to minimize the task allocation time.
     * 
     */
  static void main_executor()
  {
      
    int nofprocessors = Runtime.getRuntime().availableProcessors();
    ExecutorService executor = Executors.newFixedThreadPool(nofprocessors);
    long startTime=System.currentTimeMillis();
    

    for (int  i = 0; i < nofprocessors; i++) 
    {
           Runnable worker = new MyRunnable();
           executor.execute(worker);
    }
      try {
          // System.out.print("shutdown");
           executor.shutdown();
           executor.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
      } catch (InterruptedException ex) {
         // Logger.getLogger(MainExecutor.class.getName()).log(Level.SEVERE, null, ex);
         // System.out.print("interrupted");
      }
    
    //System.out.println("Finished all threads");
  }
} 