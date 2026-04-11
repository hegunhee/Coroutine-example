package blueberry.gunhee.coroutine_example.chapter3.example

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * async 또한 start를 지정할 수 있다 (LAZY로 지정하면 start로 실행시켜줘야 함)
start getName
start getNumber
delay 3000
name DeferredState = DeferredCoroutine{Completed}@1563da5, name is Cancelled : false name is Active : false, name is Cancelled false
number DeferredState = DeferredCoroutine{Active}@2bbf4b8b, number is Cancelled : false number is Active : true, number is Cancelled false
await after number DeferredState = DeferredCoroutine{Completed}@2bbf4b8b, number is Cancelled : false number is Active : false, number is Cancelled false
Student(name=honeyFist, number=30)
totalTime = 4024
 * 혹시 몰라서 await 이후에 다시 찍어봤으나
 * number DeferredState는 종료된것으로 찍힘
 * 즉 await은 값을 반환하고 아직 완료되지 않은것이 있다면 기다림
 *
 * async is useful when you want to perform concurrent computation
 * and only later wait for the result
 * 지금 바로 기다릴거면 async가 필요없다는 말이 있다.
 */
fun main() = runBlocking {
    val totalTime = measureTimeMillis {
        val name = async { getName() }
        val number = async { getNumber() }

        delay(3000)
        println("delay 3000")

        println("name DeferredState = $name, name is Cancelled : ${name.isCancelled} name is Active : ${name.isActive}, name is Cancelled ${name.isCancelled}")
        println("number DeferredState = $number, number is Cancelled : ${number.isCancelled} number is Active : ${number.isActive}, number is Cancelled ${number.isCancelled}")

        val student = Student(name.await(), number.await())
        println("await after number DeferredState = $number, number is Cancelled : ${number.isCancelled} number is Active : ${number.isActive}, number is Cancelled ${number.isCancelled}")
        println(student)
    }
    println("totalTime = $totalTime")
}

private suspend fun getName(): String {
    println("start getName")
    delay(1000)
    return "honeyFist"
}

private suspend fun getNumber(): Int {
    println("start getNumber")
    delay(4000)
    return 30
}
