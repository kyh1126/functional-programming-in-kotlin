package chapter4.ex3

import chapter4.None
import chapter4.Option
import chapter4.Some
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//tag::init[]
fun <A, B, C> map2(optionA: Option<A>, optionB: Option<B>, f: (A, B) -> C): Option<C> =
    optionA.flatMap { a ->
        optionB.map { b ->
            f(a, b)
        }
    }
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise3 : WordSpec({

    "!map2" should {

        val a = Some(5)
        val b = Some(20)
        val none = Option.empty<Int>()

        "combine two option values using a binary function" {
            map2(a, b) { aa, bb ->
                aa * bb
            } shouldBe Some(100)
        }

        "return none if either option is not defined" {
            map2(a, none) { aa, bb ->
                aa * bb
            } shouldBe None

            map2(none, b) { aa, bb ->
                aa * bb
            } shouldBe None
        }
    }
})
