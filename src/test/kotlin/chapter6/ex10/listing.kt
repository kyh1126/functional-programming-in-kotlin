package chapter6.ex10

// import chapter3.Cons
// import chapter3.solutions.foldRight
import chapter3.Cons
import chapter3.List
import chapter3.foldRight
import chapter6.RNG
import chapter6.rng1
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//tag::init[]
data class State<S, out A>(val run: (S) -> Pair<A, S>) {

    companion object {
        fun <S, A> unit(a: A): State<S, A> = State { s -> a to s }

        fun <S, A, B, C> map2(
            ra: State<S, A>,
            rb: State<S, B>,
            f: (A, B) -> C
        ): State<S, C> =
            State { s ->
                val (a, newRng) = ra.run(s)
                val (b, newRng2) = rb.run(newRng)
                f(a, b) to newRng2
            }

        fun <S, A> sequence(fs: List<State<S, A>>): State<S, List<A>> =
            foldRight(fs, unit(List.empty())) { a, listA -> map2(a, listA) { x, y -> Cons(x, y) } }
    }

    fun <B> map(f: (A) -> B): State<S, B> = flatMap { a -> unit(f(a)) }

    fun <B> flatMap(f: (A) -> State<S, B>): State<S, B> = State { s ->
        val (a, s2) = this.run(s)
        f(a).run(s2)
    }
}
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise10 : WordSpec({
    "!unit" should {
        "compose a new state of pure a" {
            State.unit<RNG, Int>(1).run(rng1) shouldBe (1 to rng1)
        }
    }
    "!map" should {
        "transform a state" {
            State.unit<RNG, Int>(1)
                .map { it.toString() }
                .run(rng1) shouldBe ("1" to rng1)
        }
    }
    "!flatMap" should {
        "transform a state" {
            State.unit<RNG, Int>(1)
                .flatMap { i ->
                    State.unit<RNG, String>(i.toString())
                }.run(rng1) shouldBe ("1" to rng1)
        }
    }
    "!map2" should {
        "combine the results of two actions" {

            val combined: State<RNG, String> =
                State.map2(
                    State.unit(1.0),
                    State.unit(1)
                ) { d: Double, i: Int ->
                    ">>> $d double; $i int"
                }

            combined.run(rng1).first shouldBe ">>> 1.0 double; 1 int"
        }
    }
    "!sequence" should {
        "combine the results of many actions" {

            val combined: State<RNG, List<Int>> =
                State.sequence(
                    List.of(
                        State.unit(1),
                        State.unit(2),
                        State.unit(3),
                        State.unit(4)
                    )
                )

            combined.run(rng1).first shouldBe List.of(1, 2, 3, 4)
        }
    }
})
