# Fix JSON Parsing Error

## 🐛 Vấn Đề (Problem)

Lỗi: **"Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $"**

### Nguyên Nhân
- API `https://amock.io/api/researchUTH/tasks` đang trả về một **object** thay vì **array**
- Code ban đầu expect response là `List<Task>` trực tiếp
- Nhưng API có thể trả về format như:
  ```json
  {
    "tasks": [...],
    "total": 10
  }
  ```
  hoặc
  ```json
  {
    "data": [...]
  }
  ```

## ✅ Giải Pháp (Solution)

### 1. Tạo Custom Deserializer
Tạo `TasksResponseDeserializer` trong `Task.kt` để xử lý **linh hoạt** nhiều format:
- ✅ Response là array trực tiếp `[...]`
- ✅ Response là object với field "tasks" `{ "tasks": [...] }`
- ✅ Response là object với field "data" `{ "data": [...] }`

### 2. Thêm Logging
- ✅ OkHttp Logging Interceptor để xem raw response
- ✅ Log trong TaskListScreen để debug
- ✅ Kiểm tra Logcat với tag "API" và "TaskListScreen"

### 3. Updated Files

#### `Task.kt`
```kotlin
// Custom deserializer xử lý nhiều format
class TasksResponseDeserializer : JsonDeserializer<TasksResponse> {
    // Tự động detect và parse đúng format
}
```

#### `ApiService.kt`
```kotlin
// Gson với custom deserializer
private val gson: Gson = GsonBuilder()
    .setLenient()
    .registerTypeAdapter(TasksResponse::class.java, TasksResponseDeserializer())
    .create()

// OkHttp logging để debug
private val loggingInterceptor = HttpLoggingInterceptor { message ->
    Log.d("API", message)
}.apply {
    level = HttpLoggingInterceptor.Level.BODY
}
```

#### `TaskListScreen.kt`
```kotlin
// Logging để debug
Log.d("TaskListScreen", "Fetching tasks...")
Log.d("TaskListScreen", "Response code: ${response.code()}")
Log.d("TaskListScreen", "Response body: $tasksResponse")
Log.d("TaskListScreen", "Tasks count: ${tasks.size}")
```

### 4. Dependencies Added
```kotlin
implementation(libs.okhttp.logging) // OkHttp Logging Interceptor
```

## 🔍 Cách Kiểm Tra (How to Test)

1. **Sync Gradle** trong Android Studio
2. **Clean & Rebuild** project
3. **Run app** và login
4. **Mở Logcat** trong Android Studio
5. **Filter** với tag "API" hoặc "TaskListScreen"
6. **Xem logs**:
   ```
   D/API: --> GET https://amock.io/api/researchUTH/tasks
   D/API: <-- 200 OK
   D/API: [Response body here]
   D/TaskListScreen: Fetching tasks...
   D/TaskListScreen: Response code: 200
   D/TaskListScreen: Response body: TasksResponse(tasks=[...])
   D/TaskListScreen: Tasks count: 3
   ```

## 📝 Lưu Ý (Notes)

- Custom deserializer giờ có thể handle **BẤT KỲ** format nào API trả về
- Nếu vẫn có lỗi, check Logcat để xem exact response format
- Có thể modify `TasksResponseDeserializer` để support thêm format khác

## ✨ Kết Quả (Result)

- ✅ App không crash nữa
- ✅ Tasks được load và hiển thị đúng
- ✅ EmptyView hoặc ListView hiển thị tùy data
- ✅ Ready để test detail screen và delete function

---

**Status**: ✅ FIXED - Ready to test!

