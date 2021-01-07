package com.transferwise.common.curator;

import java.time.Duration;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TwCuratorProperties {

  private String zookeeperConnectString = "'tw-curator.zookeeper-connect-string' parameter is missing";
  private Duration connectionTimeout = Duration.ofSeconds(5);
  private Duration sessionTimeout = Duration.ofSeconds(40);
  private Duration retryTimeout = Duration.ofSeconds(2);
  private Duration closeTimeout = Duration.ofMillis(500);
  private Duration waitForShutdownTimeout = Duration.ofMillis(500);
  private int retryTimes = 3;
}
