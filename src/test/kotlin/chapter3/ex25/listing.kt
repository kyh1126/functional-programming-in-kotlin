package chapter3.ex25

import chapter3.Branch
import chapter3.Leaf
import chapter3.Tree
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

// tag::init[]
fun maximum(tree: Tree<Int>): Int =
    when (tree) {
        is Leaf -> tree.value
        is Branch -> maxOf(maximum(tree.left), maximum(tree.right))
    }
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise25 : WordSpec({
    "tree maximum" should {
        "!determine the maximum value held in a tree" {
            val tree = Branch(
                Branch(Leaf(1), Leaf(9)),
                Branch(Leaf(3), Leaf(4))
            )
            maximum(tree) shouldBe 9
        }
    }
})
