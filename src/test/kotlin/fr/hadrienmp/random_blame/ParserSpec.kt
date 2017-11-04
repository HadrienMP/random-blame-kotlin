package fr.hadrienmp.random_blame

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class ParserSpec {

    @Test
    fun `an empty request implies a random polite blame`() {
        val request = ""
        val response = parse(request)
        assertThat(response).isEqualTo("random polite")
    }

    @Test
    @Parameters(
        "@Toto",
        "@Toto et autres",
        "avec autre chose @Toto",
        "avec autre chose @Toto et --un-parametre"
        // TODO "@Toto\u00A0avec un espace insécable"
    )
    fun `a request can designate someone to blame`(request: String) {
        val response = parse(request)
        assertThat(response).isEqualTo("designate @Toto polite")
    }

    @Test
    fun `a request can request a violent blame`() {
        val request = "--with-violence"
        val response = parse(request)
        assertThat(response).isEqualTo("random violent")
    }

    @Test
    fun `a request can designate someone to blame and a request a violent blame`() {
        val request = "@Toto --with-violence"
        val response = parse(request)
        assertThat(response).isEqualTo("designate @Toto violent")
    }

    @Test
    fun `a request with just an @ is taken as a random designation request`() {
        val request = "@"
        val response = parse(request)
        assertThat(response).isEqualTo("random polite")
    }
}