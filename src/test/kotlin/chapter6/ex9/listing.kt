package chapter6.ex9

import chapter6.Rand
import chapter6.ex8.flatMap
import chapter6.rng1
import chapter6.unit
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise9 : WordSpec({

    //tag::init1[]
    fun <A, B> mapF(ra: Rand<A>, f: (A) -> B): Rand<B> =
        flatMap(ra) { a -> unit(f(a)) }
    //end::init1[]

    "!mapF" should {
        "map over a value using flatMap" {
            mapF(
                unit(1),
                { a -> a.toString() })(rng1).first shouldBe "1"
            mapF(
                unit(1),
                { a -> a.toDouble() })(rng1).first shouldBe 1.0
        }
    }

    //tag::init2[]
    fun <A, B, C> map2F(
        ra: Rand<A>,
        rb: Rand<B>,
        f: (A, B) -> C
    ): Rand<C> =
        flatMap(ra) { a -> flatMap(rb) { b -> unit(f(a, b)) } }
    //end::init2[]

    "!map2F" should {
        "combine the results of two actions" {

            val combined: Rand<String> =
                map2F(
                    unit(1.0),
                    unit(1),
                    { d, i -> ">>> $d double; $i int" })

            combined(rng1).first shouldBe ">>> 1.0 double; 1 int"
        }
    }
})
