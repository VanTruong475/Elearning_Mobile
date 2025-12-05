package com.example.etuan2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.etuan2.ui.theme.Etuan2Theme

class MessagingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Etuan2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MessagingScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MessagingScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Messaging App",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Phone Mockup
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(400.dp)
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF424242)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                // Phone Screen
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Status Bar
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "9:41",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Row {
                                repeat(3) {
                                    Box(
                                        modifier = Modifier
                                            .size(4.dp)
                                            .background(
                                                Color(0xFF1976D2),
                                                RoundedCornerShape(50)
                                            )
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))
                                }
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        // Message Bubble
                        Card(
                            modifier = Modifier
                                .width(120.dp)
                                .height(80.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF1976D2)),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    repeat(4) {
                                        Box(
                                            modifier = Modifier
                                                .width(60.dp)
                                                .height(2.dp)
                                                .background(
                                                    Color.White,
                                                    RoundedCornerShape(1.dp)
                                                )
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                    }
                                }
                            }
                        }

                        // Notification Badge
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    Color.Red,
                                    RoundedCornerShape(50)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "1",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        // Home Button
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    Color(0xFF1976D2),
                                    RoundedCornerShape(50)
                                )
                        )
                    }
                }
            }
        }

        // Decorative Plants
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Left Plant
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(
                                Color(0xFF4CAF50),
                                RoundedCornerShape(4.dp)
                            )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            // Right Plant
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(
                                Color(0xFF4CAF50),
                                RoundedCornerShape(4.dp)
                            )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagingScreenPreview() {
    Etuan2Theme {
        MessagingScreen()
    }
}

