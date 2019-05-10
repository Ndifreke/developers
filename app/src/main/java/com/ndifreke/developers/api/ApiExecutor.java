package com.ndifreke.developers.api;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ApiExecutor {

    private static final BlockingDeque<Runnable> taskQue = new LinkedBlockingDeque<>();
    private static ThreadPoolExecutor threadPoolExecutor;

    private static ThreadPoolExecutor getExecutor(){
        int proccessors = Runtime.getRuntime().availableProcessors();
        threadPoolExecutor = new ThreadPoolExecutor(
                proccessors,
                proccessors,
                3,
                TimeUnit.SECONDS,
                taskQue
        );
        return threadPoolExecutor;
    }

    public static void execute(Runnable runnable){
        if(threadPoolExecutor == null)
            getExecutor();
        threadPoolExecutor.execute(runnable);
    }

    public static void shutdown(){
     threadPoolExecutor.shutdown();
    }
}
