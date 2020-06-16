package com.transferwise.common.curator;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

  @Bean
  public TestConnectionStateListener testConnectionStateListener() {
    return new TestConnectionStateListener();
  }

  public static class TestConnectionStateListener implements ConnectionStateListener {

    public AtomicInteger stateChangesCount = new AtomicInteger();

    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {
      stateChangesCount.incrementAndGet();
    }
  }
}
