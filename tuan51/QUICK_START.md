# 🚀 Quick Start Guide

## ✅ Đã hoàn thành

### 1. ✨ Firebase & Google Sign-In Integration
- ✅ Đã thêm Firebase BOM (33.5.1)
- ✅ Đã thêm Firebase Authentication
- ✅ Đã thêm Google Play Services Auth (21.2.0)
- ✅ Đã thêm Navigation Compose
- ✅ Đã thêm Coil để load ảnh

### 2. 🎨 UI Screens
- ✅ **LoginScreen** - Màn hình đăng nhập đẹp với:
  - Gradient xanh dương
  - Logo UTH
  - Nút "Sign In with Google"
  
- ✅ **ProfileScreen** - Màn hình chi tiết với:
  - Avatar người dùng
  - Thông tin Name, Email
  - Nút Back và Sign Out

### 3. 🔐 Authentication Logic
- ✅ Firebase Auth initialization
- ✅ Google Sign-In configuration
- ✅ Activity Result Launcher cho OAuth flow
- ✅ Auth State Listener cho auto navigation
- ✅ Sign out functionality

### 4. 📱 Navigation
- ✅ Jetpack Navigation Compose
- ✅ Tự động chuyển màn hình khi auth state thay đổi
- ✅ LoginScreen ↔️ ProfileScreen

### 5. 📄 Configuration
- ✅ AndroidManifest với INTERNET permission
- ✅ Gradle dependencies đã cấu hình
- ✅ Build config sẵn sàng
- ✅ .gitignore đã bao gồm google-services.json

### 6. 📚 Documentation
- ✅ README.md - Tổng quan project
- ✅ SETUP_INSTRUCTIONS.md - Hướng dẫn setup chi tiết
- ✅ GET_SHA1.md - Hướng dẫn lấy SHA-1
- ✅ google-services.json.template - Template file Firebase

---

## ⚠️ CẦN LÀM NGAY (Bắt buộc)

### Bước 1: Tạo Firebase Project (5 phút)

1. Truy cập: https://console.firebase.google.com/
2. Nhấn **"Add project"** hoặc chọn project có sẵn
3. Nhấn **"Add app"** → chọn **Android**
4. Nhập:
   - **Package name**: `com.example.tuan51`
   - **App nickname**: UTH SmartTasks (tùy chọn)

### Bước 2: Lấy SHA-1 Certificate (2 phút)

Mở **PowerShell** và chạy:

```powershell
cd C:\Users\nvt70\AndroidStudioProjects\tuan51
.\gradlew signingReport
```

**Copy dòng SHA1** (giống như: `AA:BB:CC:DD:EE:...`)

Hoặc xem chi tiết trong file: [`GET_SHA1.md`](./GET_SHA1.md)

### Bước 3: Thêm SHA-1 vào Firebase (1 phút)

1. Trong Firebase Console, dán **SHA-1** vào ô fingerprint
2. Nhấn **"Save"**

### Bước 4: Tải google-services.json (1 phút)

1. Trong Firebase Console, nhấn **"Download google-services.json"**
2. Đặt file vào thư mục:
   ```
   C:\Users\nvt70\AndroidStudioProjects\tuan51\app\google-services.json
   ```

### Bước 5: Bật Google Sign-In (2 phút)

1. Trong Firebase Console → **Authentication** → **Sign-in method**
2. Nhấn **"Google"** → **"Enable"**
3. Chọn **Support email** → **"Save"**

### Bước 6: Lấy Web Client ID (2 phút)

1. Trong Firebase Console → **⚙️ Project Settings** → **General**
2. Kéo xuống phần **"Your apps"**
3. Tìm **"Web client ID"** (có dạng `xxxx.apps.googleusercontent.com`)
4. **Copy** Web Client ID

### Bước 7: Cập nhật Web Client ID (1 phút)

Mở file: `app\src\main\res\values\strings.xml`

Thay dòng:
```xml
<string name="default_web_client_id">YOUR_WEB_CLIENT_ID_HERE</string>
```

Bằng:
```xml
<string name="default_web_client_id">123456-abc.apps.googleusercontent.com</string>
```
*(Thay bằng Web Client ID thực tế của bạn)*

### Bước 8: Sync và Build (2 phút)

Trong Android Studio:

1. **File** → **Sync Project with Gradle Files**
2. Đợi sync xong (thanh progress bar dưới cùng)
3. **Build** → **Rebuild Project**

### Bước 9: Chạy ứng dụng! 🎉

1. Kết nối điện thoại hoặc khởi động emulator
2. Nhấn **Run** (Shift + F10)
3. Đợi app cài đặt
4. Nhấn **"SIGN IN WITH GOOGLE"**
5. Chọn tài khoản Google
6. ✅ Xem màn hình Profile!

---

## 📁 Cấu trúc files đã tạo

