@echo off
echo ========================================
echo LAY SHA-1 CERTIFICATE
echo ========================================
echo.

echo Dang lay SHA-1 tu debug keystore...
echo.

cd /d "%~dp0"

echo Chay gradlew signingReport...
call gradlew.bat signingReport

echo.
echo ========================================
echo TIM DONG "SHA1:" TRONG OUTPUT O TREN
echo VA COPY NO!
echo.
echo Vi du: SHA1: AA:BB:CC:DD:EE:FF:00:11:22:33:44:55:66:77:88:99:AA:BB:CC:DD
echo ========================================
echo.

pause

