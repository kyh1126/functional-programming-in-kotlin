package chapter3.ex19

import chapter3.*
import chapter3.List
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun <A, B> flatMap(xa: List<A>, f: (A) -> List<B>): List<B> =
    foldLeft(reverse(xa), List.empty(), { y, x -> append(f(x), y) })

fun <A, B> flatMap2(xa: List<A>, f: (A) -> List<B>): List<B> =
    foldRight(xa, List.empty(), { x, y -> append(f(x), y) })
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise19 : WordSpec({
    "list flatmap" should {
        "!map and flatten a list" {
            val xs = List.of(1, 2, 3)
            flatMap(xs) { i -> List.of(i, i) } shouldBe
                    List.of(1, 1, 2, 2, 3, 3)
        }

        "!map and flatten a list" {
            val xs = List.of(1, 2, 3)
            flatMap2(xs) { i -> List.of(i, i) } shouldBe
                    List.of(1, 1, 2, 2, 3, 3)
        }
    }
})
