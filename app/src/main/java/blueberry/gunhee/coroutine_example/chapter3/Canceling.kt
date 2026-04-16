package blueberry.gunhee.coroutine_example.chapter3

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * start 2000 delay outer job
 * start Job
 * cancel before job state = StandaloneCoroutine{Active}@184f6be2
 * job cancel
 * cancel after job state = StandaloneCoroutine{Cancelling}@184f6be2
 * cancelled after job state = StandaloneCoroutine{Cancelled}@184f6be2
 *
 * Cancelling 상태는 중단중인 상태인거고
 * Cancelled 상태는 완전히 중단이 종료된 상태 (job이 종료되었을 때)
 *
 */
fun main() = runBlocking {
    val job = launch {
        // Do Something
        println("start Job")
        delay(5000)
        println("end job")
    }
    println("start 2000 delay outer job")
    delay(2000)
    println("cancel before job state = $job")
    println("job cancel")
    job.cancel()
    println("cancel after job state = $job")
    delay(1000)
    println("cancelled after job state = $job")
}
