# Checklist Kiểm tra Lỗi

## ✅ Đã Fix

1. **Import không cần thiết**
   - ✅ Đã xóa `FirebaseUser` import không sử dụng trong MainActivity
   - ✅ Đã xóa `Image` và `painterResource` không sử dụng trong LoginScreen

## 🔍 Các lỗi có thể gặp và cách fix

### 1. ⚠️ "Cannot resolve symbol R.string.default_web_client_id"

**Nguyên nhân:** File `google-services.json` chưa có hoặc chưa sync

**Cách fix:**
```
1. Đặt google-services.json vào app/
2. Android Studio > File > Sync Project with Gradle Files
3. Build > Clean Project
4. Build > Rebuild Project
```

**Hoặc tạm thời comment dòng này để test compile:**
```kotlin
// Trong MainActivity.kt line 37
.requestIdToken(getString(R.string.default_web_client_id))

// Thay bằng:
.requestIdToken("your_temp_client_id") // Chỉ để test compile
```

---

### 2. ⚠️ "Unresolved reference: Firebase" hoặc Firebase imports màu đỏ

**Nguyên nhân:** Firebase dependencies chưa được tải về

**Cách fix:**
```
1. Android Studio > File > Invalidate Caches and Restart
2. Sau khi restart: File > Sync Project with Gradle Files
3. Đợi Gradle download dependencies (xem thanh progress dưới cùng)
```

---

### 3. ⚠️ "AsyncImage" cannot be resolved

**Nguyên nhân:** Coil dependency chưa được tải

**Cách fix:**
```
1. Sync Project with Gradle Files
2. Nếu vẫn lỗi, check libs.versions.toml có đúng:
   coilCompose = "2.7.0"
```

---

### 4. ⚠️ Navigation imports màu đỏ

**Nguyên nhân:** Navigation Compose chưa được sync

**Cách fix:**
```
1. Sync Project với Gradle
2. Check app/build.gradle.kts có:
   implementation(libs.androidx.navigation.compose)
```

---

### 5. ⚠️ "@OptIn(ExperimentalMaterial3Api::class)" warning

**Đây KHÔNG phải là lỗi** - chỉ là warning vì TopAppBar đang dùng API thử nghiệm

**Có thể ignore hoặc thêm vào app/build.gradle.kts:**
```kotlin
android {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }
}
```

---

## 📝 Các bước fix lỗi trong Android Studio

### Bước 1: Check Build Output
```
View > Tool Windows > Build
```
Xem lỗi cụ thể được hiển thị ở đây

### Bước 2: Sync Gradle
```
File > Sync Project with Gradle Files
```
Đợi sync hoàn tất (xem progress bar dưới cùng)

### Bước 3: Clean & Rebuild
```
Build > Clean Project
Build > Rebuild Project
```

### Bước 4: Invalidate Caches (nếu còn lỗi)
```
File > Invalidate Caches and Restart > Invalidate and Restart
```

### Bước 5: Check Gradle Sync Log
```
View > Tool Windows > Build
```
Tab "Sync" sẽ hiển thị log chi tiết

---

## 🐛 Các lỗi Runtime (khi chạy app)

### Lỗi: App crash khi nhấn Sign In

**Check Logcat:**
```
View > Tool Windows > Logcat
```

**Nếu thấy "DEVELOPER_ERROR":**
- SHA-1 chưa được thêm vào Firebase
- Xem GET_SHA1.md để lấy và thêm SHA-1

**Nếu thấy "API_KEY_INVALID":**
- Web Client ID sai trong strings.xml
- Check lại Firebase Console

**Nếu thấy "The given sign-in provider is disabled":**
- Chưa bật Google provider trong Firebase Console
- Authentication > Sign-in method > Enable Google

---

## 💡 Tips Debug

### 1. Check imports
Nếu có dòng import màu đỏ:
- Hover chuột lên để xem lỗi
- Alt+Enter để Android Studio suggest fix
- Hoặc Sync Gradle để tải dependencies

### 2. Check Build.gradle
Mở `app/build.gradle.kts` và check:
```kotlin
plugins {
    // Phải có cả 4 plugins này
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services) // ⚠️ QUAN TRỌNG cho Firebase
}
```

### 3. Check libs.versions.toml
Đảm bảo có đầy đủ:
```toml
[versions]
firebaseBom = "33.5.1"
playServicesAuth = "21.2.0"
navigationCompose = "2.8.4"
coilCompose = "2.7.0"

[libraries]
firebase-bom = { ... }
firebase-auth = { ... }
play-services-auth = { ... }
androidx-navigation-compose = { ... }
coil-compose = { ... }
```

---

## ✅ Checklist trước khi chạy app

- [ ] ✅ Tất cả imports không còn màu đỏ
- [ ] ✅ Không có lỗi compile trong Build Output
- [ ] ✅ Sync Gradle thành công (không có error)
- [ ] ⚠️ google-services.json đã đặt trong app/
- [ ] ⚠️ Web Client ID đã cập nhật trong strings.xml
- [ ] ⚠️ SHA-1 đã thêm vào Firebase Console
- [ ] ⚠️ Google provider đã enable trong Firebase

---

## 🆘 Vẫn còn lỗi?

### Kiểm tra cụ thể:

1. **Mở file nào bị lỗi?**
   - MainActivity.kt?
   - LoginScreen.kt?
   - ProfileScreen.kt?

2. **Lỗi xuất hiện ở dòng nào?**
   - Copy message lỗi đầy đủ

3. **Loại lỗi:**
   - Cannot resolve symbol? (import issue)
   - Unresolved reference? (dependency issue)
   - Compilation error? (syntax issue)
   - Runtime crash? (config issue)

### Gửi cho tôi thông tin:
```
File: [tên file]
Line: [số dòng]
Error: [message lỗi đầy đủ]
```

Tôi sẽ giúp fix cụ thể!

---

## 📚 Tài liệu tham khảo

- [SETUP_INSTRUCTIONS.md](./SETUP_INSTRUCTIONS.md) - Setup Firebase
- [GET_SHA1.md](./GET_SHA1.md) - Lấy SHA-1 certificate
- [QUICK_START.md](./QUICK_START.md) - Hướng dẫn nhanh
- [README.md](./README.md) - Tổng quan project

