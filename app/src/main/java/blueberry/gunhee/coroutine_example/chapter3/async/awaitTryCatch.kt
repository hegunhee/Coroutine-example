package blueberry.gunhee.coroutine_example.chapter3.async

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val deferred = async {
        TODO("Not implemented yet!")
    }

    try {
        deferred.await()
    } catch (throwable: Throwable) {
        println("Deferred cancelled due to ${throwable.message}")
    }
}
