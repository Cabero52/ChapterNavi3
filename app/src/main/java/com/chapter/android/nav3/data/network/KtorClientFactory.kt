package com.chapter.android.nav3.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object KtorClientFactory {
    fun build(debugLogging: Boolean = true): HttpClient = HttpClient(Android) {
        expectSuccess = true // throw on 4xx/5xx
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = NetworkConfig.TIMEOUT_MS
            connectTimeoutMillis = NetworkConfig.TIMEOUT_MS
            socketTimeoutMillis = NetworkConfig.TIMEOUT_MS
        }
        if (debugLogging) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.BODY
            }
            install(ResponseObserver) {
                onResponse { response ->
                    // place to add custom analytics if needed
                }
            }
        }
        install(HttpRequestRetry) {
            maxRetries = 2
            retryIf { request, response ->
                !response.status.isSuccess() // retry on non-2xx
            }
            delayMillis { retry -> retry * 300L }
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = NetworkConfig.HOST
            }
            header("Accept", "application/json")
        }
    }
}