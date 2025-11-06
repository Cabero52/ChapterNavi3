package com.chapter.android.nav3.di

import com.chapter.android.nav3.data.datasource.PokemonRemoteDataSource
import com.chapter.android.nav3.data.network.KtorClientFactory
import com.chapter.android.nav3.data.network.KtorPokemonApi
import com.chapter.android.nav3.data.network.PokemonApi
import com.chapter.android.nav3.presentation.auth.product.list.ListViewModel
import com.chapter.android.nav3.presentation.auth.product.detail.DetailViewModel
import io.ktor.client.HttpClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val Modules = module {
    single { PokemonRemoteDataSource(get()) }
    single<PokemonApi> { KtorPokemonApi(get()) }
    single<HttpClient> { KtorClientFactory.build() }
    viewModelOf(::ListViewModel)
    viewModelOf(::DetailViewModel)
}