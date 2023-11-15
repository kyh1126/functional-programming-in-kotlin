package chapter6.ex4

// import chapter3.Cons
// import chapter3.Nil
import chapter3.Cons
import chapter3.List
import chapter6.RNG
import chapter6.ex1.nonNegativeInt
import chapter6.rng1
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({

    //tag::init[]
    fun ints(count: Int, rng: RNG): Pair<List<Int>, RNG> {
        if (count == 0) return (List.empty<Int>() to rng)

        val (intVal, newRng) = nonNegativeInt(rng)
        val (intList, newRng2) = ints(count - 1, newRng)

        return Cons(intVal, intList) to newRng2
    }
    //end::init[]

    "!ints" should {
        "generate a list of ints of a specified length" {

            ints(5, rng1) shouldBe (List.of(1, 1, 1, 1, 1) to rng1)
        }
    }
})
