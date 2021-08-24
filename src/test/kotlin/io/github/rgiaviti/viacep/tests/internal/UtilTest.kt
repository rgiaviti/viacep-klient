package io.github.rgiaviti.viacep.tests.internal

import io.github.rgiaviti.viacep.internal.*
import io.github.rgiaviti.viacep.tests.Util
import java.net.URL
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class UtilTest {

    companion object {
        private const val EXPECTED_VIACEP_URL = "https://viacep.com.br/ws/01010000/json/unicode"
    }

    @Test
    fun testIsCEPValid() {
        assertTrue(isCEPValid("00000000"), "test is cep is valid")
        assertFalse(isCEPValid(""), "test is cep is invalid")
        assertFalse(isCEPValid("AAAAAAAA"), "test is cep is invalid")
        assertFalse(isCEPValid("012345657878"), "test is cep is invalid")
        assertFalse(isCEPValid("01234"), "test is cep is invalid")
    }

    @Test
    fun testGetViaCepUrl() {
        val expectedViaCepUrl = "https://viacep.com.br/ws/01010000/json/unicode"
        assertEquals(getViaCepUrl("01010000"), URL(expectedViaCepUrl))
    }

    @Test
    fun testDecodeEndereco() {
        val json =
            "{\"cep\":\"01001-001\",\"logradouro\":\"Praça da Sé\",\"complemento\":\"lado par\",\"bairro\":\"Sé\",\"localidade\":\"São Paulo\",\"uf\":\"SP\",\"ibge\":\"3550308\",\"gia\":\"1004\",\"ddd\":\"11\",\"siafi\":\"7107\"}"
        assertEquals(decodeEndereco(json), Util.correctEndereco())
    }

    @Test
    fun testDecodeEnderecoList() {
        val json =
            "[{\"cep\":\"91420-270\",\"logradouro\":\"Rua São Domingos\",\"complemento\":\"\",\"bairro\":\"Bom Jesus\",\"localidade\":\"Porto Alegre\",\"uf\":\"RS\",\"ibge\":\"4314902\",\"gia\":\"\",\"ddd\":\"51\",\"siafi\":\"8801\"},{\"cep\":\"91040-000\",\"logradouro\":\"Rua Domingos Rubbo\",\"complemento\":\"\",\"bairro\":\"Cristo Redentor\",\"localidade\":\"Porto Alegre\",\"uf\":\"RS\",\"ibge\":\"4314902\",\"gia\":\"\",\"ddd\":\"51\",\"siafi\":\"8801\"}]"
        assertEquals(decodeEnderecoList(json), Util.correctEnderecoList())
    }

    @Test
    fun testGetRequestFailedMessage() {
        assertEquals(getRequestFailedMessage(200), "o request ao viacep falhou. status code: 200")
    }
}