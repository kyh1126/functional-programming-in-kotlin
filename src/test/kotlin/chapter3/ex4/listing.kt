package chapter3.ex4

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> {
    return when (l) {
        is Nil -> Nil
        is Cons -> if (f(l.head)) dropWhile(l.tail, f) else l
    }
}
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({

    "list dropWhile" should {
        "!drop elements until predicate is no longer satisfied" {
            dropWhile(
                List.of(1, 2, 3, 4, 5)
            ) { it < 4 } shouldBe List.of(4, 5)
        }

        "!drop no elements if predicate never satisfied" {
            dropWhile(
                List.of(1, 2, 3, 4, 5)
            ) { it == 100 } shouldBe List.of(1, 2, 3, 4, 5)
        }

        "!drop all elements if predicate always satisfied" {
            dropWhile(
                List.of(1, 2, 3, 4, 5)
            ) { it < 100 } shouldBe List.of()
        }

        "!return Nil if input is empty" {
            dropWhile(List.empty<Int>()) { it < 100 } shouldBe Nil
        }
    }
})
