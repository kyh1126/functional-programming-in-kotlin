package chapter3.ex15

import chapter3.*
import chapter3.List
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun increment(xs: List<Int>): List<Int> =
    foldLeft(reverse(xs), List.empty(), { y, x -> Cons(x + 1, y) })

fun increment2(xs: List<Int>): List<Int> =
    foldRight(xs, List.empty(), { x, y -> Cons(x + 1, y) })
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise15 : WordSpec({
    "list increment" should {
        "!add 1 to every element" {
            increment(List.of(1, 2, 3, 4, 5)) shouldBe
                    List.of(2, 3, 4, 5, 6)
        }

        "!add 1 to every element" {
            increment2(List.of(1, 2, 3, 4, 5)) shouldBe
                    List.of(2, 3, 4, 5, 6)
        }
    }
})
