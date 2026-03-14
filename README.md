To start the Kafka cluster, execute the bash file "./start-kafka.sh". It starts a cluster of three brokers in KRaft mode (no zookeeper), and creates a topic "first_topic" with three partitions and three replications.

Note that hostnames used for internal listeners are like "kafka-1", etc. When execute CLI commands in docker container, "bootstrap-server" shold be like "kafka-1". The commands read like this:

/opt/kafka/bin/kafka-topics.sh --bootstrap-server kafka-1:9092 --list

In "application.yaml" of the Spring application, configuration reads
```yaml
spring:
  kafka:
    bootstrap-servers:
      - localhost:9092
      - localhost:9093
      - localhost:9094
'''
