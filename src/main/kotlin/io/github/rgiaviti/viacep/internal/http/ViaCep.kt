package io.github.rgiaviti.viacep.internal.http

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.UnidadeFederativa
import io.github.rgiaviti.viacep.internal.exceptions.CEPFormatException
import io.github.rgiaviti.viacep.internal.exceptions.EnderecoNotFoundException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

interface ViaCep {

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