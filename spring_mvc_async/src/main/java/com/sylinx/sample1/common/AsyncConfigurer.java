package com.sylinx.sample1.common;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.util.concurrent.Executor;

public interface AsyncConfigurer {
    // @Asyncで利用するTaskExecutorを返却する
    // nullを返却するとSimpleAsyncTaskExecutorが利用される
    Executor getAsyncExecutor();

    // @Asyncが適用されたメソッド内で例外が発生した時のハンドリング処理を行うAsyncUncaughtExceptionHandlerを返却する
    // nullを返却するとSimpleAsyncUncaughtExceptionHandlerが利用される
    AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler();
}