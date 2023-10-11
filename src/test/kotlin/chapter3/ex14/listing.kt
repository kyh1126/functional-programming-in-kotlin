package chapter3.ex14

import chapter3.List
import chapter3.ex13.append
import chapter3.ex13.appendL
import chapter3.foldLeft
import chapter3.foldRight
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun <A> concat(lla: List<List<A>>): List<A> =
    foldRight(lla, List.empty(), { x, y -> append(x, y) })

fun <A> concat2(lla: List<List<A>>): List<A> =
    foldLeft(lla, List.empty(), { y, x -> appendL(y, x) })
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise14 : WordSpec({
    "list concat" should {
        "!concatenate a list of lists into a single list" {
            concat(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)

            concat2(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }
})
