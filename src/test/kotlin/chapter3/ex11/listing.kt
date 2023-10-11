package chapter3.ex11

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B =
    when (xs) {
        is Nil -> z
        is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
    }

fun <A> reverse(xs: List<A>): List<A> =
    foldLeft(xs, List.empty(), { y: List<A>, x: A -> Cons(x, y) })
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise11 : WordSpec({
    "!list reverse" should {
        "reverse list elements" {
            reverse(List.of(1, 2, 3, 4, 5)) shouldBe
                    List.of(5, 4, 3, 2, 1)
        }
    }
})
