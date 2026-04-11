package blueberry.gunhee.coroutine_example.chapter3

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * start = CoroutineStart.LAZY 로 지정하면 바로 launch가 실행되는것이 아니라
 * 해당 job의 start로 지정해줘야 ACTIVE 상태가 됨 (실행)
 *
 * 만약 이미 실행중(active)이거나 끝이 났다면(completed)되어있다면 launch는 다시 실행되지 않음
 */
fun main() = runBlocking {
    val lazyJob = launch(start = CoroutineStart.LAZY) {
        println("lazyJob Start ${this.toString()}")
    }

    val notLazyJob = launch {
        println("notLazyJob Start ${this.toString()}")
    }
    println("notLazyJobState = ${notLazyJob.toString()}")
    delay(500)
    println("lazyJobState = ${lazyJob.toString()}")

    lazyJob.start()
    println("lazyJobState = ${lazyJob.toString()}")
}
