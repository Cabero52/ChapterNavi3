package com.chapter.android.nav3.presentation.auth.product

import com.chapter.android.nav3.presentation.AuthGraph
import com.chapter.android.nav3.presentation.RouteOf
import kotlinx.serialization.Serializable

@Serializable
sealed interface ProductRoute : RouteOf<AuthGraph>
@Serializable
data object List : ProductRoute
@Serializable
data class Detail(val id: Int) : ProductRoute
@Serializable
data object Cart : ProductRoute