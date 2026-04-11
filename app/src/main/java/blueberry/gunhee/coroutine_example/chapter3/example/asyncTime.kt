package blueberry.gunhee.coroutine_example.chapter3.example

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * async로 2개 이상의 정보를 각각 불러올때
 * 총 시간이 어떻게 계산되는지 확인하기 위해서 작성해본 예제
 *
 * 1) 둘 다 await이 안 걸린 경우에는 각 요청마다 걸리는 시간만큼 더해짐
 * 2) await을 걸어줬을 경우는 즉시 실행되며 await이 끝났는지 대기 & 값을 반환하기 위해서 사용된다.
 */
fun main() = runBlocking() {
    val totalTime = measureTimeMillis {
        val name = async { getName() }
        val number = async { getNumber() }

        delay(1000)
        println("delay 1000")

        val student = Student(name.await(), number.await())
        println(student)
    }
    println("totalTime = $totalTime")
}

suspend private fun getName(): String {
    println("start getName")
    delay(1000)
    return "honeyFist"
}

suspend private fun getNumber(): Int {
    println("start getNumber")
    delay(2000)
    return 30
}
