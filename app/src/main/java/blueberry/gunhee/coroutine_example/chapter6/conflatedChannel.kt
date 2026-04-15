package blueberry.gunhee.coroutine_example.chapter6

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * ConflatedChannel은 마지막 값을 저장하고 있는 채널입니다
 * emit된것은 버리지만 마지막 값은 저장
 * 마지막 값을 receive하면 사라짐
 */
fun main() = runBlocking {
    val time = measureTimeMillis {
        val channel = Channel<Int>(Channel.CONFLATED)
        launch {
            repeat(5) {
                channel.send(it)
                println("Sent $it")
            }
        }
        delay(500)

        val element = channel.receive()
        println("Received $element")
    }
    println("Took ${time}ms")
}
