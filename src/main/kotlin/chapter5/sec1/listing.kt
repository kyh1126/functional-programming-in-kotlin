package chapter5.sec1

import chapter3.List
import kotlin.system.exitProcess

fun <A, B> List<A>.map(f: (A) -> B): List<B> = TODO()
fun <A> List<A>.filter(f: (A) -> Boolean): List<A> = TODO()

val listing = {
    //tag::init1[]
    List.of(1, 2, 3, 4)
        .map { it + 10 }.filter { it % 2 == 0 }.map { it * 3 }
    List.of(11, 12, 13, 14)
        .filter { it % 2 == 0 }.map { it * 3 }
    List.of(12, 14)
        .map { it * 3 }
    List.of(36, 42)
    //end::init1[]
}

//tag::init2[]
fun square(x: Double): Double = x * x
//end::init2[]

val input = ""

//tag::init3[]
val result = if (input.isEmpty()) exitProcess(-1) else input
//end::init3[]

val a = 10

//tag::init4[]
fun <A> lazyIf(
    cond: Boolean,
    onTrue: () -> A, // A 타입의 지연 값을 표현하는 함수 파라미터 타입은 () -> A
    onFalse: () -> A
): A = if (cond) onTrue() else onFalse()

val y = lazyIf((a < 22),
    { println("a") }, // () -> A를 표현하기 위한 함수 리터럴 구문
    { println("b") }
)
//end::init4[]

//tag::init5[]
fun maybeTwice(b: Boolean, i: () -> Int) =
    if (b) i() + i() else 0
//end::init5[]

//tag::init6[]
fun maybeTwice2(b: Boolean, i: () -> Int) {
    val j: Int by lazy(i)
    if (b) j + j else 0
}
//end::init6[]

fun expensiveOp(): Int = TODO()

//tag::init7[]
val x: Int by lazy { expensiveOp() } // by 키워드를 사용해 lazy가 반환하는 Lazy<Int>를 x에 바인드함

fun useit() =
    if (x > 10) "hi" // x가 조건문에서 평가되면 expensiveOp가 호출되고 그 결과가 캐시됨
    else if (x == 0) "zero" // expensiveOp를 호출하지 않고 캐시된 값을 사용함
    else ("lo")
//end::init7[]
