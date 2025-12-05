# Fix Kotlin Daemon Error

## 🐛 Lỗi
```
The daemon has terminated unexpectedly on startup attempt #1 with error code: 0
The daemon process output: Kotlin compile daemon is ready
```

## ✅ Giải Pháp - Làm Theo Thứ Tự

### 📋 BƯỚC 1: Invalidate Caches (BẮT BUỘC)

1. Trong Android Studio:
   - **File** → **Invalidate Caches...**
   - ✅ Check ALL options
   - Click **"Invalidate and Restart"**
   - Đợi Android Studio restart hoàn toàn

### 📋 BƯỚC 2: Sync Gradle

Sau khi Android Studio restart:
1. Click **File** → **Sync Project with Gradle Files**
2. Hoặc click icon 🐘 (Gradle sync) trên toolbar
3. Đợi sync xong

### 📋 BƯỚC 3: Clean & Rebuild

1. **Build** → **Clean Project** (đợi xong)
2. **Build** → **Rebuild Project**

---

## 🔧 Đã Tối Ưu Gradle Settings

File `gradle.properties` đã được cập nhật với:

### Memory Settings
```properties
# Tăng memory cho Gradle daemon
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=1024m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8 -Dkotlin.daemon.jvm.options=-Xmx2048m

# Enable performance optimizations
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.configureondemand=true
```

### Những gì đã thay đổi:
- ✅ **Gradle heap**: 2GB → **4GB** (tăng gấp đôi)
- ✅ **Kotlin daemon**: explicit **2GB** memory
- ✅ **Metaspace**: **1GB** để tránh lỗi
- ✅ **Parallel builds**: enabled
- ✅ **Build cache**: enabled
- ✅ **Configure on demand**: enabled

---

## 🚀 Sau Khi Fix

Bạn sẽ thấy:
- ✅ Build nhanh hơn
- ✅ Không còn daemon crash
- ✅ Kotlin compilation smooth
- ✅ Gradle sync thành công

---

## 🔍 Nếu Vẫn Lỗi

### Option 1: Kill All Gradle Daemons

**Windows PowerShell:**
```powershell
# Trong Android Studio Terminal
.\gradlew --stop
```

Hoặc:
```powershell
# Kill tất cả Java processes
Get-Process java | Stop-Process -Force
```

### Option 2: Delete Gradle Cache

Manually delete các folders:
```
C:\Users\nvt70\.gradle\caches\
C:\Users\nvt70\.gradle\daemon\
C:\Users\nvt70\AndroidStudioProjects\tuan51\.gradle\
C:\Users\nvt70\AndroidStudioProjects\tuan51\build\
```

Sau đó:
1. Restart Android Studio
2. Sync Gradle lại

### Option 3: Update Android Studio & Gradle

Nếu vẫn không được:
1. Check for **Android Studio updates**
2. Update **Gradle plugin** nếu cần
3. Update **Kotlin plugin** nếu cần

---

## 📝 Lưu Ý

- Lỗi này thường do **memory không đủ** cho Kotlin daemon
- **Không** liên quan đến code của bạn
- **Không** liên quan đến Firebase hay API
- Chỉ là vấn đề **build configuration**

---

## ✨ Checklist

Sau khi làm các bước trên, check:

- [ ] Android Studio đã restart
- [ ] Gradle sync thành công (không có lỗi đỏ)
- [ ] Build project thành công
- [ ] App chạy được trên emulator/device

---

**Status**: ✅ CONFIGURED - Try build now!

