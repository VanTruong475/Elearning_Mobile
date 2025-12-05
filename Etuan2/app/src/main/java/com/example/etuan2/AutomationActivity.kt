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

class AutomationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Etuan2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AutomationScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AutomationScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Automation System",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Main System Interface
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1976D2)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Top Bar
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Gear Icon
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(
                                        Color(0xFF424242),
                                        RoundedCornerShape(4.dp)
                                    )
                            )
                            
                            // Control Buttons
                            Row {
                                repeat(3) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(
                                                Color(0xFF81C784),
                                                RoundedCornerShape(50)
                                            )
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                }
                            }
                        }

                        // Content Cards
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            repeat(3) {
                                Box(
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(40.dp)
                                        .background(
                                            Color.White,
                                            RoundedCornerShape(8.dp)
                                        )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Progress Bar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .background(
                                    Color(0xFF424242),
                                    RoundedCornerShape(4.dp)
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(120.dp)
                                    .background(
                                        Color(0xFF81C784),
                                        RoundedCornerShape(4.dp)
                                    )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Bottom Content Cards
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            repeat(3) {
                                Box(
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(40.dp)
                                        .background(
                                            Color.White,
                                            RoundedCornerShape(8.dp)
                                        )
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Data Flow Pipes
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(4) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Pipe
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .height(40.dp)
                                .background(
                                    Color(0xFF424242),
                                    RoundedCornerShape(4.dp)
                                )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // Data Card
                        Box(
                            modifier = Modifier
                                .width(30.dp)
                                .height(20.dp)
                                .background(
                                    Color.White,
                                    RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AutomationScreenPreview() {
    Etuan2Theme {
        AutomationScreen()
    }
}

