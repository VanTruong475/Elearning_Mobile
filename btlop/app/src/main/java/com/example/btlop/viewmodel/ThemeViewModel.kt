package com.example.btlop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.btlop.data.ThemePreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    
    private val themePreferences = ThemePreferences(application)
    
    // State cho theme hiện tại
    private val _currentTheme = MutableStateFlow(ThemePreferences.THEME_LIGHT)
    val currentTheme: StateFlow<String> = _currentTheme.asStateFlow()
    
    // State cho theme được chọn tạm thời (chưa Apply)
    private val _selectedTheme = MutableStateFlow(ThemePreferences.THEME_LIGHT)
    val selectedTheme: StateFlow<String> = _selectedTheme.asStateFlow()
    
    init {
        // Load theme đã lưu khi khởi động
        loadSavedTheme()
    }
    
    private fun loadSavedTheme() {
        viewModelScope.launch {
            themePreferences.themeFlow.collect { theme ->
                _currentTheme.value = theme
                _selectedTheme.value = theme
            }
        }
    }
    
    // Chọn theme tạm thời (chưa lưu)
    fun selectTheme(theme: String) {
        _selectedTheme.value = theme
    }
    
    // Apply và lưu theme vào DataStore
    fun applyTheme() {
        viewModelScope.launch {
            themePreferences.saveTheme(_selectedTheme.value)
            _currentTheme.value = _selectedTheme.value
        }
    }
}


