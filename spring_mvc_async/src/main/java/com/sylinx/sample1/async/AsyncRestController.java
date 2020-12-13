package com.sylinx.sample1.async;

import com.sylinx.sample1.common.Console;
import com.sylinx.sample1.common.ProcessingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/async")
public class AsyncRestController {

    @Autowired
    private AsyncRestapiHelper asyncHelper;
    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<ProcessingResult> get(@RequestParam(defaultValue = "0") long waitSec) {
        Console.println("Start get.");

        ProcessingResult result = new ProcessingResult();
        result.setAcceptedTime(LocalDateTime.now());

        CompletableFuture<ProcessingResult> future = asyncHelper.asyncProcessing(result, waitSec);

        Console.println("End get.");
        return future;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        return "timeout";
    }
}