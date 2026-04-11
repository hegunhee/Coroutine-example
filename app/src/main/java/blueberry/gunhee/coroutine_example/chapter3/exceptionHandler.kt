package blueberry.gunhee.coroutine_example.chapter3

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler {
        coroutineContext, throwable ->
        println("Job cancelled due to ${throwable.message} [$coroutineContext]")
    }
    launch(exceptionHandler) {
        TODO("Not implemented yet!")
    }

    delay(2000)
}
