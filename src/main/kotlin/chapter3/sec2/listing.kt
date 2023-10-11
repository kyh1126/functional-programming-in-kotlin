package sec2

import kotlin.random.Random

//tag::init1[]
sealed class List<out A> { // List 데이터 구조를 정의함
    companion object { // 함수가 포함된 동반 객체
        fun <A> of(vararg aa: A): List<A> { // 팩토리 도우미 함수
            val tail = aa.sliceArray(1..<aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        //tag::sum[]
        fun sum(ints: List<Int>): Int =
            when (ints) {
                is Nil -> 0
                is Cons -> ints.head + sum(ints.tail)
            }

        //end::sum[]
        //tag::product[]
        fun product(doubles: List<Double>): Double =
            when (doubles) {
                is Nil -> 1.0
                is Cons ->
                    if (doubles.head == 0.0) 0.0
                    else doubles.head * product(doubles.tail)
            }

        //end::product[]
    }
}
//end::init1[]

//fun main() = println(List.of(1, 2))

object Nil : List<Nothing>() {
    override fun toString(): String = "Nil"
}

//tag::init2[]
fun <A> of(vararg aa: A): List<A> {
    val tail = aa.sliceArray(1..<aa.size)
    return if (aa.isEmpty()) Nil else Cons(aa[0], List.of(*tail))
}
//end::init2[]

//tag::init3[]
val ints = List.of(1, 2, 3, 4) // 추상 List 선언

fun sum(xs: List<Int>): Int =
    when (xs) {
        is Nil -> 0 // Nil 구현과 매치
        is Cons -> xs.head + sum(xs.tail) // Cons 구현으로 스마트캐스트
    }

fun main() = println(sum(ints)) // 리스트에 대해 sum 함수 호출
//end::init3[]

val listing35 = {
    //tag::init4[]
    val x = Random.nextInt(-10, 10)
    val y: String =
        if (x == 0) { // x가 0인지 검사
            "x is zero"
        } else if (x < 0) { // x가 음수인지 검사
            "is negative"
        } else { // 다른 경우는 x는 양수일 수밖에 없음
            "x is positive"
        }
    //end::init4[]
}

val listing36 = {
    //tag::init5[]
    val x = Random.nextInt(-10, 10)
    val y: String =
        when { // when에 파라미터를 전달하지 않음
            x == 0 -> // if-else 식을 대신하는 논리 가지들
                "x is zero"

            x < 0 -> // if-else 식을 대신하는 논리 가지들
                "x is negative"

            else -> // 모든 경우를 처리하는 else 가지
                "x is positive"
        }
    //end::init5[]
}

val listing37 = {
    //tag::init6[]
    fun sum(xs: List<Int>): Int =
        when (xs) {
            is Nil -> 0
            is Cons -> xs.head + sum(xs.tail) // Cons를 매칭한 다음에 xs가 스마트캐스트되므로 head와 tail을 볼 수 있음
        }
    //end::init6[]
}

//tag::init7[]
//fun sumSudo(xs: List<Int>): Int =
//    when (xs) {
//        is Nil -> 0 // 첫 번째 패턴 Nil. 뽑아내는 값 없음
//        is Cons(head, tail) -> head + sum(tail) // 두 번째 패턴 Cons(head, tail). head와 tail을 뽑아냄
//    }
data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()
//end::init7[]
