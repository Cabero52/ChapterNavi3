package com.chapter.android.nav3.presentation.auth

import com.chapter.android.nav3.presentation.AuthGraph
import com.chapter.android.nav3.presentation.RouteOf
import com.chapter.android.nav3.presentation.auth.product.ProductRoute
import kotlinx.serialization.Serializable

@Serializable
data object MenuGraph : RouteOf<AuthGraph>

@Serializable
data class ProductGraph(val route: ProductRoute) : RouteOf<AuthGraph>