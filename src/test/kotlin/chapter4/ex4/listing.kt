package chapter4.ex4

import chapter3.Cons
import chapter3.List
import chapter3.foldRight
import chapter4.None
import chapter4.Option
import chapter4.Some
import chapter4.map2
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//tag::init[]
fun <A> sequence(xs: List<Option<A>>): Option<List<A>> =
    foldRight(xs, Some(List.empty())) { optionA: Option<A>, optionB: Option<List<A>> ->
        map2(optionA, optionB) { a: A, b: List<A> -> Cons(a, b) }
    }
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({

    "!sequence" should {
        "turn a list of some options into an option of list" {
            val lo =
                List.of(Some(10), Some(20), Some(30))
            sequence(lo) shouldBe Some(List.of(10, 20, 30))
        }
        "turn a list of options containing none into a none" {
            val lo =
                List.of(Some(10), None, Some(30))
            sequence(lo) shouldBe None
        }
    }
})
