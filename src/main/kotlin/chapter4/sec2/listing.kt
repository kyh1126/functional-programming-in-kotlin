package chapter4.sec2

sealed class List<out A>

private fun <A> length(xs: List<A>): Int = TODO()

private fun List<Double>.sum(): Double = TODO()

private fun List<Double>.isEmpty(): Boolean = TODO()

fun <A> List<A>.size(): Int = TODO()

val listing1 = {
    //tag::init1[]
    fun mean(xs: List<Double>): Double =
        if (xs.isEmpty())
            throw ArithmeticException("mean of empty list!") // xs가 비어 있으면 ArithmeticException을 던짐
        else xs.sum() / length(xs) // xs가 비어 있지 않으면 올바른 결과를 반환
    // end::init1[]
}

val listing2 = {
    //tag::init2[]
    fun mean(xs: List<Double>, onEmpty: Double) =
        if (xs.isEmpty()) onEmpty // xs가 비어 있는 경우, 디폴트 값을 돌려줌
        else xs.sum() / xs.size() // xs가 비어 있지 않은 경우 올바른 결과를 돌려줌
    //end::init2[]
}
