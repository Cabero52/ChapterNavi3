package com.chapter.android.nav3.presentation.unauth.login

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.chapter.android.nav3.presentation.unauth.login.signin.SignInScreen
import com.chapter.android.nav3.presentation.unauth.login.signup.SignUpScreen

@Composable
fun NavGraphLogin(
    outerBackStack: MutableList<NavKey>,
    navToAuth: () -> Unit
) {
    val inner = rememberNavBackStack(SignIn)
    NavDisplay(inner, entryProvider = entryProvider {
        entry<SignIn> {
            SignInScreen(
                onSignIn = navToAuth,
                onGoToSignUp = { inner.add(SignUp) }
            )
        }
        entry<SignUp> {
            SignUpScreen(
                onSignUp = navToAuth,
                onGoToSignIn = { inner.add(SignIn) }
            )
        }
    })
}
