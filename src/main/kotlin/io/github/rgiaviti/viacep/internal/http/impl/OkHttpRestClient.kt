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
import io.github.rgiaviti.viacep.domains.UnidadeFederativa
import io.github.rgiaviti.viacep.internal.decodeEndereco
import io.github.rgiaviti.viacep.internal.exceptions.RequestFailedException
import io.github.rgiaviti.viacep.internal.getRequestFailedMessage
import io.github.rgiaviti.viacep.internal.getViaCepUrl
import io.github.rgiaviti.viacep.internal.http.IViaCep
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Implementação dos requests usando a biblioteca OkHttp. É necessário adicionar a dependência da biblioteca
 * no pom.xml ou no build.gradle.
 */
internal class OkHttpRestClient : IViaCep {

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