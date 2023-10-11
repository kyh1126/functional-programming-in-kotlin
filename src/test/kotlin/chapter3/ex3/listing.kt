package chapter3.ex3

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun <A> drop(l: List<A>, n: Int): List<A> {
    if (n == 0) return l

    return when (l) {
        is Nil -> throw IllegalStateException() // Nil
        is Cons -> drop(l.tail, n - 1)
    }
}
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise3 : WordSpec({
    "list drop" should {
        "!drop a given number of elements within capacity" {
            drop(List.of(1, 2, 3, 4, 5), 3) shouldBe
                    List.of(4, 5)
        }

        "!drop a given number of elements up to capacity" {
            drop(List.of(1, 2, 3, 4, 5), 5) shouldBe Nil
        }

        """!throw an illegal state exception when dropped elements
            exceed capacity""" {
            shouldThrow<IllegalStateException> {
                drop(List.of(1, 2, 3, 4, 5), 6)
            }
        }
    }
})
