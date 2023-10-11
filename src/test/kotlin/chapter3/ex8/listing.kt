package chapter3.ex8

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B): B =
    when (xs) {
        is Nil -> z
        is Cons -> f(xs.head, foldRight(xs.tail, z, f))
    }

fun <A> length(xs: List<A>): Int =
    foldRight(xs, 0, { _, y -> 1 + y })
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise8 : WordSpec({
    "list length" should {
        "!calculate the length" {
            length(List.of(1, 2, 3, 4, 5)) shouldBe 5
        }

        "!calculate zero for an empty list" {
            length(Nil) shouldBe 0
        }
    }
})
