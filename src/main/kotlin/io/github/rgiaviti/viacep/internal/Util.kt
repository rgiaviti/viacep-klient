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
package io.github.rgiaviti.viacep.internal

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.http.IViaCep
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

/**
 * Checa se o formato de um CEP é válido. Para saber se o CEP é válido, algumas regras precisam ser atendidas:
 * O CEP não pode estar branco ou nulo;
 * O CEP deve conter exatamente 8 dígitos;
 * O CEP deve conter somente dígitos [0–9]
 *
 * Se o CEP não atender essas regras, a função retornará false. Caso contrário, true.
 */
internal fun isCEPValid(cep: String) = !(cep.isBlank() || cep.length != 8 || !cep.matches(Regex("[0-9]+")))

/**
 * A função constrói e retorna um objeto URL a partir da String de request para o endpoint do ViaCEP.
 */
internal fun getViaCepUrl(cep: String) = URL(IViaCep.VIACEP_URL.replace(IViaCep.CEP_PLACEHOLDER, cep))

/**
 * Faz o decode para o objeto Endereco a partir de um JSON.
 */
internal fun decodeEndereco(json: String) = Json.decodeFromString<Endereco>(json)

/**
 * Faz o decode de um json array para o objeto Endereco.
 */
internal fun decodeEnderecoList(json: String) = Json.decodeFromString<List<Endereco>>(json)

/**
 * Constrói a mensagem de falha para subir com exceptions. Coloca o response status code no final da mensagem para
 * indicar qual erro retornado.
 */
internal fun getRequestFailedMessage(statusCode: Int) = "o request ao viacep falhou. status code: $statusCode"