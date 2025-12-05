# 🎨 So Sánh Mockup vs Thực Tế

## Mockup (Bên Trái) vs Thực Tế (Bên Phải)

---

### 📋 **Todo List Screen**

| Element | Mockup | Thực Tế | Status |
|---------|--------|---------|--------|
| Logo header | UTH SmartTasks + icon | ❓ | Cần check |
| Task cards | Pink, Yellow, Blue | ❓ | Cần check |
| Checkboxes | Vuông, nổi bật | ❓ | Đã implement |
| Bottom nav | 5 icons + FAB | ❓ | Đã implement |
| Empty view | Clipboard + Z | ✅ Show | OK |

---

### 📄 **Detail Screen** 

| Element | Mockup | Thực Tế | Status |
|---------|--------|---------|--------|
| **TITLE** | "Complete Android Project" (lớn, bold) | ✅ Có | OK |
| **DESCRIPTION** | "Finish the UI..." (gray) | ✅ Có | OK |
| **BADGES** | 3 badges inline, MỖI CÁI SIZE VỪA | 📦 Nhỏ quá | ❌ CẦN FIX |
| Pink card | Toàn bộ content trong 1 card hồng | ✅ Có | OK |
| Subtasks | 3 checkboxes | ✅ Có | OK |
| Attachments | document_1_0.pdf | ✅ Có | OK |

---

### 🔴 **VẤN ĐỀ CHÍNH: BADGES QUÁ NHỎ!**

#### Mockup (Mong muốn):
```
┌─────────────────────────────┐
│ 📂 Category  📊 Status  ⚡Priority │
│    Work      In Progress  High    │
│    (vừa)     (vừa)        (vừa)    │
└─────────────────────────────┘
```

#### Thực tế:
```
┌──────────────┐
│ 📂 Cat 📊 Sta ⚡Pri │  ← QUÁ NHỎ!
│  Work InProg High  │
└──────────────┘
```

---

## 🔧 FIX CẦN LÀM:

1. **Tăng size badges** - hiện tại 14sp/10sp/13sp → cần 16sp/12sp/15sp
2. **Tăng padding** - hiện tại 10x8 → cần 12x10
3. **Tăng spacing giữa badges** - từ 8dp → 12dp


