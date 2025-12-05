package com.example.btlop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.btlop.ui.screen.SettingScreen
import com.example.btlop.ui.theme.BtlopTheme
import com.example.btlop.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Khởi tạo ViewModel
            val themeViewModel: ThemeViewModel = viewModel()
            
            // Lấy theme hiện tại từ ViewModel
            val currentTheme by themeViewModel.currentTheme.collectAsState()
            val selectedTheme by themeViewModel.selectedTheme.collectAsState()
            
            // Áp dụng theme cho toàn bộ app
            BtlopTheme(theme = currentTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SettingScreen(
                        selectedTheme = selectedTheme,
                        onThemeSelected = { theme ->
                            themeViewModel.selectTheme(theme)
                        },
                        onApplyClicked = {
                            themeViewModel.applyTheme()
                        }
                    )
                }
            }
        }
    }
}