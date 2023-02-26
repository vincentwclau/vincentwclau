package com.muecode.binance.merchant.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {
  @Value("${async.executor.corePoolSize:20}")
  private int corePoolSize;

  @Value("${async.executor.maxPoolSize:100}")
  private int maxPoolSize;

  @Value("${async.executor.queueCapacity:50}")
  private int queueCapacity;

  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setThreadNamePrefix("async-task-thread-");
    executor.initialize();
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new AsyncUncaughtExceptionHandler() {
      @Override
      public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.out.println("Exception: " + ex.getMessage());
        System.out.println("Method Name: " + method.getName());
        ex.printStackTrace();
      }
    };
  }
}
