package com.historical.streetlight.utils

object NonRepeatingRandom {
    private var previous = -1

    fun retrieve(): Int {

        val rand = (1..3).random()
        return if (rand == previous) {
            retrieve() // recursive call if two subsequent retrieve() calls would return the same number
        } else {
            previous = rand // remember last random number
            rand
        }
    }
}