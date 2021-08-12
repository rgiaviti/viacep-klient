package io.github.rgiaviti.viacep

import io.github.rgiaviti.viacep.ViaCepKlient.HttpEngine.JavaEngine
import io.github.rgiaviti.viacep.ViaCepKlient.HttpEngine.OkHttp

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val endereco = ViaCepKlient(JavaEngine).getEndereco("13141130")
        println(endereco)
    }
}