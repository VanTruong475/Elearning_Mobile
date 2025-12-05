# UTH SmartTasks - Firebase Google Sign-In App

<div align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" />
</div>

## 📱 Giới thiệu

Ứng dụng Android sử dụng Firebase Authentication và Google Sign-In để đăng nhập người dùng. Sau khi đăng nhập thành công, ứng dụng sẽ tự động điều hướng đến màn hình chi tiết hiển thị thông tin người dùng.

### ✨ Tính năng chính

- 🔐 **Đăng nhập với Google Account** - Sử dụng Firebase Authentication
- 🎨 **UI hiện đại** - Thiết kế đẹp với Jetpack Compose
- 🧭 **Navigation tự động** - Chuyển màn hình sau khi đăng nhập thành công
- 👤 **Hiển thị thông tin user** - Tên, email, và ảnh đại diện từ Google
- 🚪 **Đăng xuất** - Thoát tài khoản và quay về màn hình login

## 🏗️ Kiến trúc

```
┌─────────────────┐
│  LoginScreen    │  ← Màn hình đăng nhập với nút Google Sign-In
└────────┬────────┘
         │ Click "Sign In"
         ↓
┌─────────────────┐
│ Google OAuth    │  ← Chuyển đến giao diện Google để chọn tài khoản
└────────┬────────┘
         │ Xác thực thành công
         ↓
┌─────────────────┐
│ Firebase Auth   │  ← Xác thực với Firebase
└────────┬────────┘
         │ Auth state changed
         ↓
┌─────────────────┐
│ ProfileScreen   │  ← Màn hình chi tiết với thông tin user
└─────────────────┘
```

## 🛠️ Công nghệ sử dụng

- **Kotlin** - Ngôn ngữ lập trình
- **Jetpack Compose** - UI framework
- **Firebase Authentication** - Quản lý xác thực
- **Google Sign-In** - OAuth 2.0 với Google
- **Navigation Compose** - Điều hướng giữa các màn hình
- **Coil** - Load ảnh từ URL (avatar)

## 📦 Dependencies

```kotlin
// Firebase
implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
implementation("com.google.firebase:firebase-auth-ktx")

// Google Sign In
implementation("com.google.android.gms:play-services-auth:21.2.0")

// Navigation
implementation("androidx.navigation:navigation-compose:2.8.4")

// Image loading
implementation("io.coil-kt:coil-compose:2.7.0")
```

## 🚀 Hướng dẫn cài đặt

### Bước 1: Clone project

```bash
cd C:\Users\nvt70\AndroidStudioProjects\tuan51
```

### Bước 2: Thiết lập Firebase

⚠️ **QUAN TRỌNG**: Đọc file [`SETUP_INSTRUCTIONS.md`](./SETUP_INSTRUCTIONS.md) để biết chi tiết

**Tóm tắt:**
1. Tạo Firebase project
2. Thêm Android app (package: `com.example.tuan51`)
3. Thêm SHA-1 certificate (QUAN TRỌNG!)
4. Tải `google-services.json` và đặt vào thư mục `app/`
5. Bật Google Sign-In trong Firebase Console
6. Lấy Web Client ID
7. Cập nhật `app/src/main/res/values/strings.xml`:

```xml
<string name="default_web_client_id">YOUR_ACTUAL_WEB_CLIENT_ID</string>
```

### Bước 3: Sync và Build

```bash
# Sync Gradle
gradlew --refresh-dependencies

# Build
gradlew build

# Install
gradlew installDebug
```

## 📂 Cấu trúc thư mục

```
tuan51/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/tuan51/
│   │   │   ├── MainActivity.kt           # Activity chính với Firebase Auth
│   │   │   ├── LoginScreen.kt            # Màn hình đăng nhập
│   │   │   ├── ProfileScreen.kt          # Màn hình profile
│   │   │   └── ui/theme/                 # Theme và colors
│   │   ├── res/
│   │   │   └── values/
│   │   │       └── strings.xml           # Chứa Web Client ID
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts                  # App dependencies
│   └── google-services.json              # ⚠️ CẦN TẢI TỪ FIREBASE
├── build.gradle.kts                      # Project config
├── gradle/
│   └── libs.versions.toml                # Version catalog
├── SETUP_INSTRUCTIONS.md                 # Hướng dẫn chi tiết
└── README.md                             # File này
```

