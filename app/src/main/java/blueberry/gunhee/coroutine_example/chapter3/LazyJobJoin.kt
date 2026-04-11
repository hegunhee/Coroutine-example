package blueberry.gunhee.coroutine_example.chapter3

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Lazy의 경우 역시 join이 시작되어야 launch가 실행됨
 * start와 join의 차이점은 start는 New 상태의 Job을 실행시키지만
 * join은 해당 launch구문이 끝날때까지 기다림
 */
fun main() = runBlocking {
    val lazyJob = launch(start = CoroutineStart.LAZY) {
        println("lazyJob Start")
        delay(3000)
        println("lazyJob end")
    }

    println("lazyJob Join")
    lazyJob.join()
}
