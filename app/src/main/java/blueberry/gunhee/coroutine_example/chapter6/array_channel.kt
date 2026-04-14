package blueberry.gunhee.coroutine_example.chapter6

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val channel = Channel<Int>(4)
        val sender = launch {
            repeat(10) {
                channel.send(it)
                println("Sent $it")
            }
        }

        delay(500)
        println("Taking two")
        channel.receive()
        channel.receive()
        delay(500)
    }
    println("Took ${time}ms")
}
