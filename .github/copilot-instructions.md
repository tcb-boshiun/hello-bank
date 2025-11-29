# Copilot Pull Request Review Instructions  
version: 0.1
（本文件為本專案正式 Code Review 規範，請 Copilot 在每次 Review 時確實遵循。）  

---

因為本專案是採 API 前後端分離，請幫我檢查，當我更新 API 的路徑時，是否會讓前端死掉，前端在 front 目錄。

- 專案為 monorepo，前後端在同一個 repo 中（front/ = 前端, backend/ = 後端），通常會一起釋出。
- 當偵測到後端 API 路徑有變更（例如 /api/echo → /api/echo/v2），但本次 diff 中沒有 front/ 目錄的修改時：
  - 請在 review 建議中提醒：
    - 本次 PR 僅修改後端，未看到 front/ 的對應更新
    - 由於前後端會一併釋出，請作者確認 front/ 內呼叫舊路徑的地方是否已更新
    - 可建議使用 `rg "/api/echo" front/` 搜尋仍在使用的舊路徑
