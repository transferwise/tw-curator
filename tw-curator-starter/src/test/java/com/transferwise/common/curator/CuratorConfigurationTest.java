package com.transferwise.common.curator;

import static org.assertj.core.api.Assertions.assertThat;

import com.transferwise.common.curator.TestApplication.TestConnectionStateListener;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@BaseTestEnvironment
public class CuratorConfigurationTest {

  @Autowired
  private CuratorFramework curatorFramework;

  @Autowired
  private TestConnectionStateListener testConnectionStateListener;

  @Test
  @SneakyThrows
  public void testIfZookeeperCanBeUsed() {
    String dataSt = "Hello World!";
    byte[] dataBytes = dataSt.getBytes(StandardCharsets.UTF_8);
    String path = "/tw/curator/test";

    curatorFramework.create().orSetData().creatingParentContainersIfNeeded().forPath(path, dataBytes);

    assertThat(curatorFramework.getData().forPath(path)).isEqualTo(dataBytes);

    assertThat(testConnectionStateListener.stateChangesCount.get()).as("listener was registered").isGreaterThan(0);
  }
}
