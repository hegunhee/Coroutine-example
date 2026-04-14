package blueberry.gunhee.coroutine_example.chapter6

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * UNLIMITED는 Int.MAX_SIZE와 같다
 */
fun main() = runBlocking {
    val time = measureTimeMillis {
        val channel = Channel<Int>(Channel.UNLIMITED)
        val sender = launch {
            repeat(5) {
                println("Sending $it")
                channel.send(it)
            }
        }
        delay(500)
    }
    println("Took ${time}ms")
}
