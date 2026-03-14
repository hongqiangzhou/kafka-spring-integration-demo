#!/bin/bash
set -e

CLUSTER_ID=$(uuidgen)
echo "Using cluster ID: $CLUSTER_ID"

# Step 1: Clean data directories
rm -rf ./data/*-data/*
mkdir -p data/kafka-1-data
mkdir -p data/kafka-2-data
mkdir -p data/kafka-3-data

chmod -R 777 data

# Step 2: Format brokers
for i in 1 2 3; do
  echo "Formatting kafka-$i storage..."
  docker run --rm -v $(pwd)/data/kafka-$i-data:/var/lib/kafka/data \
    -v $(pwd)/kafka-$i.properties:/etc/kafka/kafka-$i.properties \
    apache/kafka:3.7.0 \
    bash -c "export PATH=/opt/kafka/bin:\$PATH && kafka-storage.sh format --config /etc/kafka/kafka-$i.properties --cluster-id $CLUSTER_ID --ignore-formatted"
done

# Step 3: Start all brokers
docker compose up -d

# Step 4: Wait for quorum formation
echo "Waiting for all brokers to join quorum..."
RETRIES=20
for i in $(seq 1 $RETRIES); do
  COUNT=$(docker exec -i kafka-1 bash -c "export PATH=/opt/kafka/bin:\$PATH && kafka-broker-api-versions.sh --bootstrap-server kafka-1:9092 2>/dev/null | grep 'id:' | wc -l")
  echo "Brokers registered: $COUNT"
  if [ "$COUNT" -ge 3 ]; then
    echo "All 3 brokers registered!"
    break
  fi
  sleep 3
done

# Step 5: Create topic
echo "Creating topic first_topic with replication-factor=3..."
docker exec -i kafka-1 bash -c "export PATH=/opt/kafka/bin:\$PATH && kafka-topics.sh --bootstrap-server kafka-1:9092 --topic first_topic --create --partitions 3 --replication-factor 3"

echo "✅ Cluster ready and topic created!"
