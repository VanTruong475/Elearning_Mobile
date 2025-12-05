package com.example.etuan2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.etuan2.ui.theme.Etuan2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Etuan2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MenuScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNavigateToAgeExercise = {
                            startActivity(Intent(this@MainActivity, AgeActivity::class.java))
                        },
                        onNavigateToNumberExercise = {
                            startActivity(Intent(this@MainActivity, NumberActivity::class.java))
                        },
                        onNavigateToEmailExercise = {
                            startActivity(Intent(this@MainActivity, EmailActivity::class.java))
                        },
                        onNavigateToDataVisualization = {
                            startActivity(Intent(this@MainActivity, DataVisualizationActivity::class.java))
                        },
                        onNavigateToMessaging = {
                            startActivity(Intent(this@MainActivity, MessagingActivity::class.java))
                        },
                        onNavigateToUniversity = {
                            startActivity(Intent(this@MainActivity, UniversityActivity::class.java))
                        },
                        onNavigateToAutomation = {
                            startActivity(Intent(this@MainActivity, AutomationActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    onNavigateToAgeExercise: () -> Unit,
    onNavigateToNumberExercise: () -> Unit,
    onNavigateToEmailExercise: () -> Unit,
    onNavigateToDataVisualization: () -> Unit,
    onNavigateToMessaging: () -> Unit,
    onNavigateToUniversity: () -> Unit,
    onNavigateToAutomation: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Chọn Bài Tập",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Exercise 1 Button - Age
            Button(
                onClick = onNavigateToAgeExercise,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9C27B0)
                )
            ) {
                Text(
                    text = "Bài 1: Nhập tên và tuổi",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Exercise 2 Button - Number
            Button(
                onClick = onNavigateToNumberExercise,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text(
                    text = "Bài 2: Nhập số và tạo danh sách",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Exercise 3 Button - Email
            Button(
                onClick = onNavigateToEmailExercise,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                )
            ) {
                Text(
                    text = "Bài 3: Kiểm tra email",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // New Features Section
            Text(
                text = "Tính năng mới",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
            )

            // Data Visualization Button
            Button(
                onClick = onNavigateToDataVisualization,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text(
                    text = "Biểu đồ dữ liệu",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Messaging Button
            Button(
                onClick = onNavigateToMessaging,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9C27B0)
                )
            ) {
                Text(
                    text = "Tin nhắn",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // University Logo Button
            Button(
                onClick = onNavigateToUniversity,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE53935)
                )
            ) {
                Text(
                    text = "Logo trường UTH",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Automation System Button
            Button(
                onClick = onNavigateToAutomation,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800)
                )
            ) {
                Text(
                    text = "Hệ thống tự động",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    Etuan2Theme {
        MenuScreen(
            onNavigateToAgeExercise = {},
            onNavigateToNumberExercise = {},
            onNavigateToEmailExercise = {},
            onNavigateToDataVisualization = {},
            onNavigateToMessaging = {},
            onNavigateToUniversity = {},
            onNavigateToAutomation = {}
        )
    }
}