package io.github.rgiaviti.viacep.internal.http.engines

/**
 * HttpEngine disponíveis para utilização nos requests ao Viacep.
 */
enum class HttpEngine {
    // Utiliza a biblioteca OkHttp como motor HTTP. Necessita adicionar a dependência externa no classpath do projeto.
    OkHttp,

    // Usa a HttpUrlConnection provida pela própria JDK. Não necessita de dependência externa.
    JavaEngine
}