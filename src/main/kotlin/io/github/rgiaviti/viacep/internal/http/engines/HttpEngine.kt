package io.github.rgiaviti.viacep.internal.http.engines

/**
 * HttpEngine disponíveis para utilização nos requests ao Viacep.
 */
enum class HttpEngine {
    // Biblioteca OkHttp
    OkHttp,

    // HttpUrlConnection
    JavaEngine
}