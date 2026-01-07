# hello-bank Copilot Instructions

version: 1.1 — project-scope only

## 專案結構（Project Structure）

hello-bank 採用前後端分離架構。

### 前端

##### Frontend — `/frontend/**`

- 框架與技術棧：
   - Framework：Vue 3（Vite）
   - Language：TypeScript
   - Markup / Style：HTML、CSS

- 職責界定（Frontend Responsibility）：
   - 呼叫 Backend API 並進行資料展示
   - 負責 View Rendering 與互動行為
   - 不應承擔：
      - 業務規則判斷
      - 核心運算流程
      - 資料庫或系統流程邏輯

- Frontend PR Review — Copilot MUST 執行下列檢核：
   - 是否正確呼叫後端 API，且與回傳欄位對應一致
   - 是否存在依賴 undefined / null 欄位之風險
   - 是否具備：
      - API error handling
      - fallback / empty state 處理
      - loading 狀態切換
   - API 呼叫是否集中管理（避免散落於多處）
   - UI 層是否避免：
      - magic string
      - 過度耦合資料物件
      - inline business logic

### Backend — `/backend/**`

- 框架與技術棧：
   - Framework：Spring Boot 3
   - Language：Java 17

- 職責界定（Frontend Responsibility）：
   - 提供 REST API 服務
   - 實作業務邏輯與流程控制
   - 負責資料存取與交易流程
   - 定義並維護：
      - DTO / Response 結構
      - 錯誤處理規範
      - 回傳資料一致性

- Backend PR Review — Copilot MUST 執行下列檢核：
   - 是否涉及：
      - API 路徑 / 參數 / 回傳格式異動
   - 是否存在：
      - DTO / 回傳資料結構調整
   - 是否可能產生：
      - Null Case
      - NPE 風險
      - 邊界條件未處理情境
   - 例外處理流程是否：
      - 具一致性
      - 具可預期性
      - 能正確回傳錯誤語意
   - controller / service 分層是否符合責任界線