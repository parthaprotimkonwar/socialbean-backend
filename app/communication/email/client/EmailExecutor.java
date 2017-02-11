package communication.email.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pkonwar on 2/11/2017.
 */
public class EmailExecutor {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    //adds work to batch and execute it
    public static void sendEmailInAsyncMode(SendEmail email) {
        executorService.submit(email);
    }

    @Override
    protected void finalize() throws Throwable {

        try {
            executorService.shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.finalize();
    }

}
