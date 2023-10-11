package chapter3.ex2

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import utils.SOLUTION_HERE

// tag::init[]
fun <A> setHead(xs: List<A>, x: A): List<A> =
    when (xs) {
        is Nil -> throw IllegalStateException() // Nil
        is Cons -> Cons(x, xs.tail)
    }
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise2 : WordSpec({
    "list setHead" should {
        "!return a new List with a replaced head" {
            setHead(List.of(1, 2, 3, 4, 5), 6) shouldBe
                    List.of(6, 2, 3, 4, 5)
        }

        "!throw an illegal state exception when no head is present" {
            shouldThrow<IllegalStateException> {
                setHead(Nil, 6)
            }
        }
    }
})
