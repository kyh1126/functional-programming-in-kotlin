package chapter6.ex6

// import chapter6.RNG
import chapter6.Rand
import chapter6.rng1
import chapter6.unit
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//tag::init[]
fun <A, B, C> map2(
    ra: Rand<A>,
    rb: Rand<B>,
    f: (A, B) -> C
): Rand<C> = { rng ->
    val (a, newRng) = ra(rng)
    val (b, newRng2) = rb(newRng)
    f(a, b) to newRng2
}
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise6 : WordSpec({

    "!map2" should {
        "combine the results of two actions" {

            val combined: Rand<String> =
                map2(
                    unit(1.0),
                    unit(1), { d, i ->
                        ">>> $d double; $i int"
                    })

            combined(rng1).first shouldBe ">>> 1.0 double; 1 int"
        }
    }
})
