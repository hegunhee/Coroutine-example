package blueberry.gunhee.coroutine_example.chapter3

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler {
        coroutineContext, throwable ->
        println("Job cancelled due to ${throwable.message} [$coroutineContext]")
    }
    // runBlocking 환경에서는 해당 테스트가 불가능함 (새로운 스코프를 만들어줘야하므로 Job() 추가)
    launch(exceptionHandler + Job()) {
        TODO("Not implemented yet!")
    }

    delay(2000)
}
