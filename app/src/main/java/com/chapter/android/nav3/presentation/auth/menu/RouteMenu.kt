package com.chapter.android.nav3.presentation.auth.menu

import com.chapter.android.nav3.presentation.AuthGraph
import com.chapter.android.nav3.presentation.RouteOf
import kotlinx.serialization.Serializable

@Serializable
sealed interface MenuRoute : RouteOf<AuthGraph>
@Serializable
data object Menu : MenuRoute
@Serializable
data object SubMenu : MenuRoute