package io.github.rgiaviti.viacep

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.http.OkHttpRestClient
import io.github.rgiaviti.viacep.internal.http.ViaCep

class ViaCepKlient() {

    private var httpEngine: HttpEngine = HttpEngine.OkHttp

    constructor(engine: HttpEngine) : this() {
        this.httpEngine = engine
    }

    enum class HttpEngine {
        OkHttp;
    }

    private fun restImplementation(): ViaCep {
        return when (this.httpEngine) {
            HttpEngine.OkHttp -> OkHttpRestClient()
        }
    }

    fun getEndereco(cep: String): Endereco {
        return this.restImplementation().getEndereco(cep)
    }
}