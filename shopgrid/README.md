## Shopgrid: Cloud-Deployed Microservices Demo

**Goal:** Demonstrate end-to-end deployment of a distributed e-commerce backend on GitHub Codespaces.

**Architecture:**
Spring Boot App ↔ MySQL ↔ Kafka/Zookeeper ↔ Redis
All containerized with Docker Compose and exposed via Codespaces port forwarding.

**Key Engineering Challenges Solved:**
1. **Startup Dependency Race Condition** - Prevented Spring Boot crash by implementing MySQL `healthcheck` + `depends_on: condition: service_healthy`
2. **Cloud Networking for Kafka** - Resolved `Connection refused` by configuring `KAFKA_ADVERTISED_LISTENERS` to use Codespaces external hostname
3. **Auth Misconfiguration** - Bypassed `403 Forbidden` on demo endpoints by creating env-specific Spring Security config

**Demo:** [Video: 5-service stack deployed to Codespaces](https://github.com/nehamogal/shopgrid/releases/tag/v1.0-demo)

**Tech:** Java, Spring Boot, Docker, Docker Compose, Kafka, MySQL, Redis, GitHub Codespaces