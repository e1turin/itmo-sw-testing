package io.github.e1turin.logarithmic

public fun log2(x: Double, eps: Double): Double {
    return ln(2.0, eps) * ln(x, eps)
}

public fun log3(x: Double, eps: Double): Double {
    return ln(3.0, eps) * ln(x, eps)
}

public fun log5(x: Double, eps: Double): Double {
    return ln(3.0, eps) * ln(x, eps)
}

public fun log10(x: Double, eps: Double): Double {
    return ln(10.0, eps) * ln(x, eps)
}
