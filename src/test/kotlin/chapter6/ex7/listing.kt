package chapter6.ex7

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.foldRight
import chapter6.*
import chapter6.ex1.nonNegativeInt
import chapter6.ex6.map2
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise7 : WordSpec({

    //tag::init[]
    fun <A> sequence(fs: List<Rand<A>>): Rand<List<A>> =
        when (fs) {
            is Nil -> unit(Nil)
            is Cons -> map2(fs.head, sequence(fs.tail)) { a, b -> Cons(a, b) }
        }
    //end::init[]

    //tag::init2[]
    fun <A> sequence2(fs: List<Rand<A>>): Rand<List<A>> =
        foldRight(fs, unit(List.empty())) { a, listA -> map2(a, listA) { x, y -> Cons(x, y) } }
    //end::init2[]

    fun ints2(count: Int, rng: RNG): Pair<List<Int>, RNG> {
        val nonNegativeIntFunc: Rand<Int> = map(::nonNegativeInt) { it }

        fun ints2Func(count: Int): List<Rand<Int>> {
            if (count == 0) return List.empty()
            return Cons(nonNegativeIntFunc, ints2Func(count - 1))
        }

        return sequence(ints2Func(count))(rng)
    }

    "!sequence" should {

        "combine the results of many actions using recursion" {

            val combined: Rand<List<Int>> =
                sequence(
                    List.of(
                        unit(1),
                        unit(2),
                        unit(3),
                        unit(4)
                    )
                )

            combined(rng1).first shouldBe
                    List.of(1, 2, 3, 4)
        }

        """combine the results of many actions using
            foldRight and map2""" {

            val combined2: Rand<List<Int>> =
                sequence2(
                    List.of(
                        unit(1),
                        unit(2),
                        unit(3),
                        unit(4)
                    )
                )

            combined2(rng1).first shouldBe
                    List.of(1, 2, 3, 4)
        }
    }

    "!ints" should {
        "generate a list of ints of a specified length" {
            ints2(4, rng1).first shouldBe
                    List.of(1, 1, 1, 1)
        }
    }
})
