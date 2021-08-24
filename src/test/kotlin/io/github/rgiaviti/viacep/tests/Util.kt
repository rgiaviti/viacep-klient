package io.github.rgiaviti.viacep.tests

import io.github.rgiaviti.viacep.domains.Endereco

object Util {

    fun correctEndereco() = Endereco(
        erro = false,
        cep = "01001-001",
        logradouro = "Praça da Sé",
        complemento = "lado par",
        bairro = "Sé",
        localidade = "São Paulo",
        uf = "SP",
        ibge = "3550308",
        gia = "1004",
        ddd = "11",
        siafi = "7107"
    )

    fun correctEnderecoList() = listOf(
        Endereco(
            erro = false,
            cep = "91420-270",
            logradouro = "Rua São Domingos",
            complemento = "",
            bairro = "Bom Jesus",
            localidade = "Porto Alegre",
            uf = "RS",
            ibge = "4314902",
            gia = "",
            ddd = "51",
            siafi = "8801"
        ),
        Endereco(
            erro = false,
            cep = "91040-000",
            logradouro = "Rua Domingos Rubbo",
            complemento = "",
            bairro = "Cristo Redentor",
            localidade = "Porto Alegre",
            uf = "RS",
            ibge = "4314902",
            gia = "",
            ddd = "51",
            siafi = "8801"
        )
    )
}