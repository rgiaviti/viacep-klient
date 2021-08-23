package io.github.rgiaviti.viacep.internal.http.impl

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.UnidadeFederativa
import io.github.rgiaviti.viacep.internal.decodeEndereco
import io.github.rgiaviti.viacep.internal.exceptions.RequestFailedException
import io.github.rgiaviti.viacep.internal.getRequestFailedMessage
import io.github.rgiaviti.viacep.internal.getViaCepUrl
import io.github.rgiaviti.viacep.internal.http.ViaCep
import java.io.BufferedReader
import java.net.HttpURLConnection

class JavaRestClient : ViaCep {

    companion object {
        private const val HTTP_GET_METHOD = "GET"
    }

    override fun getEndereco(cep: String): Endereco {
        val conn: HttpURLConnection = getViaCepUrl(cep).openConnection() as HttpURLConnection

        conn.apply {
            this.requestMethod = HTTP_GET_METHOD
            this.doOutput = true
        }

        conn.connect()
        if (conn.responseCode != 200) {
            getRequestFailedMessage(conn.responseCode)
            throw RequestFailedException(getRequestFailedMessage(conn.responseCode))
        }

        val responseBody = conn.inputStream.bufferedReader().use(BufferedReader::readText)
        return decodeEndereco(responseBody)
    }

    override fun searchCEPs(uf: UnidadeFederativa, city: String, logradouro: String): List<Endereco> {
        TODO("Not yet implemented")
    }
}