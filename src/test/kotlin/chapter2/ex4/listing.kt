package chapter2.ex4

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({
    // tag::init[]
    fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C =
        { a: A, b: B -> f(a)(b) }
    // end::init[]

    "uncurry" should {
        """!take a function accepting two values and then apply that
            function to the components of the pair which is the
            second argument""" {

            val f: (Int, Int) -> String =
                uncurry<Int, Int, String> { a -> { b -> "$a:$b" } }
            f(1, 2) shouldBe "1:2"
            f(1, 3) shouldBe "1:3"
        }
    }
})