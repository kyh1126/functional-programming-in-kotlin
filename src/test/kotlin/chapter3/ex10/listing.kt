package chapter3.ex10

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

fun sumL(xs: List<Int>): Int =
    foldLeft(xs, 0, { b, a -> b + a })

fun productL(xs: List<Double>): Double =
    foldLeft(xs, 1.0, { b, a -> b * a })

fun <A> lengthL(xs: List<A>): Int =
    foldLeft(xs, 0, { b, _ -> 1 + b })
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise10 : WordSpec({
    "!list sumL" should {
        "add all integers" {
            sumL(List.of(1, 2, 3, 4, 5)) shouldBe 15
        }
    }

    "!list productL" should {
        "multiply all doubles" {
            productL(List.of(1.0, 2.0, 3.0, 4.0, 5.0)) shouldBe
                    120.0
        }
    }

    "!list lengthL" should {
        "count the list elements" {
//            lengthL(List.of(1, 2, 3, 4, 5)) shouldBe 5
        }
    }
})
