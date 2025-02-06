package com.dreamb.sauruspangcameragame

import android.content.pm.PackageManager
import android.Manifest
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dreamb.sauruspangcameragame.composables.CameraAnswerScreen
import com.dreamb.sauruspangcameragame.composables.CameraPreviewScreen
import com.dreamb.sauruspangcameragame.composables.ShowCameraPreviewScreen
import com.dreamb.sauruspangcameragame.ui.theme.SauruspangCameraGameTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Implement camera related  code
            } else {
                // Camera permission denied (Handle denied operation)
            }

        }


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) -> {
                // Camera permission already granted
                // Implement camera related code
            }
            else -> {
                cameraPermissionRequest.launch(Manifest.permission.CAMERA)
            }
        }

        setContent {
            SauruspangCameraGameTheme {
                // A surface container using the 'background' color from the theme
                NavSys()

            }
        }
    }
}


@Composable
fun NavSys() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main",
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable("main") {
            CameraQuestionScreen(navController)
        }
        composable("camera") {
            ShowCameraPreviewScreen(navController)
        }
        composable("answer") {
            CameraAnswerScreen(navController)
        }

    }
}


