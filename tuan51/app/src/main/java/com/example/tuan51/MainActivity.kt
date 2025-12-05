package com.example.tuan51

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tuan51.ui.theme.Tuan51Theme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private var auth: FirebaseAuth? = null
    private var googleSignInClient: GoogleSignInClient? = null
    private var isFirebaseAvailable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Try to initialize Firebase - nếu không có google-services.json sẽ fail
        try {
            auth = Firebase.auth
            
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                // Tạm comment để compile - uncomment sau khi có google-services.json và Web Client ID
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(this, gso)
            isFirebaseAvailable = true
            
            Log.d("MainActivity", "✅ Firebase initialized successfully")
        } catch (e: Exception) {
            Log.e("MainActivity", "❌ Firebase not available: ${e.message}")
            Log.e("MainActivity", "⚠️ Running in DEMO mode without Firebase")
            isFirebaseAvailable = false
            Toast.makeText(
                this, 
                "Demo Mode: Firebase chưa được setup", 
                Toast.LENGTH_LONG
            ).show()
        }

        setContent {
            Tuan51Theme {
                if (isFirebaseAvailable && auth != null) {
                    // Firebase mode - thật
                    SmartTasksApp(
                        auth = auth!!,
                        onSignInClick = { signInWithGoogle() },
                        onEmailSignIn = { email, password -> signInWithEmail(email, password) },
                        onEmailSignUp = { email, password -> signUpWithEmail(email, password) },
                        onAnonymousSignIn = { signInAnonymously() },
                        onSignOutClick = { signOut() }
                    )
                } else {
                    // Demo mode - không cần Firebase
                    DemoApp()
                }
            }
        }
    }

    private fun signInWithGoogle() {
        googleSignInClient?.let { client ->
            val signInIntent = client.signInIntent
            googleSignInLauncher.launch(signInIntent)
        } ?: run {
            Toast.makeText(
                this,
                "Google Sign-In not available",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Log.w("MainActivity", "Google sign in failed", e)
            Toast.makeText(
                this,
                "Google sign in failed: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("MainActivity", "signInWithCredential:success")
                    Toast.makeText(
                        this,
                        "Sign in successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.w("MainActivity", "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        this,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun signInWithEmail(email: String, password: String) {
        auth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("MainActivity", "signInWithEmail:success")
                    Toast.makeText(
                        this,
                        "Email sign in successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.w("MainActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this,
                        "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun signUpWithEmail(email: String, password: String) {
        if (password.length < 6) {
            Toast.makeText(
                this,
                "Password must be at least 6 characters",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        auth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("MainActivity", "createUserWithEmail:success")
                    Toast.makeText(
                        this,
                        "Account created successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.w("MainActivity", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this,
                        "Sign up failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun signInAnonymously() {
        auth?.signInAnonymously()
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("MainActivity", "signInAnonymously:success")
                    Toast.makeText(
                        this,
                        "Signed in as guest!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.w("MainActivity", "signInAnonymously:failure", task.exception)
                    Toast.makeText(
                        this,
                        "Anonymous sign in failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun signOut() {
        auth?.signOut()
        googleSignInClient?.signOut()
        Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun SmartTasksApp(
    auth: FirebaseAuth,
    onSignInClick: () -> Unit,
    onEmailSignIn: (email: String, password: String) -> Unit,
    onEmailSignUp: (email: String, password: String) -> Unit,
    onAnonymousSignIn: () -> Unit,
    onSignOutClick: () -> Unit
) {
    val navController = rememberNavController()
    var currentUser by remember { mutableStateOf(auth.currentUser) }
    var isNavigating by remember { mutableStateOf(false) }

    // Listen to auth state changes
    DisposableEffect(auth) {
        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val newUser = firebaseAuth.currentUser
            if (newUser != currentUser && !isNavigating) {
                currentUser = newUser
                isNavigating = true
                
                if (newUser != null) {
                    navController.navigate("tasklist") {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                } else {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
                
                isNavigating = false
            } else {
                currentUser = newUser
            }
        }
        auth.addAuthStateListener(authStateListener)

        onDispose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (currentUser != null) "tasklist" else "login"
    ) {
        composable("login") {
            LoginScreen(
                onSignInClick = onSignInClick,
                onEmailSignIn = onEmailSignIn,
                onEmailSignUp = onEmailSignUp,
                onAnonymousSignIn = onAnonymousSignIn
            )
        }

        composable("tasklist") {
            TaskListScreen(
                onTaskClick = { taskId ->
                    navController.navigate("taskdetail/$taskId")
                },
                onBackClick = {
                    navController.navigate("profile")
                }
            )
        }

        composable(
            route = "taskdetail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.StringType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            TaskDetailScreen(
                taskId = taskId,
                onBackClick = {
                    navController.popBackStack()
                },
                onTaskDeleted = {
                    navController.popBackStack()
                }
            )
        }

        composable("profile") {
            ProfileScreen(
                user = currentUser,
                onBackClick = {
                    navController.navigate("tasklist") {
                        popUpTo("tasklist") { inclusive = true }
                    }
                },
                onSignOutClick = onSignOutClick
            )
        }
    }
}

@Composable
fun DemoApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onSignInClick = {
                    // Demo mode - navigate to task list
                    navController.navigate("tasklist") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable("tasklist") {
            TaskListScreen(
                onTaskClick = { taskId ->
                    navController.navigate("taskdetail/$taskId")
                },
                onBackClick = {
                    navController.navigate("profile")
                }
            )
        }

        composable(
            route = "taskdetail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.StringType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            TaskDetailScreen(
                taskId = taskId,
                onBackClick = {
                    navController.popBackStack()
                },
                onTaskDeleted = {
                    navController.popBackStack()
                }
            )
        }

        composable("profile") {
            ProfileScreen(
                user = null, // Demo mode - no real user
                onBackClick = {
                    navController.navigate("tasklist") {
                        popUpTo("tasklist") { inclusive = true }
                    }
                },
                onSignOutClick = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}