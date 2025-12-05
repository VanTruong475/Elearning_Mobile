# Hướng dẫn cài đặt Firebase Google Sign-In

## 📋 Các bước cần thực hiện

### 1. Tạo Firebase Project

1. Truy cập [Firebase Console](https://console.firebase.google.com/)
2. Tạo project mới hoặc chọn project hiện có
3. Thêm ứng dụng Android vào project

### 2. Đăng ký ứng dụng Android

1. Trong Firebase Console, chọn **"Add app"** > **Android**
2. Nhập thông tin:
   - **Package name**: `com.example.tuan51`
   - **App nickname** (tùy chọn): UTH SmartTasks
   - **SHA-1 certificate** (BẮT BUỘC cho Google Sign-In)

#### Lấy SHA-1 Certificate:

**Trên Windows (PowerShell):**
```powershell
cd C:\Users\nvt70\AndroidStudioProjects\tuan51
.\gradlew signingReport
```

Hoặc chạy lệnh này để lấy SHA-1 từ debug keystore:
```powershell
keytool -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android
```

3. Tải file `google-services.json` và đặt vào thư mục `app/` của project

### 3. Cấu hình Google Sign-In trong Firebase

1. Trong Firebase Console, vào **Authentication** > **Sign-in method**
2. Bật **Google** provider
3. Nhập **Project support email**
4. Lưu lại

### 4. Lấy Web Client ID

1. Vào **Project Settings** > **General**
2. Cuộn xuống phần **Your apps**
3. Tìm **Web API Key** hoặc **Web client ID** trong OAuth 2.0 Client IDs
4. Copy **Web client ID** (định dạng: `xxxxx.apps.googleusercontent.com`)

### 5. Cập nhật Web Client ID trong code

Mở file `app/src/main/res/values/strings.xml` và thay thế:

```xml
<string name="default_web_client_id">YOUR_WEB_CLIENT_ID_HERE</string>
```

Bằng Web Client ID thực tế, ví dụ:

```xml
<string name="default_web_client_id">123456789-abc123.apps.googleusercontent.com</string>
```

### 6. Sync và Build Project

1. Trong Android Studio, chọn **File** > **Sync Project with Gradle Files**
2. Đợi sync hoàn tất
3. Build và chạy ứng dụng

## 🎯 Cấu trúc Project

```
tuan51/
├── app/
│   ├── google-services.json          ← ĐẶT FILE NÀY TẠI ĐÂY
│   ├── build.gradle.kts
│   └── src/main/
│       ├── java/com/example/tuan51/
│       │   ├── MainActivity.kt       ← Firebase Auth & Navigation
│       │   ├── LoginScreen.kt        ← Màn hình đăng nhập
│       │   └── ProfileScreen.kt      ← Màn hình chi tiết sau login
│       └── res/values/
│           └── strings.xml           ← Cập nhật Web Client ID
└── build.gradle.kts
```

## 🔧 Troubleshooting

### Lỗi: "The given sign-in provider is disabled"
- Kiểm tra Google provider đã được bật trong Firebase Console
- Đảm bảo đã lưu thay đổi

### Lỗi: "Status{statusCode=DEVELOPER_ERROR}"
- SHA-1 certificate chưa được thêm vào Firebase
- Web Client ID không đúng
- File `google-services.json` chưa được đặt đúng vị trí

### Lỗi: "API key not valid"
- Kiểm tra lại Web Client ID trong `strings.xml`
- Đảm bảo đã enable Google Sign-In API trong Google Cloud Console

## 📱 Chức năng

✅ Đăng nhập với Google Account
✅ Tự động điều hướng đến màn hình Profile sau khi đăng nhập thành công
✅ Hiển thị thông tin người dùng (tên, email, ảnh đại diện)
✅ Đăng xuất và quay về màn hình đăng nhập

## 📸 Demo

### Màn hình Login
- Giao diện đẹp với gradient xanh dương
- Logo UTH
- Nút "Sign In with Google"

### Màn hình Profile
- Avatar người dùng từ Google
- Thông tin tên và email
- Nút Back và Sign Out

## 🚀 Chạy ứng dụng

```bash
# Build và chạy
gradlew installDebug

# Hoặc trong Android Studio: Run > Run 'app'
```

## 📝 Lưu ý quan trọng

- **KHÔNG** commit file `google-services.json` lên Git (đã có trong .gitignore)
- **PHẢI** có internet để đăng nhập
- **PHẢI** thêm SHA-1 certificate vào Firebase Console
- Web Client ID là từ Firebase Console, **KHÔNG PHẢI** Android Client ID

