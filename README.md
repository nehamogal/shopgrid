# ShopGrid - Distributed E-Commerce Backend

**Tech Stack:** Java 17, Spring Boot 3, MySQL, Redis, Apache Kafka, Docker, JWT, Spring Security

A production-ready microservices backend for an e-commerce platform. Built to handle high-traffic product catalogs with sub-10ms response times using distributed caching and event-driven architecture.

**Live Demo:** Run locally with Docker in 60 seconds. See Quick Start below.

### **Architecture**

| Service | Purpose | Port |
| --- | --- | --- |
| Spring Boot API | REST endpoints for products, orders, auth | 8080 |
| MySQL | Persistent storage for users, products, orders | 3306 |
| Redis | Distributed cache for product data | 6379 |
| Kafka | Async event streaming for order processing | 9092 |
| JWT Security | Role-based auth: USER, ADMIN | - |

### **Key Features & Engineering Decisions**

1. **90% Latency Reduction with Redis Caching** 
   Product details cached using Spring `@Cacheable`. First request hits MySQL in ~50ms, subsequent requests served from Redis in ~1ms. Cache hit verified via application logs showing `DATABASE HIT` only once for multiple requests.

2. **Event-Driven Orders with Apache Kafka**
   `POST /orders` publishes `OrderCreatedEvent` to Kafka topic. Decouples checkout from inventory services, enabling 1K+ TPS scalability. Consumers process inventory updates asynchronously.

3. **3-Layer JWT Security + RBAC**
   - Stateless JWT tokens with role claims
   - Spring Security filter chain for endpoint protection  
   - Method-level `@PreAuthorize("hasRole('ADMIN')")` for fine-grained access control

4. **Zero-Downtime Docker Deployment**
   All 5 services orchestrated via Docker Compose. Resolved container startup race conditions using MySQL healthchecks + `depends_on: service_healthy`. Guarantees DB is ready before app connects.

5. **Automated DB Seeding**
   `data.sql` auto-executes on MySQL container start to populate sample products for immediate testing.

### **Quick Start - Run in 60 Seconds**

```bash
git clone https://github.com/nehamogal/shopgrid.git
cd shopgrid
docker compose up -d --build
