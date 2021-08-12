package io.github.rgiaviti.viacep

import io.github.rgiaviti.viacep.internal.http.engines.HttpEngine

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val endereco = ViaCepKlient(HttpEngine.JavaEngine).getEndereco("13141130")
        println(endereco)
    }
}