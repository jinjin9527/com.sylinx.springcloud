package com.sylinx.sample1.async;

import com.sylinx.sample1.common.Console;
import com.sylinx.sample1.common.ProcessingResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Async
@Component
public class AsyncRestapiHelper {

    public CompletableFuture<ProcessingResult> asyncProcessing(ProcessingResult result, long waitSec) {
        Console.println("Start Async processing.");

        if (waitSec == 999) {
            throw new IllegalStateException("Special parameter for confirm error.");
        }

        sleep(waitSec);

        result.setCompletedTime(LocalDateTime.now());

        Console.println("End Async processing.");

        return CompletableFuture.completedFuture(result);
    }

    private void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }
}