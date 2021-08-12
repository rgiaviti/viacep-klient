package io.github.rgiaviti.viacep.internal.http

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.exceptions.EnderecoNotFoundException
import io.github.rgiaviti.viacep.internal.exceptions.RequestFailedException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Implementação dos requests usando a biblioteca OkHttp. É necessário adicionar a dependência da biblioteca
 * no pom.xml ou no build.gradle.
 */
class OkHttpRestClient : ViaCep() {

    private val httpClient: OkHttpClient = OkHttpClient()

    override fun getEndereco(cep: String): Endereco {
        this.throwExceptionOnInvalidCEP(cep)

        val request: Request = Request.Builder()
            .url(this.url(cep))
            .get()
            .build()

        val response = this.httpClient.newCall(request).execute()

        if (!response.isSuccessful) {
            throw RequestFailedException("o request ao viacep falhou. status code: ${response.code}")
        }

        val endereco = this.decodeBody(response.body!!.string())
        if (endereco.erro) {
            throw EnderecoNotFoundException("endereco não encontrado para o cep $cep passado")
        }

        return endereco
    }
}