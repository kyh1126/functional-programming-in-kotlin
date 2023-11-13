package chapter5.ex14

import chapter4.None
import chapter5.Stream
import chapter5.ex13.takeWhile
import chapter5.ex13.zipAll
import chapter5.ex4.forAll
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise14 : WordSpec({

    //tag::startswith[]
    fun <A> Stream<A>.startsWith(that: Stream<A>): Boolean =
        this.zipAll(that)
            .takeWhile { it.second != None }
            .forAll { it.first == it.second }
    //end::startswith[]

    "!Stream.startsWith" should {
        "detect if one stream is a prefix of another" {
            Stream.of(1, 2, 3).startsWith(
                Stream.of(1, 2)
            ) shouldBe true
        }
        "detect if one stream is a prefix of another2" {
            Stream.of(1, 2, 3).startsWith(
                Stream.of(1, 2, 3, 4)
            ) shouldBe false
        }
        "detect if one stream is not a prefix of another" {
            Stream.of(1, 2, 3).startsWith(
                Stream.of(2, 3)
            ) shouldBe false
        }
    }
})
