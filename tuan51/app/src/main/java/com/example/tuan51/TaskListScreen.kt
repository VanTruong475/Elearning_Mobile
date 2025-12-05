package com.example.tuan51

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    onTaskClick: (String) -> Unit,
    onBackClick: (() -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Fetch tasks when screen loads
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                isLoading = true
                errorMessage = null
                Log.d("TaskListScreen", "Fetching tasks...")
                val response = RetrofitClient.apiService.getTasks()
                Log.d("TaskListScreen", "Response code: ${response.code()}")
                if (response.isSuccessful) {
                    val tasksResponse = response.body()
                    Log.d("TaskListScreen", "Response body: $tasksResponse")
                    tasks = tasksResponse?.tasks ?: emptyList()
                    Log.d("TaskListScreen", "Tasks count: ${tasks.size}")
                } else {
                    errorMessage = "Failed to load tasks: ${response.code()}"
                    Log.e("TaskListScreen", "Error: $errorMessage")
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
                Log.e("TaskListScreen", "Exception: ${e.message}", e)
            } finally {
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "UTH ",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF00ACC1)
                            )
                            Text(
                                "SmartTasks",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("📋", fontSize = 20.sp)
                        }
                        Text(
                            "A simple and efficient to-do app",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5C6BC0),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentColor = Color.Gray
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* Home */ }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            tint = Color(0xFF5C6BC0)
                        )
                    }
                    IconButton(onClick = { /* Calendar */ }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Calendar",
                            tint = Color.Gray
                        )
                    }
                    FloatingActionButton(
                        onClick = { /* Add Task */ },
                        containerColor = Color(0xFF42A5F5),
                        modifier = Modifier.size(56.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /* Files */ }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Files",
                            tint = Color.Gray
                        )
                    }
                    IconButton(onClick = { /* Settings */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    // Loading state
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                errorMessage != null -> {
                    // Error state
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "Error",
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = errorMessage ?: "Unknown error",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                scope.launch {
                                    try {
                                        isLoading = true
                                        errorMessage = null
                                        val response = RetrofitClient.apiService.getTasks()
                                        if (response.isSuccessful) {
                                            val tasksResponse = response.body()
                                            tasks = tasksResponse?.tasks ?: emptyList()
                                        } else {
                                            errorMessage = "Failed to load tasks: ${response.code()}"
                                        }
                                    } catch (e: Exception) {
                                        errorMessage = "Error: ${e.message}"
                                    } finally {
                                        isLoading = false
                                    }
                                }
                            }) {
                                Text("Retry")
                            }
                        }
                    }
                }
                tasks.isEmpty() -> {
                    // Empty view - match mockup exactly
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(32.dp)
                        ) {
                            // Clipboard icon with Z
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.size(120.dp)
                            ) {
                                Text(
                                    text = "📋",
                                    fontSize = 100.sp
                                )
                                Text(
                                    text = "Z",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.offset(y = 8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                text = "No Tasks Yet!",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Stay productive—add something to do",
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
                else -> {
                    // List view
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(tasks) { task ->
                            TaskListItem(
                                task = task,
                                onClick = { 
                                    Log.d("TaskListScreen", "Task clicked: ID=${task.id}, Title=${task.title}")
                                    onTaskClick(task.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaskListItem(
    task: Task,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (task.status.lowercase()) {
                "in progress" -> Color(0xFFFFE5E5)
                "pending" -> Color(0xFFFFF9E5)
                "completed" -> Color(0xFFE5F9E5)
                else -> Color.White
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Checkbox vuông như mockup
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(
                        if (task.status.lowercase() == "completed") 
                            Color(0xFF4CAF50) 
                        else 
                            Color.Transparent
                    )
                    .then(
                        if (task.status.lowercase() != "completed")
                            Modifier.border(2.dp, Color.Black, RoundedCornerShape(3.dp))
                        else
                            Modifier
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (task.status.lowercase() == "completed") {
                    Text("✓", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = task.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        // Category chip
                        Chip(
                            text = "📂 ${task.category}",
                            backgroundColor = Color(0xFFE3F2FD)
                        )
                        
                        // Priority chip
                        Chip(
                            text = "⚡ ${task.priority}",
                            backgroundColor = when (task.priority.lowercase()) {
                                "high" -> Color(0xFFFFCDD2)
                                "medium" -> Color(0xFFFFF9C4)
                                else -> Color(0xFFE0E0E0)
                            }
                        )
                    }
                    
                    Text(
                        text = task.dueDate,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        fontSize = 11.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Status badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            when (task.status.lowercase()) {
                                "in progress" -> Color(0xFFFF5252)
                                "pending" -> Color(0xFFFFB300)
                                "completed" -> Color(0xFF4CAF50)
                                else -> Color.Gray
                            }
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Status: ${task.status}",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun Chip(
    text: String,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

