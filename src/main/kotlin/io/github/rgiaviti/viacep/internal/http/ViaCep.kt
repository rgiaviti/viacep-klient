package io.github.rgiaviti.viacep.internal.http

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.UnidadeFederativa
import io.github.rgiaviti.viacep.internal.exceptions.CEPFormatException
import io.github.rgiaviti.viacep.internal.exceptions.EnderecoNotFoundException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

/**
 * Super classe comum entre todas as implementações de requests para o Viacep.
 */
sealed class ViaCep {

    companion object {
        const val CEP_PLACEHOLDER = "{cep}"
        const val VIACEP_URL = "https://viacep.com.br/ws/$CEP_PLACEHOLDER/json/unicode"
    }

    /**
     * Checa se o formato de um CEP é válido.
     */
    private fun isCEPValid(cep: String): Boolean {
        if (cep.isBlank() || cep.length != 8 || !cep.matches(Regex("[0-9]+"))) {
            return false
        }

        return true
    }

    /**
     * Se o CEP for inválido, é lançada a exceção.
     */
    protected fun throwExceptionOnInvalidCEP(cep: String) {
        if (!this.isCEPValid(cep)) {
            throw CEPFormatException("The CEP '$cep' is invalid. CEP must have 8 digits: '01001000'")
        }
    }

    /**
     * Constrói a URL de request para o Viacep.
     */
    protected fun url(cep: String) = URL(VIACEP_URL.replace(CEP_PLACEHOLDER, cep))

    /**
     * Faz o decode do body em JSON para o objeto Endereco
     */
    protected fun decodeBody(body: String) = Json.decodeFromString<Endereco>(body)

    /**
     * Se ocorrer um erro no request e o viacep retornar error=true, é lançada uma exceção
     */
    protected fun throwExceptionOnError(endereco: Endereco) {
        if (endereco.erro) {
            throw EnderecoNotFoundException("endereco não encontrado para o cep ${endereco.cep} passado")
        }
    }

    /**
     * Busca o Endereco a partir do CEP passado como parâmetro. O CEP precisa ter obrigatoriamente 8 caracteres
     * numericos. Não pode haver nenhum tipo de caracter especial. Exemplo de CEP correto: 01001000
     */
    abstract fun getEndereco(cep: String): Endereco
    abstract fun searchAddress(uf: UnidadeFederativa, city: String, logradouro: String)
}