package io.github.e1turin.fandom.pages

sealed interface PageStatus
object CurrentPage: PageStatus
object NotCurrentPage: PageStatus

fun onThisPageIf(assertion: Boolean) = if (assertion) CurrentPage else NotCurrentPage
