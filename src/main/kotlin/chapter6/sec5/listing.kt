package chapter6.sec5

//tag::init1[]
fun <S, A, B> map(
    sa: (S) -> Pair<A, S>,
    f: (A) -> B
): (S) -> Pair<B, S> = TODO()
//end::init1[]

//tag::init2[]
typealias State<S, A> = (S) -> Pair<A, S>
//data class State<S, out A>(val run: (S) -> Pair<A, S>)
//end::init2[]

interface RNG {
    fun nextInt(): Pair<Int, RNG>
}

typealias Rand<A> = State<RNG, A>
