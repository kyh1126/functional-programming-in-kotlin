package chapter2.ex5

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise5 : WordSpec({
    // tag::init[]
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C =
        { a: A -> f(g(a)) }
    // end::init[]

    "compose" should {
        "!apply function composition over two functions" {
            val fahrenheit2celsius: (Double) -> String =
                compose<Double, Double, String>(
                    { b -> "$b degrees celsius" },
                    { a -> (a - 32.0) * (5.0 / 9.0) }
                )

            fahrenheit2celsius(68.0) shouldBe "20.0 degrees celsius"
        }
    }
})