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