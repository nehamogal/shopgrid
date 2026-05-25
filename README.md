# ShopGrid - Distributed E-Commerce Backend

**Tech Stack:** Java 17, Spring Boot 3, MySQL 8.0, Redis 7, Apache Kafka, Docker, JWT, Spring Security

A production-ready microservices backend for an e-commerce platform. Built to handle high-traffic product catalogs with sub-10ms response times using distributed caching and event-driven architecture. All major features are backed by terminal verification.

### **Architecture**

| Service | Purpose | Port |
| --- | --- | --- |
| Spring Boot API | REST endpoints for products, orders, auth | 8080 |
| MySQL | Persistent storage for users, products, orders | 3306 |
| Redis | Distributed cache for product data | 6379 |
| Kafka | Async event streaming for order processing | 9092 |
| JWT Security | Role-based auth: USER, ADMIN | - |

### **Key Features & Engineering Decisions**

1. **Latency Reduction with Redis Caching** 
   Product details cached using Spring `@Cacheable`. First request hits MySQL, subsequent requests served from Redis. Verified via `redis-cli MONITOR` showing only 1 `GET` per endpoint on repeated calls.

2. **Event-Driven Orders with Apache Kafka**
   `POST /orders` publishes JSON to Kafka topic `orderTopic`. Decouples checkout from inventory services, enabling async processing. Verified by consuming `{"productId":1,"quantity":2}` from `orderTopic` after POST.

3. **3-Layer JWT Security + RBAC**
   - Stateless JWT tokens with role claims
   - Spring Security filter chain blocks unauthenticated requests with `401`
   - Method-level `@PreAuthorize("hasRole('ADMIN')")` returns `403` for USER role on admin endpoints

4. **Zero-Downtime Docker Deployment**
   All 5 services orchestrated via Docker Compose. Resolved container startup race conditions using MySQL healthchecks + `depends_on: service_healthy`. Guarantees DB is ready before app connects.

5. **Automated DB Seeding**
   `data.sql` auto-executes on MySQL container start to populate sample products and users for immediate testing.

### **Quick Start - Run in 60 Seconds**

```bash
git clone https://github.com/nehamogal/shopgrid.git
cd shopgrid
docker compose up -d --build
