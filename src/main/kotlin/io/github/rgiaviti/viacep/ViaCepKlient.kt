package io.github.rgiaviti.viacep

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.http.impl.JavaRestClient
import io.github.rgiaviti.viacep.internal.http.impl.OkHttpRestClient
import io.github.rgiaviti.viacep.internal.http.ViaCep
import io.github.rgiaviti.viacep.internal.http.engines.HttpEngine

/**
 * Cliente principal para as consultas no Viacep. Você pode escolher qual a engine HTTP você quer utilizar nos requests,
 * como JavaEngine (HttpUrlConnection), OkHttp... Nota, que somente a HttpUrlConnection do Java não requer dependências
 * externas.
 */
class ViaCepKlient() {

    /** Default Http Engine -> JavaEngine **/
    private var httpEngine: HttpEngine = HttpEngine.JavaEngine

    /**
     * Constróia a classe baseada no HttpEngine escolhido para efetuar os requests para o Viacep.
     */
    constructor(engine: HttpEngine) : this() {
        this.httpEngine = engine
    }

    /**
     * Retorna o endereço completo a partir do CEP passado como parâmetro.
     */
    fun getEndereco(cep: String): Endereco {
        return this.restImplementation().getEndereco(cep)
    }

    /**
     * Return the HTTP engine implementation for executing the rest requests
     */
    private fun restImplementation(): ViaCep {
        return when (this.httpEngine) {
            HttpEngine.OkHttp -> OkHttpRestClient()
            HttpEngine.JavaEngine -> JavaRestClient
        }
    }
}