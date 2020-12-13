package com.sylinx.sample1.async;

import com.sylinx.sample1.common.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.context.request.async.DeferredResult;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncMvnHelper asyncMvnHelper;

    @GetMapping(path = "deferred")
    public DeferredResult<String> getReferred(@RequestParam(defaultValue = "0") long waitSec, Model model) {

        Console.println("Start get.");

        model.addAttribute("message", "DeferredResult");
        model.addAttribute("completedTime", LocalDateTime.now());

        // 非同期処理を呼び出す。DeferredResultを非同期処理に引き渡すのがポイント。
        DeferredResult<String> deferredResult = new DeferredResult<>(4000L);
        asyncMvnHelper.asyncProcessing(model, waitSec, deferredResult);

        Console.println("End get.");
        return deferredResult; // DeferredResultを返却する
    }

    @RequestMapping(path = "listenable", method = RequestMethod.GET)
    public ListenableFuture<String> getListenable(@RequestParam(defaultValue = "0") long waitSec, Model model) {

        Console.println("Start get.");

        model.addAttribute("message", "ListenableFuture");


        ListenableFuture<String> future = asyncMvnHelper.asyncProcessingForListenable(model, waitSec);
        future.addCallback(
                Console::println, // 正常終了時のコールバック
                Console::println  // 例外発生時時のコールバック
        );
        Console.println("End get.");
        return future;
    }
    @RequestMapping(path = "completable", method = RequestMethod.GET)
    public CompletableFuture<String> getCompletable(@RequestParam(defaultValue = "0") long waitSec, Model model) {

        Console.println("Start get.");

        model.addAttribute("message", "CompletableFuture");

        CompletableFuture<String> future = asyncMvnHelper.asyncProcessingForCompletable(model, waitSec);
        future.thenAccept(Console::println); // 正常終了時のコールバック

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