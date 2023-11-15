package chapter6.ex8

import chapter6.Rand
import chapter6.ex1.nonNegativeInt
import chapter6.rng1
import chapter6.unit
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//tag::init[]
fun <A, B> flatMap(f: Rand<A>, g: (A) -> Rand<B>): Rand<B> = { rng ->
    val (a, newRng) = f(rng)
    g(a)(newRng)
}
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise8 : WordSpec({

    fun nonNegativeIntLessThan(n: Int): Rand<Int> =
        flatMap(::nonNegativeInt) { intValue ->
            val mod = intValue % n
            if (intValue + (n - 1) - mod >= 0) unit(mod) else nonNegativeIntLessThan(n)
        }

    "!flatMap" should {
        "pass along an RNG" {

            val result =
                flatMap(
                    unit(1),
                    { i -> unit(i.toString()) })(rng1)

            result.first shouldBe "1"
            result.second shouldBe rng1
        }
    }

    "!nonNegativeIntLessThan" should {
        "return a non negative int less than n" {

            val result =
                nonNegativeIntLessThan(10)(rng1)

            result.first shouldBe 1
        }
    }
})
