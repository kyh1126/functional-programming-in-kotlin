package chapter3.ex17

import chapter3.*
import chapter3.List
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> =
    foldLeft(reverse(xs), List.empty(), { y, x -> Cons(f(x), y) })

fun <A, B> map2(xs: List<A>, f: (A) -> B): List<B> =
    foldRight(xs, List.empty(), { x, y -> Cons(f(x), y) })
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise17 : WordSpec({
    "list map" should {
        "!apply a function to every list element" {
            map(List.of(1, 2, 3, 4, 5)) { it * 10 } shouldBe
                    List.of(10, 20, 30, 40, 50)
        }

        "!apply a function to every list element" {
            map2(List.of(1, 2, 3, 4, 5)) { it * 10 } shouldBe
                    List.of(10, 20, 30, 40, 50)
        }
    }
})
