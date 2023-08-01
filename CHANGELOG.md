# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.5.1] - 2023-08-01

### Added

* Support for Spring Boot 3.1

### Bumped

* Build against Spring Boot 3.0.6 --> 3.0.7
* Build against Spring Boot 2.7.11 --> 2.7.13
* Build against Spring Boot 2.6.14 --> 2.6.15

## [0.5.0] - 2022-05-06

### Added

* Support for Spring Boot 3.0

### Removed

* Support for Spring Boot 2.5

## [0.4.0] - 2022-11-17

- do not create curator beans if `tw-curator.zookeeper-connect-string` configuration is missing: one does not have to
  explicitly set `tw-curator.disabled: true` anymore, not configuring the zookeeper address leads to the same behaviour

## [0.3.2] - 2022-01-31

- do not enforce SpringBoot platform, let the service using us decide
- upgrade to use latest SpringBoot 2.5.x

## [0.3.1] - 2021-12-28

### Changed

- Moving CI from Circle to Github Actions

## [0.3.0] - 2021-05-31

### Changed

- JDK 11+ is required.
- Making it open-source.

## [0.2.3] - 2021-01-09

### Changed

Just upgraded libs.

## [0.2.1] - 2021-01-07

### Changed

- CuratorFramework will now wait up to 500 ms for a graceful shutdown of the Zookeeper client. This allows to reduce noise like following during
  services releases.

```
18:39:12.350 [Test worker-SendThread(localhost:49214)] WARN  org.apache.zookeeper.ClientCnxn - An exception was thrown while closing send thread for session 0x10001a55e96001d.
org.apache.zookeeper.ClientCnxn$EndOfStreamException: Unable to read additional data from server sessionid 0x10001a55e96001d, likely server has closed socket
at org.apache.zookeeper.ClientCnxnSocketNIO.doIO(ClientCnxnSocketNIO.java:77)
at org.apache.zookeeper.ClientCnxnSocketNIO.doTransport(ClientCnxnSocketNIO.java:350)
at org.apache.zookeeper.ClientCnxn$SendThread.run(ClientCnxn.java:1275)
```

## [0.2.0] - 2020-10-16

### Added

- New property `tw-curator.disabled` allows the library's auto loading to be skipped.

## [0.1.0] - 2020-10-01

### Changed

- Upgraded external libraries.
