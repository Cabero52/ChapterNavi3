package com.chapter.android.nav3.presentation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

interface Flow : NavKey
interface RouteOf<G : Flow> : NavKey

@Serializable
object UnAuthGraph : Flow

@Serializable
object AuthGraph : Flow