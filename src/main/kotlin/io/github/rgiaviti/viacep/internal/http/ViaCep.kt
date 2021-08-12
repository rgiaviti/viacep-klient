package io.github.rgiaviti.viacep.internal.http

import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.exceptions.CEPFormatException

sealed class ViaCep {

    companion object {
        const val CEP_PLACEHOLDER = "{cep}"
        const val VIACEP_URL = "https://viacep.com.br/ws/$CEP_PLACEHOLDER/json/"
    }

    private fun isCEPValid(cep: String): Boolean {
        if (cep.isBlank() || cep.length != 8 || !cep.matches(Regex("[0-9]+"))) {
            return false
        }

        if (cep.length != 8) {
            return false
        }

        return true
    }

    protected fun throwExceptionOnInvalidCEP(cep: String) {
        if (!this.isCEPValid(cep)) {
            throw CEPFormatException("The CEP '$cep' is invalid. CEP must have 8 digits: '01001000'")
        }
    }

    abstract fun getEndereco(cep: String): Endereco
}