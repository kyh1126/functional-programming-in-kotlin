package chapter2.sec1

object Chapter2Example1 {

    private fun abs(n: Int) =
        if (n < 0) -n
        else n

    fun formatAbs(x: Int): String {
        val msg = "The absolute value of %d is %d"
        return msg.format(x, abs(x))
    }
}

//fun main() {
//    println(Chapter2Example1.formatAbs(-42))
//}

val listing1 = {
    //tag::init1[]
    fun factorial(i: Int): Int {
        fun go(n: Int, acc: Int): Int = // 내부 또는 지역 함수 정의
            if (n <= 0) acc
            else go(n - 1, n * acc)
        return go(i, 1) // 정의한 지역 함수를 호출함
    }
    //end::init1[]
}

val listing2 = {
    //tag::init2[]
    fun factorial(i: Int): Int {
        tailrec fun go(n: Int, acc: Int): Int = // tailrec 변경자는 꼬리 호출을 제거하라고 컴파일러에 명령한다.
            if (n <= 0) acc
            else go(n - 1, n * acc) // 이 함수의 마지막 재귀 호출이 꼬리 위치에 있다.
        return go(i, 1)
    }
    //end::init2[]
}

//tag::init3[]
object Chapter2Example2 {

    private fun abs(n: Int): Int =
        if (n < 0) -n
        else n

    private fun factorial(i: Int): Int { // 계승 함수를 추가하면서 private으로 마크함
        fun go(n: Int, acc: Int): Int =
            if (n <= 0) acc
            else go(n - 1, n * acc)
        return go(i, 1)
    }

    fun formatAbs(x: Int): String {
        val msg = "The absolute value of %d is %d"
        return msg.format(x, abs(x))
    }

    fun formatFactorial(x: Int): String { // formatFactorial 함수를 추가한다. 디폴트는 public임
        val msg = "The factorial of %d is %d"
        return msg.format(x, factorial(x))
    }
}

fun main() {
    println(Chapter2Example2.formatAbs(-42))
    println(Chapter2Example2.formatFactorial(7)) // main 메서드에서 formatFactorial을 호출
}
//end::init3[]

val listing4 = {
    fun factorial(i: Int): Int = TODO()

    fun abs(n: Int): Int = TODO()

    //tag::init4[]
    fun formatResult(name: String, n: Int, f: (Int) -> Int): String {
        val msg = "The %s of %d is %d."
        return msg.format(name, n, f(n))
    }
    //end::init4[]

    //tag::init5[]
    fun main() {
        println(formatResult("factorial", 7, ::factorial))
        println(formatResult("absolute value", -42, ::abs))
    }
    //end::init5[]
}
