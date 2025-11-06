package com.chapter.android.nav3.presentation.unauth.login

import com.chapter.android.nav3.presentation.RouteOf
import com.chapter.android.nav3.presentation.UnAuthGraph
import kotlinx.serialization.Serializable


sealed interface LoginRoute : RouteOf<UnAuthGraph>
@Serializable
data object SignIn : LoginRoute
@Serializable
data object SignUp : LoginRoute