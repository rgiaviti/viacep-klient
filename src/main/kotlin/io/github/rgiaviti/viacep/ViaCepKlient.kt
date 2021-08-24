package io.github.rgiaviti.viacep

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.http.IViaCep
import io.github.rgiaviti.viacep.internal.http.engines.HttpEngine
import io.github.rgiaviti.viacep.internal.http.impl.JavaRestClient
import io.github.rgiaviti.viacep.internal.http.impl.OkHttpRestClient

/**
 * Cliente principal para as consultas no Viacep. Pode escolher qual a engine HTTP você quer utilizar nos requests,
 * como JavaEngine (HttpUrlConnection), OkHttp... Nota, que somente a HttpUrlConnection do Java não requer dependências
 * externas.
 */
class ViaCepKlient() {

    /** Default Http Engine -> JavaEngine **/
    private var httpEngine: HttpEngine = HttpEngine.JavaEngine

    /**
     * Constrói a classe baseada no HttpEngine escolhido para efetuar os requests para o Viacep.
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
     * Retorna a implementação do IViaCep com base no engine escolhido. Cada engine HTTP deverá ter a sua própria
     * implementação da ‘interface’ IViaCep.
     */
    private fun restImplementation(): IViaCep {
        return when (this.httpEngine) {
            HttpEngine.OkHttp -> OkHttpRestClient()
            HttpEngine.JavaEngine -> JavaRestClient()
        }
    }
}