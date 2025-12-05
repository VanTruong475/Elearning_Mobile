package com.example.etuan2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.etuan2.ui.theme.Etuan2Theme

class AgeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Etuan2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AgeValidationScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AgeValidationScreen(modifier: Modifier = Modifier) {
    var nameInput by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }

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
                text = "THỰC HÀNH 01",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Input section with gray background
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Name input
                    Column {
                        Text(
                            text = "Họ và tên",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = nameInput,
                            onValueChange = { 
                                nameInput = it
                                showResult = false
                            },
                            placeholder = { Text("Nhập họ và tên") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Gray,
                                unfocusedBorderColor = Color.Gray
                            )
                        )
                    }

                    // Age input
                    Column {
                        Text(
                            text = "Tuổi",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = ageInput,
                            onValueChange = { 
                                ageInput = it
                                showResult = false
                            },
                            placeholder = { Text("Nhập tuổi") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Gray,
                                unfocusedBorderColor = Color.Gray
                            )
                        )
                    }
                }
            }

            Button(
                onClick = {
                    if (nameInput.isBlank() || ageInput.isBlank()) {
                        result = "Vui lòng nhập đầy đủ thông tin"
                        showResult = true
                    } else {
                        try {
                            val age = ageInput.toInt()
                            val ageCategory = when {
                                age < 2 -> "Em bé"
                                age in 2..6 -> "Trẻ em"
                                age in 7..65 -> "Người lớn"
                                age > 65 -> "Người già"
                                else -> "Không xác định"
                            }
                            result = "Tên: $nameInput\nTuổi: $age\nPhân loại: $ageCategory"
                            showResult = true
                        } catch (e: NumberFormatException) {
                            result = "Tuổi phải là số hợp lệ"
                            showResult = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                )
            ) {
                Text(
                    text = "Kiểm tra",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            // Result display
            if (showResult) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E8)
                    )
                ) {
                    Text(
                        text = result,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(16.dp),
                        color = Color(0xFF2E7D32)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgeValidationScreenPreview() {
    Etuan2Theme {
        AgeValidationScreen()
    }
}

