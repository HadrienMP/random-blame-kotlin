package fr.hadrienmp.lib

interface ListProvider<out T> {
    fun get(): List<T>
}