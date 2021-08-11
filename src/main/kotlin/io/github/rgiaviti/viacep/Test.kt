package io.github.rgiaviti.viacep

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val endereco = ViaCepKlient().getEndereco("00010010")
        println(endereco)
    }
}