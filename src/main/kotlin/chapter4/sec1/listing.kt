package chapter4.sec1

//tag::init1[]
fun failingFn(i: Int): Int {
    val y: Int = throw Exception("boom") // Int 타입의 선언이 예외를 던짐
    return try {
        val x = 42 + 5
        x + y
    } catch (e: Exception) {
        43 // 도달할 수 없는 코드여서 43이 반환되지 않음
    }
}
//end::init1[]

fun main() {
    println(failingFn(1))
}

//tag::init2[]
fun failingFn2(i: Int): Int =
    try {
        val x = 42 + 5
        x + (throw Exception("boom!")) as Int // Exception을 던지는 코드에 임의의 타입을 지정할 수 있음. 여기서는 Int를 지정함
    } catch (e: Exception) {
        43 // 예외가 잡히고 43이 반환됨
    }
//end::init2[]
