# Hello Bank Frontend（Vue 3 + Vite）

Hello Bank 的前端採用 **Vue 3（Composition API）** + **Vite**，並透過 Proxy 方式與後端 Spring Boot 串接。

---

## 🚀 啟動方式

需求：
- Node.js 18+（Vite 5 需此版本）

安裝依賴：

```bash
cd frontend
npm install
```

啟動開發伺服器：

```bash
npm run dev
```

網址：

```
http://localhost:5173
```

---

## 🔗 與後端整合（Proxy）

`vite.config.js`：

```js
server: {
  port: 5173,
  proxy: {
    '/api': {
      target: 'http://localhost:8080',
      changeOrigin: true
    }
  }
}
```

前端呼叫：

```js
fetch('/api/hello')
```

會自動轉發到：

```
http://localhost:8080/api/hello
```

---

## 🧪 內建測試功能

前端測試畫面提供：

- 按鈕測試 `GET /api/hello`
- 輸入框測試 `POST /api/echo`
- 回應結果顯示區塊

用於確認前後端是否串接成功。

---

## 📁 專案結構

```
frontend/
├── index.html
├── package.json
├── vite.config.js
└── src/
    ├── main.js
    └── App.vue
```

---

## 📦 建置（Build）

```bash
npm run build
npm run preview
```

---

## 📜 License

MIT License
