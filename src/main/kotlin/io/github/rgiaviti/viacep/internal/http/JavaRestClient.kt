package io.github.rgiaviti.viacep.internal.http

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.UnidadeFederativa
import java.io.BufferedReader
import java.net.HttpURLConnection

/**
 * Implementação de requests usando o HttpURLConnection, disponível na própria JDK e sem necessidade
 * de dependências extras.
 */
object JavaRestClient : ViaCep() {

    override fun getEndereco(cep: String): Endereco {
        this.throwExceptionOnInvalidCEP(cep)
        val conn: HttpURLConnection = this.url(cep).openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.doOutput = true
        val responseBody = conn.inputStream.bufferedReader().use(BufferedReader::readText)
        val endereco = this.decodeBody(responseBody)
        endereco.apply { this.cep = cep }
        this.throwExceptionOnError(endereco)
        return endereco
    }

    override fun searchAddress(uf: UnidadeFederativa, city: String, logradouro: String) {
        TODO("Not yet implemented")
    }
}