package chapter4.ex2

import chapter3.List
import chapter4.*
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import kotlin.math.pow

fun mean(xs: List<Double>): Option<Double> =
    if (xs.isEmpty()) None
    else Some(xs.sum() / xs.size())

//tag::init[]
fun variance(xs: List<Double>): Option<Double> =
    mean(xs).flatMap { m ->
        mean(xs.map { x ->
            (x - m).pow(2)
        })
    }
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise2 : WordSpec({

    "variance" should {
        "!determine the variance of a list of numbers" {
            val ls =
                List.of(1.0, 1.1, 1.0, 3.0, 0.9, 0.4)
            variance(ls).getOrElse { 0.0 } shouldBe
                    (0.675).plusOrMinus(0.005)
        }
    }
})
