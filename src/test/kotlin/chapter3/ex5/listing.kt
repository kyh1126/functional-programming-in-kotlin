package chapter3.ex5

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun <A> init(l: List<A>): List<A> {
    return when (l) {
        is Nil -> throw IllegalStateException()
        is Cons -> if (l.tail == Nil) return Nil else Cons(l.head, init(l.tail))
    }
}
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise5 : WordSpec({

    "list init" should {
        "!return all but the last element" {
            init(List.of(1, 2, 3, 4, 5)) shouldBe
                    List.of(1, 2, 3, 4)
        }

        "!return Nil if only one element exists" {
            init(List.of(1)) shouldBe Nil
        }

        "!throw an exception if no elements exist" {
            shouldThrow<IllegalStateException> {
                init(List.empty<Int>())
            }
        }
    }
})
