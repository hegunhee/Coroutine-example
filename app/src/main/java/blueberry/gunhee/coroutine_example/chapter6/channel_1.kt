package blueberry.gunhee.coroutine_example.chapter6

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val channel = Channel<Int>()
        val sender = launch {
            repeat(10) {
                channel.send(it)
                println("Sent $it")
            }
        }

        channel.receive()
        channel.receive()
    }
    println("Took ${time}ms")
}
