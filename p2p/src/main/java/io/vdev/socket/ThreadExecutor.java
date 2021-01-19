package io.vdev.socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor {

    private static ThreadExecutor threadExecutor = null;
    private static ExecutorService executorService;

    private ThreadExecutor() {}

    public static ThreadExecutor getInstance() {
        if(threadExecutor == null) {
            threadExecutor = new ThreadExecutor();
            threadExecutor.executorService = Executors.newFixedThreadPool(20);
        }
        return threadExecutor;
    }

    public void execute(Runnable job) {
        executorService.execute(job);
    }

}
