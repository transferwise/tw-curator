package com.transferwise.common.curator;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TwCuratorAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  @ConfigurationProperties(prefix = "tw-curator", ignoreInvalidFields = true)
  public TwCuratorProperties twCuratorProperties() {
    return new TwCuratorProperties();
  }

  @Bean(destroyMethod = "close")
  @ConditionalOnMissingBean
  public CuratorFramework twCuratorFramework(ApplicationContext applicationContext, TwCuratorProperties properties, RetryPolicy retryPolicy) {
    if (StringUtils.trimToNull(properties.getZookeeperConnectString()) == null) {
      throw new IllegalStateException("'tw-curator.zookeeper-connect-string' parameter is missing.");
    }

    Collection<ConnectionStateListener> listeners = applicationContext.getBeansOfType(ConnectionStateListener.class).values();

    CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
        .connectionTimeoutMs((int) properties.getConnectionTimeout().toMillis())
        .sessionTimeoutMs((int) properties.getSessionTimeout().toMillis())
        .canBeReadOnly(false)
        .connectString(properties.getZookeeperConnectString())
        .retryPolicy(retryPolicy)
        .threadFactory(new ThreadFactoryBuilder().setNameFormat("tw-curator").build())
        .build();

    for (ConnectionStateListener listener : listeners) {
      curatorFramework.getConnectionStateListenable().addListener(listener);
    }

    log.info("Started curator framework with connection timeout of " + properties.getConnectionTimeout() + ", session timeout of " + properties
        .getSessionTimeout() + ", " + "retry timeout of " + properties.getRetryTimeout() + " and connect string of '" + properties
        .getZookeeperConnectString() + "'.");

    curatorFramework.start();
    return curatorFramework;
  }

  @Bean
  @ConditionalOnMissingBean(RetryPolicy.class)
  public RetryNTimes twCuratorRetryPolicy(TwCuratorProperties properties) {
    return new RetryNTimes(properties.getRetryTimes(), (int) properties.getRetryTimeout().toMillis());
  }

}
