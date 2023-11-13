package chapter5.ex4

import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import utils.SOLUTION_HERE

//tag::init[]
fun <A> Stream<A>.forAll(p: (A) -> Boolean): Boolean =
    when(this) {
        is Cons -> p(this.head()) && this.tail().forAll(p)
        is Empty -> true
    }
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({

    "!Stream.forAll" should {
        "ensure that all elements match the predicate" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.forAll { it < 6 } shouldBe true
        }
        "stop evaluating if one element does not satisfy the predicate" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.forAll { it != 3 } shouldBe false
        }
    }
})
