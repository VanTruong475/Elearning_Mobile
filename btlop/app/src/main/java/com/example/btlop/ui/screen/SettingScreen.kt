package com.example.btlop.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btlop.data.ThemePreferences
import com.example.btlop.ui.theme.*

@Composable
fun SettingScreen(
    selectedTheme: String,
    onThemeSelected: (String) -> Unit,
    onApplyClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Setting",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Subtitle
        Text(
            text = "Choosing the right theme will boost your personality of your app",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 48.dp)
                .padding(horizontal = 32.dp)
        )
        
        // Theme buttons row
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 48.dp)
        ) {
            // Light/Blue theme button
            ThemeButton(
                color = ThemeButtonLight,
                isSelected = selectedTheme == ThemePreferences.THEME_LIGHT,
                onClick = { onThemeSelected(ThemePreferences.THEME_LIGHT) }
            )
            
            // Pink theme button
            ThemeButton(
                color = ThemeButtonPink,
                isSelected = selectedTheme == ThemePreferences.THEME_PINK,
                onClick = { onThemeSelected(ThemePreferences.THEME_PINK) }
            )
            
            // Dark theme button
            ThemeButton(
                color = ThemeButtonDark,
                isSelected = selectedTheme == ThemePreferences.THEME_DARK,
                onClick = { onThemeSelected(ThemePreferences.THEME_DARK) }
            )
        }
        
        // Apply button
        Button(
            onClick = onApplyClicked,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(
                text = "Apply",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}

@Composable
fun ThemeButton(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .border(
                width = if (isSelected) 4.dp else 0.dp,
                color = if (isSelected) Color.White else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
    )
}


