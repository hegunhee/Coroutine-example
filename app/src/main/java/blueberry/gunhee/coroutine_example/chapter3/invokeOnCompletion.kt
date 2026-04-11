package blueberry.gunhee.coroutine_example.chapter3

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        TODO("Not implemented yet!")
    }.invokeOnCompletion { cause ->
        cause?.let {
            println("Job cancelled due to ${it.message}")
        }
    }

    delay(2000)
}
