package io.github.rgiaviti.viacep.internal

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.http.ViaCep
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

/**
 * Checa se o formato de um CEP é válido. Para saber se o CEP é válido, algumas regras precisam ser atendidas:
 * - O CEP não pode estar branco ou nulo;
 * - O CEP deve conter exatamente 8 dígitos;
 * - O CEP deve conter somente dígitos [0-9]]
 *
 * Se o CEP não atender essas regras, a função retornará false. Caso contrário, true.
 */
fun isCEPValid(cep: String) = !(cep.isBlank() || cep.length != 8 || !cep.matches(Regex("[0-9]+")))

/**
 * A função constrói e retorna um objeto URL a partir da String de request para o endpoint do ViaCEP.
 */
fun getViaCepUrl(cep: String) = URL(ViaCep.VIACEP_URL.replace(ViaCep.CEP_PLACEHOLDER, cep))

/**
 * Faz o decode para o objeto Endereco a partir de um JSON.
 */
fun decodeEndereco(json: String) = Json.decodeFromString<Endereco>(json)

/**
 * Faz o decode de um json array para o objeto Endereco.
 */
fun decodeEnderecoList(json: String) = Json.decodeFromString<List<Endereco>>(json)