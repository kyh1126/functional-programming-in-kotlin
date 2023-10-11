package chapter3.ex18

import chapter3.*
import chapter3.List
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun <A> filter(xs: List<A>, f: (A) -> Boolean): List<A> =
    foldLeft(reverse(xs), List.empty(), { y, x -> if (f(x)) Cons(x, y) else filter(y, f) })

fun <A> filter_2(xs: List<A>, f: (A) -> Boolean): List<A> =
    foldRight(xs, List.empty(), { x, y -> if (f(x)) Cons(x, y) else filter(y, f) })
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise18 : WordSpec({
    "list filter" should {
        "!filter out elements not compliant to predicate" {
            val xs = List.of(1, 2, 3, 4, 5)
            filter(xs) { it % 2 == 0 } shouldBe List.of(2, 4)
        }

        "!filter out elements not compliant to predicate" {
            val xs = List.of(1, 2, 3, 4, 5)
            filter_2(xs) { it % 2 == 0 } shouldBe List.of(2, 4)
        }
    }
})
