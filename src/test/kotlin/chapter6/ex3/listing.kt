package chapter6.ex3

// import chapter6.solutions.ex5.doubleR
import chapter6.RNG
import chapter6.ex1.nonNegativeInt
import chapter6.ex2.double
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise3 : WordSpec({

    //tag::init[]
    fun intDouble(rng: RNG): Pair<Pair<Int, Double>, RNG> {
        val (intVal, newRng) = nonNegativeInt(rng)
        val (doubleVal, newRng2) = double(newRng)
        return (intVal to doubleVal) to newRng2
    }

    fun doubleInt(rng: RNG): Pair<Pair<Double, Int>, RNG> {
        val (doubleVal, newRng) = double(rng)
        val (intVal, newRng2) = nonNegativeInt(newRng)
        return (doubleVal to intVal) to newRng2
    }

    fun double3(rng: RNG): Pair<Triple<Double, Double, Double>, RNG> {
        val (doubleVal, newRng) = double(rng)
        val (doubleVal2, newRng2) = double(newRng)
        val (doubleVal3, newRng3) = double(newRng2)
        return Triple(doubleVal, doubleVal2, doubleVal3) to newRng3
    }
    //end::init[]

    "!intDouble" should {

        val doubleBelowOne =
            Int.MAX_VALUE.toDouble() / (Int.MAX_VALUE.toDouble() + 1)

        val unusedRng = object : RNG {
            override fun nextInt(): Pair<Int, RNG> = TODO()
        }

        val rng3 = object : RNG {
            override fun nextInt(): Pair<Int, RNG> =
                Int.MAX_VALUE to unusedRng
        }

        val rng2 = object : RNG {
            override fun nextInt(): Pair<Int, RNG> =
                Int.MAX_VALUE to rng3
        }

        val rng = object : RNG {
            override fun nextInt(): Pair<Int, RNG> =
                Int.MAX_VALUE to rng2
        }

        "generate a pair of int and double" {
            val (id, _) = intDouble(rng)
            val (i, d) = id
            i shouldBe Int.MAX_VALUE
            d shouldBe doubleBelowOne
        }

        "generate a pair of double and int" {
            val (di, _) = doubleInt(rng)
            val (d, i) = di
            d shouldBe doubleBelowOne
            i shouldBe Int.MAX_VALUE
        }

        "generate a triple of double, double, double" {
            val (ddd, _) = double3(rng)
            val (d1, d2, d3) = ddd
            d1 shouldBe doubleBelowOne
            d2 shouldBe doubleBelowOne
            d3 shouldBe doubleBelowOne
        }
    }
})
