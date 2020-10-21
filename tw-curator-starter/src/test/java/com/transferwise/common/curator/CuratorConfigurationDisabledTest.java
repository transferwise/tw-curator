package com.transferwise.common.curator;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.curator.framework.CuratorFramework;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"integration"})
@SpringBootTest(classes = {TestApplication.class}, properties = {"tw-curator.disabled=true"})
public class CuratorConfigurationDisabledTest {

  @Autowired(required = false)
  private CuratorFramework curatorFramework;

  @Test
  public void shouldNotAutowire() {
    assertNull(curatorFramework);
  }

}