## 🎯 Luồng hoạt động

### 1. Khởi động ứng dụng
```kotlin
// MainActivity.kt
val auth = Firebase.auth
val currentUser = auth.currentUser

// Nếu đã đăng nhập → ProfileScreen
// Nếu chưa đăng nhập → LoginScreen
```

### 2. Đăng nhập
```kotlin
// User nhấn "Sign In with Google"
onSignInClick() → googleSignInLauncher.launch()
                → Google chọn tài khoản
                → Firebase xác thực
                → AuthStateListener phát hiện thay đổi
                → Navigate to ProfileScreen
```

### 3. Hiển thị thông tin
```kotlin
// ProfileScreen hiển thị:
user.displayName    // "Melissa Peters"
user.email          // "melpeters@gmail.com"
user.photoUrl       // URL ảnh đại diện
```

### 4. Đăng xuất
```kotlin
onSignOutClick() → auth.signOut()
                → googleSignInClient.signOut()
                → Navigate to LoginScreen
```

## 🎨 Screenshots

### Login Screen
- Gradient xanh dương đẹp mắt
- Logo UTH
- Slogan "SmartTasks"
- Nút "SIGN IN WITH GOOGLE" màu trắng

### Profile Screen
- Top bar màu xanh với nút Back
- Avatar tròn với border trắng
- Card hiển thị Name và Email
- Date of Birth (placeholder)
- Nút "Back" màu cyan
- Nút "Sign Out" viền đỏ

## 🔐 Bảo mật

- ✅ File `google-services.json` đã được thêm vào `.gitignore`
- ✅ Sử dụng OAuth 2.0 với Google
- ✅ Token được quản lý bởi Firebase
- ✅ Không lưu trữ mật khẩu

## 🐛 Troubleshooting

### Lỗi thường gặp:

**1. DEVELOPER_ERROR khi sign in**
```
Nguyên nhân: Chưa thêm SHA-1 vào Firebase
Giải pháp: Chạy gradlew signingReport và thêm SHA-1 vào Firebase Console
```

**2. API key not valid**
```
Nguyên nhân: Web Client ID sai hoặc chưa cập nhật
Giải pháp: Kiểm tra lại strings.xml
```

**3. Sign-in provider disabled**
```
Nguyên nhân: Chưa bật Google provider trong Firebase
Giải pháp: Firebase Console > Authentication > Sign-in method > Enable Google
```

## 📝 Checklist Setup

- [ ] Tạo Firebase project
- [ ] Thêm Android app với package name đúng
- [ ] Lấy SHA-1 certificate từ debug keystore
- [ ] Thêm SHA-1 vào Firebase Console
- [ ] Tải và đặt `google-services.json` vào `app/`
- [ ] Bật Google Sign-In provider
- [ ] Lấy Web Client ID
- [ ] Cập nhật Web Client ID trong `strings.xml`
- [ ] Sync Gradle
- [ ] Build và test

## 📚 Tài liệu tham khảo

- [Firebase Authentication - Android](https://firebase.google.com/docs/auth/android/start)
- [Google Sign-In for Android](https://developers.google.com/identity/sign-in/android/start)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)

## 👨‍💻 Tác giả

**Tuần 04 - Bài tập về nhà**
- Đại học Giao thông Vận tải TP.HCM (UTH)
- Môn: Lập trình Mobile Android

## 📄 License

Dự án này được tạo cho mục đích học tập.

---

<div align="center">
  <p>Made with ❤️ using Kotlin & Firebase</p>
  <p>© 2025 UTH SmartTasks</p>
</div>

