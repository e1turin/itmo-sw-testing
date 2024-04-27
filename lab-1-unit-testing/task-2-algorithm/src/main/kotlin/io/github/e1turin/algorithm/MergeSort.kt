package io.github.e1turin.algorithm

/*
public sealed class Part<T: Comparable<T>>(
    public val start: T,
    public val stop: T
) {
    public class Right<T : Comparable<T>>(start: T, stop: T): Part<T>(start, stop)
    public class Left<T : Comparable<T>>(start: T, stop: T): Part<T>(start, stop)
}
*/

public fun <T : Comparable<T>> mergeSort(list: List<T>): List<T> {
    return sort(list)
}

private fun <T: Comparable<T>> sort(list: List<T>): List<T> = when {
    list.size <= 1 -> list.toList()
    list.size == 2 -> if (list[0] < list[1]) list else list.reversed()
    else -> merge(
        left = sort(list.subList(0, list.size / 2)),
        right = sort(list.subList(list.size / 2, list.size))
    )
}

private fun <T : Comparable<T>> merge(
    left: List<T>,
    right: List<T>
): List<T> {
    val list = mutableListOf<T>()

    var l = 0
    var r = 0
    while (l < left.size && r < right.size) {
        if (left[l] < right[r]) {
            list.add(left[l++])
        } else {
            list.add(right[r++])
        }
    }
    while (l < left.size) list.add(left[l++])
    while (r < right.size) list.add(right[r++])

    return list
}


