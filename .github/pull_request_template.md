# 📝 PR Summary
請簡述本 PR 的目的與主要修改內容。

關於工作單號：

---

# ✅ 變更內容
請勾選或補充本次修改範圍：

- Backend / Frontend / Both
- 有無 API 變更？
- 有無 DTO / Rq, Rs 結構調整？

---

# 🔍 自我檢查清單（Author）
提交前請確認：

- [ ] Backend API 若有變更，已檢查 front/ 是否需同步  
- [ ] DTO / 回傳欄位變更已同步檢查使用端  
- [ ] 無明顯 runtime error 或未處理 null case  
- [ ] 已處理基本安全性風險（log、injection、XSS）  
- [ ] 程式碼風格一致，命名清晰  

---

# 🤖 AI Reviewer（Copilot）
請依照 `.github/copilot-instructions.md` 規則進行審查：

- 回覆使用繁體中文  
- 檢查 API 變更、DTO、Null Case、結構、前後端影響  
- 指出檔案與行號，並提供修正建議  
- 結尾務必給：`完美` / `通過` / `不通過`
