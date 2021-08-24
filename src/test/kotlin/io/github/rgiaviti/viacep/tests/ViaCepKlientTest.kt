package io.github.rgiaviti.viacep.tests

import io.github.rgiaviti.viacep.ViaCepKlient
import io.github.rgiaviti.viacep.domains.Endereco
import io.github.rgiaviti.viacep.internal.exceptions.CEPFormatException
import io.github.rgiaviti.viacep.internal.http.IViaCep
import io.github.rgiaviti.viacep.tests.Util.correctEndereco
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class ViaCepKlientTest {

    private val viacepMock = mockk<IViaCep>()
    private val klient = spyk<ViaCepKlient>(recordPrivateCalls = true)

    @BeforeTest
    fun setup() {
        every { klient["restImplementation"]() } returns viacepMock
    }

    @Test
    fun testCorrectCEP() {
        every { viacepMock.getEndereco(this.any()) } returns correctEndereco()
        val endereco = klient.getEndereco("00000000")
        assertEquals(correctEndereco(), endereco)
    }

    @Test
    fun testBlankCEP() {
        assertThrows<CEPFormatException> {  klient.getEndereco("") }
    }

    @Test
    fun testAlphaCEP() {
        assertThrows<CEPFormatException> {  klient.getEndereco("ABCDEAAAR") }
    }

    @Test
    fun testLowerDigitsCEP() {
        assertThrows<CEPFormatException> {  klient.getEndereco("0100111") }
    }

    @Test
    fun testHigherDigitsCEP() {
        assertThrows<CEPFormatException> {  klient.getEndereco("010011100000") }
    }
}