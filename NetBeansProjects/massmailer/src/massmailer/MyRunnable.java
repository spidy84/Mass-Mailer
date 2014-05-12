package massmailer;

/**
 *
 * @author spidy
 */
public class MyRunnable implements Runnable {
  

    @Override
  public void run() {
  
          new SendMessageExperiment().send_message();
  
  }
}