```
tuan51/
├── 📝 README.md                          ← Tổng quan project
├── 📝 SETUP_INSTRUCTIONS.md              ← Hướng dẫn chi tiết
├── 📝 GET_SHA1.md                        ← Hướng dẫn SHA-1
├── 📝 QUICK_START.md                     ← File này
├── app/
│   ├── 📄 google-services.json           ← ⚠️ CẦN TẢI TỪ FIREBASE
│   ├── 📄 google-services.json.template  ← Template tham khảo
│   ├── build.gradle.kts                  ← ✅ Đã cấu hình Firebase
│   └── src/main/
│       ├── java/com/example/tuan51/
│       │   ├── 🎯 MainActivity.kt        ← Firebase Auth + Navigation
│       │   ├── 🎨 LoginScreen.kt         ← Màn hình đăng nhập
│       │   └── 🎨 ProfileScreen.kt       ← Màn hình chi tiết
│       ├── res/values/
│       │   └── strings.xml               ← ⚠️ CẦN CẬP NHẬT Web Client ID
│       └── AndroidManifest.xml           ← ✅ Đã thêm permissions
├── build.gradle.kts                      ← ✅ Đã thêm Google Services
├── gradle/
│   └── libs.versions.toml                ← ✅ Đã thêm dependencies
└── .gitignore                            ← ✅ Đã bao gồm google-services.json
```

---

## 🎯 Luồng hoạt động

```
1. Mở app
   ↓
2. Hiển thị LoginScreen
   ↓
3. User nhấn "Sign In with Google"
   ↓
4. Chuyển đến Google Sign-In
   ↓
5. User chọn tài khoản Google
   ↓
6. Google gửi token về app
   ↓
7. App gửi token đến Firebase
   ↓
8. Firebase xác thực thành công
   ↓
9. AuthStateListener phát hiện user đã login
   ↓
10. Tự động navigate đến ProfileScreen
    ↓
11. Hiển thị thông tin user (name, email, avatar)
```

---

## ✨ Features đã implement

### LoginScreen
```kotlin
- ✅ Gradient background (cyan → blue)
- ✅ UTH Logo card
- ✅ "SmartTasks" title
- ✅ Welcome message
- ✅ Google Sign-In button
- ✅ Footer với copyright
```

### ProfileScreen
```kotlin
- ✅ Top bar với nút Back
- ✅ Avatar hiển thị ảnh từ Google
- ✅ Info cards cho Name và Email
- ✅ Date of Birth placeholder
- ✅ Back button (cyan)
- ✅ Sign Out button (red outline)
```

### MainActivity
```kotlin
- ✅ Firebase Auth initialization
- ✅ Google Sign-In client setup
- ✅ Activity Result Launcher
- ✅ Firebase authentication với Google credential
- ✅ Auth state listener
- ✅ Auto navigation khi auth state thay đổi
- ✅ Sign out functionality
- ✅ Toast messages cho feedback
```

---

## 🐛 Các lỗi thường gặp và cách fix

### ❌ "The given sign-in provider is disabled"
**Fix:** Bật Google provider trong Firebase Console

### ❌ "Status{statusCode=DEVELOPER_ERROR}"
**Fix:** Kiểm tra lại SHA-1 đã được thêm vào Firebase chưa

### ❌ "API key not valid"
**Fix:** Kiểm tra Web Client ID trong strings.xml

### ❌ App crashes khi sign in
**Fix:** Kiểm tra file google-services.json đã được đặt đúng vị trí chưa

---

## 📊 Checklist hoàn chỉnh

### Trước khi chạy app:
- [ ] ✅ Code đã được viết
- [ ] ⚠️ Tạo Firebase project
- [ ] ⚠️ Lấy SHA-1 certificate
- [ ] ⚠️ Thêm SHA-1 vào Firebase
- [ ] ⚠️ Tải google-services.json
- [ ] ⚠️ Đặt google-services.json vào app/
- [ ] ⚠️ Bật Google Sign-In trong Firebase
- [ ] ⚠️ Lấy Web Client ID
- [ ] ⚠️ Cập nhật Web Client ID trong strings.xml
- [ ] ⚠️ Sync Gradle
- [ ] ⚠️ Build project

### Khi test app:
- [ ] Nhấn "Sign In with Google"
- [ ] Chọn tài khoản Google
- [ ] Kiểm tra chuyển sang ProfileScreen
- [ ] Kiểm tra hiển thị tên, email, avatar
- [ ] Nhấn "Sign Out"
- [ ] Kiểm tra quay về LoginScreen

---

## 📞 Cần trợ giúp?

### Đọc tài liệu:
1. **SETUP_INSTRUCTIONS.md** - Setup chi tiết từng bước
2. **GET_SHA1.md** - Hướng dẫn lấy SHA-1 với nhiều cách
3. **README.md** - Tổng quan project và troubleshooting

### Lỗi vẫn không fix được?

1. Kiểm tra lại từng bước trong SETUP_INSTRUCTIONS.md
2. Xem phần Troubleshooting trong README.md
3. Google với từ khóa: "Firebase Google Sign In Android [tên lỗi]"
4. Kiểm tra Firebase Console logs
5. Xem Android Studio Logcat để biết lỗi cụ thể

---

## 🎉 Sau khi setup xong

App của bạn sẽ có:
- ✅ Đăng nhập nhanh chóng với Google
- ✅ UI đẹp và hiện đại
- ✅ Navigation mượt mà
- ✅ Hiển thị thông tin user chính xác
- ✅ Sign out hoạt động tốt

**Tổng thời gian setup:** ~15-20 phút

**Enjoy coding! 🚀**

---

<div align="center">
  <p><strong>Made with ❤️ for UTH SmartTasks</strong></p>
  <p>Tuần 04 - Bài tập về nhà</p>
</div>

