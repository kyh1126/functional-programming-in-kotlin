package chapter3.sec1

//tag::init[]
sealed class List<out A> // 데이터 타입에 대한 봉인된 정의

object Nil : List<Nothing>() // List의 Nil(빈 리스트) 구현

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>() // Cons도 List를 구현함
//end::init[]
