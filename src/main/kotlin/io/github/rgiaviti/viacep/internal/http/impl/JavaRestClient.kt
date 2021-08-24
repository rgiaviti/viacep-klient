/*
* MIT License
*
* Copyright (c) 2021 Ricardo Giaviti
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:

* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.

* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/
package io.github.rgiaviti.viacep.internal.http.impl

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.UnidadeFederativa
import io.github.rgiaviti.viacep.internal.decodeEndereco
import io.github.rgiaviti.viacep.internal.exceptions.RequestFailedException
import io.github.rgiaviti.viacep.internal.getRequestFailedMessage
import io.github.rgiaviti.viacep.internal.getViaCepUrl
import io.github.rgiaviti.viacep.internal.http.IViaCep
import java.io.BufferedReader
import java.net.HttpURLConnection

class JavaRestClient : IViaCep {

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