# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.2.1] - 2021-01-07
### Changed
- CuratorFramework will now wait 500 ms for a graceful shutdown of the Zookeeper client.
This allows to reduce noise like following during services releases.
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
