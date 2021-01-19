package com.example.ktlearntest.bylazy


class TestCase {

    private val name: Int by lazy { 1 }

    fun main() {
        println(name)
    }

}