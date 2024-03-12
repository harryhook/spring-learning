import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author harryhook
 * @since 2024/1/31 16:06
 */
public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(() -> System.out.println("running man"), 1,1, TimeUnit.SECONDS);
        executor.schedule(() -> System.out.println("hello world"), 10, TimeUnit.SECONDS);
        executor.schedule(() -> System.out.println("running world"), 5, TimeUnit.SECONDS);



    }

}
