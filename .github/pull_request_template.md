# 📝 Pull Request Summary

請簡述本 PR 的目的與變更內容（可包含背景、需求、改動原因）。

---

# ✅ 變更內容（Change Details）

請描述主要修改範圍：

- Backend / Frontend / 共用 DTO？
- 有無 API 變更？
- 有無資料結構更新？
- 是否包含破壞性變更（Breaking Change）？

---

# 🔍 自我檢查清單（Author Self-check）

請作者在送審前先完成以下確認：

- [ ] 若本 PR 修改了 backend API，已同步檢查 frontend 是否需要調整  
- [ ] 若修改 Request/Response DTO，已同步檢查 TS interface 與 Vue component  
- [ ] 已確認無顯而易見的 runtime error  
- [ ] 已加入必要的 null/edge case 處理  
- [ ] 已檢查是否有安全性風險（log、injection、XSS…）  
- [ ] 程式碼風格一致、可讀性良好  

---

# 🤖 AI Reviewer 指南（Copilot 請遵守 v2.1）

> 本專案使用 `.github/copilot-instructions.md`（version 2.1）。  
> 請 Copilot Reviewer：
> - 回覆使用繁體中文  
> - 開頭加：`Using instructions v2.1`  
> - 依照六大項目完整檢查  
> - 特別注意跨模組風險（backend ↔ frontend）  
> - 指出具體檔案、行數並提供修正程式碼  
> - 結尾給出：`完美` / `通過` / `不通過`  

