package io.github.e1turin.fandom.pages

sealed interface PageStatus {
    fun and(assertion: Boolean) = if (this == CurrentPage && assertion) CurrentPage else NotCurrentPage
}

object CurrentPage: PageStatus
object NotCurrentPage: PageStatus

fun pageIsCurrentIf(assertion: Boolean) = if (assertion) CurrentPage else NotCurrentPage
