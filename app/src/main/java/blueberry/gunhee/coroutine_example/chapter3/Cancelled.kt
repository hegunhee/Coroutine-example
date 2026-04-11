package blueberry.gunhee.coroutine_example.chapter3

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * cancel을 시킬 때 이유를 담아서 보낼 수 있다.
 * java.util.concurrent.CancellationException: Tired of waiting
 */
@OptIn(InternalCoroutinesApi::class)
fun main() = runBlocking {
    val job = launch {
        delay(5000)
    }

    delay(2000)

    job.cancel(cause = CancellationException("Tired of waiting"))

    val cancellation = job.getCancellationException()
    println(cancellation.toString())
}
