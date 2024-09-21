package io.github.e1turin.fandom.pages

sealed interface Status {
    fun and(assertion: Boolean) = if (this == Current && assertion) Current else Undefined

    companion object {
        fun from(assertion: Boolean) = if (assertion) Current else Undefined
    }
}

object Current: Status
object Undefined: Status