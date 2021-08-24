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
package io.github.rgiaviti.viacep.internal.http

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.UnidadeFederativa

interface IViaCep {

    companion object {
        const val CEP_PLACEHOLDER = "{cep}"
        const val VIACEP_URL = "https://viacep.com.br/ws/$CEP_PLACEHOLDER/json/unicode"
    }

    /**
     * Busca o Endereco a partir do CEP passado como parâmetro. O CEP precisa ter obrigatoriamente 8 caracteres
     * numericos. Não pode haver nenhum caracter especial. Exemplo de CEP correto: 01001000
     */
    fun getEndereco(cep: String): Endereco

    /**
     * Busca os CEPs de um determinado logradouro. Uma lista com os CEPs é retornada caso seja encontrado ao menos
     * um CEP com os argumentos passados. Caso nenhum CEP seja encontrado com os dados passados, então uma lista vazia
     * será retornada.
     */
    fun searchCEPs(uf: UnidadeFederativa, city: String, logradouro: String): List<Endereco>
}