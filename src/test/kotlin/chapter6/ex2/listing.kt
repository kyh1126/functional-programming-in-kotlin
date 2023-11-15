package chapter6.ex2

import chapter6.RNG
import chapter6.ex1.nonNegativeInt
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//tag::init[]
fun double(rng: RNG): Pair<Double, RNG> {
    val (value, newRng) = nonNegativeInt(rng)
    return value / (Int.MAX_VALUE.toDouble() + 1) to newRng
}
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise2 : WordSpec({

    "!double" should {

        val unusedRng = object : RNG {
            override fun nextInt(): Pair<Int, RNG> = TODO()
        }

        "generate a max value approaching 1 based on Int.MAX_VALUE" {

            val rngMax = object : RNG {
                override fun nextInt(): Pair<Int, RNG> =
                    Int.MAX_VALUE to unusedRng
            }

            double(rngMax) shouldBe (0.9999999995343387 to unusedRng)
        }

        "generate a min value of 0 based on 0" {
            val rngMin = object : RNG {
                override fun nextInt(): Pair<Int, RNG> =
                    0 to unusedRng
            }

            double(rngMin) shouldBe (0.0 to unusedRng)
        }
    }
})
