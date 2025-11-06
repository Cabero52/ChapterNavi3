package com.chapter.android.nav3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.chapter.android.nav3.navigation.NavGraphAnimation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            /* 1. Animations */
            NavGraphAnimation()
            /* 2. Dialog Scene */
            //NavGraphDialogScene()
            /* 3. List Detail Scene */
            //NavGraphListDetailScene()
            /* 4. Custom Scene */
            //NavGraphCustomScene()
        }
    }
}
