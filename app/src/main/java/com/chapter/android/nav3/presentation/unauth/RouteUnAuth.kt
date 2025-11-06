package com.chapter.android.nav3.presentation.unauth

import com.chapter.android.nav3.presentation.RouteOf
import com.chapter.android.nav3.presentation.UnAuthGraph
import kotlinx.serialization.Serializable

@Serializable
data object SplashGraph : RouteOf<UnAuthGraph>
@Serializable
data object LoginGraph : RouteOf<UnAuthGraph>