package io.github.rgiaviti.viacep

import io.github.rgiaviti.viacep.internal.http.engines.HttpEngine

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val endereco = ViaCepKlient(HttpEngine.JavaEngine).getEndereco("0000000012")
        println(endereco)
    }
}