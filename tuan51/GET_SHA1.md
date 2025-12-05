# Hướng dẫn lấy SHA-1 Certificate

SHA-1 Certificate fingerprint là **BẮT BUỘC** để Google Sign-In hoạt động với Firebase.

## 🔑 Tại sao cần SHA-1?

Firebase sử dụng SHA-1 để xác thực ứng dụng Android của bạn. Nếu không có SHA-1:
- Google Sign-In sẽ báo lỗi `DEVELOPER_ERROR`
- OAuth flow sẽ không hoạt động
- Không thể đăng nhập được

## 💻 Cách lấy SHA-1

### Phương pháp 1: Sử dụng Gradle (Khuyến nghị)

#### Trên Windows PowerShell:
```powershell
cd C:\Users\nvt70\AndroidStudioProjects\tuan51
.\gradlew signingReport
```

#### Trên Windows Command Prompt:
```cmd
cd C:\Users\nvt70\AndroidStudioProjects\tuan51
gradlew signingReport
```

#### Trên macOS/Linux:
```bash
cd ~/AndroidStudioProjects/tuan51
./gradlew signingReport
```

**Kết quả:**
```
Variant: debug
Config: debug
Store: C:\Users\nvt70\.android\debug.keystore
Alias: AndroidDebugKey
MD5: XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX
SHA1: AA:BB:CC:DD:EE:FF:00:11:22:33:44:55:66:77:88:99:AA:BB:CC:DD
SHA-256: ...
```

👉 **Copy dòng SHA1** (20 ký tự hex cách nhau bởi dấu `:`)

---

### Phương pháp 2: Sử dụng keytool trực tiếp

#### Trên Windows PowerShell:
```powershell
keytool -list -v -keystore "$env:USERPROFILE\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android
```

#### Trên Windows Command Prompt:
```cmd
keytool -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android
```

#### Trên macOS/Linux:
```bash
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
```

**Kết quả:**
```
Alias name: androiddebugkey
Creation date: ...
Entry type: PrivateKeyEntry
Certificate chain length: 1
Certificate[1]:
Owner: C=US, O=Android, CN=Android Debug
Issuer: C=US, O=Android, CN=Android Debug
Serial number: 1
Valid from: ... until: ...
Certificate fingerprints:
    SHA1: AA:BB:CC:DD:EE:FF:00:11:22:33:44:55:66:77:88:99:AA:BB:CC:DD
    SHA256: ...
```

👉 **Copy dòng SHA1**

---

### Phương pháp 3: Sử dụng Android Studio

1. Mở **Android Studio**
2. Bên phải, chọn tab **Gradle**
3. Mở `tuan51` > `app` > `Tasks` > `android` > `signingReport`
4. Double-click vào `signingReport`
5. Xem kết quả trong **Run** window ở dưới
6. Copy SHA-1 từ variant `debug`

---

## 🔥 Thêm SHA-1 vào Firebase Console

1. Truy cập [Firebase Console](https://console.firebase.google.com/)
2. Chọn project của bạn
3. Vào **Project Settings** (⚙️ icon)
4. Trong tab **General**, kéo xuống phần **Your apps**
5. Chọn Android app của bạn (`com.example.tuan51`)
6. Nhấn **Add fingerprint**
7. Paste SHA-1 và nhấn **Save**

## 📋 Checklist

- [ ] Chạy lệnh lấy SHA-1
- [ ] Copy SHA-1 fingerprint (định dạng: `AA:BB:CC:...`)
- [ ] Mở Firebase Console
- [ ] Vào Project Settings
- [ ] Thêm SHA-1 vào app Android
- [ ] Save changes
- [ ] Tải lại `google-services.json` (nếu cần)
- [ ] Sync và rebuild project

## ⚠️ Lưu ý quan trọng

### Debug vs Release Keystores

**Debug keystore** (cho development):
- Vị trí: `~/.android/debug.keystore` hoặc `%USERPROFILE%\.android\debug.keystore`
- Password: `android`
- Alias: `androiddebugkey`
- Tự động tạo bởi Android Studio

**Release keystore** (cho production):
- Bạn phải tự tạo keystore cho release builds
- Khi có release keystore, cần thêm **SHA-1 của release** vào Firebase
- Mỗi keystore có SHA-1 khác nhau!

### Multiple SHA-1

Bạn có thể thêm **nhiều SHA-1** vào Firebase:
- SHA-1 từ debug keystore (cho development)
- SHA-1 từ release keystore (cho production)
- SHA-1 từ máy dev khác (nếu làm việc nhóm)
- SHA-1 từ CI/CD system

## 🆘 Troubleshooting

### Lỗi: "keytool command not found"

**Nguyên nhân:** Java JDK chưa được thêm vào PATH

**Giải pháp:**
1. Tìm vị trí Java JDK (thường ở `C:\Program Files\Android\Android Studio\jbr\bin`)
2. Thêm vào PATH environment variable
3. Hoặc dùng đường dẫn đầy đủ:

```powershell
"C:\Program Files\Android\Android Studio\jbr\bin\keytool.exe" -list -v -keystore "$env:USERPROFILE\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android
```

### Lỗi: "Keystore was tampered with"

**Giải pháp:** Xóa file debug.keystore và để Android Studio tạo lại:

```powershell
# Windows
del "$env:USERPROFILE\.android\debug.keystore"

# macOS/Linux
rm ~/.android/debug.keystore
```

Sau đó rebuild project để tạo keystore mới.

### Lỗi: "DEVELOPER_ERROR" sau khi thêm SHA-1

**Kiểm tra:**
1. SHA-1 đã được save chưa?
2. Có thêm đúng SHA-1 cho đúng package name không?
3. Đợi 5-10 phút để Firebase cập nhật
4. Tải lại `google-services.json` và sync project
5. Uninstall app và cài lại

## 📚 Tài liệu tham khảo

- [Firebase - Authenticate Using Google](https://firebase.google.com/docs/auth/android/google-signin)
- [Android Developers - Sign Your App](https://developer.android.com/studio/publish/app-signing)
- [Google - SHA Fingerprint](https://developers.google.com/android/guides/client-auth)

---

<div align="center">
  <p><strong>Lưu ý:</strong> SHA-1 từ debug keystore CHỈ dùng cho development. Khi release app, nhớ thêm SHA-1 của release keystore!</p>
</div>

