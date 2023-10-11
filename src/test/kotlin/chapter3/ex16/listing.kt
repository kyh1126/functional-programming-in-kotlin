package chapter3.ex16

import chapter3.*
import chapter3.List
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun doubleToString(xs: List<Double>): List<String> =
    foldLeft(reverse(xs), List.empty(), { y, x -> Cons(x.toString(), y) })

fun doubleToString2(xs: List<Double>): List<String> =
    foldRight(xs, List.empty(), { x, y -> Cons(x.toString(), y) })
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise16 : WordSpec({
    "list doubleToString" should {
        "!convert every double element to a string" {
            doubleToString(List.of(1.1, 1.2, 1.3, 1.4)) shouldBe
                    List.of("1.1", "1.2", "1.3", "1.4")
        }

        "!convert every double element to a string" {
            doubleToString2(List.of(1.1, 1.2, 1.3, 1.4)) shouldBe
                    List.of("1.1", "1.2", "1.3", "1.4")
        }
    }
})
