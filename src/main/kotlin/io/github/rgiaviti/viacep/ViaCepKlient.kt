package io.github.rgiaviti.viacep

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.http.JavaRestClient
import io.github.rgiaviti.viacep.internal.http.OkHttpRestClient
import io.github.rgiaviti.viacep.internal.http.ViaCep

class ViaCepKlient() {

    private var httpEngine: HttpEngine = HttpEngine.JavaEngine

    constructor(engine: HttpEngine) : this() {
        this.httpEngine = engine
    }

    enum class HttpEngine {
        OkHttp,
        JavaEngine
    }

    fun getEndereco(cep: String): Endereco {
        return this.restImplementation().getEndereco(cep)
    }

    private fun restImplementation(): ViaCep {
        return when (this.httpEngine) {
            HttpEngine.OkHttp -> OkHttpRestClient()
            HttpEngine.JavaEngine -> JavaRestClient()
        }
    }
}