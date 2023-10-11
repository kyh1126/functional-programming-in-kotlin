package chapter3.ex6

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B): B =
    when (xs) {
        is Nil -> z
        is Cons -> f(xs.head, foldRight(xs.tail, z, f))
    }

fun product2(dbs: List<Double>): Double =
    foldRight(dbs, 1.0) { a, b -> a * b }

class Exercise6 : WordSpec({
    "list foldRight sum2" should {
        "!product normally" {
            product2(List.of(1.0, 2.0, 3.0)) shouldBe 6.0
        }

        "!stop immediately" {
            product2(List.of(1.0, 2.0, 0.0, 4.0)) shouldBe 0.0
        }
    }
})
