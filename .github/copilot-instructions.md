# hello-bank Copilot Instructions  
**version: 1.0 — project-scope only**

These instructions apply to all Copilot usage in this repository  
(code completion, chat, and PR review).

---

# 1. 專案結構（Project Structure）

## Backend — `backend/**`
- Spring Boot 3 + Java 17
- REST API（例如 `/api/hello`）
- 分層建議：controller → service → repository
- 回傳資料常為 JSON 物件或字串

Copilot 在後端 PR Review 時必須：
- 檢查 API 路徑是否有變更  
- 檢查 DTO / 回傳結構是否有變更  
- 檢查是否有可能 NPE / null case  
- 檢查錯誤處理是否合理  

## Frontend — `front/**`
- Demo 前端，呼叫 `/api/hello` 顯示結果
- 未來可能演進成 Vue3/Vite

Copilot 在前端 PR Review 時必須：
- 檢查是否正確呼叫後端 API  
- 檢查是否依賴某欄位造成 undefined/null  
- 檢查 API failure 時畫面 fallback 是否合理  

---

# 2. PR Review 應檢查的重點

## 2.1 Breaking Changes（最高優先）
Copilot MUST 檢查：
- API 路徑是否改動  
- Request / Response 結構是否變更  
- 回傳欄位名稱是否調整  

若有變更 → 必須提醒可能影響前端／其他呼叫端。

---

## 2.2 Null / Edge Case
Backend:
- 是否可能回傳 null / 空值造成 NPE  
- 是否需要加上防呆或預設值  

Frontend:
- 是否可能取用不存在的欄位  
- 是否有處理 API error（避免畫面壞掉）  

---

## 2.3 Coding Structure
Backend:
- controller 不應塞業務邏輯  
- service 不應直接回傳 entity 給 controller  
- naming clarity & readability  

Frontend:
- API 呼叫集中管理（避免散亂）  
- 避免硬編 UI magic string  

---

# 3. PR Review 回覆格式

Copilot MUST：

1. 使用繁體中文  
2. 開頭加上：  
   `Using hello-bank instructions v1.0`
3. 對每個問題提供：
   - 類型（Breaking Change / Null Case / Structure / etc.）  
   - 檔案路徑（若可推斷）  
   - 行號（若可推斷）  
   - 問題描述  
   - 建議修正版程式碼（code block）  

---

# 4. 最終判定（必須擇一）

PR Review 回覆最後一行必須是：

- `完美`  
- `通過`  
- `不通過`
