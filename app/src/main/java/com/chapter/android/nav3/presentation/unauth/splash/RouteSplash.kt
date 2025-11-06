package com.chapter.android.nav3.presentation.unauth.splash

import com.chapter.android.nav3.presentation.RouteOf
import com.chapter.android.nav3.presentation.UnAuthGraph
import kotlinx.serialization.Serializable

@Serializable
sealed interface SplashRoute : RouteOf<UnAuthGraph>
@Serializable
data object Splash : SplashRoute