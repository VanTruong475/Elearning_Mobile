# Fix App Crash When Clicking Tasks

## 🐛 Vấn Đề (Problem)

App bị **crash** khi click vào task trong list.

### Nguyên Nhân
- Task model có các fields **non-null** (`String`, `Boolean`)
- Nhưng API có thể trả về **null** cho một số fields
- Khi Gson parse JSON → null values → **NullPointerException** → Crash!

## ✅ Giải Pháp (Solution)

### 1. Thêm Default Values Cho Tất Cả Fields

#### Trước (Dễ crash):
```kotlin
data class Task(
    val id: String,           // ❌ Crash nếu null
    val title: String,        // ❌ Crash nếu null
    val description: String,  // ❌ Crash nếu null
    ...
)
```

#### Sau (An toàn):
```kotlin
data class Task(
    val id: String = "",           // ✅ Default = ""
    val title: String = "",        // ✅ Default = ""
    val description: String = "",  // ✅ Default = ""
    val status: String = "",
    val category: String = "",
    val priority: String = "",
    val dueDate: String = "",
    val subtasks: List<Subtask>? = null,
    val attachments: List<Attachment>? = null
)
```

### 2. Subtask & Attachment Cũng Cần Default Values

```kotlin
data class Subtask(
    val id: String = "",
    val title: String = "",
    val completed: Boolean = false  // ✅ Default = false
)

data class Attachment(
    val id: String = "",
    val name: String = "",
    val url: String = ""
)
```

### 3. Thêm Logging Để Debug

```kotlin
// Trong TaskListScreen - khi click
Log.d("TaskListScreen", "Task clicked: ID=${task.id}, Title=${task.title}")

// Trong TaskDetailScreen - khi load
Log.d("TaskDetailScreen", "Fetching task detail for ID: $taskId")
Log.d("TaskDetailScreen", "Response code: ${response.code()}")
Log.d("TaskDetailScreen", "Task loaded: ${task?.title}")
```

## 🔍 Cách Kiểm Tra (How to Debug)

### Xem Logcat trong Android Studio:

1. **Run app** và click vào một task
2. **Mở Logcat** (bottom toolbar)
3. **Filter** với:
   - Tag: **"TaskListScreen"** - xem task được click
   - Tag: **"TaskDetailScreen"** - xem detail loading
   - Tag: **"API"** - xem API response

### Ví dụ Logs Bình Thường:
```
D/TaskListScreen: Task clicked: ID=1, Title=Complete Android Project
D/TaskDetailScreen: Fetching task detail for ID: 1
D/API: --> GET https://amock.io/api/researchUTH/task/1
D/API: <-- 200 OK
D/TaskDetailScreen: Response code: 200
D/TaskDetailScreen: Task loaded: Complete Android Project
```

### Nếu Có Lỗi:
```
E/TaskDetailScreen: Error: Failed to load task: 404
hoặc
E/TaskDetailScreen: Exception: java.net.UnknownHostException
```

## 🎯 Sau Khi Fix

Bây giờ app sẽ:
- ✅ **Không crash** khi click vào task
- ✅ Navigate đúng sang detail screen
- ✅ Hiển thị loading spinner khi fetch data
- ✅ Hiển thị error nếu API fail
- ✅ Hiển thị task details nếu thành công

## 📱 Test Flow

1. **Build & Run** app
2. **Login** (hoặc demo mode)
3. **Click vào bất kỳ task nào** trong list
4. **Kỳ vọng**:
   - Loading spinner xuất hiện
   - Detail screen hiển thị với đầy đủ thông tin
   - Back button hoạt động
   - Delete button hiển thị

## 🔧 Các Trường Hợp Có Thể Xảy Ra

### ✅ Trường Hợp 1: API Trả Về Đầy Đủ Data
- Detail screen hiển thị bình thường
- All fields có giá trị

### ✅ Trường Hợp 2: API Trả Về Một Số Fields Null
- App vẫn hoạt động (không crash)
- Fields null sẽ hiển thị là empty string ""

### ✅ Trường Hợp 3: API Lỗi (404, 500, etc.)
- Hiển thị error screen với icon ⚠️
- Có nút "Go Back" để về list

### ✅ Trường Hợp 4: Không Có Internet
- Hiển thị error: "Error: Unable to resolve host..."
- User có thể back về list

## 💡 Best Practices Learned

1. **Always use default values** cho fields trong data class
2. **Never assume API always returns complete data**
3. **Add logging** để debug easier
4. **Handle all error cases** gracefully
5. **Test with poor network conditions**

## 🚀 Next Steps

Bây giờ bạn có thể test:
- ✅ Click vào tasks
- ✅ Xem detail
- ✅ Delete tasks
- ✅ Navigation back/forth

---

**Status**: ✅ FIXED - No more crashes!


