package chapter3.ex1

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun <A> tail(xs: List<A>): List<A> =
    when (xs) {
        is Nil -> throw IllegalStateException() // Nil
        is Cons -> xs.tail
    }
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise1 : WordSpec({
    "list tail" should {
        "!return the the tail when present" {
            tail(List.of(1, 2, 3, 4, 5)) shouldBe
                    List.of(2, 3, 4, 5)
        }

        "!throw an illegal state exception when no tail is present" {
            shouldThrow<IllegalStateException> {
                tail(Nil)
            }
        }
    }
})
