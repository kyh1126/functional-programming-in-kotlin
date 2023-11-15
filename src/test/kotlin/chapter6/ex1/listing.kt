package chapter6.ex1

import chapter6.RNG
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//tag::init[]
fun nonNegativeInt(rng: RNG): Pair<Int, RNG> {
    val (value, newRng) = rng.nextInt()
    if (value == Int.MIN_VALUE) return Int.MAX_VALUE to newRng
    return (if (value < 0) -(value + 1) else value) to newRng
}
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise1 : WordSpec({

    "!nonNegativeInt" should {

        val unusedRng = object : RNG {
            override fun nextInt(): Pair<Int, RNG> = TODO()
        }

        "return 0 if nextInt() yields 0" {

            val rng0 = object : RNG {
                override fun nextInt(): Pair<Int, RNG> =
                    0 to unusedRng
            }

            nonNegativeInt(rng0) shouldBe (0 to unusedRng)
        }

        "return Int.MAX_VALUE when nextInt() yields Int.MAX_VALUE" {

            val rngMax = object : RNG {
                override fun nextInt(): Pair<Int, RNG> =
                    Int.MAX_VALUE to unusedRng
            }

            nonNegativeInt(rngMax) shouldBe (Int.MAX_VALUE to unusedRng)
        }

        "return Int.MAX_VALUE when nextInt() yields Int.MIN_VALUE" {

            val rngMin = object : RNG {
                override fun nextInt(): Pair<Int, RNG> =
                    Int.MIN_VALUE to unusedRng
            }

            nonNegativeInt(rngMin) shouldBe (Int.MAX_VALUE to unusedRng)
        }

        "return 0 when nextInt() yields -1" {

            val rngNeg = object : RNG {
                override fun nextInt(): Pair<Int, RNG> =
                    -1 to unusedRng
            }

            nonNegativeInt(rngNeg) shouldBe (0 to unusedRng)
        }
    }
})
