package chapter3.ex13

import chapter3.*
import chapter3.List
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun <A> append(a1: List<A>, a2: List<A>): List<A> {
    val rightF = { x: A, y: List<A> -> Cons(x, y) }
    return foldRight(a1, a2, rightF)
}

fun <A> appendL(a1: List<A>, a2: List<A>): List<A> {
    val leftF = { y: List<A>, x: A -> Cons(x, y) }
    return foldLeft(reverse(a1), a2, leftF)
}
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise13 : WordSpec({
    "!list append" should {
        "append two lists to each other using foldRight" {
            append(
                List.of(1, 2, 3),
                List.of(4, 5, 6)
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }

    "!list appendL" should {
        "append two lists to each other using foldLeft" {
            appendL(
                List.of(1, 2, 3),
                List.of(4, 5, 6)
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }
})
