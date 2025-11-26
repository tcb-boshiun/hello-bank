# Hello Bank Backend（Spring Boot 3）

Hello Bank 的後端服務採用 **Java Spring Boot 3**，提供簡單的 REST API 給前端進行串接測試。

---

## 🚀 啟動方式

需求：
- Java 17+
- Maven 3.8+

啟動：

```bash
cd backend
mvn spring-boot:run
```

啟動後預設位置：

```
http://localhost:8080
```

---

## 🔗 內建 API

### 1. `GET /api/hello`

回傳固定字串，用於測試後端是否能正常啟動。

### 2. `POST /api/echo`

回傳前端傳入的文字（text/plain）。

---

## 🧩 Controller 程式碼

```java
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Hello-Bank backend!";
    }

    @PostMapping("/echo")
    public String echo(@RequestBody String message) {
        return "You sent: " + message;
    }
}
```

---

## 📁 專案結構

```
backend/
├── src/main/java/com/example/hellobank/
│   ├── HelloBankApplication.java
│   └── controller/
│       └── HelloController.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

---

## 🔧 可擴充方向

- 帳戶 API（Account Service）
- 交易紀錄（Transaction Service）
- JWT / OAuth2 登录
- 資料庫整合（MySQL / PostgreSQL, JPA/Hibernate）
- Service / Repository 分層架構

---

## 📜 License

MIT License
