package io.github.rgiaviti.viacep.tests

import io.github.rgiaviti.viacep.ViaCepKlient
import org.junit.jupiter.api.Test

internal class ViaCepKlientTest {

    private val klient = ViaCepKlient()

    @Test
    fun testGetEndereco() {
        val endereco = klient.getEndereco("12345100")
        println(endereco)
    }
}