package fr.hadrienmp.lib.io

import fr.hadrienmp.lib.ListProvider
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList

class ClassPathFileLines(private val path: String): ListProvider<String> {
    override fun get(): List<String> {
        val inputStream = ClassLoader.getSystemResourceAsStream(path) ?: throw IllegalArgumentException("Fichier inexistant")
        val reader = BufferedReader(InputStreamReader(inputStream))
        return reader.lines().toList()
    }
}