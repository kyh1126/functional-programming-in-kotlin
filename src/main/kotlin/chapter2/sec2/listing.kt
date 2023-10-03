package chapter2.sec2

//tag::init1[]
fun findFirst(ss: Array<String>, key: String): Int {
    tailrec fun loop(n: Int): Int =
        when {
            n >= ss.size -> -1 // 키를 찾지 못한 채로 루프 끝에 도달하면 -1을 반환
            ss[n] == key -> n // 키를 찾으면 키의 위치를 반환
            else -> loop(n + 1) // 카운터 값을 증가시키면서 재귀적으로 함수를 호출
        }
    return loop(0) // 카운터를 0으로 설정해 루프를 시작
}
//end::init1[]

//tag::init2[]
fun <A> findFirst(xs: Array<A>, p: (A) -> Boolean): Int { // 원소 타입이 A인 배열에 적용함. 배열의 각 원소에 작용하는 술어 함수를 파라미터로 받음
    tailrec fun loop(n: Int): Int =
        when {
            n >= xs.size -> -1
            p(xs[n]) -> n // 술어 함수를 배열 원소에 적용하기
            else -> loop(n + 1)
        }
    return loop(0)
}
//end::init2[]