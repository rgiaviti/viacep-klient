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
package io.github.rgiaviti.viacep.domains

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Endereco(

    @SerialName("erro")
    var erro: Boolean = false,

    @SerialName("cep")
    var cep: String = "",

    @SerialName("logradouro", )
    var logradouro: String = "",

    @SerialName("complemento")
    var complemento: String = "",

    @SerialName("bairro")
    var bairro: String = "",

    @SerialName("localidade")
    var localidade: String = "",

    @SerialName("uf")
    var uf: String = "",

    @SerialName("ibge")
    var ibge: String = "",

    @SerialName("gia")
    var gia: String = "",

    @SerialName("ddd")
    var ddd: String = "",

    @SerialName("siafi")
    var siafi: String = ""
)
