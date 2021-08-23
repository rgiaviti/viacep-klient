package io.github.rgiaviti.viacep.internal.http.impl

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.UnidadeFederativa
import io.github.rgiaviti.viacep.internal.decodeEndereco
import io.github.rgiaviti.viacep.internal.exceptions.RequestFailedException
import io.github.rgiaviti.viacep.internal.getRequestFailedMessage
import io.github.rgiaviti.viacep.internal.getViaCepUrl
import io.github.rgiaviti.viacep.internal.http.ViaCep
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Implementação dos requests usando a biblioteca OkHttp. É necessário adicionar a dependência da biblioteca
 * no pom.xml ou no build.gradle.
 */
class OkHttpRestClient : ViaCep {

    private val httpClient: OkHttpClient = OkHttpClient()

    override fun getEndereco(cep: String): Endereco {
        val request: Request = Request.Builder().url(getViaCepUrl(cep)).get().build()
        val response = this.httpClient.newCall(request).execute()

        if (!response.isSuccessful) {
            throw RequestFailedException(getRequestFailedMessage(response.code))
        }

        return decodeEndereco(response.body!!.string())
    }

    override fun searchCEPs(uf: UnidadeFederativa, city: String, logradouro: String): List<Endereco> {
        TODO("Not yet implemented")
    }
}