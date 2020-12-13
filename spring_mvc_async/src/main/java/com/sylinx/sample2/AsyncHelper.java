package com.sylinx.sample2;

import com.sylinx.sample2.common.Console;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
@Async
public class AsyncHelper {
    // ...

    public void streaming(ResponseBodyEmitter emitter, long eventNumber, long intervalSec) throws IOException {
        Console.println("Start Async processing.");

        for (long i = 1; i <= eventNumber; i++) {
            sleep(intervalSec);
            emitter.send("msg" + i + "\r\n");
        }

        emitter.complete();

        Console.println("End Async processing.");
    }
    // ...
    private void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }
}
