# Spring Boot + Redis Cache (Java 21)  
スプリングブート + レディスキャッシュ (Java 21)

---

## About the Project | プロジェクトについて

This is a **learning project** to practice **distributed caching** with **Spring Boot 3** and **Redis 7**.  
It demonstrates how **Spring Cache** (`@Cacheable`, `@CachePut`, `@CacheEvict`) can drastically reduce response time by storing frequently accessed data in memory.  

これは **学習用プロジェクト** で、**Spring Boot 3** と **Redis 7** を使った **分散キャッシュ** を練習するために作成しました。  
**Spring Cache** (`@Cacheable`, `@CachePut`, `@CacheEvict`) を用いて、よく利用されるデータをメモリに保存することで、応答時間を大幅に短縮できることを示しています。  

---

## 🗂 Technologies | 使用技術

-  Java 21  
-  Spring Boot 3  
-  Spring Cache  
-  Docker & Docker Compose  
-  Redis 7 (Alpine)

---

## How to Run | 実行方法

### Option A — Docker Compose (Recommended)  
オプションA — Docker Compose（推奨）

```bash
docker compose up -d --build
```
App: [http://localhost:8080](http://localhost:8080)  

### Option B — Local App + Redis in Docker  
オプションB — ローカルアプリ + Docker 上の Redis

```bash
docker run -d --name redis-dev -p 6379:6379 redis:7-alpine
./mvnw spring-boot:run
```

---

## 🔗 Endpoints | エンドポイント  
(Base: `/api/v1/tickets`)

- `GET /{id}` → Get ticket by ID (cached)  
　IDでチケットを取得（キャッシュ有り）  
- `GET ?status=Low` → List tickets by status  
　ステータス別にチケットを取得  
- `PUT /{id}` → Update ticket  
　チケットを更新  
- `DELETE /{id}` → Delete ticket  
　チケットを削除  

Example | 例:  
```bash
curl -s http://localhost:8080/api/v1/tickets/1 | jq
```

---

## 🔍 Check Redis | Redisを確認する

```bash
docker exec -it redis-dev redis-cli
KEYS *
TTL "ticket::1"
```

You should see keys like `ticket::1`.  
`ticket::1` のようなキーが表示されるはずです。  

---

##  Project Structure | プロジェクト構成
```
controllers/   → TicketController
service/       → TicketServiceImpl
repositories/  → FakeTicketRepository
entities/      → Ticket.java
dtos/          → TicketRequestDto, TicketResponseDto, TicketMapper
enums/         → Status, Priority
```

---

##  Concept | コンセプト

**Cache explained simply:**  
Looking up data directly in the repository is like going to the library every time to find the same book.  
Caching is like making a quick **note in your pocket** — next time, you check the note instantly.  

**キャッシュを簡単に説明すると:**  
毎回ライブラリーに行って同じ本を探すのは時間がかかります。  
キャッシュは **ポケットのメモ** のようなものです。次回からはすぐに参照できます。  

---

## 📝 Note | 備考

- This is not a production-ready system. It uses a **FakeRepository** with artificial latency to demonstrate the effect of caching.  
- 本番用のシステムではありません。キャッシュ効果を示すために、遅延を追加した **FakeRepository** を使用しています。  

---
